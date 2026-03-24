package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_PageObject {
	
	private WebElement element;
	
	@FindBy (xpath="//nav[@class='navbar']//a") 
	private List<WebElement> navigationLinks;
	
	@FindBy (xpath="//a[text()='My Patients']") 
	private WebElement myPatientLink;
	
	@FindBy (xpath="//a[text()='New Patients']") 
	private WebElement newPatientLink;
	
	@FindBy (xpath="//a[text()='Logout']")
	private WebElement logoutLink;
	
	@FindBy (xpath = "//nav[@class='navbar']/*[@class='homeIcon']") 
	private WebElement homeIcon;

	public void clickElement(String field) {
		switch (field) {
		case "My Patients link":
			element = myPatientLink;
			break;
		case "New Patient link":
			element = newPatientLink;
			break;
		case "Logout link":
			element = logoutLink;
			break;
		case "home icon":
			element = homeIcon;
			break;
		}
		element.click();
	}
	
	public void getPageUrl(String page) {
//		String url = driver.getCurrentUrl();
//		return url;
	}
}
