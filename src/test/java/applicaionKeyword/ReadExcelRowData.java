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

public class ReadExcelRowData {

    public static void main(String[] args) {
        String fileName = "Parcel Product Data.xlsx";  // Excel file name
        String sheetName = "National";                 // Sheet to read
        String targetColumnName = "parcelProduct";     // Column to extract

        try {
            // Step 1: Search for the file anywhere in your project
            Path projectRoot = Paths.get("").toAbsolutePath();
            Path filePath = Files.walk(projectRoot)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equalsIgnoreCase(fileName))
                    .findFirst()
                    .orElse(null);

            if (filePath == null) {
                System.out.println(" File not found in project!");
                return;
            }

            System.out.println("File found at: " + filePath);

            // Step 2: Open the Excel file using the file path
            try (InputStream inputStream = Files.newInputStream(filePath);
                 Workbook workbook = createWorkbook(inputStream, filePath.toString())) {

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    System.out.println("Sheet '" + sheetName + "' not found!");
                    return;
                }

                // Step 3: Find the target column index
                Row headerRow = sheet.getRow(0);
                int targetColumnIndex = -1;
                for (Cell cell : headerRow) {
                    if (cell.getCellType() == CellType.STRING &&
                            cell.getStringCellValue().trim().equalsIgnoreCase(targetColumnName)) {
                        targetColumnIndex = cell.getColumnIndex();
                        break;
                    }
                }

                if (targetColumnIndex == -1) {
                    System.out.println("Column '" + targetColumnName + "' not found!");
                    return;
                }

                // Step 4: Read values under that given column name Example "parcelProduct"
                List<String> columnValues = new ArrayList<>();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    Cell cell = row.getCell(targetColumnIndex);
                    columnValues.add(cell != null ? cell.toString() : "");
                }

                // Print row data results
                System.out.println(" Values under column '" + targetColumnName + "':");
                columnValues.forEach(System.out::println);

                // Step 5: Pick one random value from the list
                if (!columnValues.isEmpty()) {
                    int randomIndex = ThreadLocalRandom.current().nextInt(columnValues.size());
                    String randomValue = columnValues.get(randomIndex);
                    System.out.println(" Randomly picked value: " + randomValue);
                } else {
                    System.out.println(" No data found under the column!");
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Auto-detect whether file is XLS or XLSX
    private static Workbook createWorkbook(InputStream inputStream, String filePath) throws IOException {
        if (filePath.toLowerCase().endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (filePath.toLowerCase().endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        } else {
            throw new IOException("Unsupported Excel file type: " + filePath);
        }
    }

}
