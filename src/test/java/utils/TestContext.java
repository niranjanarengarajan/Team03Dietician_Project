package utils;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import driverManager.DriverFactory;
import pageObject.PageObjectManager;
import stepDefinitions.Login_Step;

public class TestContext {

	public WebDriver driver;
	public static Map<String, String> testData;
	public DriverFactory drfactory;
	public PageObjectManager poManager;
	public Login_Step loginStep;
	public WaitUtils waits;
	public JSUtils jsUtils;
//	public AddPatient_Step addPatient;

	public TestContext() {
		drfactory = new DriverFactory();		
	}
	public void setupManagers() { 
		WebDriver driver = drfactory.getDriver();
	     this.poManager = new PageObjectManager(driver);
		 this.waits = new WaitUtils(driver);
		 this.loginStep = new Login_Step(this);
//		 this.addPatient = new AddPatient_Step(this);
		 this.jsUtils = new JSUtils(driver);
			}
	 

}