@viewTestReportPage @Login
Feature: View Test Report page verification
  To verify Patient information section, View patient test reports - table, Reports table data of Test Reports Page of Dietician Application
  
  Background: User is in My Patient page after logged in
	Given User is on the dashboard page of Dietician application
    When User clicks My Patient button to view reports
    
  Scenario: Correct report opens for selected record
  	When User clicks View Previous Test Reports button for a "specific patient record"
    Then Corresponding report for that record should be opened
    
 Scenario Outline: <element> is displayed in View Test Report popup
    When User clicks View Previous Test Reports button for a "specific patient record"
    Then "<element>" should be displayed in the View Test report popup
    
    Examples: 
      | element |
      | Title View Patient Test Reports |
      | Close icon x |
      | Reports table |
      
 Scenario Outline: <field> is displayed in View Test Report popup
    When User clicks View Previous Test Reports button for a "specific patient record"
    Then "<field>" corresponding to the record selected in My Patients page should be displayed in View Test Report popup
    
    Examples: 
      | field |
      | Patient id |
      | Patient name |
      | Patient email |
      | Patient contact number |
      
  Scenario: Table headers column are displayed in View Test Report popup
  	When User clicks View Previous Test Reports button for a "specific patient record"
    Then Report Table headers should have "Record Number, FILE, Uploaded Time,File/Report Name, Vitals, Identified Health Conditions"
    
  Scenario: Table headers column order in View Test Report popup
  	When User clicks View Previous Test Reports button for a "specific patient record"
  	Then Record header should be in the exact order "Record Number, FILE, Uploaded Time,File/Report Name, Vitals, Identified Health Conditions" should be displayed
  	
  Scenario: Pagination controls are displayed in View Test Report popup
  	When User clicks View Previous Test Reports button for a "specific patient record"
  	Then Pagination controls First, previous, next, last arrows should be displayed in View Test Report popup
  	
 Scenario Outline: <field> is displayed in View Test Report popup
    When User clicks View Previous Test Reports button for a "specific patient record"
    Then Each report should display a "<field>" in View Test Report popup
    
    Examples: 
      | field |
      | Record number |
      | View PDF button |
      | Uploaded time |
      | File/Report name |
      | Vitals information |
      | Identified Health Conditions |
      
  Scenario: Vitals order in View Test Report popup
  	When User clicks View Previous Test Reports button for a "specific patient record"
  	Then Vitals should be displayed in the order "Weight, Height, Temperature, SP, DP" in reports
  	
 Scenario Outline: <field> displayed in multiline format in View Test Report popup
    When User clicks View Previous Test Reports button for a "specific patient record"
    Then "<field>" should be displayed in multilines in View Test Report popup
    
    Examples: 
      | field |
      | Vitalsin reports |
      | Identified health conditions in reports |
 
  Scenario: Corresponding PDF report opens for a record
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User clicks View PDF button for a particular record
    Then Corresponding PDF report for that record should be opened
      
  Scenario Outline: Navigate to the <element> page using pagination
  	Given User is in any page "<givenPage>" page of View Test Report table for "single patient with multiple reports"
  	When User clicks the "<element>" page arrow in View Test Report popup
    Then "<element>" set of patient records should be displayed in View Test Report popup
    
    Examples: 
      | element | givenPage |
      | next | except last |
      | previous | except first |
      | first | except first |
      | last | except last |
    
  Scenario: Pagination count is updated correctly
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User clicks any page navigation arrow in View Test Report popup
    Then Pagination text should display the correct range and total number of patients in View Test Report popup
    
  Scenario: Pagination is displayed when patient records exceed one page
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User clicks any page navigation arrow in View Test Report popup
    Then "Pagination controls" should be displayed in the View Test report popup
        
  Scenario Outline: <element> is <status> on first page
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User navigates to the first page of patient record in View Test Report popup
    Then "<element>" should be "<status>" in View Test Report popup
    
    Examples:
    	| element | status |
    	| Previous page arrow | disabled |
    	| First page arrow | disabled |
    	| Next page arrow | enabled |
    	| Last page arrow | enabled |
    	
  Scenario Outline: <element> is <status> on all pages except the first page
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User clicks any page navigation arrow in View Test Report popup
    Then "<element>" should be "<status>" in View Test Report popup
    
    Examples:
    	| element | status |
    	| Previous page arrow | enabled |
    	| First page arrow | enabled |
    	
  Scenario Outline: <element> is <status> on all pages except the last page
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User navigates to any page except the last page in View Test Report popup
    Then "<element>" should be "<status>" in View Test Report popup
    
    Examples:
    	| element | status |
    	| Next page arrow | enabled |
    	| Last page arrow | enabled |
    	
  Scenario Outline: <element> is <status> on last page
  	Given User is in View Patient Test Reports page for "single patient with multiple reports"
  	When User navigates to the last page of the patient record in View Test Report popup
    Then "<element>" should be "<status>" in View Test Report popup
    
    Examples:
    	| element | status |
    	| Next page arrow | disabled |
    	| Last page arrow | disabled |
      
  Scenario: All pagination arrows disabled when only one page exists
  	When User clicks on View Patient Test Reports button for "patient with only one report"
    Then First, previous, next, last arrows should be disabled in view patient test report page
        
  Scenario: Pagination when no patient data exists
  	When User clicks on View Patient Test Reports button for "patient with no reports data"
    Then "Showing 0 to 0 of 0 patients" should be displayed in View Test Report popup

 Scenario: All pagination arrows disabled when no data exists
  	When User clicks on View Patient Test Reports button for "patient with no reports data"
    Then First, previous, next, last arrows should be disabled in view patient test report page
  
  Scenario: Each page should display only 2 records
  	When User clicks on View Patient Test Reports button for "single patient with multiple reports"
    Then User should see only 2 records in each view patient test report page