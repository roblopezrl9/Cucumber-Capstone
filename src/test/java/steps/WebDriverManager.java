package steps;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
    static WebDriver driver;


    public static WebDriver getDriver(){
        if (driver == null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
