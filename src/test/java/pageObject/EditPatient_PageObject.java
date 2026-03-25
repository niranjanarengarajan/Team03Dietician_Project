package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPatient_PageObject {
	
	
	private WebDriver driver;
	
	
	//PageObject of My Patient page
	
	@FindBy (css="#editPatient") 
	private WebElement editGreenButton;
	@FindBy (xpath= "//span[text()='Patient Details']") 
	private WebElement AddPatientDetails;
	@FindBy (id="firstName") 
	private WebElement firstNamefield;
	@FindBy (id="lastName") 
	private WebElement lastNamefield;
	@FindBy (id="email") 
	private WebElement email;
	@FindBy (id="contactNumber") 
	private WebElement contactNumber;
	@FindBy (xpath= "//select[@id='cuisineCategory']") 
	private WebElement cuisineCategoryDropDown;
	@FindBy (xpath= "//select[@id='allergies']") 
	private WebElement allergiesDropDown;
	@FindBy (xpath= "//select[@id='foodPreference']") 
	public WebElement FoodPreferenceDropDown;
	@FindBy (xpath= "//select[@id='dob']") 
	private WebElement DatePicker;
	//@FindBy (xpath= "//div[text()='" + year + "']") 
	private WebElement YearDatePicker;
	//@FindBy (xpath= "//div[text()='" + month + "']") 
	private WebElement MonthDatePicker;
	@FindBy (xpath= "//label[contains(text(),'Weight')]") 
	public WebElement Weightfield;
	@FindBy (xpath= "//label[contains(text(),'height')]") 
	private WebElement Heightfield;
	@FindBy (xpath= "//label[contains(text(),'temperature')]") 
	private WebElement temperaturefield;
	@FindBy (xpath= "//input[@type='file']") 
	private WebElement chooseFile;
	@FindBy (xpath= "//span[@id='uploaded-file-name']") 
	private WebElement FileTypeDisplay;
	@FindBy (xpath= "//a[contains(@href, '.pdf')]") 
	private WebElement pdfFile;
	@FindBy (id = "submit") 
	private WebElement submitButton;
	@FindBy (id = "close") 
	private WebElement closeButton;
	@FindBy(xpath = "//div[contains(@class,'p-datepicker-group-container']") 
	private WebElement Calender_window;
	@FindBy (xpath="//table//tbody//tr//td[2]") 
	private WebElement editedPatientName;
	@FindBy (xpath ="//table//tbody//tr//td//button[@id='editPatient']") 
	private WebElement EditPatientButton;
	@FindBy (id = "filterGlobal") 
	private WebElement searchBar;
	
	//-------------------------------------------------------------------------------------
	
	
    //Constructor
	
	public EditPatient_PageObject(WebDriver driver) {
	this.driver = driver;	
	PageFactory.initElements(driver, this);
		
	}
	
	//Methods
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
