@EditPatient
Feature: Validating Edit Patient Page

Background: User logged into the app and patients already exists
Given User logged into the app and user is in my patient page

Rule: Visibility of EditPatient Dialogue box UI elements

Scenario Outline: Validate "<element>" EditPatient dialogue box
When User clicks edit icon for the particular patient 
Then User should see  "<element>" on the dialog box

Examples:
|element                |
|Title                  |
|Submit button Presence |
|State of submit button |
|Close button Presence  |
|State of close button  |
|9 Input field          |
|3 DropDown count       |
|FilUploadOption        |
|FirstName Field        |
|Lastname Field         | 
|Email Field            |
|Contact Number Field   |
|Date Of Birth          | 
|Sub Title Vitals       |
|SP Field               |
|DP Field               |
|Weight                 |
|Height                 |
|Temperature            |
|Upload Health Lable    |
|No File Chosen Text    |
|Close button color     |


Scenario Outline: Validate dropdown <element> EditPatient dialogue box
When User checks dropdown "<element>" after clicks edit icon for the particular patient 
Then User should see  "<element>" popsup on the dialog box

Examples:
|element                |
|Allergy info           | 
|Food Preference        |
|Cuisine Preference     |

#--------------Data validation---------------------------------------------------------------------

Rule: User Validates Edit window Funtionality
    
    Background: 
      Given User clicks edit icon for the particular patient and sees the edit popup window

Scenario Outline: Validate presence of placholder "<element>" of Edit Patient dialogue box  
When User clears existing value in "<element>" of edit patient dialogue box
Then User should see placeholder text is "<expected_text>"

Examples:
  | element              | expected_text |
  | First Name Field     | First Name    |
  | Last Name Field      | Last Name     |
  | Email Field          | Email         |
  | Contact Number Field | Contact       |
  
  
 Scenario Outline: Validate edit patient functionality for valid data - <ScenarioName>
  When User clicks the "Submit" button after entering valid data from Excel for "<ScenarioName>" edit window
  Then User should be redirected to the "My Patient" page
  
  
Examples:
  | ScenarioName       |
  | ValidFirst Name    |
  | ValidLast Name     |
  | ValidEmail         |
  | ValidContact No    |
  | ValidCTC No        |
  | ValidWeight        |
  | ValidHeight        |
  | ValidTemperature   |
  | ValidSP            |
  | ValidDP            |
  | Valid SP and DP    |
  | ValidDate          |
  
  Scenario Outline: Updated values on MyPatient page for valid data - <ScenarioName>
  When User clicks the "Submit" button after entering valid data from Excel for "<ScenarioName>" edit window
  Then User should see updated values from Excel for "<ScenarioName>" in My Patient table 
    
Examples:
  | ScenarioName       |
  | ValidFirst Name    |
  | ValidLast Name     |
  | ValidEmail         |
  | ValidContact No    |
  | ValidDate          |
  
  
 Scenario Outline: Updated values on MyPatient page for valid data - <ScenarioName>
  When User clicks the "Submit" button after entering valid data from Excel for "<ScenarioName>" edit window
  And  User clicks "View Previous Test Report" after being redirected to the My Patient page 
  Then User should see updated values from Excel for "<ScenarioName>" in ViewTestReport page  
  
 Examples:
  |ScenarioName        | 
  | ValidWeight        |
  | ValidHeight        |
  | ValidTemperature   |
  | ValidSP            |
  | ValidDP            |
  | Valid SP and DP    |
  
 
Scenario Outline: Validate edit patient funtionality for invalid data - <ScenarioName>
  When User clicks "Submit" button after entering invalid data from Excel to Edit "<ScenarioName>" edit window
  Then User should see the error message "<ErrorMessage>" from Excel

Examples:
  | ScenarioName                         |
  | FirstName with numeric               |
  | FirstName with spl Char and Numeric  |
  | FirstName with spl Char              |
  | LastName with numeric                |
  | LastName with spl Char and Numeric   |
  | LastName with spl Char               |
  | Email with invalid format            |
  | Email with spl char                  |
  | Email with missing @                 |
  | Email with empty field               |
  | ContactNumber with alphabets         |
  | ContactNumber with spl Char          |
  | ContactNumber with less Digits       |
  | ContactNumber as Empty               |
  | Weight with Alphabets                |
  | Weight with spl char                 |
  | Height with Alphabets                |
  | Height with spl char                 |
  | Temp with Alphabets                  |
  | Temp with spl char                   |
  | SP with spl char                     |
  | SP with Alphabets                    |
  | Only SP                              |
  | DP with spl char                     |
  | DP with Alphabets                    |
  | only DP                              |
  
  Scenario: Validate visibility of DOB Date Picker in EditPatient
  When User clicks on the DOB input field
  Then User should see the calendar date picker displayed with Month, Day, and Year
  
  Scenario Outline: Validate DOB Date Picker constraints - <ScenarioName>
  When User enters "<InputDate>" for "<ScenarioName>" for editPatient
  Then User should see the date picker "<expected message>" from Excel

  Examples:
  | ScenarioName                   |
  | Prevent future date            |
  | Selecting current date         |
  | Selecting Invalid date         |
  | User enters non-numeric value  |
  | Selecting partial date         |
  | Leap Year                      |
  | Boundary Year                  |
  | NonLeapYear                    |
  
  Scenario Outline: Validate File upload functionality for validAndInvalid - <ScenarioName>
  When User uploads file for "<ScenarioName>" from excel for editPatient
  And  User clicks the "Submit" button in edit window
  Then User should see the error message "<ErrorMessage>" from Excel
  
  Examples:
  | ScenarioName                    |
  | Upload invalid type - docx      |
  | UploadFile exceeding size limit |
  | Upload invalid type - jpeg      |
  | Missed Upload File              |
  
 Scenario: Validate File upload functionality for valid - <Upload Valid File>  
  When User clicks the "Submit" button and uploads file for "<Upload Valid File>" from excel for editPatient
  And   User clicks "View Previous Test Report" after being redirected to the My Patient page  
  And User clicks "View Previous Test Report" and verifies the following report details:
    | field            |
    | record number    |
    | pdf file         |
    | upload date      |
    | health condition |
  
  
#---------------------------------------------------------------------------------------------------------------------------------------------------------  
  


# Delete Patient Test cases
Rule: User Validates Delete window Funtionality in MyPatients Page
    
    Background: 
      Given User logged into the app and user is in my patient page


Scenario Outline: Validate Delete Popup Alert info and buttons- <ScenarioName>
When User clicks the Delete icon in MyPatientPage for "<ScenarioName>"
Then Alert box should see "<element>" in delete popup

Examples:
|ScenarioName|
|Alert Title |
|Alert text  |
|Yes button  |
|No button   |


Scenario: Validate Confirm Navigation in Delete Popup -<ScenarioName>
When User clicks the Delete icon in MyPatientPage and performs "<Action>" deletion 
Then User should be navigated to MyPatient Page upon deletion

Examples:
|ScenarioName         |Action |
|Confirm for Deletion |Confirm|
|Cancel for Deletion  |Cancle |


Scenario: Validate Confirm Navigation in Delete Popup -<ScenarioName>
When User clicks the Delete icon in MyPatientPage and performs "<Action>" deletion 
Then User verifies the Patient record state is "<ExpectedState>" for "<ScenarioName>" after being directed MyPatient Page

Examples:
| ScenarioName         | Action  | ExpectedState   |
| Confirm for Deletion | Confirm | Patient Removed |
| Cancel for Deletion  | Cancel  | Patient Remains |








 
