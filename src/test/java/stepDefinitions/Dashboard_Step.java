package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.Dashboard_PageObject;

public class Dashboard_Step {
	
	Dashboard_PageObject dashboardObj = new Dashboard_PageObject();
	
	@Given("User is on the login page of Dietician application")
	public void user_is_on_the_login_page_of_dietician_application() {
	    
	    
	}

	@When("User clicks login button after entering valid credentials")
	public void user_clicks_login_button_after_entering_valid_credentials() {
	    
	    
	}

	@Then("Navigation bar of Dashboard page should display exactly four links {string}, {string}, {string}, and {string}")
	public void navigation_bar_of_dashboard_page_should_display_exactly_four_links_and(String string, String string2, String string3, String string4) {
	    
	    
	}

	@When("User clicks the {string} in the navigation bar of Dashboard page")
	public void user_clicks_the_in_the_navigation_bar_of_dashboard_page(String field) {
		dashboardObj.clickElement(field);
	}

	@Then("User should be redirected to the {string} page from dashboard page")
	public void user_should_be_redirected_to_the_page_from_dashboard_page(String string) {
	    
	    
	}

	@Then("User should be logged out of the application from Dashboard page")
	public void user_should_be_logged_out_of_the_application_from_dashboard_page() {
	    
	    
	}

	@Then("User should be navigated to the dashboard page")
	public void user_should_be_navigated_to_the_dashboard_page() {
	    
	    
	}

}
