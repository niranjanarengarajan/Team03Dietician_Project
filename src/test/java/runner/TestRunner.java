package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features = "src/test/resources/FeatureFiles", 
        tags= "@loginPage",
        glue = "stepDefinitions", 
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/CucumberReports/CodeProphetsJson.json",
                "html:target/CucumberReports/CodeProphets.html",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        		    },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	public static ThreadLocal<String> browserName = new ThreadLocal<>();


    @Parameters("browser")
    @BeforeClass
    public void setBrowser(@Optional("chrome")String browser) {
        browserName.set(browser);
    }
}
