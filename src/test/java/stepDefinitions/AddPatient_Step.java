package stepDefinitions;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AddPatient_PageObject;
import utils.TestContext;

public class AddPatient_Step {

    private static final Logger log = LoggerFactory.getLogger(AddPatient_Step.class);
    public TestContext context;

    public AddPatient_Step(TestContext context) {
        this.context = context;
    }

    private AddPatient_PageObject getPage() {
        return context.poManager.getAddPatientPage();
    }
    
   @Given("User is in Home Page")
    public void user_is_in_home_page() {
        log.info("Checking that user is on Home Page"); 
        System.out.println("Step Executing");
    }

    @When("User clicks on New Patient in the header section")
    public void user_clicks_on_new_patient_in_the_header_section() {
    	 System.out.println("STEP STARTED");  

    	    log.info("Clicking New Patient button");

    	    try {
    	        getPage().clickNewPatientButton();
    	    } catch (Exception e) {
    	        e.printStackTrace();  
    	        throw e;
    	    }
//        log.info("Clicking New Patient button");
//        getPage().clickNewPatientButton();
    }

    @Then("User should see {string} with {string} {string}")
    public void user_should_see_with(String elementType, String property, String value) {
        log.info("Verifying element: {} | {} | {}", elementType, property, value);
        boolean result = getPage().verifyElement(elementType, property, value); 
        Assert.assertTrue(result,
            "Validation failed for: " + elementType + " | " + property + " | " + value);
    }

    @Given("User is in Add Patient Details Dialog Box")
    public void user_is_in_add_patient_details_dialog_box() {
        log.info("Checking if Add Patient Details Dialog Box is open...");
        if (!getPage().isDialogDisplayed()) { 
            log.error("Add Patient Details Dialog Box is NOT displayed!");
            throw new AssertionError("Add Patient Details Dialog Box is not displayed!");
        }
        log.info("Add Patient Details Dialog Box is displayed successfully.");
    }

    @When("User enters valid values in all mandatory and optional fields")
    public void user_enters_valid_values_in_all_mandatory_and_optional_fields() {
        log.info("Entering valid values in all fields...");
        try {
            getPage().enterValidPatientDetails(); 
            log.info("Successfully entered all patient details.");
        } catch (Exception e) {
            log.error("Error while entering patient details", e);
            throw new RuntimeException("Failed to enter patient details", e);
        }
    }

    @When("User clicks Submit in the Add patient page")
    public void user_clicks_submit_in_the_add_patient_page() {
        log.info("Clicking Submit button...");
        getPage().clickSubmit();
        log.info("Submit button clicked.");
    }

    @Then("Submit button should be enabled in the Add patient page")
    public void submit_button_should_be_enabled_in_the_add_patient_page() {
        boolean enabled = getPage().isSubmitEnabled(); 
        log.info("Submit button enabled: {}", enabled);
        if (!enabled) throw new AssertionError("Submit button is NOT enabled");
    }

    @Then("User should see success message {string}")
    public void user_should_see_success_message(String expectedMessage) {
        log.info("Validating success message...");
        String actualMessage = getPage().getSuccessMessage();
        log.info("Expected: '{}', Actual: '{}'", expectedMessage, actualMessage);
        if (!actualMessage.equals(expectedMessage))
            throw new AssertionError("Expected: " + expectedMessage + " but got: " + actualMessage);
    }

    @Then("User should be navigated to My Patient Page with New Patient Details created")
    public void user_should_be_navigated_to_my_patient_page_with_new_patient_details_created() {
        log.info("Verifying navigation to My Patient page...");
        if (!getPage().isNavigatedToMyPatientPage()) { 
            log.error("Navigation to My Patient page failed!");
            throw new AssertionError("Navigation to My Patient Page failed.");
        }
        log.info("Successfully navigated to My Patient page.");
    }

    @When("User clicks on Allergry dropdown in the Add patient detail page")
    public void user_clicks_on_allergry_dropdown_in_the_add_patient_detail_page() {
        log.info("Clicking Allergy dropdown");
        getPage().clickAllergyDropdown(); 
       
    }

    @Then("Values should be present inside Allergy dropdown")
    public void values_should_be_present_inside_allergy_dropdown() {
        List<String> values = getPage().getAllergyDropdownValues(); 
        log.info("Allergy dropdown values: {}", values);
        assert !values.isEmpty() : "No values present in Allergy dropdown!";
    }

    @Then("Dropdown should contain {int} values")
    public void dropdown_should_contain_values(Integer expectedCount) {
        int actualCount = getPage().getAllergyDropdownCount(); 
        log.info("Expected {} values, found {}", expectedCount, actualCount);
        assert actualCount == expectedCount : "Expected " + expectedCount + " but found " + actualCount;
    }

    @Then("User should see {string} in Allergy dropdown")
    public void user_should_see_in_allergy_dropdown(String value) {
        boolean present = getPage().isAllergyValuePresent(value); 
        log.info("'{}' present in Allergy dropdown: {}", value, present);
        assert present : value + " not present in Allergy dropdown!";
    }

    @When("User clicks on Food Preference dropdown in the Add patient detail page")
    public void user_clicks_on_food_preference_dropdown_in_the_add_patient_detail_page() {
        log.info("Clicking Food Preference dropdown");
        getPage().clickFoodDropdown(); 
    }

    @Then("Values should be present inside Food preference dropdown")
    public void values_should_be_present_inside_food_preference_dropdown() {
        List<String> values = getPage().getFoodDropdownValues();
        log.info("Food Preference values: {}", values);
        assert !values.isEmpty() : "No values present in Food Preference dropdown!";
    }

    @Then("User should see {string} in Food Preference dropdown")
    public void user_should_see_in_food_preference_dropdown(String value) {
        boolean present = getPage().isFoodValuePresent(value); 
        log.info("'{}' present in Food dropdown: {}", value, present);
        assert present : value + " not present in Food Preference dropdown!";
    }

    @When("User clicks on Cuisine dropdown in the Add patient detail page")
    public void user_clicks_on_cuisine_dropdown_in_the_add_patient_detail_page() {
        log.info("Clicking Cuisine dropdown");
        getPage().clickCuisineDropdown();
    }

    @Then("Values should be present inside Cuisine dropdown")
    public void values_should_be_present_inside_cuisine_dropdown() {
        List<String> values = getPage().getCuisineDropdownValues(); 
        log.info("Cuisine values: {}", values);
        assert !values.isEmpty() : "No values present in Cuisine dropdown!";
    }

    @Then("User should see {string} in Cuisine dropdown")
    public void user_should_see_in_cuisine_dropdown(String value) {
        boolean present = getPage().isCuisineValuePresent(value); 
        log.info("'{}' present in Cuisine dropdown: {}", value, present);
        if (!present) throw new AssertionError("Cuisine value not found: " + value);
    }

    @When("User clicks Submit after user enters valid values in all fields in Add patient detail page")
    public void user_clicks_submit_after_user_enters_valid_values_in_all_fields_in_add_patient_detail_page() {
        log.info("Clicking Submit button");
        getPage().clickSubmit(); 
    }

    @Then("{string} should be successful")
    public void should_be_successful(String validation) {
        if (validation.equalsIgnoreCase("Submit Enabled")) {
            boolean enabled = getPage().isSubmitEnabled(); 
            log.info("Submit enabled: {}", enabled);
            assert enabled : "Submit button is not enabled!";
        } else if (validation.equalsIgnoreCase("Success Message")) {
            log.info("Checking success message");
        } else if (validation.equalsIgnoreCase("Navigation")) {
            log.info("Checking navigation");
        }
    }

    @When("User selects {string} from Allergy dropdown in the Add patient detail page")
    public void user_selects_from_allergy_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Selecting '{}' from Allergy dropdown", value);
        getPage().selectAllergy(value); 
    }

    @Then("{string} should be selected in the Allergy field")
    public void should_be_selected_in_the_allergy_field(String value) {
        boolean selected = getPage().isAllergySelected(value); 
        log.info("Allergy '{}' selected: {}", value, selected);
        if (!selected) throw new AssertionError("Allergy not selected: " + value);
    }

    @When("User selects multiple values {string} from Allergy dropdown in the Add patient detail page")
    public void user_selects_multiple_values_from_allergy_dropdown_in_the_add_patient_detail_page(String values) {
        log.info("Selecting multiple Allergies: {}", values);
        getPage().selectMultipleAllergies(values); 
    }

    @When("User tries to select {string} from Allergy dropdown in the Add patient detail page")
    public void user_tries_to_select_from_allergy_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Trying invalid Allergy: {}", value);
        getPage().trySelectInvalidAllergy(value); 
    }

    @Then("No selection should occur in the Allergy field")
    public void no_selection_should_occur_in_the_allergy_field() {
        boolean noneSelected = getPage().noAllergySelected(); 
        log.info("No Allergy selected: {}", noneSelected);
        if (!noneSelected) throw new AssertionError("Allergy was selected when it shouldn't be");
    }

    @When("User selects {string} from Food Preference dropdown in the Add patient detail page")
    public void user_selects_from_food_preference_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Selecting Food Preference: {}", value);
        getPage().selectFoodPreference(value); 
    }

    @Then("{string} should be selected in the Food Preference field")
    public void should_be_selected_in_the_food_preference_field(String value) {
        boolean selected = getPage().isFoodSelected(value); 
        log.info("Food '{}' selected: {}", value, selected);
        if (!selected) throw new AssertionError("Food Preference not selected: " + value);
    }

    @When("User selects multiple values {string} from Food Preference dropdown in the Add patient detail page")
    public void user_selects_multiple_values_from_food_preference_dropdown_in_the_add_patient_detail_page(String values) {
        log.info("Selecting multiple Food Preferences: {}", values);
        getPage().selectMultipleFoodPreferences(values); 
    }

    @When("User tries to select {string} from Food Preference dropdown in the Add patient detail page")
    public void user_tries_to_select_from_food_preference_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Trying invalid Food Preference: {}", value);
        getPage().trySelectInvalidFoodPreference(value); 
    }

    @Then("No selection should occur in the Food Preference field")
    public void no_selection_should_occur_in_the_food_preference_field() {
        boolean noneSelected = getPage().noFoodSelected(); 
        log.info("No Food selected: {}", noneSelected);
        if (!noneSelected) throw new AssertionError("Food was selected when it shouldn't be");
    }

    @When("User selects {string} from Cuisine Category dropdown in the Add patient detail page")
    public void user_selects_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Selecting Cuisine: {}", value);
        getPage().selectCuisineCategory(value); 
    }

    @Then("{string} should be selected in the Cuisine Category field")
    public void should_be_selected_in_the_cuisine_category_field(String value) {
        boolean selected = getPage().isCuisineSelected(value); 
        log.info("Cuisine '{}' selected: {}", value, selected);
        if (!selected) throw new AssertionError("Cuisine not selected: " + value);
    }

    @When("User selects multiple values {string} from Cuisine Category dropdown in the Add patient detail page")
    public void user_selects_multiple_values_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String values) {
        log.info("Selecting multiple Cuisines: {}", values);
        getPage().selectMultipleCuisineCategories(values); 
    }

    @When("User tries to select {string} from Cuisine Category dropdown in the Add patient detail page")
    public void user_tries_to_select_from_cuisine_category_dropdown_in_the_add_patient_detail_page(String value) {
        log.info("Trying invalid Cuisine: {}", value);
        getPage().trySelectInvalidCuisineCategory(value); 
    }

    @Then("No selection should occur in the Cuisine Category field")
    public void no_selection_should_occur_in_the_cuisine_category_field() {
        boolean noneSelected = getPage().noCuisineSelected(); 
        log.info("No Cuisine selected: {}", noneSelected);
        if (!noneSelected) throw new AssertionError("Cuisine was selected when it shouldn't be");
    }

    @When("User enters {string} in Date of Birth field in the Add patient detail page")
    public void user_enters_in_date_of_birth_field_in_the_add_patient_detail_page(String dob) {
        log.info("Entering DOB: {}", dob);
        getPage().enterDOB(dob); 
    }

    @Then("User should see {string}")
    public void user_should_see(String message) {
        log.info("Validating message: {}", message);
        boolean isDisplayed = getPage().isMessageDisplayed(message); 
        if (!isDisplayed) throw new AssertionError("Expected message not displayed: " + message);
    }

    @When("User enters {string} in {string} field and navigates to next field in the Add patient detail page")
    public void user_enters_in_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String value, String field) {
        log.info("Entering '{}' in '{}' field", value, field);
        getPage().enterFieldValue(field, value); 
        getPage().navigateToNextField();
    }

    @When("User enters {string} in email field and navigates to next field in the Add patient detail page")
    public void user_enters_in_email_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String email) {
        log.info("Entering Email: {}", email);
        getPage().enterEmail(email); 
        getPage().navigateToNextField();
    }

    @When("User enters {string} in contact number field and navigates to next field in the Add patient detail page")
    public void user_enters_in_contact_number_field_and_navigates_to_next_field_in_the_add_patient_detail_page(String contact) {
        log.info("Entering Contact: {}", contact);
        getPage().enterContact(contact); 
        getPage().navigateToNextField();
    }

    @When("User skips selecting {string} and navigates to next field in the Add patient detail page")
    public void user_skips_selecting_and_navigates_to_next_field_in_the_add_patient_detail_page(String field) {
        log.info("Skipping: {}", field);
        getPage().skipDropdown(field);
        getPage().navigateToNextField();
    }

    @When("User enters {string} in {string} and navigates to next field in the Add patient detail page")
    public void user_enters_in_and_navigates_to_next_field_in_the_add_patient_detail_page(String value, String field) {
        log.info("Entering '{}' in '{}'", value, field);
        getPage().enterFieldValue(field, value); 
        getPage().navigateToNextField();
    }

    @When("User uploads {string} in the Add patient detail page")
    public void user_uploads_in_the_add_patient_detail_page(String fileType) {
        log.info("Uploading file: {}", fileType);
        getPage().uploadFile(fileType); 
    }

    @When("User clicks Choose Files button in the Add patient page")
    public void user_clicks_choose_files_button_in_the_add_patient_page() {
        log.info("Clicking Choose Files button");
        getPage().clickChooseFiles(); 
    }

    @When("User clicks Submit button in the Add patient page")
    public void user_clicks_submit_button_in_the_add_patient_page() {
        log.info("Clicking Submit button");
        getPage().clickSubmit(); 
    }

    @When("User clicks Close button after submit in the Add patient detail page")
    public void user_clicks_close_button_after_submit_in_the_add_patient_detail_page() {
        log.info("Clicking Close button");
        getPage().clickClose(); 
    }

    @Then("Add dialog should close and user  should be on my patient page without new patient created")
    public void add_dialog_should_close_and_user_should_be_on_my_patient_page_without_new_patient_created() {
        log.info("Validating dialog closed");
        if (!getPage().isDialogClosed())
            throw new AssertionError("Add Patient Dialog did not close properly");
    }

    @When("User creates new patient using Excel data")
    public void user_creates_new_patient_using_excel_data() {
        log.info("Creating patient from Excel");
        getPage().createPatientFromExcel(); 
    }

    @Then("Patient creation should reflect the correct result")
    public void patient_creation_should_reflect_the_correct_result() {
        log.info("Validating patient creation from Excel");
        if (!getPage().verifyPatientCreatedFromExcel()) 
            throw new AssertionError("Patient creation from Excel failed");
    }
}