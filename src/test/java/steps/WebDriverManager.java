package steps;

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
            opts.addArguments("--start-maximized", "--lang=en-US");
            opts.addArguments("window-size=1920, 1080");
            opts.addArguments("--disable-blink-features=AutomationControlled");

            //opts.addArguments("--headless");

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
    }


}
