@DeletePatient
Feature: 
    
    Background: 
      Given User logged into the app and user is in my patient page


Scenario Outline: Validate Delete Popup Alert info and buttons- <ScenarioName>
When User clicks the Delete icon in MyPatientPage for "<ScenarioName>"
Then Alert box should see "<values>" in delete popup

Examples:
|ScenarioName|values|
|Alert Title |Confirm|
|Alert text  |Are you sure to delete patient Name ?|
|Yes button  |Yes|
|No button   |No|

@ete
Scenario: Validate Confirm Navigation in Delete Popup -<ScenarioName>
When User clicks the Delete icon in MyPatientPage and performs "<Action>" deletion 
Then User should be navigated to "MyPatient Page" upon deletion

Examples:
|ScenarioName         |Action |
|Confirm for Deletion |Confirm|
|Cancel for Deletion  |Cancle |


Scenario: Validate Confirm Navigation in Delete Popup -<ScenarioName>
When User clicks the Delete icon in MyPatientPage and performs "<Action>" deletion 
Then User verifies the Patient record state is "<ExpectedState>" for "<ScenarioName>"  for "<action>" after being directed MyPatient Page

Examples:
| ScenarioName         | Action  | ExpectedState   |
| Confirm for Deletion | Confirm | Patient Removed |
| Cancel for Deletion  | Cancel  | Patient Remains |

