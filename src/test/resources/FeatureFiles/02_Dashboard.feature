@dashboardPage
Feature: Dashboard page verification
  To verify UI elements and functionality of Dashboard Page of Dietician Application
  
  Background: User is in dashboard page after logged in
		Given User is on the login page
    When User clicks login button after entering valid credentials
    
  Scenario: Navigation bar links presence in Dashboard page
    Then Navigation bar of Dashboard page should display exactly four links "My Patients", "New Patient", "Login", and "Logout"
  
  Scenario Outline: "<element>" navigation link is clickable in Dashboard page
    Given User is on the Dashboard page
    When User clicks the "<element>" in the navigation bar of Dashboard page
    Then User should be redirected to the "<element>" page from dashboard page
    
    Examples: 
      | element  |
      | My Patients link |
      | New Patient link |
    
  Scenario: "Logout" navigation link is clickable in Dashboard page
    Given User is on the Dashboard page
    When User clicks the "Logout link" in the navigation bar of Dashboard page
    Then User should be logged out of the application from Dashboard page
    
  Scenario: Home icon is clickable
  	Given User is on the Dashboard page
  	When User clicks the "home icon" in the navigation bar of Dashboard page
  	Then User should be navigated to the dashboard page