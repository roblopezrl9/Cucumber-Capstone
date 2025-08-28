package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty", 
                "html:target/cucumber-reports.html",
                "json:mcp-results/cucumber.json",
                "junit:mcp-results/junit.xml"
        },
        tags = "@T1",
        monochrome = true
)
public class TestRunner {


}
