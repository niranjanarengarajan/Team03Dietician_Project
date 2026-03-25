package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePatient_PageObject {
	
	//Class Variables
	
	private WebDriver driver;
	
	
	
	
	//Delete Patients PageObjects
	
		@FindBy(xpath ="//table/tbody/tr[1]/td/button")
		private WebElement deleteIcon;
		@FindBy (css="#deletePatient") 
		private WebElement DeleteRedButton;
		@FindBy (xpath = "//span[text()='confirm']")
		private  WebElement confirmAlert;
	    @FindBy(xpath = "//span[text()='Yes']")
	    private  WebElement yesbutton;
		@FindBy(xpath = "//span[text()='No']")
		private  WebElement Nobutton;
	    @FindBy(xpath="//button[@id='close']")
		private  WebElement closebutton;
		@FindBy(xpath = "//div[text()='Successful']")
		private  WebElement Successmessage1;
		@FindBy(xpath = "//div[text()='patient deleted']")
		private  WebElement Successmessage2;

//--------------------------------------------------------------------------------------------------

        //Constructor

		public DeletePatient_PageObject(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
			}


     	//Methods







































	
}
