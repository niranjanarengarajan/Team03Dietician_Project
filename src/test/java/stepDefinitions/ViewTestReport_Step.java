package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.PageObjectManager;
import pageObject.ViewTestReport_PageObject;
import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;

public class ViewTestReport_Step {
	String actual,expected;
	
	public TestContext context;
	ViewTestReport_PageObject reportObj;
	PageObjectManager poManager;
	
	public ViewTestReport_Step(TestContext context){
		this.context=context;
		this.reportObj = context.poManager.getViewTestReportPage();
	}
	
	@Given("User is on the dashboard page of Dietician application")
	public void user_is_on_the_dashboard_page_of_dietician_application() {
		LoggerLoad.info("User is on the dashboard page of Dietician application");
	}

	@When("User clicks My Patient button to view reports")
	public void user_clicks_my_patient_button_to_view_reports() {
		reportObj.clickMypatient();
	}

	@When("User clicks View Previous Test Reports button for a {string}")
	public void user_clicks_view_previous_test_reports_button_for_a_specific_record(String scenario) {
		context.testData = ExcelReader.getTestData(scenario);
		reportObj.clickPreviousTestReport(context.testData.get("Patient id"));
	}

	@Then("Corresponding report for that record should be opened")
	public void corresponding_report_for_that_record_should_be_opened() {
	    actual=reportObj.validateRecord("Patient id");
	    expected = context.testData.get("Patient id");
	    assertEquals(actual, expected);	    
	}

	@Then("{string} should be displayed in the View Test report popup")
	public void should_be_displayed_in_the_view_test_report_popup(String element) {
		assertTrue(reportObj.isDisplayed(element), element +" is not displayed");
	}

	@Then("{string} corresponding to the record selected in My Patients page should be displayed in View Test Report popup")
	public void corresponding_to_the_record_selected_in_my_patients_page_should_be_displayed_in_view_test_report_popup(String field) {
	    expected = context.testData.get(field);
	    actual = reportObj.validateRecord(field);
	    assertEquals(actual, expected);	  
	}

	@Then("Report Table headers should have {string}")
	public void report_table_headers_should_have_record_number_file_uploaded_time_file_report_name_vitals_identified_health_conditions(String allHeader) {
		List<String> actual = reportObj.reportHeader(allHeader);
		List<String> expected = reportObj.getHeaderTexts();
		assertEquals(actual, expected);
	}

	@Then("Record header should be in the exact order {string} should be displayed")
	public void record_header_should_be_in_the_exact_order_record_number_file_uploaded_time_file_report_name_vitals_identified_health_conditions_should_be_displayed(String allHeader) {
		List<String> actual = reportObj.reportHeader(allHeader);
		List<String> expected = reportObj.getHeaderTexts();
		assertEquals(actual, expected);
	}

	@Then("Pagination controls First, previous, next, last arrows should be displayed in View Test Report popup")
	public void pagination_controls_first_previous_next_last_arrows_should_be_displayed_in_view_test_report_popup() {
		assertTrue(reportObj.disablePagination(), "Pagination controls not displayed");
	}

	@Then("Each report should display a {string} in View Test Report popup")
	public void each_report_should_display_a_in_view_test_report_popup(String element) {
		assertTrue(reportObj.reportValidation(element), element +" is not displayed");	    
	}

	@Then("Vitals should be displayed in the order {string} in reports")
	public void vitals_should_be_displayed_in_the_order_weight_height_temperature_sp_dp_in_reports(String order) {
		assertTrue(reportObj.orderValidation(order), " Weight, Height, Temperature, SP, DP is not displayedin order");
	}

	@Then("{string} should be displayed in multilines in View Test Report popup")
	public void should_be_displayed_in_multilines_in_view_test_report_popup(String scenario) {
		assertTrue(reportObj.multilineValidation(scenario), scenario +" is not displayed in multiline");
	}

	@When("User clicks View PDF button for a particular record")
	public void user_clicks_view_pdf_button_for_a_particular_record() {
		reportObj.clickReportsPdf();
	}

	@Then("Corresponding PDF report for that record should be opened")
	public void corresponding_pdf_report_for_that_record_should_be_opened() {
	    expected = context.testData.get("ReportName");
	    actual = reportObj.getPageUrl();
	    assertTrue(actual.contains(expected),"Corresponding PDF report did not open");
	}

	@Given("User is in any page {string} page of View Test Report table for {string}")
	public void user_is_in_any_page_page_of_view_test_report_table_for(String scenario, String string2) {
	    reportObj.paginationNavGiven(scenario);
	}

	@When("User clicks the {string} page arrow in View Test Report popup")
	public void user_clicks_the_page_arrow_in_view_test_report_popup(String element) {
		reportObj.paginationNavWhen(element);
	}

	@Then("{string} set of patient records should be displayed in View Test Report popup")
	public void set_of_patient_records_should_be_displayed_in_view_test_report_popup(String action) {
		assertTrue(reportObj.setOfPatients(action), action +" set of patients not displayed");
	}

	@When("User clicks any page navigation arrow in View Test Report popup")
	public void user_clicks_any_page_navigation_arrow_in_view_test_report_popup() {
		reportObj.clickNextArrow();	    
	}

	@Then("Pagination text should display the correct range and total number of patients in View Test Report popup")
	public void pagination_text_should_display_the_correct_range_and_total_number_of_patients_in_view_test_report_popup() {
	    expected = context.testData.get("PaginationRange");
	    actual = reportObj.paginationText();
	    assertEquals(actual, expected);
	}

	@Given("User is in View Patient Test Reports page for {string}")
	public void user_is_in_view_patient_test_reports_page_for(String scenario) {
		context.testData = ExcelReader.getTestData(scenario);
		reportObj.clickPreviousTestReport(context.testData.get("Patient id"));
	}

	@When("User navigates to the first page of patient record in View Test Report popup")
	public void user_navigates_to_the_first_page_of_patient_record_in_view_test_report_popup() {
	    LoggerLoad.info("User is in the first page of the patient record ");
	}

	@Then("{string} should be {string} in View Test Report popup")
	public void should_be_in_view_test_report_popup(String element, String status) {
	    assertTrue(reportObj.paginationEnabledDisabled(element,status),element + " is not " +status);
	}

	@When("User navigates to any page except the last page in View Test Report popup")
	public void user_navigates_to_any_page_except_the_last_page_in_view_test_report_popup() {
	    reportObj.clickNextArrow();	    
	}

	@When("User navigates to the last page of the patient record in View Test Report popup")
	public void user_navigates_to_the_last_page_of_the_patient_record_in_view_test_report_popup() {
	    reportObj.clicklastPageArrow();
	}

	@When("User clicks on View Patient Test Reports button for {string}")
	public void user_clicks_on_view_patient_test_reports_button_for(String scenario) {
		context.testData = ExcelReader.getTestData(scenario);
		reportObj.clickPreviousTestReport(context.testData.get("Patient id"));	    
	}

	@Then("First, previous, next, last arrows should be disabled in view patient test report page")
	public void first_previous_next_last_arrows_should_be_disabled_in_view_patient_test_report_page() {
		 assertTrue(reportObj.disablePagination()," Pagination controls is not  disabled");
	}

	@Then("{string} should be displayed in View Test Report popup")
	public void should_be_displayed_in_view_test_report_popup(String expected) {
	    actual = reportObj.paginationText();
	    assertTrue(actual.contains(expected),"Pagination text not displayed");
	}

	@Then("User should see only {int} records in each view patient test report page")
	public void user_should_see_only_records_in_each_view_patient_test_report_page(int count) {
		assertTrue(reportObj.numOfRecords(count),"User does not see 2 records in each report page.");
	}
}