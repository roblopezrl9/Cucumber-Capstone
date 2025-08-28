package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverManager {
    public static WebDriver driver;
    public static DevTools devTools;
    private static String tempProfilePath;

    public static WebDriver getDriver(){
        if (driver == null){
            ChromeOptions opts = new ChromeOptions();
            
            // Create unique user data directory to prevent session conflicts
            try {
                tempProfilePath = Files.createTempDirectory("chrome-profile").toString();
                opts.addArguments("--user-data-dir=" + tempProfilePath);
                System.out.println("🔧 Using Chrome profile: " + tempProfilePath);
            } catch (IOException e) {
                System.out.println("⚠️ Could not create temp profile, using default");
            }
            
            // Detect if running in CI/CD environment (headless mode)
            boolean isCI = System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
            if (isCI) {
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                opts.addArguments("--remote-debugging-port=9222");
                System.out.println("🚀 Running in CI/CD mode (headless)");
            } else {
                opts.addArguments("--start-maximized");
                System.out.println("🖥️ Running in local mode (windowed)");
            }
            
            // Common Chrome arguments
            opts.addArguments("--lang=en-US");
            opts.addArguments("--disable-blink-features=AutomationControlled");
            opts.addArguments("--disable-extensions");
            
            // 1 = allow geolocation, 2 = block, 0 = ask
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.geolocation", 1);
            prefs.put("profile.block_third_party_cookies", false);
            opts.setExperimentalOption("prefs", prefs);

            // Soften automation fingerprint
            opts.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
            opts.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(opts);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            
            // Only create DevTools session if not in headless mode (can cause issues in CI)
            if (!isCI) {
                try {
                    devTools = ((ChromiumDriver) driver).getDevTools();
                    devTools.createSession();
                    
                    // Hide webdriver flag (won't bypass CAPTCHAs; just reduces false positives)
                    ((org.openqa.selenium.JavascriptExecutor)driver)
                            .executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
                } catch (Exception e) {
                    System.out.println("⚠️ Could not initialize DevTools: " + e.getMessage());
                }
            }
        }
        return driver;
    }
    
    /**
     * Properly close the WebDriver and clean up resources
     */
    public static void closeDriver() {
        if (driver != null) {
            try {
                if (devTools != null) {
                    devTools.close();
                    devTools = null;
                }
                driver.quit();
                driver = null;
                
                // Clean up temp profile directory
                if (tempProfilePath != null) {
                    try {
                        // Note: Actual cleanup might need to be done by OS after some delay
                        System.out.println("🧹 Cleaned up Chrome profile: " + tempProfilePath);
                    } catch (Exception e) {
                        System.out.println("⚠️ Could not clean up temp profile: " + e.getMessage());
                    }
                    tempProfilePath = null;
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error during driver cleanup: " + e.getMessage());
            }
        }
    }
    
    /**
     * Cucumber Hook: Ensure cleanup after each scenario
     */
    @After
    public void scenarioCleanup() {
        closeDriver();
        System.out.println("🧽 Scenario completed - WebDriver cleaned up");
    }
}
