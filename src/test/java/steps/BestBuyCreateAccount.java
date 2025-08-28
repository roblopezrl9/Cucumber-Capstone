package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BestBuyCreateAccount {

    private final By cookieBanner = By.cssSelector("#onetrust-banner-sdk,[id*='onetrust'],[class*='onetrust']");
    private final By acceptBtn    = By.cssSelector("#onetrust-accept-btn-handler,button[aria-label*='Accept' i]");
    private final By countryUS    = By.xpath("//*[self::a or self::button][contains(.,'United States') and not(contains(.,'Canada'))]");

    private final By firstName = By.xpath("//input[@name='firstName' or contains(@id,'firstName')]");
    private final By lastName  = By.xpath("//input[@name='lastName' or contains(@id,'lastName')]");
    private final By password  = By.xpath("//input[@type='password' and (@name='fld-p1' or contains(@id,'fld-p1'))]");
    private final By reenterPassword     = By.xpath("//input[@type='password' and (contains(@name,'reenterPassword') or contains(@id,'reenterPassword') or contains(@aria-label,'reenterPassword'))]");
    private final By mobile  = By.xpath("//input[@name='phone' or contains(@id,'phone')]");
    private final By submitBtn = By.xpath("//button[@type='submit' or contains(.,'Create Account') or contains(.,'Create an Account')]");
    private final By anyError  = By.xpath("//*[@role='alert' or contains(@class,'error') or contains(@data-validation,'error')]");
    private final By emailField = By.xpath("//input[@type='email' and (@name='email' or contains(@id,'email'))]");
    private final By emailSpinnerNear = By.xpath(
            "//input[@type='email']/ancestor::*[self::div or self::section][1]" +
                    "//*[contains(@class,'spinner') or contains(@class,'loading')]");
    private String generatedEmail;
    WebDriver driver=WebDriverManager.getDriver();

    @Given("I open the Best Buy Create Account page")
    public void openCreate() { driver.get("https://www.bestbuy.com/identity/global/createAccount"); }

    @And("I accept all cookies if shown")
    public void acceptCookies() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.findElements(cookieBanner).isEmpty()) {
            var btns = driver.findElements(acceptBtn);
            if (!btns.isEmpty()) { try { btns.get(0).click(); } catch (Exception ignored) {} }
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(cookieBanner));
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @And("I choose country {string} if prompted")
    public void chooseCountry(String country) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        if (!driver.findElements(countryUS).isEmpty() && country.equalsIgnoreCase("United States")) {
            driver.findElements(countryUS).get(0).click();
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }



    @When("I enter first name {string} and last name {string}")
    public void enterNames(String fn, String ln) {
        clearAndType(firstName, fn);
        clearAndType(lastName, ln);
    }
    @When("I enter password {string}")
    public void enterPassword(String pwd) {
        clearAndType(password, pwd);

    }

    @When("I reenter password {string}")
    public void reenterPassword(String pwd) {
        clearAndType(reenterPassword, pwd);

    }

    @When("I enter mobile {string}")
    public void enterMobile(String pwd) {
        clearAndType(mobile, pwd);

    }

    @When("I accept Terms and submit")
    public void acceptTermsAndSubmit() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    @Then("registration should proceed or show verify email")
    public void registrationOutcome() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(12)).until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/account"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(translate(.,'EMAILVERIFY','emailverify'),'verify your email')]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(.,'Welcome') or contains(.,'Hi')]"))
            ));
        } catch (TimeoutException e) {
            var banners = driver.findElements(anyError);
            String msg = banners.stream().map(WebElement::getText).reduce("", (a, b)->a+" | "+b);
            throw new AssertionError("Signup failed. Error(s): " + msg +
                    "\nEmail used: " + generatedEmail +
                    "\nLikely causes: blocked/normalized email, validator race, rate limit, or geo/country gate.", e);
        }
    }

    private void clearAndType(By locator, String text) {
        var el = new WebDriverWait(driver, Duration.ofSeconds(12))
                .until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, text);
    }

    private void typeSlowly(By locator, String text) {
        var el = new WebDriverWait(driver, Duration.ofSeconds(12))
                .until(ExpectedConditions.elementToBeClickable(locator));
        el.click(); el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        for (char c : text.toCharArray()) { el.sendKeys(Character.toString(c)); try { Thread.sleep(25);} catch (InterruptedException ignored) {} }
    }

    private void waitForEmailCheckUI() {
        try { new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.invisibilityOfElementLocated(emailSpinnerNear)); }
        catch (TimeoutException ignored) {}
        try { Thread.sleep(300);} catch (InterruptedException ignored) {}
    }

    @When("I enter a fresh, valid email")
    public void enterFreshValidEmail() {
        //String domain = System.getProperty("EMAIL_DOMAIN");
        //if (domain == null || domain.isBlank())
         //   throw new IllegalStateException("Set -DEMAIL_DOMAIN=qa.yourcompany.dev (catch-all domain)");
        generatedEmail = "alextestbarbara123@gmail.com";
        typeSlowly(emailField, generatedEmail);
        waitForEmailCheckUI(); // <<< key fix
    }


}
