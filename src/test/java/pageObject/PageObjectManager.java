package pageObject;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	public WebDriver driver;
	public Login_PageObject loginPage;
	public Dashboard_PageObject dashboardPage;
	public MyPatients_PageObject myPatientPage;
	public AddPatient_PageObject addPatientPage;
	public EditPatient_PageObject editPatientPage;
	public ViewTestReport_PageObject viewReportPage;
	public DeletePatient_PageObject deletePatientPage;
	
		
	public PageObjectManager (WebDriver driver) {
		this.driver = driver;
	}
	
	public Login_PageObject getLoginPage()
	{
	loginPage= new Login_PageObject(driver);
	 return loginPage;
	}
	
	public Dashboard_PageObject getDashboardPage()
	{
	dashboardPage= new Dashboard_PageObject(driver);
	 return dashboardPage;
	}
	
	public MyPatients_PageObject getMyPatientPage()
	{
		myPatientPage= new MyPatients_PageObject(driver);
		 return myPatientPage;	}

	public AddPatient_PageObject getAddPatientPage()
	{
	addPatientPage= new AddPatient_PageObject(driver);
	 return addPatientPage;
	}
	
	public EditPatient_PageObject getEditPatientPage()
	{
		editPatientPage= new EditPatient_PageObject(driver);
	 return editPatientPage;
	}
	
	public DeletePatient_PageObject getDeletePatientPage()
	{
	deletePatientPage= new DeletePatient_PageObject(driver);
	 return deletePatientPage;
	}

	public ViewTestReport_PageObject getViewTestReportPage()
	{
	viewReportPage= new ViewTestReport_PageObject(driver);
	 return viewReportPage;
	}


	
}
