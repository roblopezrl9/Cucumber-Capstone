package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class RegisterSteps {

    WebDriver driver= WebDriverManager.getDriver();;

    @Given("I navigate to the Demo Web Shop registration page")
    public void i_navigate_to_registration_page() {
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/register");
    }

    @When("I select gender {string}")
    public void i_select_gender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(By.id("gender-male")).click();
        } else {
            driver.findElement(By.id("gender-female")).click();
        }
    }

    @When("I enter {string} into the First Name field")
    public void i_enter_first_name(String firstName) {
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
    }

    @When("I enter {string} into the Last Name field")
    public void i_enter_last_name(String lastName) {
        driver.findElement(By.id("LastName")).sendKeys(lastName);
    }

    @When("I enter a unique email into the Email field")
    public void i_enter_unique_email() {
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.id("Email")).sendKeys(uniqueEmail);
    }

    @When("I enter {string} into the Password field")
    public void i_enter_password(String password) {
        driver.findElement(By.id("Password")).sendKeys(password);
    }

    @When("I enter {string} into the Confirm Password field")
    public void i_enter_confirm_password(String confirmPassword) {
        driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
    }

    @When("I click the Register button")
    public void i_click_register_button() {
        driver.findElement(By.id("register-button")).click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_message(String expectedMessage) {
        WebElement resultMessage = driver.findElement(By.className("result"));
        assertEquals(expectedMessage, resultMessage.getText());
       // driver.quit();
    }
}
