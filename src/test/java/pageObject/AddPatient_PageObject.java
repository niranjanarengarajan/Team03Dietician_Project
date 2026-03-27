package pageObject;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.ExcelReader;
import utils.JSUtils;
import utils.LoggerLoad;
import utils.TestContext;
import utils.WaitUtils;

public class AddPatient_PageObject {

	WebDriver driver;
	private WaitUtils waitUtils;
	private JSUtils jsUtils;
	TestContext context;

	public AddPatient_PageObject(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
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

	@FindBy(id = "foodPreference")
	private WebElement foodDropdown;

	@FindBy(id = "cuisinecategory")
	private WebElement cuisineDropdown;

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

	@FindBy(id = "chooseFile")
	private WebElement chooseFileButton;

	@FindBy(xpath = "//div[@class='toast-message']")
	private List<WebElement> messageText;

	public boolean isHomePageDisplayed() {
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			waitUtils.waitForVisibility(driver, dialogTitle, 10);
			return newPatientButton.isDisplayed();
		} catch (TimeoutException e) {
			LoggerLoad.error("New Patient button not found in 10 seconds");
			return false;
		}
	}

	public void clickNewPatientButton() {
		waitUtils.waitForClickable(newPatientButton).click();
	}

	public String getDialogTitle() {
		waitUtils.waitForVisibility(driver, dialogTitle, 10);
		return dialogTitle.getText();
	}

	public boolean isDialogDisplayed() {
		waitUtils.waitForVisibility(driver, dialogBox, 10);
		return dialogBox.isDisplayed();
	}

	public boolean isDialogScrollable() {
		return jsUtils.hasScroll(dialogBox);
	}

	public int getInputFieldCount() {
		waitUtils.waitForVisibilityOfAll(inputFields);
		return inputFields.size();
	}

	public int getDropdownCount() {
		waitUtils.waitForVisibilityOfAll(dropdowns);
		return dropdowns.size();
	}

	public int getFileUploadCount() {
		waitUtils.waitForVisibilityOfAll(fileUploads);
		return fileUploads.size();
	}

	public boolean isPlaceholderDisplayed(String expected) {
		waitUtils.waitForVisibilityOfAll(inputFields);
		for (WebElement element : inputFields) {
			String placeholder = element.getAttribute("placeholder");

			if (placeholder != null && placeholder.equalsIgnoreCase(expected)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDropdownDisplayed(String expected) {
		waitUtils.waitForVisibilityOfAll(dropdowns);
		for (WebElement element : dropdowns) {
			String text = element.getText();

			if (text != null && text.contains(expected)) {
				return true;
			}
		}
		return false;
	}

	public boolean isTextPresent(String text) {
		waitUtils.waitForPageLoad();
		return driver.getPageSource().contains(text);
	}

	public boolean isDOBFormatDisplayed() {
		waitUtils.waitForVisibility(driver, dobField, 10);
		return dobField.getAttribute("placeholder").contains("MM/DD/YYYY");
	}

	public boolean isSubmitEnabled() {

		return waitUtils.isVisible(driver, submitBtn, 10) && submitBtn.isEnabled();
	}

	public boolean isSubmitDisplayed() {

		return waitUtils.isVisible(driver, submitBtn, 10);
	}

	public boolean isCloseEnabled() {

		return waitUtils.isVisible(driver, closeBtn, 10) && closeBtn.isEnabled();
	}

	public boolean isCloseDisplayed() {

		return waitUtils.isVisible(driver, closeBtn, 10);
	}

	public void enterValidPatientDetails() {
		type(firstName, "Abc");
		type(lastName, "Def");
		type(email, "abc.def@test.com");
		type(contact, "9876543210");

		selectByText(allergyDropdown, "Peanuts");
		selectByText(foodDropdown, "Vegan");
		selectByText(cuisineDropdown, "Indian");

		type(dobField, "01/12/2000");

		type(weight, "70");
		type(height, "5.3");
		type(temperature, "98");
		type(sp, "120");
		type(dp, "80");
		waitUtils.waitForVisibility(driver, fileUpload, 10);
		fileUpload.sendKeys("/path/to/file.pdf");
	}

	public void clickSubmit() {
		waitUtils.waitForClickable(submitBtn).click();
	}

	public void clickClose() {
		waitUtils.waitForClickable(closeBtn).click();
	}

	public boolean isMessageDisplayed(String message) {
		try {
			waitUtils.waitForVisibility(driver, successToast, 10);
			return successToast.getText().contains(message);
		} catch (Exception e) {
			return false;
		}
	}

	public String getSuccessMessage() {
		waitUtils.waitForVisibility(driver, successToast, 10);
		return successToast.getText();
	}

	public boolean isNavigatedToMyPatientPage() {

		return waitUtils.isVisible(driver, myPatientPage, 10);
	}

	private void selectByText(WebElement element, String value) {
		waitUtils.waitForVisibility(driver, element, 10);
		try {
			new Select(element).selectByVisibleText(value);
		} catch (NoSuchElementException e) {
		}
	}

	private void type(WebElement element, String value) {
		waitUtils.waitForVisibility(driver, element, 10);
		element.click();
		element.clear();
		element.sendKeys(value);
	}

	public boolean verifyElement(String elementType, String property, String value) {

		switch (elementType.toLowerCase()) {

		case "dialog":
			if (property.equalsIgnoreCase("title")) {
				return getDialogTitle().equals(value);
			} else if (property.equalsIgnoreCase("scrollbar")) {
				return jsUtils.hasScroll(dialogBox);
			}
			break;

		case "inputfield":
			if (property.equalsIgnoreCase("count")) {
				return getInputFieldCount() == Integer.parseInt(value);
			} else if (property.equalsIgnoreCase("placeholder")) {
				return isPlaceholderDisplayed(value);
			}
			break;

		case "dropdown":
			if (property.equalsIgnoreCase("count")) {
				return getDropdownCount() == Integer.parseInt(value);
			} else if (property.equalsIgnoreCase("placeholder")) {
				return isDropdownDisplayed(value);
			}
			break;

		case "datepicker":
			if (property.equalsIgnoreCase("format")) {
				return isDOBFormatDisplayed();
			} else if (property.equalsIgnoreCase("placeholder")) {
				return isPlaceholderDisplayed(value);
			}
			break;

		case "fileupload":
			if (property.equalsIgnoreCase("count")) {
				return getFileUploadCount() == Integer.parseInt(value);
			}
			break;

		case "button":
			if (property.equalsIgnoreCase("type")) {
				if (value.equalsIgnoreCase("submit")) {
					return isSubmitDisplayed();
				} else if (value.equalsIgnoreCase("close")) {
					return isCloseDisplayed();
				}
			} else if (property.equalsIgnoreCase("state")) {
				if (value.equalsIgnoreCase("submitdisabled")) {
					return !isSubmitEnabled();
				} else if (value.equalsIgnoreCase("closeenabled")) {
					return isCloseEnabled();
				}
			}
			break;

		case "text":
			return isTextPresent(value);
		}
		return false;
	}

	public void clickAllergyDropdown() {

		waitUtils.waitForClickable(allergyDropdown).click();
	}

	public void clickFoodDropdown() {

		waitUtils.waitForClickable(foodDropdown).click();
	}

	public List<String> getAllergyDropdownValues() {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);
		List<String> values = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
		return values;
	}

	public List<String> getFoodDropdownValues() {
		waitUtils.waitForVisibility(driver, foodDropdown, 10);
		Select select = new Select(foodDropdown);
		List<String> values = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
		return values;
	}

	public void selectAllergy(String value) {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);

		select.selectByVisibleText(value);
	}

	public void selectFoodPreference(String value) {
		waitUtils.waitForVisibility(driver, foodDropdown, 10);
		Select select = new Select(foodDropdown);

		select.selectByVisibleText(value);
	}

	public boolean isAllergyValuePresent(String value) {

		return getAllergyDropdownValues().contains(value);
	}

	public boolean isFoodValuePresent(String value) {

		return getFoodDropdownValues().contains(value);
	}

	public int getAllergyDropdownCount() {
		int count = getAllergyDropdownValues().size();

		return count;
	}

	public void clickCuisineDropdown() {

		waitUtils.waitForClickable(cuisineDropdown).click();
	}

	public List<String> getCuisineDropdownValues() {
		waitUtils.waitForVisibility(driver, cuisineDropdown, 10);
		Select select = new Select(cuisineDropdown);
		return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public boolean isCuisineValuePresent(String value) {

		return getCuisineDropdownValues().contains(value);
	}

	public void selectMultipleAllergies(String values) {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);
		List<String> items = Arrays.stream(values.split(",")).map(String::trim).collect(Collectors.toList());
		items.forEach(select::selectByVisibleText);
	}

	public boolean isAllergySelected(String value) {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);
		return select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals(value));
	}

	public void trySelectInvalidAllergy(String value) {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);
		List<String> options = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
		if (options.contains(value)) {
			select.selectByVisibleText(value);
		}
	}

	public boolean noAllergySelected() {
		waitUtils.waitForVisibility(driver, allergyDropdown, 10);
		Select select = new Select(allergyDropdown);
		return select.getAllSelectedOptions().isEmpty();
	}

	public void selectMultipleFoodPreferences(String values) {
		waitUtils.waitForVisibility(driver, foodDropdown, 10);
		Select select = new Select(foodDropdown);
		List<String> items = Arrays.stream(values.split(",")).map(String::trim).collect(Collectors.toList());
		items.forEach(select::selectByVisibleText);
	}

	public boolean isFoodSelected(String value) {
		waitUtils.waitForVisibility(driver, foodDropdown, 10);
		Select select = new Select(foodDropdown);
		return select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals(value));
	}

	public void trySelectInvalidFoodPreference(String value) {
		waitUtils.waitForVisibility(driver, foodDropdown, 10);
		Select select = new Select(foodDropdown);
		List<String> options = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
		if (options.contains(value)) {
			select.selectByVisibleText(value);
		}
	}

	public boolean noFoodSelected() {
		Select select = new Select(foodDropdown);
		return select.getAllSelectedOptions().isEmpty();
	}

	public void selectCuisineCategory(String value) {
		Select select = new Select(cuisineDropdown);
		select.selectByVisibleText(value);
	}

	public void selectMultipleCuisineCategories(String values) {
		waitUtils.waitForVisibility(driver, cuisineDropdown, 10);
		Select select = new Select(cuisineDropdown);
		List<String> items = Arrays.stream(values.split(",")).map(String::trim).collect(Collectors.toList());
		items.forEach(select::selectByVisibleText);
	}

	public boolean isCuisineSelected(String value) {
		waitUtils.waitForVisibility(driver, cuisineDropdown, 10);
		Select select = new Select(cuisineDropdown);
		return select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals(value));
	}

	public void trySelectInvalidCuisineCategory(String value) {
		waitUtils.waitForVisibility(driver, cuisineDropdown, 10);
		Select select = new Select(cuisineDropdown);
		List<String> options = select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
		if (options.contains(value)) {
			select.selectByVisibleText(value);
		}
	}

	public boolean noCuisineSelected() {
		waitUtils.waitForVisibility(driver, cuisineDropdown, 10);
		Select select = new Select(cuisineDropdown);
		return select.getAllSelectedOptions().isEmpty();
	}

	public void enterDOB(String dob) {
		waitUtils.waitForVisibility(driver, dobField, 10);
		dobField.click();
		dobField.clear();
		dobField.sendKeys(dob);
	}

	public void enterFieldValue(String field, String value) {
		WebElement element;
		switch (field.toLowerCase()) {
		case "first name":
			element = firstName;
			break;
		case "last name":
			element = lastName;
			break;
		case "weight":
			element = weight;
			break;
		case "height":
			element = height;
			break;
		case "temperature":
			element = temperature;
			break;
		case "sp":
			element = sp;
			break;
		case "dp":
			element = dp;
			break;
		default:
			throw new IllegalArgumentException("Unknown field: " + field);
		}
		waitUtils.waitForVisibility(driver, element, 10);
		element.click();
		element.clear();
		element.sendKeys(value);
	}

	public void navigateToNextField() {

		driver.switchTo().activeElement().sendKeys("\t");
	}

	public void enterEmail(String mail) {
		waitUtils.waitForVisibility(driver, email, 10);
		email.click();
		email.clear();
		email.sendKeys(mail);
	}

	public void enterContact(String number) {
		waitUtils.waitForVisibility(driver, contact, 10);
		contact.click();
		contact.clear();
		contact.sendKeys(number);
	}

	public void skipDropdown(String field) {
		WebElement element;
		switch (field.toLowerCase()) {
		case "allergies":
			element = allergyDropdown;
			break;
		case "food preference":
			element = foodDropdown;
			break;
		case "cusine category":
			element = cuisineDropdown;
			break;
		default:
			throw new IllegalArgumentException("Unknown dropdown: " + field);
		}
		waitUtils.waitForVisibility(driver, element, 10);
		element.sendKeys("\t");
	}

	public void uploadFile(String fileType) {
		waitUtils.waitForVisibility(driver, fileUpload, 10);
		String path;
		switch (fileType.toLowerCase()) {
		case "pdf":
			path = "src/test/resources/files/sample.pdf";
			break;
		case "docx":
			path = "src/test/resources/files/sample.docx";
			break;
		case "jpeg":
			path = "src/test/resources/files/sample.jpeg";
			break;
		case "largefile":
			path = "src/test/resources/files/largefile.pdf";
			break;
		case "none":
			return;
		default:
			throw new IllegalArgumentException("Unknown file type: " + fileType);
		}
		fileUpload.sendKeys(new File(path).getAbsolutePath());
	}

	public void clickChooseFiles() {
		waitUtils.waitForClickable(chooseFileButton).click();
	}

	public boolean isDialogClosed() {

		return !dialogBox.isDisplayed();
	}

	public void createPatientFromExcel() {
		Map<String, String> data = ExcelReader.getTestData("AddPatient");
		LoggerLoad.info("----- Excel Data -----");
		for (Map.Entry<String, String> entry : data.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		LoggerLoad.info("----------------------");

		if (data.containsKey("first_name"))
			enterFieldValue("first name", data.get("first_name"));
		if (data.containsKey("last_name"))
			enterFieldValue("last name", data.get("last_name"));
		if (data.containsKey("email"))
			enterEmail(data.get("email"));
		if (data.containsKey("contact_number"))
			enterContact(data.get("contact_number"));
		if (data.containsKey("dob"))
			enterDOB(data.get("dob"));
		if (data.containsKey("weight"))
			enterFieldValue("weight", data.get("weight"));
		if (data.containsKey("height"))
			enterFieldValue("height", data.get("height"));
		if (data.containsKey("temperature"))
			enterFieldValue("temperature", data.get("temperature"));
		if (data.containsKey("sp"))
			enterFieldValue("sp", data.get("sp"));
		if (data.containsKey("dp"))
			enterFieldValue("dp", data.get("dp"));
		if (data.containsKey("allergy"))
			selectAllergy(data.get("allergy"));
		if (data.containsKey("food_preference"))
			selectFoodPreference(data.get("food_preference"));
		if (data.containsKey("cuisine_category"))
			selectCuisineCategory(data.get("cuisine_category"));
		if (data.containsKey("file_type"))
			uploadFile(data.get("file_type"));

		clickSubmit();
	}

	public boolean verifyPatientCreatedFromExcel() {
		waitUtils.waitForVisibilityOfAll(messageText);
		for (WebElement msg : messageText) {
			if (msg.getText().contains("Patient successfully created"))
				return true;
		}
		return false;
	}

}
