package stepdefinitions;


import applicaionKeyword.ProductContext;
import applicaionKeyword.ProductRead;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class YatraTicketBookingTest {

    private final ProductContext product_context;
    public String selectedRandomProduct;

    public YatraTicketBookingTest(ProductContext product_context) {
        this.product_context = product_context;

        String sheetName = "NATIONAL";
        //  String randomProduct = ProductRead.productZone(sheetName);
        String randomProduct = ProductRead.getRandomDataFromExcel(sheetName);

        product_context.setRandomProduct(randomProduct);
        this.selectedRandomProduct = randomProduct;
        System.out.println(" Selected Random Product for '"+sheetName+"' --> '"+selectedRandomProduct+"' ");
    }

    @Given("Soniya login in yatra website Test")
    public void loginyatraTest() {
        // selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("login: " + selectedRandomProduct);
    }

    @When("she buys a ticket Test")
    public void booksTicketTest() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("book ticket: " + selectedRandomProduct);
        // Add your purchase code here
    }

    @When("she receives a ticket confirmation Test")
    public void ticketConfirmationTest() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("ticket confirmation: " + selectedRandomProduct);
        // Add your purchase code here
    }

    @Then("she backs to yatra home page Test")
    public void backsYatra() {
        System.out.println("Backs to Yatra Home Page: " + selectedRandomProduct);
        // Add your verification code here
    }

}
