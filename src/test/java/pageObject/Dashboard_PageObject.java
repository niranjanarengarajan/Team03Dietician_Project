package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_PageObject {
	
	@FindBy () List<WebElement> navigationLinks;
	@FindBy () WebElement myPatientLink;
	@FindBy () WebElement newPatientLink;
	@FindBy () WebElement logoutLink;
	@FindBy () WebElement homeIcon;


}
