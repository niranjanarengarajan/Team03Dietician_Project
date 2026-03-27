package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.LoggerLoad;
import utils.WaitUtils;

public class DeletePatient_PageObject {
	
	//Class Variables
	
	private WebDriver driver;
	WaitUtils waitUtils;
	
	
	
	//Delete Patients PageObjects
	
		@FindBy(xpath ="//table/tbody/tr[1]/td/button")
		private WebElement deleteIcon;
		
		@FindBy (css="#deletePatient") 
		private WebElement DeleteRedButton;
		
		@FindBy (xpath = "//span[text()='confirm']")
		private  WebElement confirmAlert;
		
		@FindBy (xpath = "//span[text()='Are you sure to delete Patient Name ?']")
		private  WebElement AlertText;
		
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

		@FindBy(id = "filterGlobal")
		private WebElement searchBar;
//--------------------------------------------------------------------------------------------------

        //Constructor

		public DeletePatient_PageObject(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		PageFactory.initElements(driver, this);
			}


     	//Methods
		
		public void ConfirmAlertPresent() {
			String confirmAlertTitle = confirmAlert.getText();
			LoggerLoad.info("cofirm Alert present");
			
		}
		
		public void confirmAlertTextPresent(String patientName) {
			
			String xpath = "//span[text()='Are you sure to delete" +patientName+ " ?']";
			driver.findElement(By.xpath(xpath)).getText();
			
			LoggerLoad.info("Are you sure to delete" +patientName+" ?");
		}
		
		
		

        public void clickOnDeletebuttonOnpatientPage() {
        	deleteIcon.click();
        	
        }
        
        public boolean alertInfoAndButtonVisibity(String scenarioName) {
  		  WebElement elements;

  			// pick dropdown

  			switch (scenarioName.toLowerCase()) {
  			case "Alert Title":
  				elements = confirmAlert;
  				break;
  			case "Alert text":
  				elements = AlertText;
  				break;
  			case "Yes button":
  				elements = yesbutton;
  				break;
  			case "No button":
  				elements = Nobutton;
  				break;
  			default:
  				throw new IllegalArgumentException("elements not found: " + scenarioName);
  			}

  			waitUtils.waitForVisibility(driver, elements, 5);
  			elements.isDisplayed();
  			return elements.isDisplayed() ;
  		}
        
        
        

        public void deletePatientDetailFromTable() {
        	deleteIcon.click();
        	yesbutton.click();
        	String PatientDeleted = Successmessage2.getText();
        	LoggerLoad.info("patient deleted");
        }


        public void performDeleteAction(String action) {
            
            waitUtils.waitForVisibility(driver, confirmAlert, 5);

           
            if (action.equalsIgnoreCase("Confirm")) {
            	yesbutton.click();
                LoggerLoad.info("Clicked 'Yes' to confirm deletion.");
            } else if (action.equalsIgnoreCase("Cancel") || action.equalsIgnoreCase("Cancle")) {
                // Handling the typo in your example table ("Cancle")
            	Nobutton.click();
                LoggerLoad.info("Clicked 'No' to cancel deletion.");
            } else {
                throw new IllegalArgumentException("Invalid deletion action: " + action);
            }
        }


        public boolean isPatientNamePresent(String patientName) {
            try {
                
                String xpath = "//tr[td[2][contains(text(),'" + patientName + "')]]";
                return driver.findElement(By.xpath(xpath)).isDisplayed();
            } catch (Exception e) {
                
                return false;
            }
        }




























	
}
