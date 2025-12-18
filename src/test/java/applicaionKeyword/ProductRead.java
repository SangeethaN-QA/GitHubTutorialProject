package applicaionKeyword;

public class ProductRead {

    /*****
     * NOTE : JAVA 17 SWITCH CASE METHOD
     *
     * **/
    public static String randomProduct;
     public static String productZone(String sheetName) {
        switch (sheetName.toUpperCase()) {
            case "NATIONAL", "EU", "WELT" -> {
                String fileName = "ParcelProductData.xlsx";  // Excel file name
                //String sheetName = parcelZone;                 // SheetName to read
                String targetColumnName = "parcelProduct";     // HeaderColumn to extract
                randomProduct = ProductHelper.getExcelRandomProduct(fileName, sheetName, targetColumnName);
               // product_context.setRandomProduct(randomProduct);

                System.out.println("<<Before Scenario>> Selected Random Product :: '" + randomProduct + "' ");


            }
        }
        return randomProduct;
    }

    public static String getRandomDataFromExcel(String sheetName) {
        switch (sheetName.toUpperCase()) {
            case "NATIONAL", "EU", "WELT" -> {
                String fileName = "ParcelProductDataDifferentColumn.xlsx";  // Excel file name
                //String sheetName = parcelZone;                 // SheetName to read
                String targetColumnName = "parcelProduct";     // HeaderColumn to extract
                randomProduct = ProductHelperForColumnFilter.getRandomValue(fileName, sheetName, targetColumnName);
                // product_context.setRandomProduct(randomProduct);

                System.out.println("<<Before Scenario>> Selected Random Product :: '" + randomProduct + "' ");


            }
        }
        return randomProduct;
    }


}

