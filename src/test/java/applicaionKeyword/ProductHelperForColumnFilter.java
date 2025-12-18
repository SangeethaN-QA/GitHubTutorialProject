package applicaionKeyword;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductHelperForColumnFilter {

    public static List<String> getColumnValues(String fileName, String sheetName, String targetColumnName) {
        List<String> columnValues = new ArrayList<>();

        try {
            //Search for the excel-file anywhere in thiss project
            Path projectRoot = Paths.get("").toAbsolutePath();
            Path filePath = Files.walk(projectRoot)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equalsIgnoreCase(fileName))
                    .findFirst()
                    .orElse(null);

            if (filePath != null) {
                System.out.println("File found at: " + filePath);
            } else {
                System.out.println(" Excel File not found in thiss project");
                return columnValues;
            }

            // Open the Excel file using the file path
            try (InputStream inputStream = Files.newInputStream(filePath);
                 Workbook workbook = createWorkbook(inputStream, filePath.toString())) {

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    System.out.println("Sheet '" + sheetName + "' not found");
                    return null;
                }

                DataFormatter formatter = new DataFormatter();

                // Find the target column index
                // Row headerRow = sheet.getRow(0);
                int headerRowIndex = -1;
                int targetColumnIndex = -1;

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == CellType.STRING &&
                                cell.getStringCellValue().trim().equalsIgnoreCase(targetColumnName)) {
                            headerRowIndex = row.getRowNum();
                            targetColumnIndex = cell.getColumnIndex();
                            break;
                        }
                    }
                    if (targetColumnIndex != -1) break;

                }
                if (targetColumnIndex == -1) {
                    System.out.println("Column '" + targetColumnName + "' not found");
                    return columnValues;
                }

                System.out.println("Found Column '" + targetColumnName + "' at index :: " + targetColumnIndex);

                // Step 4: Read values under that given column name -> Example "parcelProduct"
                for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    Cell cell = row.getCell(targetColumnIndex);
                    String value = formatter.formatCellValue(cell).trim();
                    if (!value.isEmpty()) {
                        columnValues.add(value);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return columnValues;
    }
    // Check file format whether XLS or XLSX
    private static Workbook createWorkbook(InputStream inputStream, String filePath) throws IOException {
        if (filePath.toLowerCase().endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (filePath.toLowerCase().endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        } else {
            throw new IOException("Unsupported Excel file type: " + filePath);
        }
    }

    public static String getRandomValue(String fileName,String sheetName,String targetColumnName)
    {
        String randomValue = null;
        List<String> columnValues = getColumnValues(fileName, sheetName, targetColumnName);
        if (!columnValues.isEmpty()) {
            int randomIndex = ThreadLocalRandom.current().nextInt(columnValues.size());
            randomValue = columnValues.get(randomIndex);
            System.out.println(" Randomly picked value Parcel Product: " + randomValue);
        } else {
            System.out.println(" No data found under the column ## '" +targetColumnName+ "'");
        }

        return randomValue;
    }


}
