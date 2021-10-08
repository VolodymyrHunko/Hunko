package cucumberTest;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/featureFiles/"},
        glue = {"cucumberTest"},
        //dryRun = true,
        monochrome = true,
        format = {"pretty", "html:target/cucumber-html-report"},
        tags = {"@FullTest","~@LongTest"}

)

public class TestRunner {
}
