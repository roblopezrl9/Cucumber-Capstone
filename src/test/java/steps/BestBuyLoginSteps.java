package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;

public class BestBuyLoginSteps {

    WebDriver driver=WebDriverManager.getDriver();

    @BeforeTest
    @Given("start with the BestBuy home page")
    public void startWithTheBestBuyHomePage() {
        //driver = new ChromeDriver();
        driver.get("https://www.bestbuy.com/ ");
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Then("I click on Account button")
    public void i_click_on_account_button() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']")).click();


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
    // Method to generate a unique email address
    String uniqueEmail()
    {
        String prefix = "testuser";
        String domain = "@capestone.com";
        long timestamp = System.currentTimeMillis();
        return prefix + timestamp + domain;
    }

    @Then("I can enter all of my account information")
    public void i_can_enter_all_of_my_account_information() {
        try {

            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.sendKeys("John");
            WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
            lastName.sendKeys("Doe");
            WebElement emailAddress = driver.findElement(By.xpath("//input[@id='email']"));
            //automate the email address input
            emailAddress.sendKeys("sachin.test@gmail.com");
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
        //driver.quit();
    }


    @Then("I should be navigated to my account page")
    public void i_should_be_navigated_to_my_account_page() throws InterruptedException {
        Thread.sleep(2000);
        String expectedUrl = "https://www.bestbuy.com/customer/myaccount";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        System.out.println("Successfully navigated to my account page: " + actualUrl);
    }

    @Given("I am on the BestBuy Create Account page")
    public void i_am_on_the_best_buy_create_account_page() {
        //driver= new ChromeDriver();
        driver.get("https://www.bestbuy.com/identity/newAccount?token=tid%3A60255b47-7eb0-11f0-bc6f-12e22549baeb");
       // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @When("I enter {string} it should check for {string}")
    public void i_enter_it_should_check_for(String string, String invalid) throws InterruptedException {
       WebElement firstName= driver.findElement(By.id("firstName"));
       firstName.sendKeys(string);
       firstName.sendKeys(Keys.ENTER);
        if(invalid.equals("true")){

            Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Too many numeric characters.']")).isDisplayed());
    }
        else
        {
            Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Please enter your first name.']")).isDisplayed()) ;
        }

        //clear the input field for the next test
        firstName.clear();
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
