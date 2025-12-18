package stepdefinitions;


import applicaionKeyword.ProductContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class YatraTicketBookingStep {

    private final ProductContext product_context;
    public String selectedRandomProduct;

    public YatraTicketBookingStep(ProductContext product_context)
    {
        this.product_context = product_context;

        // Directly store the random product into a global variable
        //  works because PicoContainer injects the same product_context instance per scenario
        this.selectedRandomProduct = product_context.getRandomProduct();
    }


    @Given("Soniya login in yatra website")
    public void loginyatra() {
       // selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("login: " + selectedRandomProduct);
    }

    @When("she buys a ticket")
    public void booksTicket() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("book ticket: " + selectedRandomProduct);
        // Add your purchase code here
    }

    @When("she receives a ticket confirmation")
    public void ticketConfirmation() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("ticket confirmation: " + selectedRandomProduct);
        // Add your purchase code here
    }

    @Then("she receives a parcelTest label")
    public void she_receives_a_parcel_test() {
        System.out.println("Received label for: " + selectedRandomProduct);
        // Add your verification code here
    }
}
