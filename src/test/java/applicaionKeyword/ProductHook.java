package applicaionKeyword;

import io.cucumber.java.Before;

public class ProductHook {

    private final ProductContext product_context;

    public ProductHook(ProductContext product_context)
    {
        this.product_context = product_context;
    }

  //  @Before
    public void beforeScenario()
    {
        String fileName = "Parcel Product Data.xlsx";  // Excel file name
        String sheetName = "National";                 // SheetName to read
        String targetColumnName = "Fruits";     // HeaderColumn to extract
        String randomProduct = ProductHelper.getExcelRandomProduct(fileName,sheetName,targetColumnName);
        product_context.setRandomProduct(randomProduct);

        System.out.println("<<Before Scenario>> Selected Random Fruits :: '"+randomProduct+"' ");

    }

}
