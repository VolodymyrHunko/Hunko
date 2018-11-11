package helpers;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\java\\featureFiles\\cucumberFile.feature",
        glue = "src\\test\\java\\cucumberTest\\MyStepdefs",
        dryRun = true,
        monochrome = false,
        format = {"pretty"}

)

public class TestRunner {
}
