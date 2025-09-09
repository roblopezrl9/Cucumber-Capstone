package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",

        plugin = {"pretty",
                "html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
//        tags = "@T1",
        monochrome = true,

        tags="@L3 or @Positive or @Negative" //Login Functionality with Positive and Negative Scenarios
        //tags ="@Positive"
        //tags="@L1"
)
public class TestRunner {


}
