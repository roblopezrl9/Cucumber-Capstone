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
                // Headless mode for CI/CD - make it behave EXACTLY like windowed mode
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                
                // Window behavior - exact same as your IntelliJ
                opts.addArguments("--window-size=1920,1080");
                opts.addArguments("--start-maximized");
                
                // JavaScript and rendering - same as windowed
                opts.addArguments("--disable-web-security");
                opts.addArguments("--allow-running-insecure-content");
                opts.addArguments("--disable-features=VizDisplayCompositor");
                opts.addArguments("--enable-javascript");
                opts.addArguments("--disable-background-timer-throttling");
                opts.addArguments("--disable-renderer-backgrounding");
                opts.addArguments("--disable-backgrounding-occluded-windows");
                
                // Modal and popup behavior - same as windowed
                opts.addArguments("--disable-popup-blocking");
                opts.addArguments("--disable-default-apps");
                opts.addArguments("--disable-translate");
                
                // Performance - same as windowed
                opts.addArguments("--memory-pressure-off");
                opts.addArguments("--max_old_space_size=4096");
                
                System.out.println("🚀 Running in CI/CD mode (headless configured to match IntelliJ behavior)");
            } else {
                // Windowed mode for local testing (your working IntelliJ setup)
                opts.addArguments("--start-maximized");
                System.out.println("🖥️ Running in local mode (windowed - IntelliJ setup)");
            }
            
            opts.addArguments("--lang=en-US");

            // Browser preferences - make CI behave exactly like your IntelliJ
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);
            prefs.put("profile.block_third_party_cookies", false);
            prefs.put("profile.default_content_setting_values.popups", 1); // Allow popups (like your IntelliJ)
            prefs.put("profile.default_content_setting_values.notifications", 1); // Allow notifications
            prefs.put("profile.managed_default_content_settings.images", 1); // Load images
            prefs.put("profile.default_content_settings.popups", 0); // Allow popups
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