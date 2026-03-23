package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PageObject {
	
	@FindBy (xpath="//nav[@class='navbar']/*[text()='Dietician Project']") WebElement dieticianPjt;
	@FindBy () WebElement homeIcon;
	@FindBy () WebElement navBar;
	@FindBy () WebElement dieticianAppln;
	@FindBy () WebElement usernameLabel;
	@FindBy () WebElement passswordLabel;
	@FindBy () WebElement usernameField;
	@FindBy () WebElement passwordField;
	@FindBy () WebElement loginBtn;
	@FindBy () List<WebElement> inputFields;


}
