package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPatient_PageObject {
	
	 WebDriver driver;
	 public AddPatient_PageObject(WebDriver driver) { 
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	@FindBy(xpath = "//h2[@class='dialog-title']")
	private WebElement dialogTitle;
	    
	@FindBy(id = "dialogBox")
	private WebElement dialogBox;
	 
	@FindBy(id = "newPatientBtn")
	private WebElement newPatientButton;
	
	@FindBy(xpath = "//input")
	private List<WebElement> inputFields;

	@FindBy(xpath = "//select")
	private List<WebElement> dropdowns;
	
	@FindBy(id = "patient_firstName")
    private WebElement firstName;

    @FindBy(id = "patient_lastName")
    private WebElement lastName;
    
    @FindBy(id = "patient_email")
    private WebElement email;

    @FindBy(id = "patient_contactnumber")
    private WebElement contact;

    @FindBy(id = "allergy")
    private WebElement allergyDropdown;

    @FindBy(xpath = "//select[@id='allergies']/option")  
    private List<WebElement> allergyOptions;
    
    @FindBy(id = "foodPreference")
    private WebElement foodDropdown;
    
    @FindBy(xpath = "//select[@id='foodPreference']/option")
    private List<WebElement> foodOptions;

    @FindBy(id = "cuisinecategory")
    private WebElement cuisineDropdown;
    
    @FindBy(xpath = "//select[@id='cuisineCategory']/option")
    private List<WebElement> cuisineOptions;
    
    @FindBy(id = "patient_dob")
    private WebElement dobField;
    
    @FindBy(id = "patient_weight")
    private WebElement weight;

    @FindBy(id = "patient_height")
    private WebElement height;

    @FindBy(id = "patient_temperature")
    private WebElement temperature;

    @FindBy(id = "patient_sp")   
    private WebElement sp;

    @FindBy(id = "patient_dp")   
    private WebElement dp;
    
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileUpload;
    
    @FindBy(xpath = "//input[@type='file']")
    private List<WebElement> fileUploads;

    @FindBy(id = "noFileChosenText")
    private WebElement noFileChosenText;

    @FindBy(id = "submitBtn")
    private WebElement submitBtn;

    @FindBy(id = "closeBtn")
    private WebElement closeBtn;
   
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement successToast;

    @FindBy(xpath = "//h1[contains(text(),'My Patient')]")
    private WebElement myPatientPage;
    
    @FindBy(name = "dobError")
    private WebElement dobError;
    
    @FindBy(xpath = "//input[@id='firstName']/following-sibling::span[@class='error']")
    private WebElement firstnameError;
    
    @FindBy(xpath = "//input[@id='lastName']/following-sibling::span[@class='error']")
    private WebElement lastnameError;
    
    @FindBy(xpath = "//span[@class='emailerror'][contains(text(),'email')]")  
    private WebElement emailError;
    
    @FindBy(id = "chooseFile")
    private WebElement chooseFileButton;

    @FindBy(id = "fileUpload")  
    private WebElement fileUploadInput;
    
    @FindBy(xpath = "//div[@class='toast-message']") 
    private List<WebElement> messageText;
    
    public void clickNewPatientButton() {
        newPatientButton.click();
    }

    public String getDialogTitle() {
        return dialogTitle.getText();
    }

    public boolean isDialogDisplayed() {
        return dialogBox.isDisplayed();
    }

  
   }
