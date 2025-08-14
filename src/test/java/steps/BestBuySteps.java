package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class BestBuySteps {
    WebDriver driver;

    @Given("I am on the BestBuy home page")
    public void i_am_on_the_best_buy_home_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am on the BestBuy home page");
        System.out.println("test rob branch again");

    }

    @When("When I close the add modal and search for “macbook pro”")
    public void when_i_close_the_add_modal_and_search_for_macbook_pro() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("When I close the add modal and search for “macbook pro”");
    }

    @Then("one of the laptops listed should be {double} 8GB Memory and 256GB SSD")
    public void one_of_the_laptops_listed_should_be_8gb_memory_and_256gb_ssd(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("one of the laptops listed should be {double}” 8GB Memory and 256GB SSD");
    }
}
