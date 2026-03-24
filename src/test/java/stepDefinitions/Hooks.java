package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import driverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;
import utils.ExcelReader;


public class Hooks {
	private WebDriver driver;
	
	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

	@Before
	public void setup() {
		
		logger.info("Initializing test setup...");
		Properties prop = ConfigReader.initializeProperties();
		logger.debug("Loaded configuration properties");
		ExcelReader.readDataFromExcel(prop.getProperty("loginsheetName"));

		
	}

    
	@After
	public void tearDown() {
			if (DriverFactory.getDriver() != null) {
				logger.info("Tearing down WebDriver and closing browser");
				DriverFactory.getDriver().quit();
				DriverFactory.mydriver.remove();
				logger.info("Driver removed from ThreadLocal");
			}
		}
	
	
	
	

}
