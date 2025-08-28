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
            
            // Detect CI environment for speed optimizations
            boolean isCI = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
            
            if (isCI) {
                // CI/CD mode - optimized for 5min pipeline
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                
                // Speed optimizations for 5min target
                opts.addArguments("--disable-extensions");
                opts.addArguments("--disable-plugins");
                opts.addArguments("--disable-images");
                opts.addArguments("--disable-background-networking");
                opts.addArguments("--disable-background-timer-throttling");
                opts.addArguments("--disable-renderer-backgrounding");
                opts.addArguments("--disable-backgrounding-occluded-windows");
                opts.addArguments("--disable-client-side-phishing-detection");
                opts.addArguments("--disable-default-apps");
                opts.addArguments("--disable-hang-monitor");
                opts.addArguments("--disable-prompt-on-repost");
                opts.addArguments("--disable-sync");
                opts.addArguments("--disable-translate");
                opts.addArguments("--metrics-recording-only");
                opts.addArguments("--no-first-run");
                opts.addArguments("--disable-logging");
                opts.addArguments("--disable-permissions-api");
                opts.addArguments("--aggressive-cache-discard");
                
                System.out.println("🚀 CI/CD Mode: Speed-optimized for 5min pipeline");
            } else {
                // Local mode - your existing settings
                opts.addArguments("--start-maximized");
                System.out.println("🖥️ Local Mode: Standard settings");
            }
            
            // Common settings for both modes
            opts.addArguments("--lang=en-US");
            opts.addArguments("--window-size=1920,1080");
            opts.addArguments("--disable-blink-features=AutomationControlled");

            // Browser preferences
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);
            prefs.put("profile.block_third_party_cookies", false);
            opts.setExperimentalOption("prefs", prefs);

            // Soften automation fingerprint
            opts.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
            opts.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(opts);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
