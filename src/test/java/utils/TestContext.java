
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
	//public Login_Step loginObj;
	
	
	public TestContext() {
		
		drfactory = new DriverFactory();
//		poManager = new PageObjectManager(drfactory.getDriver());
//		loginStep = new Login_Step();
		
	}
	
	 public void initPageObjects() {
	        poManager = new PageObjectManager(drfactory.getDriver());
	    }	
	
	
}
