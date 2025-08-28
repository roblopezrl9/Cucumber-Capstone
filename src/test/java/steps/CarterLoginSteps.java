package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class CarterLoginSteps {

    private  static WebDriver driver;
    private final By cookieBanner = By.cssSelector(
            "#onetrust-banner-sdk, [id*='onetrust'], [class*='onetrust'], [aria-label*='cookie' i]"
    );
    private final By acceptButton = By.cssSelector(
            "#onetrust-accept-btn-handler, button#onetrust-accept-btn-handler, " +
                    "button[aria-label*='Accept' i], button[aria-label*='Allow all' i]"
    );

    @Given("I open the Carters home page")
    public void i_open_the_carters_home_page() {
       // WebDriverManager.chromedriver().setup();
        //ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless");
        //options.addArguments("window-size=1920, 1080");
       // options.addArguments("--disable-blink-features=AutomationControlled");
        //WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");

        options.addArguments("window-size=1920, 1080");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized", "--lang=en-US");
        options.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Create the Chrome driver
        driver = new ChromeDriver(options);
        // Maximize the browser window
        driver.manage().window().maximize();
        // Add Implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Navigate to webpage


       // driver = new ChromeDriver();
        driver.get("https://www.carters.com");
        driver.manage().window().maximize();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

        try
        {
            //WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));

            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("closeIconContainer"))).click();
            Thread.sleep(20000);
            var spinPopup = driver.findElements(By.xpath("//button[@id='closeIconContainer']"));
            System.out.println(spinPopup);
            if (!spinPopup.isEmpty()) {
                try {
                    System.out.println("Spin popup displayed");
                    spinPopup.getFirst().click();
                } catch (ElementClickInterceptedException e) {
                   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", spinPopup.get(0));
                    spinPopup.getFirst().click();
                }
                // wait for banner to vanish
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.invisibilityOfElementLocated(cookieBanner));
            }

        }
        catch (TimeoutException e)
        {
            //e.printStackTrace();
            System.out.println("No popup displayed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //driver.findElement(By.id("closeIconContainer")).click();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));


    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.xpath("//button[@class='chakra-button css-149g159']")).click();
    }

    @When("I click on the Create Account button carters")
    public void i_click_on_the_create_account_button_carters() {
        driver.findElement(By.xpath("//button[@class='chakra-button css-ftv3hg']")).click();
    }

    @Then("I should see a panel to enter account information for Carters")
    public void i_should_see_a_panel_to_enter_account_information_for_carters() {
        WebElement pageHeader=driver.findElement(By.id("chakra-modal--header-:r4q:"));
        assert pageHeader.isDisplayed();
    }

    @Then("I can enter all of my account information for Carters")
    public void i_can_enter_all_of_my_account_information_for_carters() throws InterruptedException {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement firstName = driver.findElement(By.id("firstName"));
            firstName.clear();

            typeSlowly(By.id("firstName"), "ryan");
            WebElement lastName = driver.findElement(By.id("lastName"));
            typeSlowly(By.id("lastName"), "john");

            WebElement emailAddress = driver.findElement(By.id("email"));
            typeSlowly(By.id("email"), "ryan4565@gmail.com");
            WebElement password = driver.findElement(By.id("password"));
            typeSlowly(By.id("password"), "Plus01Min@1234568");

            WebElement confirmPassword = driver.findElement(By.id("passwordConfirmation"));
            typeSlowly(By.id("passwordConfirmation"), "Plus01Min@1234568");

            //WebElement mobileNo = driver.findElement(By.id("mobilePhone"));
            //mobileNo.sendKeys("(246)545-7689");
            //typeSlowly(By.id("mobilePhone"), "2465457689");
            Thread.sleep(2000);
            WebElement termsCheckbox = driver.findElement(By.xpath("//*[@id='create-account-form']/div/div/div[9]/div[1]/label/span[1]"));
            termsCheckbox.click();
            Thread.sleep(5000);
            WebElement createAccountButton = driver.findElement(By.xpath("//button[@type='submit']"));
            createAccountButton.click();
            Thread.sleep(5000);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error while entering account information: " + e.getMessage());
//        }
    }
//    @Then("I should be navigated to my account page for Carters")
//    public void i_should_be_navigated_to_my_account_page_for_carters() {
//
//        driver.findElement(By.xpath("//button[@class='chakra-button css-999vy9']")).click();
//        driver.quit();
//
//    }

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
