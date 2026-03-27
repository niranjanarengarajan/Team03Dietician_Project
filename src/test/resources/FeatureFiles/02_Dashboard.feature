@dashboardPage @Login
Feature: Dashboard page verification
  To verify UI elements and functionality of Dashboard Page of Dietician Application

  Scenario: Navigation bar links presence in Dashboard page
    Then Navigation bar of Dashboard page should display exactly four links "My Patients", "New Patient", "Login", and "Logout"

  Scenario Outline: "<element>" navigation link is clickable in Dashboard page
    When User clicks the "<element>" in the navigation bar of Dashboard page
    Then User should be redirected to the "<element>" page from dashboard page

    Examples:
      | element          |
      | My Patients link |
      | New Patient link |
      | logout link      |
      | home icon        |
