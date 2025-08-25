package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class BestBuyLoginSteps {

    WebDriver driver=WebDriverManager.getDriver();
    // Common selectors for OneTrust + fallbacks
    private final By cookieBanner = By.cssSelector(
            "#onetrust-banner-sdk, [id*='onetrust'], [class*='onetrust'], [aria-label*='cookie' i]"
    );
    private final By acceptButton = By.cssSelector(
            "#onetrust-accept-btn-handler, button#onetrust-accept-btn-handler, " +
                    "button[aria-label*='Accept' i], button[aria-label*='Allow all' i]"
    );

   @Before
    @Given("start with the BestBuy home page")
    public void startWithTheBestBuyHomePage() throws InterruptedException {
        driver.get("https://www.bestbuy.com/");
        //Thread.sleep(5000);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<WebElement> banners = driver.findElements(cookieBanner);
        if (!banners.isEmpty() && banners.getFirst().isDisplayed()) {
            // Try click accept; if intercepted, scroll and retry
            List<WebElement> buttons = driver.findElements(acceptButton);
            if (!buttons.isEmpty()) {
                try {
                    buttons.getFirst().click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", buttons.get(0));
                    buttons.getFirst().click();
                }
                // wait for banner to vanish
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.invisibilityOfElementLocated(cookieBanner));
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    }

    @Then("I click on Account button")
    public void i_click_on_account_button() throws InterruptedException {
        // Wait for the Account button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement accountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='v-p-right-xxs line-clamp']")));
        accountButton.click();
        //Thread.sleep(2000);
        //driver.findElement(By.xpath("//span[@class='v-p-right-xxs line-clamp']")).click();
    }

    @Then("I see the panel with Create account button")
    public void i_see_the_panel_with_create_account_button() throws InterruptedException {
        // Wait for the panel to be visible
        // create account button is displayed: Create Account

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Create Account']")));
        //Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Create Account']")).isDisplayed());
    }

    @When("I click on the Create Account button")
    public void i_click_on_the_create_account_button() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Create Account']")));
        element.click();


    }

    @Then("I should be navigated to Create Account page")
    public void i_should_be_navigated_to_create_account_page() throws InterruptedException {
        String expected = "Best Buy: Create an Account";
        // Wait for the page to load and title to be set
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.clear();
            //firstName.sendKeys("SamJ");
            typeSlowly(By.id("firstName"),"SteveM");
            WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));
            //lastName.sendKeys("Johnson");
            typeSlowly(By.xpath("//input[@name='lastName']"),"LeeS");
            WebElement emailAddress = driver.findElement(By.xpath("//input[@id='email']"));
            //automate the email address input
            emailAddress.clear();
            //emailAddress.sendKeys("Priya468Tar@gmail.com");
            typeSlowly(By.xpath("//input[@id='email']"),"StevemLees@yahoo.com");
            WebElement password = driver.findElement(By.id("fld-p1"));
            //password.sendKeys("Test@123@Test12456");
            typeSlowly(By.id("fld-p1"),"Plus01Min$1234568");
            Thread.sleep(1000);
            // The confirm password field is usually the same as the password field
            WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='reenterPassword']"));
            //confirmPassword.sendKeys("Test@123@Test12456");
            typeSlowly(By.xpath("//input[@id='reenterPassword']"),"Plus01Min$1234568");
            Thread.sleep(1000);
            WebElement mobileNo = driver.findElement(By.xpath("//input[@name='phone']"));
            //mobileNo.sendKeys("2122457212");
            typeSlowly(By.xpath("//input[@name='phone']"),"7017321878");
            Thread.sleep(2000);
            WebElement useForAccountRecovery = driver.findElement(By.xpath("//input[@name='isRecoveryPhone']"));
            useForAccountRecovery.click();
            //WebElement keepMeSignedIn = driver.findElement(By.id("cia-remember-me"));
           // keepMeSignedIn.clear();
            WebElement createAccountButton = driver.findElement(By.xpath("//button[@type='submit']"));
            createAccountButton.click();
            //Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while entering account information: " + e.getMessage());
        }

    }


    @Then("I should be navigated to my account page")
    public void i_should_be_navigated_to_my_account_page() throws InterruptedException {
        Thread.sleep(2000);
        String expectedTitle = "Account Home - Best Buy";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Successfully navigated to my account page: " + actualTitle);
        driver.quit();
    }



    @When("I enter {string} it should check for password validations {string}")
    public void i_enter_it_should_check_for_password_validations(String password, String valid) {
       WebElement passwordtextfield =driver.findElement(By.id("fld-p1"));
       passwordtextfield.sendKeys(password);
       passwordtextfield.sendKeys(Keys.TAB);
        List<WebElement> passwordElement = driver.findElements(By.xpath("//p[text()='Please enter a strong password.']"));

        if(valid.equals("true")){
            System.out.println("Valid = true " + passwordElement.isEmpty());
            Assert.assertTrue(passwordElement.isEmpty());
        }
        else {
            System.out.println("Valid = false " + passwordElement.isEmpty());
            Assert.assertTrue(passwordElement.getFirst().isDisplayed());
            //Assert.assertFalse(passwordElement.isEmpty());
        }

       // driver.quit();
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void typeSlowly(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        for (char c : text.toCharArray()) {
            el.sendKeys(Character.toString(c));
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {
            }
        }
    }


    }
