package pageObject;

import java.util.List;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;

import utils.JSUtils;
import utils.LoggerLoad;
import utils.WaitUtils;

public class EditPatient_PageObject {

	private WebDriver driver;
	private WaitUtils waitUtils;
	
	private JSUtils jsUtils;

	// PageObject of My Patient page and EditWindow
	
	@FindBy (xpath="//a[text()='My Patients']") 
	private WebElement myPatientLink;
	
	@FindBy(id = "My Patients")
	private WebElement MyPatientPageTitle;
	
	@FindBy(xpath="//table//th")
	private List<WebElement> MyPatientTableHeaders;
	
	@FindBy(xpath="//table//th//tr[2]//td")
	private WebElement patientId;
		
	@FindBy(xpath="//table//th//tr[2]//td[2]")
	private WebElement nameCell;
	
	@FindBy(xpath = "//table//th//tr[2]//td[3]")
	private WebElement patientDetailsCell;
	
	@FindBy(xpath = "//span[text()='Edit Patient Details']")
	private WebElement editPatientDetails;

	@FindBy(id = "edit Patient Details")
	private WebElement editWindowTitle;

	@FindBy(css = "#editPatient")
	private WebElement editGreenButton;

	@FindBy(id = "firstName")
	private WebElement firstNamefield;

	@FindBy(id = "lastName")
	private WebElement lastNamefield;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "contactNumber")
	private WebElement contactNumber;

	@FindBy(xpath = "//select[@id='cuisineCategory']")
	private WebElement cuisineCategoryDropDown;

	@FindBy(xpath = "//select[@id='allergies']")
	private WebElement allergiesDropDown;

	@FindBy(xpath = "//select[@id='foodPreference']")
	public WebElement FoodPreferenceDropDown;

	@FindBy(xpath = "//input[@id='dob']")
	private WebElement dateOfBirth;

	@FindBy(xpath = "//select[@class='year']")
    private WebElement yearDropdown;
	
	@FindBy(xpath = "//select['month']")
    private WebElement monthDropdown;
	
	@FindBy(xpath = "//select['disabled']")
    private WebElement futureDatearrow;
	
	@FindBy(xpath = "//table[@class//td/a")
	private List<WebElement> allDaysDatepicker;
	
	@FindBy(id = "Vitals")
	private WebElement vitalsText;

	@FindBy(xpath = "//span[@style]")
	private WebElement redAsterisk;

	@FindBy(xpath = "//input['Weight')]")
	public WebElement Weightfield;

	@FindBy(xpath = "//input['height')]")
	private WebElement Heightfield;

	@FindBy(xpath = "//input['temperature']")
	private WebElement temperaturefield;

	@FindBy(xpath = "//input['SP')]")
	private WebElement SPField;

	@FindBy(xpath = "//input['DP')]")
	private WebElement DPField;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement chooseFile;

	@FindBy(id = "No File Choosen")
	private WebElement NoFileChoosenText;

	@FindBy(id = "Upload Health Report ")
	private WebElement upLoadHealthReportText;

	@FindBy(xpath = "//a[contains(@href, '.pdf')]")
	private WebElement pdfFileLink;
	
	@FindBy(xpath = "//td[@id='record-no']") 
	private WebElement recordNumber;
	
	@FindBy(xpath = "//td[@id='condition']")
	private WebElement healthCondition;

	@FindBy(id = "submit")
	private WebElement submitButton;

	@FindBy(css = "#closeButton")
	private WebElement closeButton;

	@FindBy(xpath = "//div[@class,container']")
	private WebElement calenderWindow;

	@FindBy(xpath = "//table//tbody//tr//td")
	private WebElement editedPatientName;

	@FindBy(xpath = "//table//tbody//tr//td//button[@id='editPatient']")
	private WebElement EditPatientButton;

	@FindBy(id = "filterGlobal")
	private WebElement searchBar;

	@FindBy(xpath = "//div//input")
	private List<WebElement> editWindowInputFields;

	@FindBy(xpath = "//select]//input")
	private List<WebElement> NoOfDropdowns;
	
	@FindBy(xpath = "//a//report")
	private WebElement ViewTestReport;
	
	@FindBy(xpath = "//table//th//tr/td[5]")
	private WebElement vitalsViewReport;
	
	@FindBy(xpath = "//div[@id='errormsg']")
	private WebElement dobErrorMessage;
	
	@FindBy(xpath = "//mat-error")
	private List<WebElement> errorMessages;
	
	

	// -------------------------------------------------------------------------------------

	// Constructor

	public EditPatient_PageObject(WebDriver driver) {
		this.driver = driver;
		// 2. Initialize it here using the driver passed from the Step Definition
		this.waitUtils = new WaitUtils(driver);
		this.jsUtils = new JSUtils(driver);
		PageFactory.initElements(driver, this);

	}
    //---------------------------------------------------------------------------------------------------------------
	// Methods
	
	public void clickMypatient() {
		myPatientLink.click();
	}
	
	public void clickOnEditButton() {
		waitUtils.waitForClickable(EditPatientButton);

	}

	public String getTitleEditPatientWindow() {

		return waitUtils.getTextAfterVisibility(driver, editPatientDetails, "Edit Patient Window Title");

	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	//Validate elements in the edit popup window

	public void validateElementEditPopup(String scenarioName) {
		switch (scenarioName.toLowerCase()) {
		case "edit patient title":
			String title = editWindowTitle.getText();
			Assert.assertEquals(title, "Edit Patient Details", "Title Mismatch");
			break;
		case "submit button presence":
			Assert.assertTrue(submitButton.isDisplayed(), "Submit button not present");
			break;
		case "state of submit button":
			Assert.assertFalse(submitButton.isEnabled(), "Submit button should be disabled");
			break;
		case "close button presence":
			Assert.assertTrue(closeButton.isDisplayed(), "close button not present");
			break;
		case "state of close button":
			Assert.assertTrue(closeButton.isEnabled(), "Submit button should be enabled");
			break;
		case "9 input field":
			Assert.assertEquals(editWindowInputFields.size(), 9, "Count mismatch");
			break;
		case "3 dropdown count":
			Assert.assertEquals(NoOfDropdowns.size(), 3, "Count mismatch");
			break;
		case "firstname field":
			Assert.assertTrue(firstNamefield.isDisplayed());
			break;
		case "lastname field":
			Assert.assertTrue(lastNamefield.isDisplayed());
			break;
		case "email field":
			Assert.assertTrue(email.isDisplayed());
			break;
		case "contact no":
			Assert.assertTrue(contactNumber.isDisplayed());
			break;
		case "upload health report text":
			Assert.assertTrue(upLoadHealthReportText.isDisplayed());
			break;
		case "no file chosen text":
			Assert.assertEquals(NoFileChoosenText.getText(), "No file chosen");
			break;
		case "sub title vitals":
			Assert.assertEquals(vitalsText.getText(), "Vitals");
			break;
		case "no mandatory mark in vitals":
			Assert.assertFalse(isRedAsteriskDisplayed(), "Vitals should not have mandatory, but found");
			break;
		case "close button color":
			String color = closeButton.getCssValue("background-color");
			Assert.assertEquals(color, "rgba(255, 0, 0, 1)", "Color mismatch");
			break;
		case "date of birth":
			boolean actualDOB = dateOfBirth.isDisplayed();
			Assert.assertEquals(actualDOB, "DOB empty");
			break;
		case "weight":
			String weightplaceholder = Weightfield.getAttribute("placeholder");
			Assert.assertEquals(weightplaceholder, "Weight", "Placeholder mismatch");
			break;
		case "height":
			String heightplaceholder = Heightfield.getAttribute("placeholder");
			Assert.assertEquals(heightplaceholder, "Height", "Placeholder mismatch");
			break;
		case "temperature":
			String tempPlaceholder = temperaturefield.getAttribute("placeholder");
			Assert.assertEquals(tempPlaceholder, "Temperature", "Placeholder mismatch");
			break;
		case "sp field":
			String spFieldPlaceholder = SPField.getAttribute("placeholder");
			Assert.assertEquals(spFieldPlaceholder, "SP", "Placeholder mismatch");
			break;
		case "dp field":
			String dpFieldPlaceholder = DPField.getAttribute("placeholder");
			Assert.assertEquals(dpFieldPlaceholder, "DP", "Placeholder mismatch");
			break;
		case "prevent future date DOB":
			boolean futureDate = futureDatearrow.isDisplayed();
			Assert.assertEquals(futureDate, "future date", "future date disabled");
			break;
			

		default:
			throw new IllegalArgumentException("Unknown element: " + element);
		}
	}

	public boolean isRedAsteriskDisplayed() {
		try {

			return redAsterisk.isDisplayed();
		} catch (NoSuchElementException e) {

			return false;
		}
	}

	//--------------------------------------------------------------------------------------------------------
	
	// Validate dropdown populate upon clicking
	
	public Boolean checkDropdownPopulated(String element) {
		WebElement dropdown;

		// pick dropdown

		switch (element.toLowerCase()) {
		case "allergy info":
			dropdown = allergiesDropDown;
			
			break;
		case "food preference":
			dropdown = FoodPreferenceDropDown;
			break;
		case "cuisine preference":
			dropdown = cuisineCategoryDropDown;
			break;
		default:
			throw new IllegalArgumentException("dropdown not found: " + element);
		}
		
		waitUtils.waitForVisibility(driver, dropdown, 10);
		dropdown .click();
		
		return dropdown.isDisplayed();
        

	}
	 
	
	//--------------------------------------------------------------------------------------------------------------
	
	
	public boolean isSubmitButtonDisplayed() { return submitButton.isDisplayed();
	  }
	  
	  public int getInputFieldCount() { return editWindowInputFields.size(); }
	  public boolean presenceOfCloseButton() { return closeButton.isDisplayed(); }
	  
	  public String getCloseButtonColor() { return
	  closeButton.getCssValue("color"); }
	  
	  public boolean isSubmitButtonEnabled() { submitButton.isEnabled(); return
	  false; }
	  
	  public boolean iscloseButtonEnabled() { closeButton.isEnabled(); return
	  false; }
	 

     

//--------------------------------------------------------------------------------------------------------------------

// Validate placeHolder
	  
	  public String placeholderFieldEditPopup(String element) {
		  WebElement field;

			// pick dropdown

			switch (element.toLowerCase()) {
			case "First Name Field":
				field = firstNamefield;
				break;
			case "Last Name Field":
				field = lastNamefield;
				break;
			case "Email Field":
				field = email;
				break;
			case "Contact Number Field":
				field = contactNumber;
				break;
			default:
				throw new IllegalArgumentException("dropdown not found: " + element);
			}

			waitUtils.waitForVisibility(driver, field, 5);
			field.clear();
			return field.getDomAttribute("placeholder");
		}
	  
//-------------------------------------	------------------------------------------------------------------------------
	  
	  //Edit valid data in edit window fields 
	  
	  
	  
	  public void searchForPatienName(String patientName) {
		  
		  waitUtils.waitForVisibility(driver, searchBar, 5); 
		  searchBar.sendKeys(patientName);
	  }
	 
		  public void clearEnterAndSubmitValidData(String scenarioName, String value) {
			    WebElement editField;

			    switch (scenarioName.toLowerCase()) {
			        case "ValidFirst Name": 
			        	editField = firstNamefield; 
			        	break;
			        case "ValidLast Name":  
			        	editField = lastNamefield;  
			        	break;
			        case "ValidEmail":      
			        	editField = email;    
			        break;
			        case "ValidContact No": 
			        	editField = contactNumber; 
			        	break;
			        case "ValidWeight":     
			        	editField = Weightfield;    
			        	break;
			        case "ValidHeight":     
			        	editField = Heightfield;    
			        break;
			        case "ValidTemperature":       
			        	editField = temperaturefield;     
			        	break;
			        case "ValidSP":         
			        	editField = SPField;        
			        break;
			        case "ValidDP":         
			        	editField = DPField;        
			        	break;
			        default:
			            throw new IllegalArgumentException("Unknown field: " + scenarioName);
			    }

			    
			    waitUtils. waitForVisibility(driver, editField, 5);
			    editField.clear();
			    editField.sendKeys(value);

			    jsUtils.clickElement(submitButton);
			    
			}
		  
		  
		public String redirectedToMyPatientsPage() {
			
			waitUtils.waitForVisibility(driver, MyPatientPageTitle, 10);
			return MyPatientPageTitle.getText();
			
			
		}
		  
		  
	//-------------------------------------------------------------------------------------------------------------------	  
		public int getColumnIndex(String columnName) {
			int index = -1;
		    
		    for (int i = 0; i < MyPatientTableHeaders.size(); i++) {
		        String headerText = MyPatientTableHeaders.get(i).getText().trim();
		        
		        // Use contains or equalsIgnoreCase to match the column name
		        if (headerText.equalsIgnoreCase(columnName)) {
		            index = i + 1; // XPath is 1-indexed, Java List is 0-indexed
		            break;
		        }
		        
		    }
			return index;
			
		}
		
		
		public String getNameTextPatient(String patientName) {		
			searchForPatienName(patientName);
		    return nameCell.getText();
		}
		public String getDetailsTextOfPatient(String patientName) {		
		    return patientDetailsCell.getText();
		}
		
		public void getPatientId(String patientName) {		
			searchForPatienName(patientName);
		     patientId.getText();
		     return;
		}
		public void viewPatientTestReport() {
			waitUtils.getVisibleText(driver, ViewTestReport, 5);
			jsUtils.clickElement(ViewTestReport);
		    
		}
		
		public String vitalsDetailsOfViewTestReport() {		
		    return vitalsViewReport.getText();
		}
		
  //-----------------------------------------------------------------------------------------------------------------
		
	//--Invalid Data for edit popup window------------	
		
		
		 public void ValidateEditWindowFieldsInvalidDataAndSubmit(String scenarioName, String value) {
			    WebElement editField;

			    // 1. Identify the element based on the 'Field' column in Excel
			    switch (scenarioName.toLowerCase()) {
			        case "FirstName with numeric": 
			        	editField = firstNamefield; 
			        	break;
			        case "FirstName with spl Char and Numeric":  
			        	editField = firstNamefield;  
			        	break;
			        case "FirstName with spl Char":      
			        	editField = firstNamefield;    
			        break;
			        case "LastName with numeric": 
			        	editField = lastNamefield; 
			        	break;
			        case "LastName with spl Char and Numeric":     
			        	editField = lastNamefield;    
			        	break;
			        case "LastName with spl Char":     
			        	editField = lastNamefield;    
			        break;
			        case "Email with invalid format":       
			        	editField = email;     
			        	break;
			        case "Email with spl char":         
			        	editField = email;        
			        break;
			        case "Email with missing @":         
			        	editField = email;        
			        	break;
			        case "Email with empty field":     
			        	editField = email;    
			        	break;
			        case "ContactNumber with alphabets":     
			        	editField = contactNumber;    
			        break;
			        case "ContactNumber with spl Char":       
			        	editField = contactNumber;     
			        	break;
			        case "ContactNumber with less Digits":         
			        	editField = contactNumber;        
			        break;
			        case "ContactNumber as Empty":         
			        	editField = contactNumber;        
			        	break;
			        case "Weight with Alphabets":     
			        	editField = Weightfield;    
			        	break;
			        case "Weight with spl char":     
			        	editField = Weightfield;    
			        break;
			        case "Height with Alphabets":       
			        	editField = Heightfield;     
			        	break;
			        case "Height with spl char":         
			        	editField = Heightfield;        
			        break;
			        case "Temp with Alphabets":         
			        	editField = temperaturefield;        
			        	break;
			        case "Temp with spl char":     
			        	editField = temperaturefield;    
			        break;
			        case "SP with spl char":       
			        	editField = temperaturefield;     
			        	break;
			        case "SP with Alphabets":         
			        	editField = SPField;        
			        break;
			        case "Only SP":         
			        	editField = SPField;  
			        	waitUtils.waitForVisibility(driver, DPField, 5);
			            DPField.clear();
			        	break;
			        case "DP with spl char":     
			        	editField = DPField;    
			        break;
			        case "DP with Alphabets":       
			        	editField = DPField;     
			        	break;
			        case "only DP":         
			        	editField = SPField; 
			        	waitUtils.waitForVisibility(driver, DPField, 5);
			            DPField.clear();
			        break;
			        	
			        default:
			            throw new IllegalArgumentException("Unknown field: " + scenarioName);
			    }

			    
			    waitUtils. waitForVisibility(driver, editField, 5);
			    editField.clear();
			    editField.sendKeys(value);
			    jsUtils.clickElement(submitButton);
			    waitUtils.getActualErrorMessage(errorMessages);
			    
			}	
		 
		 public String getErrorMessages() {
			 return waitUtils.getActualErrorMessage(errorMessages);
		 }
		 
		 
		 
		 
		
	//-----------------------------------------------------------------------------------------------------------------------
		 
		 public void clickDOBField() {
			 dateOfBirth.click();
		    }
		
		 public boolean isDatePickerDisplayed() {
		        // Use your WaitUtils to ensure the element is actually there
		        return calenderWindow.isDisplayed();
		    }
		
		
			public void selectDateFromPicker(String date) {
			   
			    String[] dateParts = date.split("/");
			    String month = dateParts[0];
			    String day = Integer.toString(Integer.parseInt(dateParts[1])); 
			    String year = dateParts[2];

     		    Select selectYear = new Select(yearDropdown);
			    selectYear.selectByVisibleText(year);
			    
			    Select selectMonth = new Select(monthDropdown);
			    int monthIndex = Integer.parseInt(month) - 1; 
			    selectMonth.selectByIndex(monthIndex);
			   
			    for (WebElement dayElement : allDaysDatepicker) {
			        if (dayElement.getText().equals(day)) {
			            dayElement.click();
			            break;
			        }
			    }
			}
		
			public String getActualDOBErrorMessage() {
			    try {
			        // Use your WaitUtils to wait for visibility (usually 2-5 seconds)
			        WebElement visibleError = waitUtils.waitForVisibility(driver, dobErrorMessage, 5);
			        String actualText = visibleError.getText().trim();
			        
			        LoggerLoad.info("Actual message  " + actualText);
			        return actualText;
			        
			    } catch (Exception e) {
			        LoggerLoad.error("Error message element not found or not visible: " + e.getMessage());
			        return "No message displayed"; 
			    }
			}
			
	//--------------------------------------------------------------------------------------------------------------------------	
			
			public void submituponUploadFile(String PatientId) {
				getPatientId(PatientId);
				submitButton.click();
				
			}
			
		
		
			public String getReportDetailValue(String fieldName) {
			    WebElement element;
			    
			    switch (fieldName.toLowerCase().trim()) {
			        case "record number":
			            element = recordNumber;
			            break;
			        case "pdf file":
			            element = pdfFileLink;
			            // For a PDF, you might want the file name or the 'href' attribute
			            return waitUtils.waitForVisibility(driver, element, 5).getText();
			        case "upload date":
			            element = chooseFile;
			            break;
			        case "health condition":
			            element = healthCondition;
			            break;
			        default:
			            throw new IllegalArgumentException("Report field not recognized: " + fieldName);
			    }

			    waitUtils.waitForVisibility(driver, element, 5);
			    return element.getText().trim();
			}
			
			
		
}
		
		
		
		
		
		
		
		
		
		
		
	
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  
		  
		  
		  
		  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
		  
	  
























  

