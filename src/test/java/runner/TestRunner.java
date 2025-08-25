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
        monochrome = true,
        //tags = "@P21" // Uncomment to run specific tags
        //tags = "@L1" //Login Functionality

        //tags = "@P22" //Product Search Function
        tags="@L3" //Login with Data-Driven
        //tags="@L1"

)
public class TestRunner {


}
