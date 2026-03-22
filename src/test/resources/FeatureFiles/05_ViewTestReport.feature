@viewTestReportPage
Feature: View Test Report page verification
  To verify Patient information section, View patient test reports - table, Reports table data of Test Reports Page of Dietician Application
  
  Background: User is in dashboard page after logged in
		Given User is on the login page
    When User is logged in after entering valid credentials and clicks My Patient button
    
  Scenario: Correct report opens for selected record
  	Given User is in My Patients page for existing patient
  	When User clicks View Previous Test Reports button for a specific record
    Then Corresponding report for that record should be opened
    
 Scenario Outline: "<element>"  is displayed in View Test Report popup
    Given User is in My Patients page for existing patient
    When User clicks View Previous Test Reports button for a specific record
    Then "<element>" should be displayed in the View Test report popup
    
    Examples: 
      | element  |
      | Title View Patient Test Reports |
      | Close icon x |
      | Reports table |
      
 Scenario Outline: "<field>" is displayed in View Test Report popup
    Given User is in My Patients page for existing patient
    When User clicks View Previous Test Reports button for a specific record
    Then "<field>" corresponding to the record selected in My Patients page should be displayed
    
    Examples: 
      | field  |
      | Patient id |
      | Patient name |
      | Patient email |
      | Patient contact number |
      
  Scenario: Table headers column are displayed in View Test Report popup
  	Given User is in My Patients page for existing patient
  	When User clicks View Previous Test Reports button for a specific record
    Then Table headers should have Record Number, File, Uploaded Time,File Report Name, Vitals, Identified Health Conditions
    
  Scenario: Table headers column order in View Test Report popup
  	Given User is in My Patients page for existing patient
  	When User clicks View Previous Test Reports button for a specific record
  	Then Record header should be in the exact order Record Number, File, Uploaded Time, File/Report Name, Vitals, Identified Health Conditions should be displayed
  	
  Scenario: Pagination controls are displayed in View Test Report popup
  	Given User is in My Patients page for existing patient
  	When User clicks View Previous Test Reports button for a specific record
  	Then Pagination controls First, previous, next, last arrows should be displayed in View Test Report popup
  	
 Scenario Outline: "<field>" is displayed in View Test Report popup
    Given User is in My Patients page for existing patient
    When User clicks View Previous Test Reports button for a specific record
    Then Each report should display a "<field>" in View Test Report popup
    
    Examples: 
      | field |
      | record number |
      | View PDF button |
      | uploaded time |
      | file/report name |
      | vitals information |
      | Identified health conditions |
      
  Scenario: Vitals order in View Test Report popup
  	Given User is in My Patients page for existing patient
  	When User clicks View Previous Test Reports button for a specific record
  	Then Vitals should be displayed in the order Weight, Height, Temperature, SP, DP
  	
 Scenario Outline: "<field>" displayed in multiline format in View Test Report popup
    Given User is in My Patients page for existing patient
    When User clicks View Previous Test Reports button for a specific record
    Then "<field>" should be displayed in multilines in View Test Report popup
    
    Examples: 
      | field |
      | Vitals |
      | Identified health conditions |