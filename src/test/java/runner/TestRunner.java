package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
//        tags = "@T1",
        monochrome = true
        //tags = "@P21" // Uncomment to run specific tags
//        tags = "@L1" //Login Functionality
        //tags = "@L1" //Login Functionality
         //tags ="@L2" //Login Functionality with negative tests
        //tags = "@P22" //Product Search Function
)
public class TestRunner {


}
