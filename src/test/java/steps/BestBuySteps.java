package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;

public class BestBuySteps {
    WebDriver driver;

    @BeforeTest
    @Given("I am on the BestBuy home page")
    public void i_am_on_the_best_buy_home_page() {
        driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/ ");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @Then("I click on Account button")
    public void i_click_on_account_button() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']")).click();

        Thread.sleep(5000);
    }

    @Then("I see the panel with Create account button")
    public void i_see_the_panel_with_create_account_button() throws InterruptedException {
        // Wait for the panel to be visible
        Thread.sleep(2000);
        // create account button is displayed: Create Account
        //System.out.println("Create Account button is displayed: " + actual);
         Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Create Account']")).isDisplayed());


    }

    @When("I click on the Create Account button")
    public void i_click_on_the_create_account_button() {
        driver.findElement(By.xpath("//a[text()='Create Account']")).click();

    }

    @Then("I should be navigated to Create Account page")
    public void i_should_be_navigated_to_create_account_page() {
        String expected = "Best Buy: Create an Account";
        // Wait for the page to load and title to be set
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // Get the actual title of the page
        String actualUrl = driver.getTitle();
        Assert.assertEquals(actualUrl, expected);


    }

    @Then("I can enter all of my account information")
    public void i_can_enter_all_of_my_account_information() {
        try {

            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.sendKeys("John");
            WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
            lastName.sendKeys("Doe");
            WebElement emailaddress = driver.findElement(By.xpath("//input[@id='email']"));
            emailaddress.sendKeys("johndoe123@gmail.com");
            WebElement password = driver.findElement(By.id("fld-p1"));
            password.sendKeys("StrongPassword123");
            // The confirm password field is usually the same as the password field
            WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='reenterPassword']"));
            confirmPassword.sendKeys("StrongPassword123");
            WebElement mobileNo = driver.findElement(By.xpath("//input[@name='phone']"));
            mobileNo.sendKeys("1234567890");
            WebElement useforAccountRecovery = driver.findElement(By.xpath("//input[@name='isRecoveryPhone']"));
            useforAccountRecovery.click();
            WebElement createAccountButton = driver.findElement(By.xpath("//button[@type='submit']"));
            createAccountButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while entering account information: " + e.getMessage());
        }
        driver.quit();
    }


//    @Then("I should see the account created successfully message")
//    public void i_should_see_the_account_created_successfully_message() {
//        String expectedMessage = "Account created successfully";
//        String actualMessage = driver.findElement(By.id("success-message")).getText();
//        Assert.assertEquals(actualMessage, expectedMessage, "Account creation message does not match expected.");
//
//
//    }
}

