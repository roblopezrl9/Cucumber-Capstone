package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import java.time.Duration;

public class StaplesRegisterSteps {

    WebDriver driver = WebDriverManager.getDriver();

    @Given("I navigate to the Staples account creation page")
    public void goToCreateAccountPage() throws InterruptedException {

        driver.get("https://www.staples.com/idm/com/createaccount");
        Thread.sleep(20000);
        handleIFrames();

    }
    private void handleIFrames() throws InterruptedException {
        try { driver.switchTo().activeElement().sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        ((JavascriptExecutor)driver).executeScript(
                "document.querySelectorAll(\"[role='dialog'],.modal,.popup,[data-modal='true']\").forEach(e=>{" +
                        "e.style.setProperty('display','none','important');});"
        );
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    @When("I enter a unique email")
    public void enterUniqueEmail() {
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
    }

    @When("I enter {string} into the First Name field of staples")
    public void enterFirstName(String firstName) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
    }

    @When("I enter {string} into the Last Name field of staples")
    public void enterLastName(String lastName) {
        driver.findElement(By.id("lastName")).sendKeys(lastName);
    }

    @When("I enter {string} into the Phone Number field")
    public void enterPhoneNumber(String phone) {
        //driver.findElement(By.id("mobileNumber")).sendKeys(phone);
        typeSlowly(By.id("mobileNumber"), phone);
        WebElement phoneField = driver.findElement(By.id("mobileNumber"));
        phoneField.sendKeys(Keys.TAB);
    }

    @When("I enter {string} into the Password field of staples")
    public void enterPassword(String password) throws InterruptedException {
        //driver.findElement(By.id("password")).sendKeys(password);
        typeSlowly(By.id("password"), password);
        Thread.sleep(1000);

    }

    @When("I select {string} for the shopping preference")
    public void selectShoppingPreference(String preference) {
        //WebElement dropdown = driver.findElement(By.id("shoppingPreference"));
        //dropdown.click();
        //dropdown.findElement(By.xpath("//option[text()='" + preference + "']")).click();
    }

    @When("I click the Create Account button of staples")
    public void clickCreateAccount() throws InterruptedException {
        driver.findElement(By.id("submitIdmCreateForm")).click();
        Thread.sleep(10000);

    }

    @Then("I should see a confirmation message or be redirected to My Account")
    public void verifyAccountCreated() throws InterruptedException {
        String expectedUrl="https://www.staples.com/idm/com/createuserconfirm";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        Thread.sleep(10000);
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


    private final By likelySignupFrames = By.cssSelector(
            "iframe[src*='signup' i], iframe[title*='sign' i], iframe[id*='sign' i]," +
                    "iframe[src*='newsletter' i], iframe[src*='overlay' i], iframe[src*='modal' i], iframe"
    );


    @Then("I should see a incorrect phone number format error message")
    public void iShouldSeeAIncorrectPhoneNumberFormatErrorMessage() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(driver.findElement(
                By.xpath("//div[text()='Please enter a valid phone number.']")).isDisplayed());

    }

    @Then("I close the browser")
    public void i_close_the_browser() {
        // Write code here that turns the phrase above into concrete actions
        WebDriverManager.quitDriver();
    }

}
