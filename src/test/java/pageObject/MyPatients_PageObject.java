package pageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.LoggerLoad;
import utils.TestContext;
import utils.WaitUtils;

public class MyPatients_PageObject {
	WebDriver driver;
	 private WaitUtils waitUtils;
	
	 TestContext context;
	 public MyPatients_PageObject(WebDriver driver){ 
	        this.driver = driver;
	        this.waitUtils = new WaitUtils(driver);
	        PageFactory.initElements(driver, this);
	    }
	 
	private static final Logger logger = LoggerFactory.getLogger(MyPatients_PageObject.class);

	@FindBy(xpath = "//h1[text()='My Patients']")
	private WebElement pagetitle;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchbar;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchicon;

	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement searchplaceholder;

	@FindBy(xpath = "//table//th")
	private List<WebElement> columnheaders;

	@FindBy(xpath = "//th[contains(text(),'Patient Id')]")
	private WebElement patientIdheader;

	@FindBy(xpath = "//th[contains(text(),'Name')]")
	private WebElement nameheader;

	@FindBy(xpath = "//th[contains(text(),'Details')]")
	private WebElement detailsheader;

	@FindBy(xpath = "//th[contains(text(),'Last Visit Date')]")
	private WebElement lastvisitdateheader;

	@FindBy(xpath = "//th[contains(text(),'Actions')]")
	private WebElement actionsheader;

	@FindBy(xpath = "//th[contains(text(),'Edit/Delete')]")
	private WebElement editdeleteheader;

	@FindBy(xpath = "//th[contains(text(),'Patient Id')]//span[contains(@class,'up')]")
	private WebElement patientIduparrow;

	@FindBy(xpath = "//th[contains(text(),'Patient Id')]//span[contains(@class,'down')]")
	private WebElement patientIddownarrow;

	@FindBy(xpath = "//th[contains(text(),'Name')]//span[contains(@class,'up')]")
	private WebElement nameuparrow;

	@FindBy(xpath = "//th[contains(text(),'Name')]//span[contains(@class,'down')]")
	private WebElement namedownarrow;

	@FindBy(xpath = "//table//tbody//tr")
	private List<WebElement> tablerows;

	@FindBy(xpath = "//table//tbody//tr//td[1]")
	private List<WebElement> patientidcolumn;

	@FindBy(xpath = "//table//tbody//tr//td[2]")
	private List<WebElement> patientnamecolumn;

	@FindBy(xpath = "//table//tbody//tr//td[3]")
	private List<WebElement> detailscolumn;

	@FindBy(xpath = "//table//tbody//tr//td[4]")
	private List<WebElement> lastVisitdatecolumn;

	@FindBy(xpath = "//button[normalize-space()='View Previous Test Reports']")
	private List<WebElement> viewPreviousTestReportsButtons;

	@FindBy(xpath = "//button[normalize-space()='View Previous Diet Plans']")
	private List<WebElement> viewPreviousDietPlansButtons;

	@FindBy(xpath = "//button[normalize-space()='Create New Report/Plan']")
	private List<WebElement> createNewReportPlanButtons;

	@FindBy(xpath = "//table//tbody//tr//td[last()]//button[1]")
	private List<WebElement> editbutton;

	@FindBy(xpath = "//table//tbody//tr//td[last()]//button[2]")
	private List<WebElement> deletebutton;

	@FindBy(xpath = "//button[normalize-space()='>']")
	private WebElement nextPageArrow;

	@FindBy(xpath = "//button[normalize-space()='<']")
	private WebElement previousPageArrow;

	@FindBy(xpath = "//button[normalize-space()='<<']")
	private WebElement firstPageArrow;

	@FindBy(xpath = "//button[normalize-space()='>>']")
	private WebElement lastPageArrow;

	@FindBy(xpath = "//div[contains(text(),'Showing')]")
	private WebElement paginationText;

	@FindBy(xpath = "//h1[text()='View Patient Test Reports']")
	private WebElement viewPatientTestReportsHeader;
	
	 public String getCurrentUrl() {
	        String url = driver.getCurrentUrl();
	        LoggerLoad.info("mypatients page url is"+url);
	        return url;
	    }

	public boolean isPageTitleDisplayed() {
		boolean visible = pagetitle.isDisplayed();
		return visible;

	}

	public String getPageTitleText() {
		String textString = pagetitle.getText();
		return textString;

	}

	public boolean isSearchBarDisplayed() {
		boolean displayed = searchbar.isDisplayed();
		return displayed;

	}

	public boolean isSearchIconDisplayed() {
		boolean icondisplay = searchicon.isDisplayed();
		return icondisplay;

	}

	public boolean isSearchPlaceholderTextDisplayed() {
		boolean placeholder = searchplaceholder.isDisplayed();
		return placeholder;

	}

	public String getPlaceHolderText() {
		String text = searchplaceholder.getAttribute("placeholder");
		return text;

	}

	public List<String> getAllColumnHeaderTexts() {

		List<String> headers = new ArrayList<>();

		for (WebElement header : columnheaders) {
			headers.add(header.getText());
		}

		LoggerLoad.info("Column headers found:"+headers);

		return headers;
	}

	public boolean isUpArrowDisplayed(String columnName) {

		switch (columnName) {
		case "Patient Id":
			boolean displayed = patientIduparrow.isDisplayed();
			LoggerLoad.info("Patient Id up arrow displayed"+ displayed);
			
			return displayed;
		case "Name":
			boolean nameDisplayed = nameuparrow.isDisplayed();
			LoggerLoad.info("Name up arrow displayed"+nameDisplayed);
			return nameDisplayed;
		default:
			LoggerLoad.error("Unknown column name"+columnName);
			
			return false;
		}

	}

	public boolean isDownArrowDisplayed(String columnName) {

		switch (columnName) {
		case "Patient Id":
			boolean displayed = patientIddownarrow.isDisplayed();
			
			return displayed;
		case "Name":
			boolean nameDisplayed = namedownarrow.isDisplayed();
			
			return nameDisplayed;
		default:
			LoggerLoad.error("Unknown column name"+columnName);
			return false;

		}

	}

	public boolean checkAllColumnshasValues() {
		for (WebElement patientid : patientidcolumn) {
			if (patientid.getText().isEmpty()) {
				
				return false;
			}
		}
		for (WebElement patientname : patientnamecolumn) {
			if (patientname.getText().isEmpty()) {
				
				return false;
			}
		}
		for (WebElement detail : detailscolumn) {
			if (detail.getText().isEmpty()) {
				
				return false;
			}
		}
		for (WebElement lastvisitDate : lastVisitdatecolumn) {
			if (lastvisitDate.getText().isEmpty()) {
				
				return false;
			}
		}
		LoggerLoad.info("All columns have values");
		return true;
	}

	public boolean isPatientIdDisplayedForAllRows() {
		for (WebElement patientId : patientidcolumn) {
			if (!patientId.isDisplayed() || patientId.getText().isEmpty()) {
				return false;
			}
		}
		LoggerLoad.info("Patient ID displayed for all rows");
		return true;
	}

	public boolean isPatientnameDisplayedForAllRows() {
		for (WebElement patientName : patientnamecolumn) {
			if (!patientName.isDisplayed() || patientName.getText().isEmpty()) {
				return false;
			}
		}
		logger.info("Patient name displayed for all rows");
		return true;
	}

	public boolean detailsColumnInfo(List<String> expectedfields) {

		for (WebElement detail : detailscolumn) {

			String text = detail.getText();
			String[] lines = text.split("\n");

			String phone = lines[0].trim();
			String email = lines[1].trim();
			String date = lines[2].trim();

			if (!phone.matches("\\d{10}")) {
				return false;
			}

			if (!email.matches(".+@.+\\..+")) {
				return false;
			}

			if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
				return false;
			}
		}
		return true;
	}

	public boolean isDetailsDisplayedSeparateLines() {
		for (WebElement detail : detailscolumn) {
			String[] lines = detail.getText().split("\n");
			if (lines.length < 3) {
				LoggerLoad.error("Details not in separate lines"+detail.getText());
				
				return false;
			}
		}
		LoggerLoad.info("Details displayed in separate lines");
		return true;
	}

	public boolean FieldFormatValidation(String field, String format) {

		for (WebElement detail : detailscolumn) {

			String text = detail.getText();
			String[] lines = text.split("\n");

			switch (field) {

			case "Phone number":

				String phone = lines[0].trim();
				String digitsOnly = phone.replaceAll("\\D", "");
				if (digitsOnly.length() != 10) {
					
					return false;
				}
				break;

			case "Email":

				String email = lines[1].trim();
				if (!email.matches("^[A-Za-z0-9+_.-]+" + "@" + "[A-Za-z0-9.-]+" + "\\." + "[A-Za-z]{2,}$")) {
					
					return false;
				}
				break;

			case "Date of birth":

				String dob = lines[2].trim();
				if (!dob.matches("^(0[1-9]|[12]\\d|3[01])" + "-" + "(0[1-9]|1[0-2])" + "-" + "\\d{4}$")) {
					
					return false;
				}
				break;

			default:
				
				return false;
			}
		}

		
		return true;
	}

	public boolean lastVisitDateFormatCheck() {
		for (WebElement lastVisitDate : lastVisitdatecolumn) {
			if (!lastVisitDate.getText().trim().matches("\\d{2}-\\d{2}-\\d{4}")) {
				LoggerLoad.error("Invalid last visit date format:"+lastVisitDate.getText());
				
				return false;
			}
		}
		LoggerLoad.info("Last visit date format valid for all rows");
		return true;
	}

	public boolean buttonsinActionsRow(String buttonName) {
		List<WebElement> buttons;
		switch (buttonName) {
		case "View Previous Test Reports":
			buttons = viewPreviousTestReportsButtons;
			break;
		case "View Previous Diet Plans":
			buttons = viewPreviousDietPlansButtons;
			break;
		case "Create New Report/plan":
			buttons = createNewReportPlanButtons;
			break;
		default:
			
			return false;
		}
		for (WebElement button : buttons) {
			if (!button.isDisplayed()) {
				
				return false;
			}
		}
		LoggerLoad.info("Buttons in Action row displayed for all rows"+buttonName);
		return true;
	}

	public boolean actionsIconDisplayedForAllRows(String iconName) {
		List<WebElement> icons = iconName.equals("Edit") ? editbutton : deletebutton;
		for (WebElement icon : icons) {
			if (!icon.isDisplayed()) {
				
				return false;
			}
		}
		
		return true;
	}

	public void clickSortArrow(String column, String arrow) {
		if (column.equals("Patient Id")) {
			if (arrow.equals("up")) {
				patientIduparrow.click();
			} else {
				patientIddownarrow.click();
			}
		} else if (column.equals("Name")) {
			if (arrow.equals("up")) {
				nameuparrow.click();
			} else {
				namedownarrow.click();
			}
		}
		
	}

	public boolean isColumnSortedInOrder(String column, String order) {
		List<WebElement> columnCells = column.equals("Patient Id") ? patientidcolumn : patientnamecolumn;

		List<String> actualValues = columnCells.stream().map(WebElement::getText).collect(Collectors.toList());

		List<String> sortedValues = new ArrayList<>(actualValues);
		if (order.equals("ascending")) {
			Collections.sort(sortedValues);
		} else {
			sortedValues.sort(Collections.reverseOrder());
		}

		boolean sorted = actualValues.equals(sortedValues);
		
		return sorted;
	}

	// remove comment once excelclass is complete
	public void performSearchFromExcel(String scenarioType) {
		// 1. Load the data (if not already loaded in a hook)
		// ExcelReader.readDataFromExcel("Sheet1");

		// 2. Fetch the specific row using your reader's logic
		// Map<String, String> testData = ExcelReader.getTestData(scenarioType);

		// 3. Extract the search value based on your Excel column header
		// String searchdata = testData.get("searchdata");

		// 4. Perform the action
		// searchbar.clear();
		// searchbar.sendKeys(searchdata);

		// logger.info("Executed search for scenariotype"+scenarioType+"using search data"+
		// searchdata);
	}

	// public boolean verifySearchResultsMatchExcel(String scenarioType) {
	// Map<String, String> testData = ExcelReader.getTestData(scenarioType);
	// String expectedValue = testData.get("searchdata");

	// return tableRows.stream()
	// .allMatch(row ->
	// row.getText().toLowerCase().contains(expectedValue.toLowerCase()));
	// }

	public int textSearch() {

		int initialCount = tablerows.size();
		

		String dummyText = "Patient123";

		searchbar.clear();
		searchbar.sendKeys(dummyText);

		waitUtils.waitForVisibilityOfAll(tablerows);
	    logger.info("Search results loaded");

		return initialCount;
	}

	public void clearSearch() {
		searchbar.clear();

		searchbar.click();
		
	}

	
	public boolean checkingTableEmpty() {
		boolean tableempty = tablerows.size() == 0;
		logger.info("Table row count: {}", tablerows.size());
		return tableempty;
	}

	public boolean checkingMultipleRecords() {
		int rowCount = tablerows.size();
		logger.info("Total records in table: {}", rowCount);
		return rowCount > 1;
	}

	public void navigateToPrecondition(String precondition) {
		String text = precondition.toLowerCase();

		if (text.contains("later page")) {
			// going to desired page to satisfy precondition.not related to arrow column in
			// feature
			waitUtils.waitForClickable(nextPageArrow).click();
			 
		        // Wait for page to fully load
		  
		        logger.info("Moved to Page 2");

		} else if (text.contains("except first")) {

			// wait.until(ExpectedConditions.elementToBeClickable(lastPageArrow)).click();
			logger.info("Moved to Last Page");

		} else {
			// for multiple pages and except last
			logger.info("In first page");
		}

		// giving time for table contents to load
		// wait.until(ExpectedConditions.visibilityOfAllElements(patientidcolumn));
	}

	public void clickPaginationArrow(String arrow) {
		switch (arrow) {
		case ">" -> nextPageArrow.click();
		case "<" -> previousPageArrow.click();
		case "<<" -> firstPageArrow.click();
		case ">>" -> lastPageArrow.click();
		}
	}

	public boolean verifyPaginationResult(String result) {
		if (result.contains("Next") || result.contains("Previous")) {
			String fullText = paginationText.getText(); // e.g., "Showing 11 to 20 of 25 patients"
			String[] words = fullText.split(" ");

			// Word index 1 is "11" (The start number)
			// Word index 3 is "20" (The end number)
			String startNumber = words[1];

			// Logic: If we are on the NEXT page, the start number should NOT be "1"
			boolean isNotOnPageOne = !startNumber.equals("1");

			logger.info("Current start record is: {}. Is it page 1? {}", startNumber, !isNotOnPageOne);
			return isNotOnPageOne;

			// return patientidcolumn.size() > 0;
			// boolean isNotOnPageOne = !pageNumberIndicator.getText().equals("1");
		}

		if (result.contains("First")) {

			return !firstPageArrow.isEnabled() || firstPageArrow.getAttribute("class").contains("disabled");
		}

		if (result.contains("Last")) {

			return !lastPageArrow.isEnabled() || lastPageArrow.getAttribute("class").contains("disabled");
		}

		return false;
	}
	// *************************************8
	/*
	 * public boolean verifyPaginationResult(String result) { // Wait for the text
	 * to appear so we don't get an empty string
	 * wait.until(ExpectedConditions.visibilityOf(paginationText));
	 * 
	 * String fullText = paginationText.getText(); String[] words =
	 * fullText.split(" "); String startNumber = words[1];
	 * 
	 * // --- CASE: NEXT --- if (result.contains("Next")) { // We should NOT be at
	 * the start anymore boolean isMovedForward = !startNumber.equals("1") &&
	 * !startNumber.equals("0");
	 * logger.info("Next Check: Start number is {}. Moved forward? {}", startNumber,
	 * isMovedForward); return isMovedForward; }
	 * 
	 * // --- CASE: PREVIOUS or FIRST --- if (result.contains("Previous") ||
	 * result.contains("First")) { // We expect to be back at the beginning (Showing
	 * 1 to...) boolean isAtStart = startNumber.equals("1");
	 * 
	 * // Also check if the 'First' button is disabled (Double verification!) if
	 * (result.contains("First")) { isAtStart = isAtStart &&
	 * (!firstPageArrow.isEnabled() ||
	 * firstPageArrow.getAttribute("class").contains("disabled")); }
	 * 
	 * logger.info("Back to Start Check: Start number is {}. Is at start? {}",
	 * startNumber, isAtStart); return isAtStart; }
	 * 
	 * // --- CASE: LAST --- if (result.contains("Last")) { // The 'Last' arrow
	 * should now be disabled boolean isAtEnd = !lastPageArrow.isEnabled() ||
	 * lastPageArrow.getAttribute("class").contains("disabled");
	 * logger.info("Last Page Check: Is 'Last' arrow disabled? {}", isAtEnd); return
	 * isAtEnd; }
	 * 
	 * return false; }
	 */

	//

	public void clickAnyPageNavigationArrow() {
		nextPageArrow.click();
		logger.info("Clicked next page arrow");
	}

	public boolean PaginationtextCheck() {

		String text = paginationText.getText();
		logger.info("Pagination text is: {}", text);

		if (!text.contains("Showing")) {
			logger.error("Pagination text does not contain Showing");
			return false;
		}

		if (!text.contains("patients")) {
			logger.error("Pagination text does not contain patients");
			return false;
		}
		// Showing 0 to 0 of 0 patients
		// d+ -include 2 digit number too
		boolean formatmatch = text.matches("Showing \\d+ to \\d+ of \\d+ patients");
		logger.info("Pagination text format correct: {}", formatmatch);
		return formatmatch;
	}

	public boolean checkPaginationControlsDisplayed() {

		if (!nextPageArrow.isDisplayed()) {
			logger.error("arrow > not displayed");
			return false;
		}

		if (!previousPageArrow.isDisplayed()) {
			logger.error(" arrow < not displayed");
			return false;
		}

		if (!firstPageArrow.isDisplayed()) {
			logger.error("arrow << not displayed");
			return false;
		}

		if (!lastPageArrow.isDisplayed()) {
			logger.error(" arrow >> not displayed");
			return false;
		}

		if (!paginationText.isDisplayed()) {
			logger.error("Pagination text not displayed");
			return false;
		}

		logger.info("All pagination controls displayed");
		return true;
	}

	public WebElement getArrow(String arrow) {
		Map<String, WebElement> arrowMap = new HashMap<>();
		arrowMap.put(">", nextPageArrow);
		arrowMap.put("<", previousPageArrow);
		arrowMap.put("<<", firstPageArrow);
		arrowMap.put(">>", lastPageArrow);
		return arrowMap.get(arrow);
	}

	public void navigateToFirstPage() {
		firstPageArrow.click();
		logger.info("Navigated to first page");
	}

	public void navigateToAnyPageOtherThanFirst() {
		nextPageArrow.click();
		logger.info("Navigated to second page");
	}

	public void navigateToLastPage() {
		lastPageArrow.click();
		logger.info("Navigated to last page");
	}

	public boolean checkArrowInCorrectState(String arrow, String state) {
		boolean isEnabled = getArrow(arrow).isEnabled();
		boolean result = state.equals("enabled") ? isEnabled : !isEnabled;
		logger.info("Arrow {} expected {} : {}", arrow, state, result);
		return result;
	}

	public boolean tablewithOneRecord() {
		int rowCount = tablerows.size();
		logger.info("Total records in table: {}", rowCount);
		return rowCount == 1;
	}

	public boolean isArrowDisabled(String arrow) {

		WebElement arrowElement = getArrow(arrow);

		boolean isEnabled = arrowElement.isEnabled();

		boolean isDisabled = !isEnabled;

		logger.info("Arrow {} is disabled: {}", arrow, isDisabled);
		return isDisabled;
	}

	public boolean tablewithNoRecords() {
		int rowCount = tablerows.size();
		logger.info("Total records in table: {}", rowCount);
		return rowCount == 0;
	}

	public boolean PaginationTextEqualCheck(String expectedmessage) {
		String actualText = paginationText.getText();
		boolean matches = actualText.equals(expectedmessage);
		return matches;
	}

	public boolean tablewithMaximumRecords() {
		int rowCount = tablerows.size();
		logger.info("Total records in table: {}", rowCount);
		return rowCount == 5;
	}

	public boolean isOnlyFiveRecordsDisplayed() {
		int rowCount = tablerows.size();
		boolean fiveRecords = rowCount == 5;
		logger.info("Max Records displayed on page: {}", rowCount);
		return fiveRecords;
	}

	public String getLastRecordId() {

		String lastId = patientidcolumn.get(patientidcolumn.size() - 1).getText();
		logger.info("Last record id: {}", lastId);
		return lastId;
	}

	public boolean isNewRecordOnNextPage(String lastRecordId) {

		nextPageArrow.click();

		if (tablerows.isEmpty()) {
			logger.error("Next page has no records");
			return false;
		}

		String firstRecordOnNextPage = patientidcolumn.get(0).getText();
		logger.info("First record on next page: {}", firstRecordOnNextPage);

		boolean isNewRecord = Integer.parseInt(firstRecordOnNextPage) > Integer.parseInt(lastRecordId);
		logger.info("New record on next page: {}", isNewRecord);
		return isNewRecord;
	}

	public void clickViewPreviousTestReports() {
		viewPreviousTestReportsButtons.get(0).click();
		logger.info("Clicked View Previous Test Reports");
	}

	public boolean isNavigatedToViewPatientTestReportsPage() {
		boolean displayed = viewPatientTestReportsHeader.isDisplayed();
		logger.info("View Patient Test Reports navigated ");
		return displayed;
	}
	
	

	public String ViewPatientTestReportsPageHeader() {
		String headerText = viewPatientTestReportsHeader.getText();
		logger.info("Page header text: {}", headerText);
		return headerText;
	}
	
	public int getPatientCount() {
		int count=tablerows.size();
		System.out.println("Total patients in table: " + count);
		return count;
		
	}

}
