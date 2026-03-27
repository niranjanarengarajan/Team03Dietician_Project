package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/resources/FeatureFiles", 
        tags= "@AddPatient",
        glue = "stepDefinitions", 
        plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber.json" },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	public static ThreadLocal<String> browserName = new ThreadLocal<>();


    @Parameters("browser")
    @BeforeClass
    public void setBrowser(String browser) {
        browserName.set(browser);
    }
}