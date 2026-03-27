@loginPage
Feature: Login page UI and functional verification
  To verify UI elements and functionality of Login Page of Dietician Application
  
  Background: User is in the login page
    Given User is on the browser
    When user enters app url
    
  Scenario Outline: <scenario> visibility
    Then User should see the "<scenario>" on "<align>" side of Navigation bar in login page
    
    Examples: 
      | scenario  | align |
      | Navigation bar text | left |
      | Navigation bar home icon | left |
    
  Scenario: Navigation bar background colour
    Then "Navigation bar background" should have a blue-purple color "63, 81, 181"
    
  Scenario: Page title visibility
    Then Heading "Dietician Application" should be visible inside the login card
    
  Scenario Outline: label for <scenario>
    Then User should see the label "<text>" in login page for "<scenario>"

    Examples: 
      | scenario  | text |
      | first field | username |
      | second field | password |
        
  Scenario Outline: <Element> presence
    Then "<Element>" should be visible in login page
    
    Examples: 
      | Element |
      | Username input field |
      | Password input field |
      | Login button|
    
  Scenario: Login button color and styling
    Then "Login button" should be displayed with a blue-purple "63, 81, 181" background and white "255,255,255" text
    |background|
    |text|
        
  Scenario: Input field label alignment
    Then Username and Password labels should be left-aligned above their respective input fields
        
  Scenario: Total number of input fields
    Then User should see exactly two input field in login page
        
  Scenario: Login button enabled state
    Then User should see login button enabled
  
  Scenario: Login with valid credential
    Given User is on the login page
    When User clicks login button after entering "valid credentials" in login page
    Then User should be redirected to "dashboard page" from login page
    
  Scenario Outline: Login with <scenario>
    Given User is on the login page
    When User clicks login button after entering invalid "<scenario>" in login page
    Then An error message "<errorMsg>" should be displayed in the login page

    Examples: 
      | scenario  | errorMsg |
      | non-existing user | Invalid username or password|
      | spl charac in user name | Invalid username or password |
      | only few charac of username | Invalid username or password |
      
  Scenario Outline: Login with <scenario> in password field
    Given User is on the login page
    When User clicks login button after entering "<scenario>" in password field
    Then An error message "<errorMsg>" should be displayed in the login page

    Examples: 
      | scenario  | errorMsg |
      | wrong password | Invalid username or password|
      |  spl charac | Invalid username or password|
      
  Scenario Outline: Login with <scenario>
    Given User is on the login page
    When User clicks login button after entering only password and "<scenario>"
    Then An error message "<errorMsg>" should be displayed in the login page
    
    Examples: 
      | scenario  | errorMsg |
      | empty username field | Username is Required |
      
  Scenario Outline: Login with <scenario>
    Given User is on the login page
    When User clicks login button after entering only username and "<scenario>"
    Then An error message "<errorMsg>" should be displayed in the login page
    
    Examples: 
      | scenario  | errorMsg |
      | empty password field | Password is Required |