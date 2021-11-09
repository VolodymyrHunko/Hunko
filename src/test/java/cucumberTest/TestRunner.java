package cucumberTest;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles/"},
        glue = {"cucumberTest"},
        //dryRun = true,
        monochrome = true,
        format = {"pretty", "html:target/cucumber-html-report"},
        tags = {"@experiment"}

)

public class TestRunner {
}
