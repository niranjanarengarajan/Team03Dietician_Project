
package stepDefinitions;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;	
import utils.Screenshot;


public class Hooks {
   // private WebDriver driver;
    private TestContext context;
    private Login_Step loginObj;
	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
	
	public Hooks(TestContext context, Login_Step loginObj) {
	this.context =context ;
	this.loginObj = loginObj;
	}

    @Before(order=0)
    public void setup() {
        LoggerLoad.info("Initializing test setup...");
        
        Properties prop = ConfigReader.initializeProperties();
        LoggerLoad.info("Loaded configuration properties");        
       
        context.drfactory.launchBrowser(prop.getProperty("browserName")); 
    	context.drfactory.getDriver().get(prop.getProperty("baseURL"));
    	LoggerLoad.info("Navigated to base URL: " + prop.getProperty("baseURL"));
    	context.initPageObjects();
    	 LoggerLoad.info("poManager initialized: " + (context.poManager != null));
    	 LoggerLoad.info("Driver: " + (context.drfactory.getDriver() != null));
        ExcelReader.readDataFromExcel(prop.getProperty("loginsheetName"));
    	ExcelReader.readDataFromExcel(prop.getProperty("editPatient"));
    	ExcelReader.readDataFromExcel(prop.getProperty("deletePatient"));    
    	ExcelReader.readDataFromExcel(prop.getProperty("AddPatientSheetName"));
    }
	
   @Before(value = "@Login", order = 1)
    public void performLogin() {
	    logger.info("Performing background login...");
	    //Login_Step loginStep = new Login_Step(context);
	    if (loginObj == null) {
	        logger.error("loginStep is NULL � PicoContainer did not inject Login_Step!");
	        throw new RuntimeException("loginStep is null in context");
	    }
	  
	   
        loginObj.user_clicks_login_button_after_entering_valid_credentials_in_login_page();       
//	    context.loginStep.user_clicks_login_button_after_entering_valid_credentials_in_login_page();
	    logger.info("Performed login with valid credentials");
	}


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
        	 logger.error("SCENARIO FAILED: {}", scenario.getName());
             logger.error("FAILURE STATUS: {}", scenario.getStatus());
            Screenshot.takeScreenshot(context.drfactory.getDriver(), scenario);
            LoggerLoad.info("Screenshot captured for failed scenario: " + scenario.getName());
        }

      //calling the instance method through the context
	    context.drfactory.closeDriver();
    }
	}
