package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


	

@CucumberOptions(
	    features = "src/test/resources/FeatureFiles", // Path to your feature files
	    //tags =  "",
//	    glue = "stepDefinitions", // Package where your step definitions are located
//	    plugin = {
//	        "pretty", // Pretty formatting for console output
//	        "html:target/cucumber-reports.html", // Generate HTML report
//	        "json:target/cucumber-reports.json" // Generate JSON report
	   // },
	    monochrome = true // Clean console output
	)
	public class TestRunner extends AbstractTestNGCucumberTests {
	    // This class doesn't need any code; it's just a configuration class.
	}
	
	
	


