package steps;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;


public class GapCreateAccountSteps {
    WebDriver driver = WebDriverManager.getDriver();

    @Given("I launch the Gap sign-in page")
    public void i_launch_gap_sign_in_page() throws InterruptedException {


        driver.get("https://secure-www.gap.com/my-account/sign-in");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //driver.findElement(By.id("verify-email-input")).sendKeys("sachinb225@gmail.com");
        typeSlowly(By.id("verify-email-input"),"sachinb225@gmail.com");
        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        Thread.sleep(40000);
    }

    @When("I navigate to the Create Account section")
    public void i_navigate_to_create_account_section() {
        WebElement createAccountForm = driver.findElement(By.id("createAccountForm"));
        assertTrue(createAccountForm.isDisplayed());
    }

    @When("I enter {string} in the First Name field")
    public void i_enter_first_name(String firstName) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
    }

    @When("I enter {string} in the Last Name field")
    public void i_enter_last_name(String lastName) {
        driver.findElement(By.id("lastName")).sendKeys(lastName);
    }

    @When("I enter {string} in the Email field")
    public void i_enter_email(String email) {
        driver.findElement(By.id("emailAddress")).sendKeys(email);
    }

    @When("I enter {string} in the Password field")
    public void i_enter_password(String password) {
        driver.findElement(By.id("password-new")).sendKeys(password);
    }

    @When("I enter {string} in the Confirm Password field")
    public void i_enter_confirm_password(String confirmPassword) {
        driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);
    }

    @When("I check the {string} checkbox")
    public void i_check_checkbox(String label) {
        driver.findElement(By.id("keepMeSignedIn")).click();
    }

    @When("I click the Create Account button")
    public void i_click_create_account_button() {
        driver.findElement(By.id("createAccountBtn")).click();
    }

    @Then("I should be redirected to the My Account page")
    public void i_should_be_redirected_to_my_account_page() {
        assertTrue(driver.getCurrentUrl().contains("my-account"));
        driver.quit();
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



    

