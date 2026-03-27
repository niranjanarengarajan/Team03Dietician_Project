package stepDefinitions;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;
import utils.Screenshot;
import runner.TestRunner;


public class Hooks {
	private WebDriver driver;
	private TestContext context;

	public Hooks(TestContext context) {
		this.context = context;
	}

	@Before(order = 0)
	public void setup() {

		LoggerLoad.info("Initializing test setup...");
		LoggerLoad.info("Starting Hooks setup");

		Properties prop = ConfigReader.initializeProperties();
		LoggerLoad.info("Loaded configuration properties");

		String browser = null;

		browser = System.getProperty("browser"); // Maven

		if (browser == null || browser.isEmpty()) { // TestNG
			browser = TestRunner.browserName.get();
		}

		if (browser == null || browser.isEmpty()) { // default from config
			browser = prop.getProperty("browserName");
		}

		LoggerLoad.info("Launching browser: " + browser);
		context.drfactory.launchBrowser(browser);
		context.setupManagers();
		context.drfactory.getDriver().get(prop.getProperty("baseURL"));
		LoggerLoad.info("Navigated to base URL: " + prop.getProperty("baseURL"));

		ExcelReader.readDataFromExcel(prop.getProperty("loginsheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("dashboardsheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("AddPatientSheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("editPatientSheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("reportsheetName"));
		ExcelReader.readDataFromExcel(prop.getProperty("MyPatientsSheetName"));

	}
	@Before(value = "@Login", order = 1)
    public void performLogin() {
		LoggerLoad.info("Performing background login...");
		context.loginStep.user_clicks_login_button_after_entering_valid_credentials_in_login_page("valid credentials");
		LoggerLoad.info("Performed login with valid credentials");

	}
	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			Screenshot.takeScreenshot(context.drfactory.getDriver(), scenario);
			LoggerLoad.info("Screenshot captured for failed scenario: " + scenario.getName());
		}
		context.drfactory.closeDriver();
	}
}
