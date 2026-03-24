package stepDefinitions;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverManager.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AddPatient_PageObject;

public class AddPatient_Step {
	
	private static final Logger log = LogManager.getLogger(AddPatient_Step.class);
	WebDriver driver = DriverFactory.getDriver();
	
	AddPatient_PageObject addPatientPage = new AddPatient_PageObject(driver);
    
    public AddPatient_Step() {
    	this.driver = DriverFactory.getDriver();
    	this.addPatientPage = new AddPatient_PageObject(driver);    	
    }
	
	@Given("User is in Home Page")
	public void user_is_in_home_page() {
		log.info("Checking that user is on Home Page");
		Assert.assertTrue(driver.getTitle().contains("Home"));
	}

	@When("User clicks on New Patient in the header section")
	public void user_clicks_on_new_patient_in_the_header_section() {
		addPatientPage.clickNewPatientButton();
	}

	@Then("User should see {string} with {string} {string}")
	public void user_should_see_with(String elementType, String property, String value) {
		 boolean result = addPatientPage.verifyElement(elementType, property, value);		    
		    Assert.assertTrue(result, 
		        "Validation failed for: " + elementType + " | " + property + " | " + value);
	}
	
	@Given("User is in Add Patient Details Dialog Box")
	public void user_is_in_add_patient_details_dialog_box() {
		 log.info("Checking if Add Patient Details Dialog Box is open...");
		   
		    if (!addPatientPage.isDialogDisplayed()) {
		        log.error("Add Patient Details Dialog Box is NOT displayed!");
		        throw new AssertionError("Add Patient Details Dialog Box is not displayed!");
		    }
		    log.info("Add Patient Details Dialog Box is displayed successfully.");
	}

	@When("User enters valid values in all mandatory and optional fields")
	public void user_enters_valid_values_in_all_mandatory_and_optional_fields() {
		log.info("Starting to enter valid values in all mandatory and optional fields...");

	    try {
	        addPatientPage.enterValidPatientDetails();
	        log.info("Successfully entered all patient details, including optional fields and file upload.");
	    } catch (Exception e) {
	        log.error("Error while entering patient details", e);
	        throw new RuntimeException("Failed to enter patient details", e);
	    }
	}

	@When("User clicks Submit in the Add patient page")
	public void user_clicks_submit_in_the_add_patient_page() {
		log.info("Clicking Submit button on Add Patient page...");
        addPatientPage.clickSubmit();
        log.info("Submit button clicked.");
	}

	@Then("Submit button should be enabled in the Add patient page")
	public void submit_button_should_be_enabled_in_the_add_patient_page() {
		boolean enabled = addPatientPage.isSubmitEnabled();
	    log.info("Submit button enabled: " + enabled);

	    if (!enabled) {
	        throw new AssertionError("Submit button is NOT enabled");
	    }
	}

	@Then("User should see success message {string}")
	public void user_should_see_success_message(String expectedMessage) {
		log.info("Validating success message...");
        String actualMessage = addPatientPage.getSuccessMessage();
        log.info("Expected message: '{}', Actual message: '{}'", expectedMessage, actualMessage);
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("Success message mismatch! Expected: " + expectedMessage + " but got: " + actualMessage);
        }
	}

	@Then("User should be navigated to My Patient Page with New Patient Details created")
	public void user_should_be_navigated_to_my_patient_page_with_new_patient_details_created() {
		 log.info("Verifying navigation to My Patient page...");
	        if (addPatientPage.isNavigatedToMyPatientPage()) {
	            log.info("Successfully navigated to My Patient page with new patient details.");
	        } else {
	            log.error("Navigation to My Patient page failed!");
	            throw new AssertionError("Navigation to My Patient Page failed.");
	        }	    
	}

	@When("User clicks on Allergry dropdown in the Add patient detail page")
	public void user_clicks_on_allergry_dropdown_in_the_add_patient_detail_page() {
		log.info("Clicking Allergy dropdown in Add Patient page");
		addPatientPage.clickAllergyDropdown();
	}

	@Then("Values should be present inside Allergy dropdown")
	public void values_should_be_present_inside_allergy_dropdown() {
		 List<String> values = addPatientPage.getAllergyDropdownValues();
	        log.info("Allergy dropdown values found: {}", values);
	        assert !values.isEmpty() : "No values present in Allergy dropdown!";
	}

	@Then("Dropdown should contain {int} values")
	public void dropdown_should_contain_values(Integer expectedCount) {
		int actualCount = addPatientPage.getAllergyDropdownCount();
        log.info("Expected {} values, found {} values in dropdown", expectedCount, actualCount);
        assert actualCount == expectedCount : "Expected " + expectedCount + " but found " + actualCount;
	   	}

	@Then("User should see {string} in Allergy dropdown")
	public void user_should_see_in_allergy_dropdown(String value) {
		boolean present = addPatientPage.isAllergyValuePresent(value);
        log.info("Checking if '{}' is present in Allergy dropdown: {}", value, present);
        assert present : value + " not present in Allergy dropdown!";
	}

	@When("User clicks on Food Preference dropdown in the Add patient detail page")
	public void user_clicks_on_food_preference_dropdown_in_the_add_patient_detail_page() {
		log.info("Clicking Food Preference dropdown in Add Patient page");
        addPatientPage.clickFoodDropdown();
	}

	@Then("Values should be present inside Food preference dropdown")
	public void values_should_be_present_inside_food_preference_dropdown() {
		 List<String> values = addPatientPage.getFoodDropdownValues();
	        log.info("Food Preference dropdown values found: {}", values);
	        assert !values.isEmpty() : "No values present in Food Preference dropdown!";
	}

	@Then("User should see {string} in Food Preference dropdown")
	public void user_should_see_in_food_preference_dropdown(String value) {
		 boolean present = addPatientPage.isFoodValuePresent(value);
	        log.info("Checking if '{}' is present in Food Preference dropdown: {}", value, present);
	        assert present : value + " not present in Food Preference dropdown!";
	}

	@Then("Values should be present inside Cuisine dropdown")
	public void values_should_be_present_inside_cuisine_dropdown() {
		List<String> values = addPatientPage.getCuisineDropdownValues();
        log.info("Cuisine dropdown values found: {}", values);
        assert !values.isEmpty() : "No values present in Cuisine dropdown!";
	}

	@When("User clicks on Cuisine dropdown in the Add patient detail page")
	public void user_clicks_on_cuisine_dropdown_in_the_add_patient_detail_page() {
		log.info("Clicking Cuisine dropdown in Add Patient page");
        addPatientPage.clickCuisineDropdown();
	}

	@Then("User should see {string} in Cuisine dropdown")
	public void user_should_see_in_cuisine_dropdown(String value) {
		boolean present = addPatientPage.isCuisineValuePresent(value);
	    log.info("Checking cuisine value: " + value + " -> " + present);

	    if (!present) {
	        throw new AssertionError("Cuisine value not found: " + value);
	    }
	}

	@When("User clicks Submit after user enters valid values in all fields in Add patient detail page")
	public void user_clicks_submit_after_user_enters_valid_values_in_all_fields_in_add_patient_detail_page() {
		 log.info("Clicking Submit button in Add Patient page");
	        addPatientPage.clickSubmit();
	}

	@Then("{string} should be successful")
	public void should_be_successful(String validation) {
		 if (validation.equalsIgnoreCase("Submit Enabled")) {
	            boolean enabled = addPatientPage.isSubmitEnabled();
	            log.info("Submit button enabled: {}", enabled);
	            assert enabled : "Submit button is not enabled!";
	        } else if (validation.equalsIgnoreCase("Success Message")) {
	            log.info("Check success message displayed"); 	            
	        } else if (validation.equalsIgnoreCase("Navigation")) {
	            log.info("Check navigation to My Patient Page");	           
	        }
	}

	@When("User selects {string} from Allergy dropdown in the Add patient detail page")
	public void user_selects_from_allergy_dropdown_in_the_add_patient_detail_page(String value) {
		log.info("Selecting '{}' from Allergy dropdown", value);
        addPatientPage.selectAllergy(value);
	}

	@Then("{string} should be selected in the Allergy field")
	public void should_be_selected_in_the_allergy_field(String value) {
		  boolean selected = addPatientPage.isAllergySelected(value);
	        log.info("Checking if Allergy is selected: " + value + " -> " + selected);
	        if (!selected) throw new AssertionError("Allergy value not selected: " + value);	   
	}

	@When("User selects multiple values {string} from Allergy dropdown in the Add patient detail page")
	public void user_selects_multiple_values_from_allergy_dropdown_in_the_add_patient_detail_page(String values) {
		log.info("Selecting multiple Allergies: " + values);
        addPatientPage.selectMultipleAllergies(values);
	}

	@When("User tries to select {string} from Allergy dropdown in the Add patient detail page")
	public void user_tries_to_select_from_allergy_dropdown_in_the_add_patient_detail_page(String value) {
		 log.info("Trying to select invalid Allergy: " + value);
	        addPatientPage.trySelectInvalidAllergy(value);
	}

	@Then("No selection should occur in the Allergy field")
	public void no_selection_should_occur_in_the_allergy_field() {
		 boolean noneSelected = addPatientPage.noAllergySelected();
	        log.info("Checking no Allergy selection -> " + noneSelected);
	        if (!noneSelected) throw new AssertionError("Some Allergy value is selected when it shouldn't be");
	}

	@When("User selects {string} from Food Preference dropdown in the Add patient detail page")
	public void user_selects_from_food_preference_dropdown_in_the_add_patient_detail_page(String value) {
		log.info("Selecting Food Preference: " + value);
        addPatientPage.selectFoodPreference(value);
	}

	@Then("{string} should be selected in the Food Preference field")
	public void should_be_selected_in_the_food_preference_field(String value) {
		boolean selected = addPatientPage.isFoodSelected(value);
        log.info("Checking if Food Preference is selected: " + value + " -> " + selected);
        if (!selected) throw new AssertionError("Food Preference value not selected: " + value);
    
	}

	@When("User selects multiple values {string} from Food Preference dropdown in the Add patient detail page")
	public void user_selects_multiple_values_from_food_preference_dropdown_in_the_add_patient_detail_page(String values) {
		log.info("Selecting multiple Food Preferences: " + values);
        addPatientPage.selectMultipleFoodPreferences(values);
	}

	@When("User tries to select {string} from Food Preference dropdown in the Add patient detail page")
	public void user_tries_to_select_from_food_preference_dropdown_in_the_add_patient_detail_page(String value) {
		log.info("Trying to select invalid Food Preference: " + value);
        addPatientPage.trySelectInvalidFoodPreference(value);
	}

	@Then("No selection should occur in the Food Preference field")
	public void no_selection_should_occur_in_the_food_preference_field() {
		 boolean noneSelected = addPatientPage.noFoodSelected();
	        log.info("Checking no Food Preference selection -> " + noneSelected);
	        if (!noneSelected) throw new AssertionError("Some Food Preference value is selected when it shouldn't be");
	}

	@When("User selects {string} from Cuisine Category dropdown in the Add patient detail page")
	public void user_selects_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String value) {
		log.info("Selecting Cuisine Category: " + value);
        addPatientPage.selectCuisineCategory(value);
	}

	@Then("{string} should be selected in the Cuisine Category field")
	public void should_be_selected_in_the_cuisine_category_field(String value) {
		 boolean selected = addPatientPage.isCuisineSelected(value);
	        log.info("Checking if Cuisine Category is selected: " + value + " -> " + selected);
	        if (!selected) throw new AssertionError("Cuisine Category value not selected: " + value);	    
	}

	@When("User selects multiple values {string} from Cuisine Category dropdown in the Add patient detail page")
	public void user_selects_multiple_values_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String values) {
		log.info("Selecting multiple Cuisine Categories: " + values);
        addPatientPage.selectMultipleCuisineCategories(values);
	}

	@When("User tries to select {string} from Cuisine Category dropdown in the Add patient detail page")
	public void user_tries_to_select_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String value) {
		log.info("Trying to select invalid Cuisine Category: " + value);
        addPatientPage.trySelectInvalidCuisineCategory(value);
	}

	@Then("No selection should occur in the Cuisine Category field")
	public void no_selection_should_occur_in_the_cuisine_category_field() {
		boolean noneSelected = addPatientPage.noCuisineSelected();
        log.info("Checking no Cuisine Category selection -> " + noneSelected);
        if (!noneSelected) throw new AssertionError("Some Cuisine Category value is selected when it shouldn't be");  
	}

	@When("User enters {string} in Date of Birth field in the Add patient detail page")
	public void user_enters_in_date_of_birth_field_in_the_add_patient_detail_page(String dob) {
		 log.info("Entering DOB: " + dob);
	        addPatientPage.enterDOB(dob);
	}

	@Then("User should see {string}")
	public void user_should_see(String message) {
		log.info("Validating message: " + message);
        boolean isDisplayed = addPatientPage.isMessageDisplayed(message);
        if (!isDisplayed) throw new AssertionError("Expected message not displayed: " + message);
    
	}

	@When("User enters {string} in {string} field and navigates to next field in the Add patient detail page")
	public void user_enters_in_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String value, String field) {
		log.info("Entering " + value + " in " + field + " field");
		 addPatientPage.enterFieldValue(field, value);
		    addPatientPage.navigateToNextField();
	}

	@When("User enters {string} in email field and navigates to next field in the Add patient detail page")
	public void user_enters_in_email_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String email) {
		log.info("Entering Email: " + email);
        addPatientPage.enterEmail(email);
        addPatientPage.navigateToNextField();
	}

	@When("User enters {string} in contact number field and navigates to next field in the Add patient detail page")
	public void user_enters_in_contact_number_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String contact) {
		log.info("Entering Contact Number: " + contact);
        addPatientPage.enterContact(contact);
        addPatientPage.navigateToNextField();
	}

	@When("User skips selecting {string} and navigates to next field in the Add patient detail page")
	public void user_skips_selecting_and_navigates_to_next_field_in_the_add_patient_detail_page(String field) {
		log.info("Skipping selection for " + field);
        addPatientPage.skipDropdown(field);
        addPatientPage.navigateToNextField();
	}

	@When("User enters {string} in {string} and navigates to next field in the Add patient detail page")
	public void user_enters_in_and_navigates_to_next_field_in_the_add_patient_detail_page(String value, String field) {
		log.info("Entering " + value + " in " + field);
        addPatientPage.enterFieldValue(field, value);
        addPatientPage.navigateToNextField();
	}

	@When("User uploads {string} in the Add patient detail page")
	public void user_uploads_in_the_add_patient_detail_page(String fileType) {
		log.info("Uploading file of type: " + fileType);
        addPatientPage.uploadFile(fileType);
	}

	@When("User clicks Choose Files button in the Add patient page")
	public void user_clicks_choose_files_button_in_the_add_patient_page() {
		 log.info("Clicking 'Choose Files' button");
	        addPatientPage.clickChooseFiles();
	}

	@When("User clicks Submit button in the Add patient page")
	public void user_clicks_submit_button_in_the_add_patient_page() {
		log.info("Clicking Submit button");
        addPatientPage.clickSubmit();
	}

	@When("User clicks Close button after submit in the Add patient detail page")
	public void user_clicks_close_button_after_submit_in_the_add_patient_detail_page() {
		log.info("Clicking Close button");
        addPatientPage.clickClose();
	}

	@Then("Add dialog should close and user  should be on my patient page without new patient created")
	public void add_dialog_should_close_and_user_should_be_on_my_patient_page_without_new_patient_created() {
		log.info("Validating dialog is closed and user is on My Patient Page");
        if (!addPatientPage.isDialogClosed()) throw new AssertionError("Add Patient Dialog did not close properly");
    
	}

	@When("User creates new patient using Excel data")
	public void user_creates_new_patient_using_excel_data() {
		 log.info("Creating new patient using Excel data");
	        addPatientPage.createPatientFromExcel();
	}

	@Then("Patient creation should reflect the correct result")
	public void patient_creation_should_reflect_the_correct_result() {
		 log.info("Validating patient creation result from Excel data");
	        boolean success = addPatientPage.verifyPatientCreatedFromExcel();
	        if (!success) throw new AssertionError("Patient creation from Excel failed");	  
	}
}
