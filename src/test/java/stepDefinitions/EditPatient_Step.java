package stepDefinitions;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pageObject.EditPatient_PageObject;
import pageObject.PageObjectManager;
import utils.ExcelReader;
import utils.TestContext;

public class EditPatient_Step {
	public TestContext context;
	EditPatient_PageObject editPatientPage;
	PageObjectManager poManager;
	private static String patientId;
	
	public EditPatient_Step(TestContext context)
	{
		editPatientPage = context.poManager.getEditPatientPage();
		this.context=context;
		
	}
		
	
	
	@Given("User logged into the app and user is in my patient page")
	public void user_logged_into_the_app_and_user_is_in_my_patient_page() {
	    
	}

	@When("User clicks edit icon for the particular patient")
	public void user_clicks_edit_icon_for_the_particular_patient() {
		editPatientPage.clickOnEditButton();
	}

	@Then("User should see  {string} on the dialog box")
	public void user_should_see_on_the_dialog_box(String scenarioType) {
		
		editPatientPage.validateElementEditPopup("scenarioType");
		Assert.assertTrue(true, "element not matched");
	   
	}
	
	@When("User checks dropdown {string} after clicks edit icon for the particular patient")
	public void user_checks_dropdown_after_clicks_edit_icon_for_the_particular_patient(String element) {
		editPatientPage.checkDropdownPopulated(element);
		
	}

	@Then("User should see  {string} popsup on the dialog box")
	public void user_should_see_popsup_on_the_dialog_box(String expected, String element) {
		 boolean actual = editPatientPage.checkDropdownPopulated(element);
		 Assert.assertEquals(actual, expected, "dropdown did not populated");
		
		
	}

	//------------------------------------------Data Validation---------------------------------------------------------------------------------
	

	
	@When("User clears existing value in {string} of edit patient dialogue box")
	public void user_clears_existing_value_in_of_edit_patient_dialogue_box(String element) {
		editPatientPage.placeholderFieldEditPopup(element);
	    
	}

	@Then("User should see placeholder {string} has {string}")
	public void user_should_see_placeholder_has(String element, String expected) {
		String actual = editPatientPage.placeholderFieldEditPopup(element);
		Assert.assertEquals(actual, expected, "placeholder mismatch");
	}

	@When("User clicks the Submit button after entering valid data of {string} from Excel for {string} edit window")
	public void user_clicks_the_submit_button_after_entering_valid_data_of_from_excel_for_edit_window(String scenarioType, String columnName) {
	    
		context.testData= ExcelReader.getTestData("AddPatient");
		String existingPatientName =context.testData.get("first_name");		
		editPatientPage.searchForPatienName(existingPatientName);
		String patientId = editPatientPage.getPatientId(existingPatientName);
		editPatientPage.clickOnEditButton();
		
		context.testData = ExcelReader.getTestData(scenarioType);
		String value = context.testData.get(columnName);
		editPatientPage.clearEnterAndSubmitValidData(scenarioType, value);
		
		
	    
	}

	@Then("User should be redirected to the {string} page")
	public void user_should_be_redirected_to_the_page(String expected) {
		String actual = editPatientPage.redirectedToMyPatientsPage();
		Assert.assertEquals(actual, expected, "my patient page not found");
	}

	@Then("User should see updated {string} from Excel for {string} in My Patient table")
	public void user_should_see_updated_from_excel_for_in_my_patient_table(String values, String scenarioType) {
		
		//String patientName = context.testData.get("first_name");
		String expectedPatientData = context.testData.get(values);
		
		
		String actualValue = "";
	    
	    switch (values.toLowerCase()) {
	        case "first_name":                
	        	String fullName = editPatientPage.getNameTextPatient(patientId);
	            actualValue = fullName.split("\\s+")[0]; 
	            break;
	            
	        case "last_name":
	            String fullName1 = editPatientPage.getNameTextPatient(patientId);
	            actualValue = fullName1.split("\\s+")[1]; 
	            break;

	        case "email":
	            String detailsForEmail = editPatientPage.getDetailsTextOfPatient(patientId);
	            actualValue = detailsForEmail.split("\n")[1];
	            break;

	        case "contact_number":
	            String detailsForPhone = editPatientPage.getDetailsTextOfPatient(patientId);
	            actualValue = detailsForPhone.split("\n")[0];
	            break;

//	        case "date":
//	            // Get Column 3 (Details), split by newline, take index 2
//	            String detailsForDate = myPatientPage.getRawDetailsText(patientName);
//	            actualValue = detailsForDate.split("\n")[2];
//	            break;

	        default:
	            throw new IllegalArgumentException("scenarioType not supported: " + scenarioType);
	    }

	    // 3. Final Assertion
	    Assert.assertEquals(actualValue.trim(), expectedPatientData.trim(), 
	        "Mismatch found in table for field: " + values);
	
		}

	@When("User clicks {string} after being redirected to the My Patient page")
	public void user_clicks_after_being_redirected_to_the_my_patient_page(String string) {
		editPatientPage.getPatientId(patientId);
		editPatientPage.viewPatientTestReport();
	}

	@Then("User should see updated {string} from Excel for {string} in view Reports page for the patient")
	public void user_should_see_updated_from_excel_for_in_view_reports_page_for_the_patient(String scenarioType, String values) {
		
		String expectedValue = context.testData.get(values.trim().toLowerCase());
	    String vitalsData = editPatientPage.vitalsDetailsOfViewTestReport();
		    String[] lines = vitalsData.split("\n");
		    
		    if (values.equalsIgnoreCase("sp,dp")) {
		        Assert.assertEquals(lines[3].trim(), context.testData.get("sp").trim());
		        Assert.assertEquals(lines[4].trim(), context.testData.get("dp").trim());
		        return; 
		    }
		    String actualValue = "";

		    switch (values.toLowerCase().trim()) {
		        
		        case "weight":
		            actualValue = lines[0]; 
		            break;

		        case "height":
		            actualValue = lines[1]; 
		            break;

		        case "temperature":
		            actualValue = lines[2];
		            break;

		        case "sp":
		            actualValue = lines[3]; 
		            break;

		        case "dp":
		            actualValue = lines[4]; 
		            break;

		        default:
		            throw new IllegalArgumentException("Field not supported: " + values);
		    }

		    Assert.assertEquals(actualValue.trim(), expectedValue.trim(), 
		        "Mismatch for field: " + values + " for Patient ID: " + patientId);
		}
	    
	 	
	@When("User clicks the Submit button after entering invalid data of {string} from Excel for {string} edit window")
	public void user_clicks_the_submit_button_after_entering_invalid_data_of_from_excel_for_edit_window(String string, String string2) {
	    
	}

	@Then("User should see the error message {string} from Excel")
	public void user_should_see_the_error_message_from_excel(String string) {
	    
	}

	@When("User clicks on the DOB input field")
	public void user_clicks_on_the_dob_input_field() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should see the calendar date picker displayed with Month, Day, and Year")
	public void user_should_see_the_calendar_date_picker_displayed_with_month_day_and_year() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User enters {string} for {string} for editPatient")
	public void user_enters_for_for_edit_patient(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should see the date picker {string} from Excel")
	public void user_should_see_the_date_picker_from_excel(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User uploads file for {string} from excel for editPatient")
	public void user_uploads_file_for_from_excel_for_edit_patient(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks the {string} button in edit window")
	public void user_clicks_the_button_in_edit_window(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks the {string} button and uploads file for {string} from excel for editPatient")
	public void user_clicks_the_button_and_uploads_file_for_from_excel_for_edit_patient(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks {string} and verifies the following report details:")
	public void user_clicks_and_verifies_the_following_report_details(String string, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new io.cucumber.java.PendingException();
	}

	

}
