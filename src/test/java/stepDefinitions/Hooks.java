package stepDefinitions;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;


public class Hooks {

	
	private TestContext context;
	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
	
	public Hooks(TestContext context) {
	this.context =context ;
	}

	@Before
	public void setup() {
				
		logger.info("Initializing test setup...");
		Properties prop = ConfigReader.initializeProperties();
		logger.debug("Loaded configuration properties");
		
		context.drfactory.launchBrowser(prop.getProperty("browserName")); 
		context.drfactory.getDriver().get(prop.getProperty("baseURL"));
		LoggerLoad.info("Navigated to base URL: " + prop.getProperty("baseURL"));
		
		ExcelReader.readDataFromExcel(prop.getProperty("loginsheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("editPatient"));
		ExcelReader.readDataFromExcel(prop.getProperty("deletePatient"));
				
	}
	
	@Before(order = 1)
	public void performLogin() {
	    logger.info("Performing background login...");
	    
	    // Using instance from context
	    context.loginStep.user_clicks_login_button_after_entering_valid_credentials_in_login_page();
	    
	    logger.info("Performed login with valid credentials");
	}

    	
	@After
	public void tearDown() {
	    //calling the instance method through the context
	    context.drfactory.closeDriver();
	}
			
	
}
