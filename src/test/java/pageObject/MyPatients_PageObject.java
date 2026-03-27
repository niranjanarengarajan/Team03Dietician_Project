package pageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ExcelReader;
import utils.LoggerLoad;
import utils.TestContext;
import utils.WaitUtils;

public class MyPatients_PageObject {
	WebDriver driver;
	private WaitUtils waitUtils;

	TestContext context;

	public MyPatients_PageObject(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		PageFactory.initElements(driver, this);
	}

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
		LoggerLoad.info("mypatients page url is" + url);
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

		LoggerLoad.info("Column headers found:" + headers);

		return headers;
	}

	public boolean isUpArrowDisplayed(String columnName) {

		switch (columnName) {
		case "Patient Id":
			boolean displayed = patientIduparrow.isDisplayed();
			LoggerLoad.info("Patient Id up arrow displayed" + displayed);

			return displayed;
		case "Name":
			boolean nameDisplayed = nameuparrow.isDisplayed();
			LoggerLoad.info("Name up arrow displayed" + nameDisplayed);
			return nameDisplayed;
		default:
			LoggerLoad.error("Unknown column name" + columnName);

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
			LoggerLoad.error("Unknown column name" + columnName);
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
		LoggerLoad.info("Patient name displayed for all rows");
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
				LoggerLoad.error("Details not in separate lines" + detail.getText());

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
				LoggerLoad.error("Invalid last visit date format:" + lastVisitDate.getText());

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
		LoggerLoad.info("Buttons in Action row displayed for all rows" + buttonName);
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

	public void performSearchFromExcel(String scenarioType) {

		Map<String, String> testData = ExcelReader.getTestData(scenarioType);

		String searchdata = testData.get("Searchdata");

		searchbar.clear();
		searchbar.sendKeys(searchdata);

		LoggerLoad.info("Executed search for scenariotype" + scenarioType);
	}

	public boolean verifySearchResultsMatchExcel(String scenarioType) {

		Map<String, String> testData = ExcelReader.getTestData(scenarioType);
		String expectedValue = testData.get("Searchdata");

		if (tablerows.isEmpty()) {
			LoggerLoad.error("No records found or search data " + expectedValue);
			return false;
		}

		boolean result = tablerows.stream()
				.allMatch(row -> row.getText().toLowerCase().contains(expectedValue.toLowerCase()));

		return result;
	}

	public int textSearch() {

		int initialCount = tablerows.size();

		String dummyText = "Test";

		searchbar.clear();
		searchbar.sendKeys(dummyText);

		waitUtils.waitForVisibilityOfAll(tablerows);
		LoggerLoad.info("Search results loaded");

		return initialCount;
	}

	public void clearSearch() {
		searchbar.clear();

		searchbar.click();

	}

	public boolean checkingTableEmpty() {
		boolean tableempty = tablerows.size() == 0;

		return tableempty;
	}

	public boolean checkingMultipleRecords() {
		int rowCount = tablerows.size();

		return rowCount > 1;
	}

	public void navigateToPrecondition(String precondition) {
		String text = precondition.toLowerCase();

		waitUtils.waitForVisible(paginationText);

		switch (text) {
		case "later page":

			if (nextPageArrow.isEnabled()) {
				waitUtils.waitForClickable(nextPageArrow).click();
				LoggerLoad.info("Precondition: Moved to a later page - (Page 2");
			}
			break;

		case "any except first":

			if (lastPageArrow.isEnabled()) {
				waitUtils.waitForClickable(lastPageArrow).click();
				LoggerLoad.info("Precondition: Moved to the last page");
			}
			break;

		case "any except last":
		case "multiple pages":

			if (firstPageArrow.isEnabled()) {
				waitUtils.waitForClickable(firstPageArrow).click();
			}
			LoggerLoad.info("Precondition: On the first page ( multiple/except last)");
			break;

		default:
			LoggerLoad.warn("Unknown precondition: " + precondition + ". Staying on current page.");
		}

		waitUtils.waitForVisibilityOfAll(patientidcolumn);
	}

	public void clickPaginationArrow(String arrow) {
		switch (arrow) {
		case ">" -> nextPageArrow.click();
		case "<" -> previousPageArrow.click();
		case "<<" -> firstPageArrow.click();
		case ">>" -> lastPageArrow.click();
		}
	}

	public boolean verifyPaginationResult(String expectedResult) {
	    String text = expectedResult.toLowerCase();
	    
	    
	    waitUtils.waitForVisibilityOfAll(patientidcolumn);
	    
	    switch (text) {
	        case "next set":
	            //noton page 1 
	            // arrow << is enabled
	            return firstPageArrow.isEnabled();

	        case "previous set":
	            
	            // arrow >> enabled
	            return lastPageArrow.isEnabled();

	        case "first page":
	            // first << and Previous < arrows must be disabled
	            return !firstPageArrow.isEnabled() && !previousPageArrow.isEnabled();

	        case "last page":
	            // Last >> and Next > arrows must be disabled
	            return !lastPageArrow.isEnabled() && !nextPageArrow.isEnabled();

	        default:
	            LoggerLoad.error("Unknown expected result: " + expectedResult);
	            return false;
	    }
	}
	
	public void clickAnyPageNavigationArrow() {
		nextPageArrow.click();

	}

	public boolean PaginationtextCheck() {

		String text = paginationText.getText();

		if (!text.contains("Showing")) {

			return false;
		}

		if (!text.contains("patients")) {

			return false;
		}
		// Showing 0 to 0 of 0 patients
		// d+ -include 2 digit number too
		boolean formatmatch = text.matches("Showing \\d+ to \\d+ of \\d+ patients");

		return formatmatch;
	}

	public boolean checkPaginationControlsDisplayed() {

		if (!nextPageArrow.isDisplayed()) {

			return false;
		}

		if (!previousPageArrow.isDisplayed()) {

			return false;
		}

		if (!firstPageArrow.isDisplayed()) {

			return false;
		}

		if (!lastPageArrow.isDisplayed()) {

			return false;
		}

		if (!paginationText.isDisplayed()) {

			return false;
		}

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

	}

	public void navigateToAnyPageOtherThanFirst() {
		nextPageArrow.click();

	}

	public void navigateToLastPage() {
		lastPageArrow.click();

	}

	public boolean checkArrowInCorrectState(String arrow, String state) {
		boolean isEnabled = getArrow(arrow).isEnabled();
		boolean result = state.equals("enabled") ? isEnabled : !isEnabled;

		return result;
	}

	public boolean tablewithOneRecord() {
		int rowCount = tablerows.size();

		return rowCount == 1;
	}

	public boolean isArrowDisabled(String arrow) {

		WebElement arrowElement = getArrow(arrow);

		boolean isDisabled = !arrowElement.isEnabled();

		return isDisabled;
	}

	public boolean tablewithNoRecords() {
		int rowCount = tablerows.size();

		return rowCount == 0;
	}

	public boolean PaginationTextEqualCheck(String expectedmessage) {
		String actualText = paginationText.getText();
		boolean matches = actualText.equals(expectedmessage);
		return matches;
	}

	public boolean tablewithMaximumRecords() {
		int rowCount = tablerows.size();

		return rowCount == 5;
	}

	public boolean isOnlyFiveRecordsDisplayed() {
		int rowCount = tablerows.size();
		boolean fiveRecords = rowCount == 5;

		return fiveRecords;
	}

	public String getLastRecordId() {

		String lastId = patientidcolumn.get(patientidcolumn.size() - 1).getText();

		return lastId;
	}

	public boolean isNewRecordOnNextPage(String lastRecordId) {

		nextPageArrow.click();

		if (tablerows.isEmpty()) {

			return false;
		}

		String firstRecordOnNextPage = patientidcolumn.get(0).getText();

		boolean isNewRecord = Integer.parseInt(firstRecordOnNextPage) > Integer.parseInt(lastRecordId);

		return isNewRecord;
	}

	public void clickViewPreviousTestReports() {
		viewPreviousTestReportsButtons.get(0).click();

	}

	public boolean isNavigatedToViewPatientTestReportsPage() {
		boolean displayed = viewPatientTestReportsHeader.isDisplayed();

		return displayed;
	}

	public String ViewPatientTestReportsPageHeader() {
		String headerText = viewPatientTestReportsHeader.getText();

		return headerText;
	}

	public int getPatientCount() {
		int count = tablerows.size();
		LoggerLoad.info("Total patients in table: " + count);
		return count;

	}

}
