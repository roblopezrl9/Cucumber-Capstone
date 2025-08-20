package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BestBuyCart {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait;

//    @Given("I am on the Best Buy modal page")
//    public void i_am_on_the_best_buy_modal_page() {
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        // Write code here that turns the phrase above into concrete actions
//        driver.getCurrentUrl();
//
//    }
//
//    @When("I click on go to cart")
//    public void i_click_on_go_to_cart() {
//        try{
//            WebElement addToCart = driver.findElement(By.xpath("//*[@id='main-results']/main/li[1]/div/div[3]/div/div/div/div/div[1]/div[1]/div/div/div"));
//            addToCart.click();
//
//            Thread.sleep(5000);
//            WebElement cart = driver.findElement(By.xpath("//*[@id='recs-interruptor-drawer-overlay-backdrop']/div/div[3]/div/div/div/div[2]"));
//            cart.click();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Then("I navigate to the laptop and the order summary")
//    public void i_navigate_to_the_laptop_and_the_order_summary() {
//        String expUrl = "https://www.bestbuy.com/cart";
//        String actUrl = driver.getCurrentUrl();
//        Assert.assertEquals(expUrl, actUrl);
  }
