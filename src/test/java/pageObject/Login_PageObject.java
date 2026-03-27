package pageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.LoggerLoad;
import utils.WaitUtils;

public class Login_PageObject {
	

	private WebDriver driver;
	private WebElement element;
	private WaitUtils waitUtils;
	
	public Login_PageObject(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//nav[@class='navbar']/*[text()='Dietician Project']") 
	private WebElement dieticianPjt;
	
	@FindBy (xpath = "//nav[@class='navbar']/*[@class='homeIcon']") 
	private WebElement homeIcon;
	
	@FindBy (xpath="//nav[@class='navbar']")
	private WebElement navBar;
	
	@FindBy (xpath="//div[@class='login=box']/h1[text()='Dietician Application']") 
	private WebElement dieticianAppln;
	
	@FindBy (xpath="//label[@for='username']") 
	private WebElement usernameLabel;
	
	@FindBy (xpath="//label[@for='password']") 
	private WebElement passswordLabel;
	
	@FindBy (id="username") 
	private WebElement usernameField;
	
	@FindBy (id="password") 
	private WebElement passwordField;
	
	@FindBy (xpath="//input[@value='Login']") 
	private WebElement loginBtn;
	
	@FindBy (xpath="//input") 
	private List<WebElement> inputFields;
	
	@FindBy (xpath="//div[contains(text(),'Invalid') or contains(text(),'Required')]")
	private WebElement errorMsg;

	public String elementLeftAligned(String scenario) {
		switch (scenario) {
		case "Navigation bar text":
			element = dieticianPjt;
			break;
		case "Navigation bar home icon":
			element = homeIcon;
			break;
		}
		String align = element.getCssValue("text-align");
		return align;
	}
	
	public String getBackgroundColor(String scenario) {
		switch (scenario) {
		case "Navigation bar background":
			element = navBar;
			break;
		case "Login button":
			element = loginBtn;
			break;
		}
		String color = element.getCssValue("background-color");
		return color;
	}
	
	public String getTextColor(String field) {
		element = loginBtn;
		String color = element.getCssValue("color");
		return color;
	}
	
	public boolean isDisplayed(String field) {
		switch (field) {
		case "Dietician Application":
			element = dieticianAppln;
			break;
		case "Username input field":
			element = usernameField;
			break;
		case "Password input field":
			element = passwordField;
			break;
		case "Login button":
			element = loginBtn;
			break;
		}
		return element.isDisplayed();
	}
	
	public String getText(String field) {
		String text;
		switch (field) {
		case "first field":
			element = usernameLabel;
			break;
		case "second field":
			element = passswordLabel;
			break;
		case "Error":
			element = errorMsg;
			break;
		}
		text = element.getText();
		return text;
	}
	
	public int inputFieldCount() {
		int count = inputFields.size();
		return count;
	}
	
	public boolean isEnabled() {
		element = loginBtn;
		return element.isEnabled();
	}
	
	public void clickLoginBtn() {
		waitUtils.waitForClickable(loginBtn).click();
	}
	
	public void enterUsername(String name) {
		try {
			usernameField.sendKeys(name);
		}
		catch (Exception e){
			throw new RuntimeException("Failed to type into the Username field. Error: " + e.getMessage());
		}
	}
	
	public void enterPassword(String password) {
		try {
			passwordField.sendKeys(password);
		}
		catch (Exception e){
			throw new RuntimeException("Failed to type into the password field. Error: " + e.getMessage());
		}
	}
	
	public String getPageUrl() {
		String url=null;
	        url = driver.getCurrentUrl();
		return url;
	}
	
	public void browserIsOpen() {
		LoggerLoad.info("Checking if browser is initialized.");
		if (driver == null) {
			LoggerLoad.error("WebDriver is not initialized.");
			throw new IllegalStateException("WebDriver is not initialized");
		}
		LoggerLoad.info("WebDriver is active.");
	}
	
	public boolean usernamePasswordLeftAligned() {
		String align1 = usernameLabel.getCssValue("text-align");
		String align2 = passswordLabel.getCssValue("text-align");
		if((align1.contains("left")) && (align2.contains("left"))){
			return true;
		}
		return false;
	}
}
