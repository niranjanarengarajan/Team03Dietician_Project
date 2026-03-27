package pageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard_PageObject {
	
	private WebElement element;
	private WebDriver driver;
	
	public Dashboard_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//nav[@class='navbar']//a") 
	private List<WebElement> navigationLinks;
	
	@FindBy (xpath="//a[text()='My Patients']") 
	private WebElement myPatientLink;
	
	@FindBy (xpath="//a[text()='New Patients']") 
	private WebElement newPatientLink;
	
	@FindBy (xpath="//a[text()='Login']")
	private WebElement loginLink;
	
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
	
	public String getPageUrl() {
		String url = null;
		url = driver.getCurrentUrl();
		return url;
	}
	
	public boolean linksCount(String myPat, String newPat, String logout, String login) {
		int count = navigationLinks.size();
		if(myPatientLink.getText().equals(myPat) &&
		   newPatientLink.getText().equals(newPat) &&
		   logoutLink.getText().equals(logout) &&
		   loginLink.getText().equals(login) &&
		   (count==4)){
			return true;
		}
		return false;
	}
}
