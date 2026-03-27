package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.MyPatients_PageObject;
import utils.LoggerLoad;
import utils.TestContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class MyPatients_Step {
	public TestContext context;
    private MyPatients_PageObject myPatientPage;

    public MyPatients_Step(TestContext context) {
        this.context = context;
        this.myPatientPage = context.poManager.getMyPatientPage();
    }

	

	private static final Logger logger = LoggerFactory.getLogger(MyPatients_Step.class);
	int initialrowcount;
	String lastRecordId;

	@Given("Signed in user has navigated to {string} page")
	public void signed_in_user_has_navigated_to_page(String string) {
		String currenturlString= myPatientPage.getCurrentUrl();
		LoggerLoad.info(currenturlString);
		

	}

	@Then("Page header {string} should be displayed on MyPatients page")
	public void page_header_should_be_displayed_on_my_patients_page(String expectedheader) {
		String actualheader = myPatientPage.getPageTitleText();
		Assert.assertEquals(actualheader, expectedheader, "MyPatients Page title mismatch");

	}

	@Then("Search bar should be displayed on MyPatients page")
	public void Search_bar_should_be_displayed_on_my_patients_page() {
		Assert.assertTrue(myPatientPage.isSearchBarDisplayed(), "search bar is not displayed");
		
	}

	@Then("Search icon should be displayed inside search bar on MyPatients page")
	public void Search_icon_should_be_displayed_inside_search_bar_on_my_patients_page() {
		Assert.assertTrue(myPatientPage.isSearchIconDisplayed(), "search icon is not displayed");
		
	}

	@Then("{string} placeholder text should be displayed inside search bar on MyPatients page")
	public void placeholder_text_should_be_displayed_inside_search_bar_on_my_patients_page(String placeholdertext) {
		String actualtext = myPatientPage.getPlaceHolderText();
		Assert.assertEquals(actualtext, placeholdertext, "Search placeholder text mismatch");
	}

	@Then("user should see column headers in table present in  MyPatients page")
	public void user_should_see_column_headers_in_table_present_in_my_patients_page(DataTable dataTable) {
		List<String> actualheaders = myPatientPage.getAllColumnHeaderTexts();
		List<String> expectedheaders = dataTable.asList();

		Assert.assertEquals(actualheaders, expectedheaders, "Mismatch in column headers in mypatients page");
		LoggerLoad.info("Verified column headers in mypatients page");
	}

	@Then("Up and down arrow icons should be displayed in the {string} column header on the MyPatients page")
	public void up_and_down_arrow_icons_should_be_displayed_in_the_column_header_on_the_my_patients_page(
			String columnname) {
		Assert.assertTrue(myPatientPage.isDownArrowDisplayed(columnname),
				"Up arrow is not displayed in " + columnname + " column header");

		Assert.assertTrue(myPatientPage.isDownArrowDisplayed(columnname),
				"Down arrow is not displayed in " + columnname + " column header");
	}

	@Given("Patient records already exist in the system for that user in MyPatients page")
	public void patient_records_already_exist_in_the_system_for_that_user_in_my_patients_page() {
                    myPatientPage.getPatientCount();

	}

	@Then("All coumns should have values on the MyPatients table")
	public void all_coumns_should_have_values_on_the_my_patients_table() {
		Assert.assertTrue(myPatientPage.checkAllColumnshasValues(), "Some columns have missing values");

	}

	@Then("Patient name should be displayed for each patient record on the MyPatients table")
	public void patient_name_should_be_displayed_for_each_patient_record_on_the_my_patients_table() {
		Assert.assertTrue(myPatientPage.isPatientnameDisplayedForAllRows(),
				"Patient name is not displayed for all rows");
	}

	@Then("Patient ID should be displayed for each patient record on the MyPatients table")
	public void patient_id_should_be_displayed_for_each_patient_record_on_the_my_patients_table() {
		Assert.assertTrue(myPatientPage.isPatientIdDisplayedForAllRows(), "Patient ID not displayed for all rows");
	}

	@Then("Details column should display the information for each patient record on the MyPatients table")
	public void details_column_should_display_the_information_for_each_patient_record_on_the_my_patients_table(
			DataTable dataTable) {
		List<String> expectedFields = dataTable.asList();
		Assert.assertTrue(myPatientPage.detailsColumnInfo(expectedFields), "Details column content validation failed");
	}

	@Then("Details column should display the information in seperate line for each patient record on the MyPatients table")
	public void details_column_should_display_the_information_in_seperate_line_for_each_patient_record_on_the_my_patients_table(
			DataTable dataTable) {

		Assert.assertTrue(myPatientPage.isDetailsDisplayedSeparateLines(),
				"Details are not displayed in separate lines");
	}

	@Then("{string}details should be displayed in {string} for each patient record on the MyPatients table")
	public void field_format_should_be_valid_for_each_patient_record(String field, String format) {
		Assert.assertTrue(myPatientPage.FieldFormatValidation(field, format),
				field + " is not displayed in " + format + " for each patient record");
	}

	@Then("Last visit date should be displayed in {string} format for each patient record on the MyPatients table")
	public void last_visit_date_should_be_displayed_in_format_for_each_patient_record_on_the_my_patients_table(
			String lastvisitformat) {
		Assert.assertTrue(myPatientPage.lastVisitDateFormatCheck(),
				lastvisitformat + " is not displayed in format for patient record");
	}

	@Then("User must see buttons displayed under Actions column for each patient record on the MyPatients table")
	public void user_must_see_buttons_displayed_under_actions_column_for_each_patient_record_on_the_my_patients_table(
			io.cucumber.datatable.DataTable dataTable) {

		List<String> buttons = dataTable.asList();
		for (String buttonName : buttons) {
			Assert.assertTrue(myPatientPage.buttonsinActionsRow(buttonName),
					"Button " + buttonName + " is missing in Actions column");

		}
	}

	@When("user clicks {string} arrow in {string} column on the MyPatients table")
	public void user_clicks_arrow_in_column_on_the_my_patients_table(String column, String arrow) {
		myPatientPage.clickSortArrow(column, arrow);
	}

	@Then("patient records should be sorted in {string} order by {string} on the MyPatients table")
	public void patient_records_should_be_sorted_in_order_by_on_the_my_patients_table(String column, String order) {
		Assert.assertTrue(myPatientPage.isColumnSortedInOrder(column, order),
				column + " records are not sorted in " + order + " order");
	}

	@Then("icon {string} should be displayed for each patient record on the MyPatients table")
	public void icon_should_be_displayed_for_each_patient_record_on_the_my_patients_table(String iconname) {
		Assert.assertTrue(myPatientPage.actionsIconDisplayedForAllRows(iconname), iconname + " icon is not displayed");

	}

	@When("User searches using {string} information on the MyPatients table")
	public void user_searches_using_information_on_the_my_patients_table(String scenarioType) {
		myPatientPage.performSearchFromExcel(scenarioType);
	}

	@Then("Matching patient details should be displayed for {string} on the MyPatients table")
	public void matching_patient_details_should_be_displayed_for_on_the_my_patients_table(String scenariotype) {
		// boolean checkresult =
		// mypatientpage.verifySearchResultsMatchExcel(scenariotype);
		// Assert.assertTrue("Search results did not match the Excel data for: " +
		// scenariotype, checkresult);
	}

	@Given("User has entered text in search box on the MyPatients table")
	public void user_has_entered_text_in_search_box_on_the_my_patients_table() {
		this.initialrowcount = myPatientPage.textSearch();
	}

	@When("User clears the search text on the MyPatients table")
	public void user_clears_the_search_text_on_the_my_patients_table() {
		myPatientPage.clearSearch();
	}

	@Then("All patient records should be displayed again on the MyPatients table")
	public void all_patient_records_should_be_displayed_again_on_the_my_patients_table() {
		int rowcountafterclear = myPatientPage.getPatientCount();

		Assert.assertEquals(initialrowcount, rowcountafterclear, "Mismatch is table row count after clearing search");

	}

	@Given("Patient records are not added for that user in MyPatients page")
	public void patient_records_are_not_added_for_that_user_in_my_patients_page() {
		Assert.assertTrue(myPatientPage.checkingTableEmpty(), "Table is not empty");
	}

	@Then("My Patients page should display with empty table")
	public void my_patients_page_should_display_with_empty_table() {
		Assert.assertTrue(myPatientPage.checkingTableEmpty(), "table has patient reocrds");

	}

	@Given("Patient has multiple records already exist in the system for that user in MyPatients page")
	public void patient_has_multiple_records_already_exist_in_the_system_for_that_user_in_my_patients_page() {
		Assert.assertTrue(myPatientPage.checkingMultipleRecords(), "table does not have multiple patient records");
	}

	@Given("User is in {string} in My Patients table")
	public void user_is_in_in_my_patients_table(String precondition) {
		myPatientPage.navigateToPrecondition(precondition);
		logger.info("Setting up the table as per precondiion page: {}", precondition);
	}

	@When("User clicks the {string} page arrow in My Patients table")
	public void user_clicks_the_page_arrow_in_my_patients_table(String arrow) {
		logger.info("user clicks the '{}' pagination arrow ", arrow);

		myPatientPage.clickPaginationArrow(arrow);
	}

	@Then("{string} should be displayed for user in My Patients table")
	public void should_be_displayed_for_user_in_my_patients_table(String expectedResult) {

		boolean isCorrect = myPatientPage.verifyPaginationResult(expectedResult);

		Assert.assertTrue(isCorrect, "Pagination verification failed for: " + expectedResult);

		logger.info("Pagination result '{}' verified successfully.", expectedResult);
	}

	@When("User clicks any page navigation arrow in My Patients table")
	public void user_clicks_any_page_navigation_arrow_in_my_patients_table() {

		myPatientPage.clickAnyPageNavigationArrow();
		logger.info("User clicked page navigation arrow");
	}

	@Then("Pagination text should display the correct range and total number of patients in My Patients table")
	public void pagination_text_should_display_the_correct_range_and_total_number_of_patients_in_my_patients_table() {
		Assert.assertTrue(myPatientPage.PaginationtextCheck(), "Pagination text is not correct");
	}

	@When("User navigates to any page")
	public void user_navigates_to_any_page() {
		myPatientPage.clickAnyPageNavigationArrow();
		logger.info("User navigated to next page");
	}

	@Then("Pagination controls should be displayed")
	public void pagination_controls_should_be_displayed() {
		Assert.assertTrue(myPatientPage.checkPaginationControlsDisplayed(), "Pagination controls are not displayed");
	}

	@Given("User is on first page with multiple pages of patient record in My Patients table")
	public void user_is_on_first_page_with_multiple_pages_of_patient_record_in_my_patients_table() {
		Assert.assertTrue(myPatientPage.checkingMultipleRecords(), "Multiple records do not exist");
		logger.info("User is on first page with multiple records");
	}

	@When("User navigates to the first page of patient record in My Patients table")
	public void user_navigates_to_the_first_page_of_patient_record_in_my_patients_table() {
		myPatientPage.navigateToFirstPage();
		logger.info("User navigated to first page");
	}

	@Then("{string} arrow should be displayed in this {string} in My Patients table")
	public void arrow_should_be_displayed_in_this_in_my_patients_table(String arrow, String state) {
		Assert.assertTrue(myPatientPage.checkArrowInCorrectState(arrow, state),
				"pagination arrow " + arrow + " state " + state);
	}

	@When("User navigates to any page after the first page in My Patients table")
	public void user_navigates_to_any_page_after_the_first_page_in_my_patients_table() {
		myPatientPage.navigateToAnyPageOtherThanFirst();
		logger.info("User navigated to second page");
	}

	@Then("Pagination {string} arrow should be {string} in My Patients table")
	public void pagination_arrow_should_be_in_my_patients_table(String arrow, String state) {
		Assert.assertTrue(myPatientPage.checkArrowInCorrectState(arrow, state),
				"pagination arrow " + arrow + " state " + state);
	}

	@When("User navigates to any page except the last page in My Patients table")
	public void user_navigates_to_any_page_except_the_last_page_in_my_patients_table() {
		myPatientPage.navigateToFirstPage();
		logger.info("User navigated to first page for scenario  any page except the last page ");
	}

	@Then("{string} arrow should be {string} in My Patients table")
	public void arrow_should_be_in_my_patients_table(String arrow, String state) {
		Assert.assertTrue(myPatientPage.checkArrowInCorrectState(arrow, state),
				"pagination arrow " + arrow + " state " + state);
	}

	@When("User navigates to the last page of patient record in My Patients table")
	public void user_navigates_to_the_last_page_of_patient_record_in_my_patients_table() {
		myPatientPage.navigateToLastPage();
		logger.info("User navigated to last page");
	}

	@Then("{string} arrow should be dispalyed as {string} in My Patients table")
	public void arrow_should_be_dispalyed_as_in_my_patients_table(String arrow, String state) {
		Assert.assertTrue(myPatientPage.checkArrowInCorrectState(arrow, state),
				"pagination arrow " + arrow + " state " + state);
	}

	@Given("Patient has only one record in the system for that user in MyPatients page")
	public void patient_has_only_one_record_in_the_system_for_that_user_in_my_patients_page() {
		Assert.assertTrue(myPatientPage.tablewithOneRecord(), "found more than 1 record");

	}

	@Then("Pagination {string} arrows should be disabled in My Patient table")
	public void pagination_arrows_should_be_disabled_in_my_patient_table(String arrow) {
		Assert.assertTrue(myPatientPage.isArrowDisabled(arrow), arrow + " arrow should be disabled , but is enabled");
	}

	@Given("No patient record present  in the system for that user in MyPatients table")
	public void no_patient_record_present_in_the_system_for_that_user_in_my_patients_table() {
		Assert.assertTrue(myPatientPage.tablewithNoRecords(), "found patient records");
	}

	@Then("{string} should be displayed in MyPatients table")
	public void should_be_displayed_in_my_patients_table(String expectedmessage) {
		Assert.assertTrue(myPatientPage.PaginationTextEqualCheck(expectedmessage), "Pagination text not matching. ");

	}

	@Then("{string} arrows should be disabled in MyPatients table")
	public void arrows_should_be_disabled_in_my_patients_table(String arrow) {
		Assert.assertTrue(myPatientPage.isArrowDisabled(arrow), arrow + " arrow is enabled");
	}

	@Given("Patient has maximum records present in the system for that user in MyPatients table")
	public void patient_has_maximum_records_present_in_the_system_for_that_user_in_my_patients_table() {
		Assert.assertTrue(myPatientPage.tablewithMaximumRecords(), "5 records not present");

	}

	@Then("User should see only five records in each page")
	public void user_should_see_only_five_records_in_each_page() {
		Assert.assertTrue(myPatientPage.isOnlyFiveRecordsDisplayed(), "5 records not found ");

	}

	@Given("User is in My Patients table displays maximum of five records per page")
	public void user_is_in_my_patients_table_displays_maximum_of_records_per_page() {
		lastRecordId = myPatientPage.getLastRecordId();
		LoggerLoad.info("Stored last record id"+lastRecordId);
	}

	@When("User adds 6th record")
	public void user_adds_6th_record() {
		//check this 
		logger.info("sixth record added");
	}

	@Then("User should see the newly added record in the next page")
	public void user_should_see_the_newly_added_record_in_the_next_page() {

		Assert.assertTrue(myPatientPage.isNewRecordOnNextPage(lastRecordId), "Newly added record is not on next page");
	}

	@When("User clicks View Previous Test Reports under action column in MyPatients table")
	public void user_clicks_view_previous_test_reports_under_action_column_in_my_patients_table() {
		myPatientPage.clickViewPreviousTestReports();

	}

	@Then("User should be navigated to {string} page from MyPatients table")
	public void user_should_be_navigated_to_page_from_my_patients_table(String expectedPageHeader) {
		Assert.assertTrue(myPatientPage.isNavigatedToViewPatientTestReportsPage(),
				"User is not navigated to " + expectedPageHeader + " page");
		Assert.assertEquals(myPatientPage.ViewPatientTestReportsPageHeader(), expectedPageHeader,
				"Page header does not match");
	}

}
