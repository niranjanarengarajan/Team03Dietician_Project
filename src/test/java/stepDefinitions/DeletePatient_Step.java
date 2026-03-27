package stepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.DeletePatient_PageObject;
import pageObject.EditPatient_PageObject;
import pageObject.PageObjectManager;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;


public class DeletePatient_Step {
	
	public TestContext context;
	DeletePatient_PageObject deletePatientPage;
	PageObjectManager poManager;
	EditPatient_PageObject editPatientPage;
	
	DeletePatient_Step(TestContext context)
	{
		deletePatientPage = context.poManager.getDeletePatientPage();
		editPatientPage = context.poManager.getEditPatientPage();
		this.context=context;
		
	}
	
	@When("User clicks the Delete icon in MyPatientPage for {string}")
	public void user_clicks_the_delete_icon_in_my_patient_page_for(String string) {
		context.testData= ExcelReader.getTestData("editPatient");
		String existingPatientName =context.testData.get("first_name");		
		editPatientPage.searchForPatienName(existingPatientName);
		deletePatientPage.clickOnDeletebuttonOnpatientPage();
		LoggerLoad.info("user clicks on delete button");
	}

	@Then("Alert box should see {string} in delete popup")
	public void alert_box_should_see_in_delete_popup(String values, String ScenarioName) {
	boolean elementspresent = deletePatientPage.alertInfoAndButtonVisibity(values);
	
	Assert.assertTrue("is displayed", elementspresent);
	LoggerLoad.info("elements are present in delete window");
	}

	

	@When("User clicks the Delete icon in MyPatientPage and performs {string} deletion")
	public void user_clicks_the_delete_icon_in_my_patient_page_and_performs_deletion(String action) {
		deletePatientPage.performDeleteAction(action);
		LoggerLoad.info("user clicks on delete button and performs delete");
	}
	
	@Then("User should be navigated to {string} upon deletion")
	public void user_should_be_navigated_to_my_patient_page_upon_deletion(String expected) {
		String actual = editPatientPage.redirectedToMyPatientsPage();
		Assert.assertEquals(actual, expected, "my patient page not found");
		LoggerLoad.info("user navigated to my patients page");
	}

	@Then("User verifies the Patient record state is {string} for {string}  for {string} after being directed MyPatient Page")
	public void user_verifies_the_patient_record_state_is_for_for_after_being_directed_my_patient_page(String string, String action, String string3) {
		context.testData= ExcelReader.getTestData("editPatient");
		String existingPatientName =context.testData.get("first_name");		
		editPatientPage.searchForPatienName(existingPatientName);
		
	
	    boolean isPresent = deletePatientPage.isPatientNamePresent(existingPatientName);

	  
	    if (action.equalsIgnoreCase("Confirm")) {
	        // If we confirmed, we EXPECT the name to be GONE (isPresent should be false)
	        Assert.assertFalse("FAILURE: Patient '" + existingPatientName + "' is still in the table after Confirm!", isPresent);
	        LoggerLoad.info("SUCCESS: Patient '" + existingPatientName + "' was removed.");
	    } 
	    else if (action.equalsIgnoreCase("Cancel") || action.equalsIgnoreCase("Cancle")) {
	        // If we cancelled, we EXPECT the name to STAY (isPresent should be true)
	        Assert.assertTrue("FAILURE: Patient '" + existingPatientName + "' was removed even after Cancel!", isPresent);
	        LoggerLoad.info("SUCCESS: Patient '" + existingPatientName + "' is still present as expected.");
	    }
	
	


}
}
