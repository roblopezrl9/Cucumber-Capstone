package steps;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class WebDriverManager {
    public static WebDriver driver;


    public static WebDriver getDriver(){
        if (driver == null){
            //io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions opts = new ChromeOptions();
            
            // Check if running in CI environment (GitHub Actions, Jenkins, etc.)
            boolean isCI = System.getenv("CI") != null || 
                          System.getenv("GITHUB_ACTIONS") != null ||
                          System.getenv("JENKINS_URL") != null ||
                          System.getProperty("java.awt.headless") != null;
            
            if (isCI) {
                // CI/Headless configuration
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
                opts.addArguments("--disable-gpu");
                opts.addArguments("--disable-extensions");
                opts.addArguments("--remote-debugging-port=9222");
                opts.addArguments("--window-size=1920,1080");
                System.out.println("🤖 Running in CI mode - Headless Chrome enabled");
            } else {
                // Local development configuration
                opts.addArguments("--start-maximized");
                opts.addArguments("window-size=1920, 1080");
                System.out.println("🖥️ Running in local mode - GUI Chrome enabled");
            }
            
            // Common arguments for both environments
            opts.addArguments("--lang=en-US");
            opts.addArguments("--disable-blink-features=AutomationControlled");

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



