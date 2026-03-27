
package pageObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerLoad;

public class ViewTestReport_PageObject {
	
	private WebDriver driver;
	private WebElement element;
	
	public ViewTestReport_PageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//a[text()='My Patients']") 
	private WebElement myPatientLink;
	
	@FindBy (xpath="//table[@id='patientTable']//tr/td[1]") 
	private List<WebElement> patientIdList;
	
	@FindBy (xpath="//h2[@class='reportTitle']") 
	private WebElement viewTestReportTitle;
	
	@FindBy (id="patienId")
	private WebElement patientId;
	
	@FindBy (id="patientName")
	private WebElement patientName;
	
	@FindBy (id="patientEmail")
	private WebElement email;
	
	@FindBy (id="patientContact")
	private WebElement contactNum;
	
	@FindBy (xpath = "//*[name()='svg']//*[name()='closeIcon']") 
	private WebElement closeIcon;
	
	@FindBy (xpath="//table[@id='reportTable']") 
	private WebElement reportTable;
	
	@FindBy (xpath="//table[@id='reportTable']//th") 
	private List<WebElement> reportTableHeader;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[1]") 
	private List<WebElement> recordNumber;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[2]") 
	private List<WebElement> viewPdfBtn;
	
	@FindBy (xpath="//table[@id='reportTable']//tr[1]/td[2]") 
	private WebElement viewPdfBtnRecord;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[3]") 
	private List<WebElement> uploadedTime;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[4]") 
	private List<WebElement> fileReportName;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[5]") 
	private List<WebElement> vitalsInfo;
	
	@FindBy (xpath="//table[@id='reportTable']//tr[1]/td[5]") 
	private WebElement vitalsInfoRecord;
	
	@FindBy (xpath="//table[@id='reportTable']//tr/td[6]") 
	private List<WebElement> identifiedHealthCondition;
	
	@FindBy (xpath="//table[@id='reportTable']//tr[1]/td[6]") 
	private WebElement identifiedHealthConditionRecord;
	
	@FindBy (xpath="//ul[@class='pagination']")
	private WebElement pagination;
	
	@FindBy (xpath="//ul[@class='pagination']//a[@class='nextPageArrow']")
	private WebElement nextPageArrow;
	
	@FindBy (xpath="//ul[@class='pagination']//a[@class='previousPageArrow']")
	private WebElement previousPageArrow;
	
	@FindBy (xpath="//ul[@class='pagination']//a[@class='firstPageArrow']")
	private WebElement firstPageArrow;
	
	@FindBy (xpath="//ul[@class='pagination']//a[@class='lastPageArrow']")
	private WebElement lastPageArrow;

	public void clickMypatient() {
		myPatientLink.click();
	}
	
	public void clickPreviousTestReport(String patientId) {
		for (WebElement id : patientIdList) {
			if(id.getText().equals(patientId)) {
				String reportsXpath = String.format("//tr/td[text()='%s']/..//button[@class='viewPreviousReports']", patientId);
			    driver.findElement(By.xpath(reportsXpath)).click();
				break;
			}
		}
	}
	
	public String validateRecord(String scenario) {
		String value = null;
		switch (scenario) {
		case "Patient id":
			element = patientId;
			break;
		case "Patient name":
			element = patientName;
			break;
		case "Patient email":
			element = email;
			break;
		case "Patient contact number":
			element = contactNum;
			break;
		}
		value=element.getText();
		return value;
	}
	
	public boolean isDisplayed(String field) {
		switch (field) {
		case "Title View Patient Test Reports":
			element = viewTestReportTitle;
			break;
		case "Close icon x":
			element = closeIcon;
			break;
		case "Reports table":
			element = reportTable;
			break;
		case "Pagination controls":
			element = pagination;
			break;
		}
		return element.isDisplayed();
	}
	
	public List<String> getHeaderTexts() {
		return reportTableHeader.stream()
				.map(WebElement::getText)
				.map(String::trim)
				.collect(Collectors.toList());
	}
	
	public List<String> reportHeader(String allHeader) {
		String[] allHeaderArray = allHeader.split(",");
		return Arrays.stream(allHeaderArray)
				.map(String::trim)
				.collect(Collectors.toList());
	}

	
	public boolean reportValidation(String field) {
		List<WebElement> element = null;
		switch (field) {
		case "Record number":
			element = recordNumber;
			break;
		case "View PDF button":
			element = viewPdfBtn;
			break;
		case "Uploaded time":
			element = uploadedTime;
			break;
		case "File/Report name":
			element = fileReportName;
			break;
		case "Vitals information":
			element = vitalsInfo;
			break;
		case "Identified Health Conditions":
			element = identifiedHealthCondition;
			break;
		}
		for(WebElement ele : element) {
			if(!ele.isDisplayed()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean orderValidation(String expOrder) {
		String innerhtml = vitalsInfoRecord.getAttribute("innerHTML");
		if(!innerhtml.contains("<br>")) {
			return false;
		}
		String[] multiLine = innerhtml.split("<br>");
		List<String> singleline =  Arrays.stream(multiLine)
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
		String[] expOrderArray = expOrder.split(",");
		List<String> singlelineExpected =  Arrays.stream(expOrderArray)
				.map(String::trim)
				.collect(Collectors.toList());
		for (int i=0; i<singlelineExpected.size(); i++) {
			String actual = singleline.get(i);
			String expected = singlelineExpected.get(i);
			if(!actual.contains(expected)) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean multilineValidation(String scenario) {
		switch (scenario) {
		case "Vitals in reports":
			element = vitalsInfoRecord;
			break;
		case "Identified health conditions in reports":
			element = identifiedHealthConditionRecord;
			break;
		}
		String innerhtml = element.getAttribute("innerHTML");
		if(!innerhtml.contains("<br>")) {
			return false;
		}
		return true;
	}
	
	public void clickReportsPdf() {
		viewPdfBtnRecord.click();
	}
	
	public void paginationNavGiven(String scenario) {
		switch (scenario) {
		case "except last":
			LoggerLoad.info("User is first page of view test report table.");
			break;
		case "except first":
			nextPageArrow.click();
			break;
		}
	}
	
	public void paginationNavWhen(String scenario) {
		switch (scenario) {
		case "next":
			element = nextPageArrow;
			break;
		case "previous":
			element = previousPageArrow;
			break;
		case "first":
			element = firstPageArrow;
			break;
		case "last":
			element = lastPageArrow;
			break;
		}
		element.click();
	}
	
	public boolean setOfPatients(String action) {
		switch (action) {
		case "previous":
		case "first":
			for (WebElement ele : recordNumber) {
				String recNum = ele.getText().trim();
		        if(!(recNum.equals("1") || recNum.equals("2"))) {
		        	return false;
		        }
		    }
			break;
		case "next":
		case "last":
			for (WebElement ele : recordNumber) {
				String recNum = ele.getText().trim();
		        if(!(recNum.equals("3") || recNum.equals("4"))){
		        	return false;
		        }
		    }
			break;
		}
		return true;
	}
	
	public void clickNextArrow() {
		nextPageArrow.click();
	}
	
	public String paginationText() {
		String text = pagination.getText();
		return text;
	}
	
	public boolean paginationEnabledDisabled(String field, String status) {
		switch (field) {
		case "Previous page arrow":
			element = previousPageArrow;
			break;
		case "First page arrow":
			element = firstPageArrow;
			break;
		case "Next page arrow":
			element = nextPageArrow;
			break;
		case "Last page arrow":
			element = lastPageArrow;
			break;
		}
		if (status.equalsIgnoreCase("enabled")) {
			return element.isEnabled();
		}
		else if (status.equalsIgnoreCase("disabled")) {
			return !element.isEnabled();
		}
		return false;
	}
	
	public boolean disablePagination() {
		if(!(previousPageArrow.isEnabled()&&firstPageArrow.isEnabled()&&nextPageArrow.isEnabled()&&lastPageArrow.isEnabled())) {
			return true;
		}
		return false;
	}
	
	public boolean numOfRecords(int expCount) {
		return (recordNumber.size()==expCount);
	}
	
	public void clicklastPageArrow() {
		lastPageArrow.click();
	}
	
	public boolean displayPagination() {
		if(!(previousPageArrow.isDisplayed()&&firstPageArrow.isDisplayed()&&nextPageArrow.isDisplayed()&&lastPageArrow.isDisplayed())) {
			return true;
		}
		return false;
	}
	
	public String getPageUrl() {
		String url = null;
		url = driver.getCurrentUrl();
		return url;
	}
}

