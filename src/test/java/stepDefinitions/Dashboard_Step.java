package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.*;
import pageObject.Dashboard_PageObject;
import pageObject.PageObjectManager;
import utils.ExcelReader;
import utils.TestContext;

public class Dashboard_Step {
	public TestContext context;
	Dashboard_PageObject dashboardObj;
	PageObjectManager poManager;
	
	public Dashboard_Step(TestContext context){
		this.context=context;
		this.dashboardObj = context.poManager.getDashboardPage();
	}

	@Then("Navigation bar of Dashboard page should display exactly four links {string}, {string}, {string}, and {string}")
	public void navigation_bar_of_dashboard_page_should_display_exactly_four_links_and(String myPat, String newPat, String login, String logout) {
		assertTrue(dashboardObj.linksCount(myPat, newPat, login, logout));
	}

	@When("User clicks the {string} in the navigation bar of Dashboard page")
	public void user_clicks_the_in_the_navigation_bar_of_dashboard_page(String field) {
		dashboardObj.clickElement(field);
	}

	@Then("User should be redirected to the {string} page from dashboard page")
	public void user_should_be_redirected_to_the_page_from_dashboard_page(String scenario) {
		TestContext.testData = ExcelReader.getTestData(scenario);
		String expected = TestContext.testData.get("URL");
		String actual = dashboardObj.getPageUrl();
	    assertEquals(actual, expected);
	}

}
