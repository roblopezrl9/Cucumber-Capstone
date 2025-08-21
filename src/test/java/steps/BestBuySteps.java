package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.fail;


public class BestBuySteps {
    WebDriver driver = WebDriverManager.getDriver();
  
    @Given("I am on the BestBuy home page")
    public void i_am_on_the_best_buy_home_page() {

        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.bestbuy.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement usaFlag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("us-link")));
            // If the page contains flags, click on the USA flag
            // If the page does not contain flags, this step will be skipped
            if(usaFlag.isDisplayed()){
                usaFlag.click();
                System.out.println("USA flag clicked successfully.");
            }
        } catch (TimeoutException e) {
            System.out.println("USA flag not found, continuing without clicking.");
        }


    }

    @When("When I close the add modal and search for “macbook pro”")
    public void when_i_close_the_add_modal_and_search_for_macbook_pro() {
        // Write code here that turns the phrase above into concrete actions

        System.out.println("When I close the add modal and search for “macbook pro”");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("store-display-name")));
        WebElement inputBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete-search-bar")));
        inputBar.sendKeys("macbook pro");
        WebElement searchButton = driver.findElement(By.id("autocomplete-search-button"));
        searchButton.click();

        // Wait for the search results to load
//        try{
//            Thread.sleep(15000);
//        }catch (Exception e){
//            System.out.println("Error while waiting for search results to load: " + e.getMessage());
//        }
    }

    @Then("one of the laptops listed should be {double} 8GB Memory and 256GB SSD")
    public void one_of_the_laptops_listed_should_be_8gb_memory_and_256gb_ssd(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("one of the laptops listed should be {double}” 8GB Memory and 256GB SSD");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement macBook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'sku-block'][contains(., '16') and contains(., '24GB Memory') and contains(., '512GB SSD') and .//button[contains(., 'Add to cart')]]")));
        Assert.assertTrue(macBook.getText().contains("16"));
        Assert.assertTrue(macBook.getText().contains("24GB Memory"));
        Assert.assertTrue(macBook.getText().contains("512GB SSD"));

//        System.out.println("Found a 14 inch mac with 24 GB Memory and 512GB SSD: " + macBook.findElement(By.className("product-title")).getText());

    }

    @Given("I perform the above search")
    public void i_perform_the_above_search() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("We are in the given i perform above");

    }
    @When("I click the “Add to Cart” button next to the laptop")
    public void i_click_the_add_to_cart_button_next_to_the_laptop() {
        // Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement macBook = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'sku-block'][contains(., '16') and contains(., '24GB Memory') and contains(., '512GB SSD') and .//button[contains(., 'Add to cart')]]")));
        System.out.println(macBook.getText());
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(macBook.findElement(By.xpath(".//button[contains(., 'Add to cart')]"))));
        button.click();
    }
    @Then("I should see a modal window with the cart subtotal")
    public void i_should_see_a_modal_window_with_the_cart_subtotal() {
//         Write code here that turns the phrase above into concrete actions
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("I should see a modal window with the cart subtotal");
        WebElement cartSubtotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-subtotal")));
        String actualText = cartSubtotal.getText();
        String expectedText = "Cart Subtotal";
        Assert.assertTrue(actualText.contains(expectedText), "Cart subtotal does not contain expected text: " + expectedText);
    }

    // GO TO MODAL PAGE
    @Given("I am on the Best Buy modal page")
    public void i_am_on_the_best_buy_modal_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am in the Best Buy Modal page");
    }

    @When("I click on go to cart")
    public void i_click_on_go_to_cart() {
        try{
            Thread.sleep(5000);
            WebElement goToCart = driver.findElement(By.xpath("//*[@id='recs-interruptor-drawer-overlay-backdrop']/div/div[3]/div/div/div/div[2]"));
            goToCart.click();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Then("I navigate to the laptop and the order summary")
    public void i_navigate_to_the_laptop_and_the_order_summary() {
        String expUrl = "https://www.bestbuy.com/cart";
        String actUrl = driver.getCurrentUrl();
        WebElement amountSummary = driver.findElement(By.xpath("//*[@id='cartApp']/div[2]/div/div[1]/div/div[1]/div[1]/section[2]/div/div/div[1]/div/table/tbody/tr[5]"));
        System.out.println("Total: "+ amountSummary.getText());
        Assert.assertEquals(actUrl, expUrl);
    }




    // THE REMOVE ITEM FROM CART
    @Given("I am on the Best Buy Cart page")
    public void i_am_on_the_best_buy_cart_page() {
//        try{
////            driver.get("https://www.bestbuy.com/cart");
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @When("I remove an item from the cart")
    public void i_remove_an_item_from_the_cart() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("REMOVE ITEM FROM CART ");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the cart to be clickable and click it
        try {
            driver.getCurrentUrl();
            WebElement cart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("span.cart-label"))
            );
            cart.click();
//            Thread.sleep(5000);
            WebElement removeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button.cart-item__remove"))
            );
            removeButton.click();
//            Thread.sleep(5000);
        } catch (Exception e) {
            throw new RuntimeException("Cart is not clickable: " + e.getMessage());
        }

    }


    @Then("I verify that the item is removed from the cart")
    public void i_verify_that_the_item_is_removed_from_the_cart() {
        // Write code here that turns the phrase above into concrete actions

        // h1 class="heading-5 page-heading__title" what shoudl show
        // Your cart is empty
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emptyCartMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.heading-5.page-heading__title"))
        );
        System.out.println("Message: "+ emptyCartMessage.getText());
        String expectedMessage = "Your cart is empty";
        String actualMessage = emptyCartMessage.getText();
        org.junit.Assert.assertEquals(expectedMessage, actualMessage);
        driver.quit();
    }
}

