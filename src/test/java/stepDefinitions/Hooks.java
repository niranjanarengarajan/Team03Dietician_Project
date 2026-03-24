package stepDefinitions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import driverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.Screenshot;

public class Hooks {
    private WebDriver driver;

    @Before(order=0)
    public void setup() {
        LoggerLoad.info("Initializing test setup...");
        
        Properties prop = ConfigReader.initializeProperties();
        LoggerLoad.info("Loaded configuration properties");        
       
        ExcelReader.readDataFromExcel(prop.getProperty("loginsheetName"));
        ExcelReader.readDataFromExcel(prop.getProperty("AddPatientSheetName"));        
        
        DriverFactory.launchBrowser(prop.getProperty("browserName"));
        DriverFactory.getDriver().get(prop.getProperty("baseURL"));
        LoggerLoad.info("Navigated to base URL: " + prop.getProperty("baseURL"));
    }

    @Before(value = "@Login", order = 1)
    public void performLogin() {
        Login_Step login_Step = new Login_Step();
        login_Step.user_clicks_login_button_after_entering_valid_credentials_in_login_page();
        LoggerLoad.info("Performed login with valid credentials");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Screenshot.takeScreenshot(DriverFactory.getDriver(), scenario);
            LoggerLoad.info("Screenshot captured for failed scenario: " + scenario.getName());
        }

        if (DriverFactory.getDriver() != null) {
            LoggerLoad.info("Tearing down WebDriver and closing browser");
            DriverFactory.getDriver().quit();
            DriverFactory.mydriver.remove();
            LoggerLoad.info("Driver removed from ThreadLocal");
        }
    }
}