package stepDefinitions;

import static org.testng.Assert.*;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.Login_PageObject;
import utils.LoggerLoad;
import utils.TestContext;

public class Login_Step {
	boolean isVisible;
	String actualColor;
	TestContext context;
	Login_PageObject loginObj;
	
	public Login_Step(TestContext context){
		this.context=context;
		loginObj = context.poManager.getLoginPage();
		//this.driver = context.driver;
	}
	

	
	@Given("User is on the browser")
	public void user_is_on_the_browser() {
		
		loginObj.browserIsOpen();
		LoggerLoad.info("Browser is open and ready");
	}

	@When("user enters app url")
	public void user_enters_app_url() {
		String currentUrl = loginObj.getPageUrl();
		LoggerLoad.info("Navigated to URL: "+ currentUrl);	    
	}

	@Then("User should see the {string} on {string} side of Navigation bar in login page")
	public void user_should_see_the_on_left_side_of_navigation_bar_in_login_page(String scenario, String expected) {
	    String actualAlign = loginObj.elementLeftAligned(scenario);
	    assertEquals(actualAlign, expected);
	}

	@Then("{string} should have a blue-purple color {string}")
	public void navigation_bar_background_should_have_a_blue_purple_color(String scenario, String expected) {
	    actualColor = loginObj.getBackgroundColor(scenario);
	    assertTrue(actualColor.contains(expected));
	}

	@Then("Heading {string} should be visible inside the login card")
	public void heading_should_be_visible_inside_the_login_card(String field) {
		isVisible = loginObj.isDisplayed(field);
		assertTrue(isVisible, field + " is not visible");
	}

	@Then("User should see the label {string} in login page for {string}")
	public void user_should_see_the_label_in_login_page(String expected, String field) {
	    String actualText = loginObj.getText(field);
	    assertEquals(actualText, expected);
	}

	@Then("{string} should be visible in login page")
	public void should_be_visible_in_login_page(String field) {
		isVisible = loginObj.isDisplayed(field);
		assertTrue(isVisible, field + " is not visible");
	}

	@Then("{string} should be displayed with a blue-purple {string} background and white {string} text")
	public void login_button_should_be_displayed_with_a_blue_purple_background_and_white_text(String field, String backColor, String textColor, DataTable table) {
		List<String> datas = table.asList();
		for(String data : datas) {
			switch (data) {
			case "background":
				actualColor = loginObj.getBackgroundColor(field);
			    assertTrue(actualColor.contains(backColor), "Login Background color not blue-purple");
				break;
			case "text":
				actualColor = loginObj.getTextColor(field);
			    assertTrue(actualColor.contains(textColor),"Login Text color not white");
				break;
			}
		}
	    
	}

	@Then("Username and Password labels should be left-aligned above their respective input fields")
	public void username_and_password_labels_should_be_left_aligned_above_their_respective_input_fields() {
	    
	    
	}

	@Then("User should see exactly two input field in login page")
	public void user_should_see_exactly_two_input_field_in_login_page() {
		int actualCount = loginObj.inputFieldCount();
		assertEquals(actualCount, 2);
	}

	@Then("User should see login button enabled")
	public void user_should_see_login_button_enabled() {
		assertTrue(loginObj.isEnabled(), "Login button is not enabled");
	}

	@Given("User is on the login page")
	public void user_is_on_the_login_page() {
	    System.out.println("User is on the login page of Dietician application");
	}

	@When("User clicks login button after entering valid credentials in login page")
	public void user_clicks_login_button_after_entering_valid_credentials_in_login_page() {
//		Map<String, String> testData = ExcelReader.getTestData(Sheet, Testcase_ID);
//		String email = testData.get("Email");
//		loginUI_page.enterEmail(email);
//		loginUI_page.clickContinueWithEmailButton();
		loginObj.clickLoginBtn();
	}

	@Then("User should be redirected to {string} from login page")
	public void user_should_be_redirected_to_dashboard_page_from_login_page(String page) {
		
	    
	}

	@When("User clicks login button after entering {string} in login page")
	public void user_clicks_login_button_after_entering_in_login_page(String string) {
	    
	    
	}

	@Then("An error message {string} should be displayed in the login page")
	public void an_error_message_should_be_displayed_in_the_login_page(String string) {
	    
	    
	}

	@When("User clicks login button after entering {string} in password field")
	public void user_clicks_login_button_after_entering_in_password_field(String string) {
	    
	    
	}

	@When("User clicks login button after entering only password and {string}")
	public void user_clicks_login_button_after_entering_only_password_and(String string) {
	    
	    
	}

}
