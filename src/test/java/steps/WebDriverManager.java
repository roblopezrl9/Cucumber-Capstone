package steps;


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
            
            // Detect CI/CD environment (GitHub Actions)
            boolean isCI = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
            
            if (isCI) {
                // Headless mode for CI/CD with window-like behavior
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                // Make headless mode behave more like windowed mode
                opts.addArguments("--window-size=1920,1080");
                opts.addArguments("--disable-web-security");
                opts.addArguments("--allow-running-insecure-content");
                opts.addArguments("--disable-features=VizDisplayCompositor");
                System.out.println("🚀 Running in CI/CD mode (headless with window-like behavior)");
            } else {
                // Windowed mode for local testing
                opts.addArguments("--start-maximized");
                System.out.println("🖥️ Running in local mode (windowed)");
            }
            
            opts.addArguments("--lang=en-US");

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
            
            // Initialize DevTools only in local mode (can cause issues in CI headless)
            if (!isCI) {
                try {
                    devTools = ((ChromiumDriver) driver).getDevTools();
                    devTools.createSession();
                    
                    //Hide webdriver flag (won't bypass CAPTCHAs; just reduces false positives)
                    ((org.openqa.selenium.JavascriptExecutor)driver)
                            .executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
                } catch (Exception e) {
                    System.out.println("⚠️ DevTools initialization failed: " + e.getMessage());
                }
            }

        }
        return driver;
        /*if (driver == null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;*/
    }
}