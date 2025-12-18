package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestYatraStep {


    @Given("Soniya login in yatra website Sample")
    public void loginyatraTestSample() {
        // selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("login yatra successfully");
    }

    @When("she buys a ticket Sample")
    public void booksTicketTestSample() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("buy a ticket");
        // Add your purchase code here
    }

    @When("she receives a ticket confirmation Sample")
    public void ticketConfirmationTestSample() {
        //selectedRandomProduct = product_context.getRandomProduct();
        System.out.println("ticket confirmation");
        // Add your purchase code here
    }


}
