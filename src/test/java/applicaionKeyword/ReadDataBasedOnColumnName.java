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

    public class ReadDataBasedOnColumnName {

        public static void main(String[] args) {

            String fileName = "Parcel Product Data.xlsx";
            String sheetName = "National";

            String randomFruit = getRandomValueFromColumn(fileName, sheetName, "parcelProduct");
            System.out.println("Random fruit: " + randomFruit);
        }
        public static List<String> getColumnValues(String fileName, String sheetName, String targetColumnName) {
            List<String> columnValues = new ArrayList<>();

            try {
                // Locate Excel file anywhere in the project
                Path projectRoot = Paths.get("").toAbsolutePath();
                Path filePath = Files.walk(projectRoot)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().equalsIgnoreCase(fileName))
                        .findFirst()
                        .orElse(null);

                if (filePath == null) {
                    System.out.println("Excel file not found: " + fileName);
                    return columnValues;
                }

                System.out.println("File found at: " + filePath);

                try (InputStream inputStream = Files.newInputStream(filePath);
                     Workbook workbook = createWorkbook(inputStream, filePath.toString())) {

                    Sheet sheet = workbook.getSheet(sheetName);
                    if (sheet == null) {
                        System.out.println(" Sheet '" + sheetName + "' not found!");
                        return columnValues;
                    }

                    DataFormatter formatter = new DataFormatter();

                    // 🔍 Find the header row (first row that contains the target column name)
                    int headerRowIndex = -1;
                    int targetColumnIndex = -1;

                    for (Row row : sheet) {
                        for (Cell cell : row) {
                            String headerValue = formatter.formatCellValue(cell).trim();
                            if (headerValue.equalsIgnoreCase(targetColumnName)) {
                                headerRowIndex = row.getRowNum();
                                targetColumnIndex = cell.getColumnIndex();
                                break;
                            }
                        }
                        if (targetColumnIndex != -1) break; // header found
                    }

                    if (targetColumnIndex == -1) {
                        System.out.println("Column '" + targetColumnName + "' not found in sheet '" + sheetName + "'");
                        return columnValues;
                    }

                    System.out.println("Found column '" + targetColumnName + "' at index: " + targetColumnIndex);

                    // 📖 Read all values under that column
                    for (int i = headerRowIndex + 1; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        if (row == null) continue;
                        Cell cell = row.getCell(targetColumnIndex);
                        String value = formatter.formatCellValue(cell).trim();
                        if (!value.isEmpty()) columnValues.add(value);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return columnValues;
        }

        // 🔧 Detect file type automatically (XLS or XLSX)
        private static Workbook createWorkbook(InputStream inputStream, String filePath) throws IOException {
            if (filePath.toLowerCase().endsWith(".xlsx")) {
                return new XSSFWorkbook(inputStream);
            } else if (filePath.toLowerCase().endsWith(".xls")) {
                return new HSSFWorkbook(inputStream);
            } else {
                throw new IOException("Unsupported Excel file type: " + filePath);
            }
        }

        // 🎲 (Optional) Pick one random value from the column
        public static String getRandomValueFromColumn(String fileName, String sheetName, String targetColumnName) {
            List<String> values = getColumnValues(fileName, sheetName, targetColumnName);
            if (values.isEmpty()) {
                System.out.println(" No data found under column " + targetColumnName);
                return null;
            }
            int randomIndex = (int) (Math.random() * values.size());
            String randomValue = values.get(randomIndex);
            System.out.println("Randomly picked value from '" + targetColumnName + "': " + randomValue);
            return randomValue;
        }
    }


