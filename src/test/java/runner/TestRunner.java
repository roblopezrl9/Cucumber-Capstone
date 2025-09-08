package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {



//        "pretty",
//        "html:target/cucumber-reports/html-report.html",
//        "json:target/cucumber-reports/Cucumber.json",
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },


        monochrome = true,
        tags="@L3"
        //tags ="@L10"
        //tags ="@L9"

)
public class TestRunner {


}
