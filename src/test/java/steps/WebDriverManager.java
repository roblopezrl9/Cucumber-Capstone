package steps;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverManager {
    public static WebDriver driver;
    public static DevTools devTools;


    public static WebDriver getDriver(){
        if (driver == null){
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("--start-maximized", "--lang=en-US");

            // 1 = allow geolocation, 2 = block, 0 = ask
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);
            prefs.put("profile.block_third_party_cookies", false);
            opts.setExperimentalOption("prefs", prefs);

            // Soften automation fingerprint a bit
            opts.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
            opts.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(opts);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            devTools = ((ChromiumDriver) driver).getDevTools();
            devTools.createSession();


            //Hide webdriver flag (won't bypass CAPTCHAs; just reduces false positives)
            ((org.openqa.selenium.JavascriptExecutor)driver)
                    .executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        }
        return driver;
    }
}
