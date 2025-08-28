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
            //io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions opts = new ChromeOptions();
            opts.addArguments("--start-maximized", "--lang=en-US");
            opts.addArguments("window-size=1920, 1080");
            opts.addArguments("--disable-blink-features=AutomationControlled");

            // Only add headless mode when running in CI (GitHub Actions)
            if (System.getenv("CI_HEADLESS") != null) {
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                System.out.println("Running in CI headless mode");
            } else {
                System.out.println("Running in windowed mode (IntelliJ)");
            }

            // 1 = allow geolocation, 2 = block, 0 = ask
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);
            prefs.put("profile.block_third_party_cookies", false);
            opts.setExperimentalOption("prefs", prefs);

            // Soften automation fingerprint a bit
            opts.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
            opts.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(opts);
        }
        return driver;
        /*if (driver == null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;*/
    }


    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
