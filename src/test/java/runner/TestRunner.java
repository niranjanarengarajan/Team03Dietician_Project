package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/FeatureFiles", // path to feature folder
        glue = "stepDefinitions", // package containing step definitions
        plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber.json" },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No extra code needed here
}