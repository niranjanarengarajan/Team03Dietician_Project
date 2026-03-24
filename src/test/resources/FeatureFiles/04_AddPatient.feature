@AddPatient @login
Feature: Add Patient Dialog Box -Validation   

Rule: Basic Add Patient Dialog Box UI Validation

Background: User logged into Application,clicks on New Patient
  Given User is in Home Page
  When User clicks on New Patient in the header section
Scenario Outline: Verify UI elements in Add Patient Dialog
  Then User should see "<ElementType>" with "<Property>" "<Value>"

Examples:
| ElementType  | Property       | Value                  |
| Dialog       | title          | Add Patient Details    |
| InputField   | count          | 9                      |
| Dropdown     | count          | 3                      |
| DatePicker   | format         | MM/DD/YYYY             |
| FileUpload   | count          | 1                      |
| Button       | type           | Submit                 |
| Button       | state          | SubmitDisabled         |
| Button       | type           | Close                  |
| Button       | state          | CloseEnabled           |
| InputField   | placeholder    | First name             |
| InputField   | placeholder    | Last name              |
| InputField   | placeholder    | Email                  |
| InputField   | placeholder    | Contact Number         |
| Dropdown     | placeholder    | Allergies              |
| Dropdown     | placeholder    | Food Preference        |
| Dropdown     | placeholder    | Cusine Category        |
| DatePicker   | placeholder    | Date of Birth          |
| InputField   | placeholder    | Weight                 |
| InputField   | placeholder    | Height                 |
| InputField   | placeholder    | Temperature            |
| InputField   | placeholder    | SP                     |
| InputField   | placeholder    | DP                     |
| Text         | value          | Upload Health Report   |
| Text         | value          | No file Chosen         |
| Text         | value          | Vitals                 |
| Dialog       | scrollbar      | right                  |

#------------ Funtional TestCase for Add Patient-------------

 Rule: Add Patient Details - Functional TestCase
 
 Background: User is in Add Patient Details, creating New Patient Details
 Given User is in Add Patient Details Dialog Box
 
 Scenario: Successful patient creation with valid inputs
  When User enters valid values in all mandatory and optional fields
  When User clicks Submit in the Add patient page
  Then Submit button should be enabled in the Add patient page
  Then User should see success message "Patient successfully created"
  Then User should be navigated to My Patient Page with New Patient Details created
 
Scenario: Presence of dropdown values in Allergy
When User clicks on Allergry dropdown in the Add patient detail page
Then Values should be present inside Allergy dropdown

Scenario: Number of values in Allergy dropdown
When User clicks on Allergry dropdown in the Add patient detail page
Then Dropdown should contain 13 values

Scenario Outline: Verify each value in Allergy dropdown
When User clicks on Allergry dropdown in the Add patient detail page
Then User should see "<AllergyValue>" in Allergy dropdown

Examples:
| AllergyValue |
| Egg          |
| Milk         |
| Soy          |
| Almond       |
| Peanuts      |
| Walnut       |
| Pistachio    |
| Sesame       |
| Hazelnut     |
| Pecan        |
| Cashew       |
| NONE         |

Scenario: Presence of dropdown values in food presence
When User clicks on Food Preference dropdown in the Add patient detail page
Then Values should be present inside Food preference dropdown

Scenario: Number of values in Food Preference dropdown
When User clicks on Food Preference dropdown in the Add patient detail page
Then Dropdown should contain 5 values

Scenario Outline: Verify each value in Food Preference dropdown
When User clicks on Food Preference dropdown in the Add patient detail page
Then User should see "<FoodType>" in Food Preference dropdown

Examples:
| FoodType   |
| Vegan      |
| Vegetarian |
| Jain       |
| Eggitarian |
| NonVeg     |

Scenario: Presence of dropdown values in  cuisine
When  User clicks on Cuisine dropdown in the Add patient detail page
Then Values should be present inside Cuisine dropdown

Scenario: Number of values in Cuisine dropdown
When User clicks on Cuisine dropdown in the Add patient detail page
Then Dropdown should contain 36 values

Scenario Outline: Verify each value in Cuisine dropdown
When User clicks on Cuisine dropdown in the Add patient detail page
Then User should see "<CuisineType>" in Cuisine dropdown

Examples:
| CuisineType        |
| Indian             |
| South Indian       |
| Rajasthani         |
| Punjabi            |
| Bengali            |
| Orissa             |
| Gujarati           |
| Maharashtrian      |
| Andhra             |
| Kerala             |
| Goan               |
| Kashmiri           |
| Himachali          |
| Tamil Nadu         |
| Karnataka          |
| Sindhi             |
| Chhattisgarhi      |
| Madhya Pradesh     |
| Assamese           |
| Manipuri           |
| Tripuri            |
| Sikkimese          |
| Mizo               |
| Arunachali         |
| Uttarakhand        |
| Haryanvi           |
| Awadhi             |
| Bihari             |
| Uttar Pradesh      |
| Delhi              |
| North Indian       |

Scenario Outline: Validate successful patient creation flow
When User clicks Submit after user enters valid values in all fields in Add patient detail page
Then "<Validation>" should be successful

Examples:
| Validation         |
| Submit Enabled     |
| Success Message    |
| Navigation         |

Scenario Outline: Select a single value from Allergy dropdown
    When User selects "<AllergyValue>" from Allergy dropdown in the Add patient detail page
    Then "<AllergyValue>" should be selected in the Allergy field

  Examples:
    | AllergyValue |
    | Peanuts      |
    | Milk         |
    | Soy          |
    | Almond       |
    | Walnut       |
    | Pistachio    |
    | NONE         |

Scenario Outline: Select multiple values from Allergy dropdown
    When User selects multiple values "<AllergyValues>" from Allergy dropdown in the Add patient detail page
    Then "<AllergyValues>" should be selected in the Allergy field

  Examples:
    | AllergyValues                    |
    | Peanuts,Milk                     |
    | Soy,Almond,Pistachio             |
    | Milk,Walnut,Hazelnut,Peanuts     |
    | Almond,Soy,Milk                  |
    | Peanuts,Almond,Walnut,Milk       |
    
Scenario Outline: Selecting a value not present in Allergy dropdown
Given User is in Add Patient Details Dialog Box
When User tries to select "<InvalidValue>" from Allergy dropdown in the Add patient detail page
Then No selection should occur in the Allergy field

Examples:
| InvalidValue |
| Soybean      |
| CashewNut    |
| RandomValue  |
| Chocolate    |

Scenario Outline: Select a single value from Food Preference dropdown
    When User selects "<FoodValue>" from Food Preference dropdown in the Add patient detail page
    Then "<FoodValue>" should be selected in the Food Preference field

  Examples:
    | FoodValue   |
    | Vegan       |
    | Vegetarian  |
    | Jain        |
    | Eggitarian  |
    | NonVeg      |

Scenario Outline: Select multiple values from Food Preference dropdown
    When User selects multiple values "<FoodValues>" from Food Preference dropdown in the Add patient detail page
    Then "<FoodValues>" should be selected in the Food Preference field

  Examples:
    | FoodValues          |
    | Vegan,Vegetarian    |
    | Jain,NonVeg         |
    | Eggitarian,Vegan    |
    | Vegetarian,Jain     |

Scenario Outline: Selecting a value not present in Food Preference dropdown
    When User tries to select "<InvalidFood>" from Food Preference dropdown in the Add patient detail page
    Then No selection should occur in the Food Preference field

  Examples:
    | InvalidFood |
    | Fish        |
    | Chocolate   |
    | RandomValue |
    | Pizza       |
    
Scenario Outline: Select a single value from Cuisine Category dropdown
    When User selects "<CuisineValue>" from Cuisine Category dropdown in the Add patient detail page
    Then "<CuisineValue>" should be selected in the Cuisine Category field

  Examples:
    | CuisineValue    |
    | Indian          |
    | South Indian    |
    | Punjabi         |
    | Bengali         |
    | Rajasthani      |

Scenario Outline: Select multiple values from Cuisine Category dropdown
    When User selects multiple values "<CuisineValues>" from Cuisine Category dropdown in the Add patient detail page
    Then "<CuisineValues>" should be selected in the Cuisine Category field

  Examples:
    | CuisineValues                  |
    | Indian,South Indian            |
    | Punjabi,Bengali,Rajasthani     |
    | Indian,Punjabi,Bengali         |

Scenario Outline: Selecting a value not present in Cuisine Category dropdown
    When User tries to select "<InvalidCuisine>" from Cuisine Category dropdown in the Add patient detail page
    Then No selection should occur in the Cuisine Category field

  Examples:
    | InvalidCuisine |
    | Mexican        |
    | Japanese       |
    | RandomValue    |
 
   
Scenario Outline: Validate DOB field with different inputs
When User enters "<DOB>" in Date of Birth field in the Add patient detail page
Then User should see "<ExpectedResult>"

Examples:
| testId | DOB           | ExpectedResult                           |
| TC01   | 01/12/2000    | 01/12/2000                               |
| TC02   | 34/20/2022    | Invalid date, Please select valid date   |
| TC03   | 12/31/2030    | Future date to be disabled               |
| TC04   | CURRENT_DATE  | Invalid date, Please select valid date   |
| TC05   | 01/2020       | Invalid date format                      |
| TC06   | abc           | Invalid date format                      |
| TC07   | ab/cd/efgh    | Invalid date format                      |
| TC08   | VALID_YEAR    | enabled                                  |
| TC09   | BOUNDARY_YEAR | enabled                                  |
| TC10   | INVALID_YEAR  | disabled                                 |
| TC11   | 02/29/2000    | 02/29/2000                               | 
| TC12   | 02/29/2024    | 02/29/2024                               | 
| TC13   | 02/29/2023    | Please select valid date                 | 

Scenario Outline: Validate name fields (First Name / Last Name)
When User enters "<Value>" in "<Field>" field and navigates to next field in the Add patient detail page
Then User should see "<ExpectedMessage>"

Examples:
| Field       | Value     | ExpectedMessage                            |
| First Name  | 12345     | Patient first name accepts only alphabets  |
| First Name  | @#$%      | Patient first name accepts only alphabets  |
| First Name  |           | Firstname field is required                |
| Last Name   | 12345     | Patient last name accepts only alphabets   |
| Last Name   | @#$%      | Patient last name accepts only alphabets   |
| Last Name   |           | Lastname field is required                 |

Scenario Outline: Validate Email field
When User enters "<Email>" in email field and navigates to next field in the Add patient detail page
Then User should see "<ExpectedMessage>"

Examples:
| Email              | ExpectedMessage                     |
| 123abc@test.com    | Please enter a valid email address  |
| abc.com            | Please enter a valid email address  |
| abc@#$%.com        | Please enter a valid email address  |
| abc@test           | Please enter a valid email address  |
| existing@test.com  | Email id already exists             |
|                   | Email field is required              |

Scenario Outline: Validate Email field
When User enters "<Email>" in email field and navigates to next field in the Add patient detail page
Then User should see "<ExpectedMessage>"

Examples:
| Email              | ExpectedMessage                     |
| 123abc@test.com    | Please enter a valid email address  |
| abc.com            | Please enter a valid email address  |
| abc@#$%.com        | Please enter a valid email address  |
| abc@test           | Please enter a valid email address  |
| existing@test.com  | Email id already exists             |
|                   | Email field is required              |

Scenario Outline: Validate Contact Number field
When User enters "<Contact>" in contact number field and navigates to next field in the Add patient detail page
Then User should see "<ExpectedMessage>"

Examples:
| Contact        | ExpectedMessage                              |
| abcdef         | Contact number accepts only numeric values   |
| @#$%           | Contact number accepts only numeric values   |
| 12345          | Please enter a valid contact number          |
| 1234567890123  | Please enter a valid contact number          |
| 9999999999     | Contact number already exists                |
|                | Contact Number is required                   |

Scenario Outline: Validate mandatory dropdown fields
When User skips selecting "<Field>" and navigates to next field in the Add patient detail page
Then User should see "<ExpectedMessage>"

Examples:
| Field              | ExpectedMessage              |
| Allergies          | Allergies is required        |
| Food Preference    | Food Preference is required  |
| Cuisine Category   | Cusine Category is required  |

Scenario Outline: Validate Vitals fields
When User enters "<Value>" in "<Field>" and navigates to next field in the Add patient detail page
Then User should see "<ExpectedResult>"

Examples:
| Field        | Value   | ExpectedResult                         |
| Weight       | 70      | Valid                                  |
| Weight       | @#$     | Please enter a valid weight            |
| Weight       | abc     | Please enter a valid weight            |
| Height       | 5.8     | Valid                                  |
| Height       | @#$     | Please enter a valid height            |
| Height       | abc     | Please enter a valid height            |
| Temperature  | 98      | Valid                                  |
| Temperature  | @#$     | Please enter a valid temperature       |
| Temperature  | abc     | Please enter a valid temperature       |
| SP           | 120     | Valid                                  |
| SP           | abc     | Please enter a valid SP value          |
| SP           | @#$     | Please enter a valid SP value          |
| DP           | 80      | Valid                                  |
| DP           | abc     | Please enter a valid SP value          |
| DP           | @#$     | Please enter a valid SP value          |

Scenario Outline: Validate file upload behavior
When User uploads "<FileType>" in the Add patient detail page
And User clicks Choose Files button in the Add patient page
When User clicks Submit button in the Add patient page
Then User should see "<ExpectedResult>"

Examples:
| FileType   | ExpectedResult                                      |
| pdf        | File uploaded successfully                          |
| docx       | Invalid file type. Please upload a valid file       |
| jpeg       | Invalid file type. Please upload a valid file       |
| largefile  | File size exceeds the allowed limit                 |
| none       | Patient created successfully without file           |


Scenario: Close add dialog using Close button
When User clicks Close button after submit in the Add patient detail page
Then Add dialog should close and user  should be on my patient page without new patient created 
 
Scenario: Validate Patient Creation from Excel
  When User creates new patient using Excel data
  Then Patient creation should reflect the correct result
  