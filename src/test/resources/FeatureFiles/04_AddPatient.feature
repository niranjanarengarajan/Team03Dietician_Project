@AddPatient 
Feature: Add Patient Dialog Box -Validation   

Rule: Basic Add Patient Dialog Box UI Validation

Background: User logged into Application,clicks on New Patient
  Given User is in Home Page
  When User clicks on New Patient in the header section

Scenario: Title of the dialog box
Then User should see Add Patient Details on the dialog box

Scenario: Presence of 9 input fields
Then User should see 9 input boxes in the Add Patient Details dialog box

Scenario: Presence of 3 dropdowns
Then User should see 3 dropdowns in the Add Patient Details dialog box

Scenario: Presence of a Date Picker field 
Then User should see a date picker for DOB field with MM/DD/YYYY displayed

Scenario: Presence of file Upload option
Then User should see exactly 1 file upload option in Add Patient Details dialog box

Scenario: Presence of Submit button
Then User should see one Submit button 

Scenario: State of Submit button
Then User should see one Submit button in disabled state

Scenario: Presence of Close button
Then  User should see one Close button

Scenario: State of Close button
Then User should see one Close button in enabled state

Scenario: Placeholder for first field - firstname
Then User should see mandatory field with placeholder "First name"

Scenario: Placeholder for second field - lastname
Then User should see mandatory field with placeholder "Last name"

Scenario: Placeholder for thrid field - Email
Then User should see mandatory field with placeholder "Email"

Scenario: Placeholder for second field - Contact Num
Then User should see mandatory field with placeholder "Contact Number"

Scenario: Placeholder for dropdown -Allergies
Then User should see mandatory dropdown with placeholder "Allergies"

Scenario: Placeholder for dropdown -Food Preference 
Then User should see mandatory dropdown with placeholder "Food Preference"

Scenario: Placeholder for dropdown -Cusine Category
Then User should see mandatory dropdown with placeholder "Cusine Category"

Scenario: Placeholder for DOB field
Then User should see mandatory DOB with placeholder "Date of Birth"

Scenario: Placeholder for Vitals section -Weight
Then User should see non-manadatory field placeholder with "Weight"

Scenario: Placeholder for Vitals section -Height
Then User should see non-manadatory field placeholder with "Height"

Scenario: Placeholder for Vitals section -Temperature
Then User should see non-manadatory field placeholder with "Temperature"

Scenario: Placeholder for Vitals section -SP
Then User should see non-manadatory field placeholder with "SP"

Scenario: Placeholder for Vitals section -DP
Then User should see non-manadatory field placeholder with "DP"

Scenario: Presence of Upload Health Report
Then User should see text Upload Health Report 

Scenario: Presence of No file Chosen when no files uploaded
Then User should see text No file Chosen

Scenario: Presence of scroll bar on the dialog box
Then User should see a scroll bar at the right side of dialog box

#------------ Funtional TestCase for Add Patient-------------

 Rule: Add Patient Details - Functional TestCase
 
 Background: User is in Add Patient Details, creating New Patient Details
 Given User is in Add Patient Details Dialog Box
 
Scenario: Presence of dropdown values in Allergy
When User clicks on Allergry dropdown 
Then Values should be present inside Allergy dropdown

Scenario: Number of values in Allergy dropdown
When  User clicks on Allergry dropdown
Then Dropdown should contain 13 values

Scenario Outline: Verify each value in Allergy dropdown
When User clicks on Allergy dropdown
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
When User clicks on Food Preference dropdown
Then Values should be present inside Food preference dropdown

Scenario: Number of values in Food Preference dropdown
When User clicks on Food Preference dropdown
Then Dropdown should contain 5 values

Scenario Outline: Verify each value in Food Preference dropdown
When User clicks on Food Preference dropdown
Then User should see "<FoodType>" in Food Preference dropdown

Examples:
| FoodType   |
| Vegan      |
| Vegetarian |
| Jain       |
| Eggitarian |
| NonVeg     |

Scenario: Presence of dropdown values in  cuisine
When User clicks on cuisine dropdown
Then Values should be present inside Cuisine dropdown

Scenario: Number of values in Cuisine dropdown
When User clicks on cuisine dropdown
Then Dropdown should contain 36 values

Scenario Outline: Verify each value in Cuisine dropdown
When User clicks on Cuisine dropdown
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


Scenario: State of Submit button after adding values in all field
When User enters valid values in all field
Then Submit button should be enabled

Scenario: Success message validation for adding new patient with valid data 
When User clicks Submit after entering valid data in all mandatory fields
Then User should see Patient successfully created - toast message

Scenario: Navigation to My patient after adding new patient with valid data
When User clicks Submit after entering valid data in all mandatory fields
Then User is directed to My Patient Page with New Patient Details created

Scenario Outline: Select a single value from Allergy dropdown
    When User selects "<AllergyValue>" from Allergy dropdown
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
    When User selects multiple values "<AllergyValues>" from Allergy dropdown
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
When User tries to select "<InvalidValue>" from Allergy dropdown
Then No selection should occur in the Allergy field

Examples:
| InvalidValue |
| Soybean      |
| CashewNut    |
| RandomValue  |
| Chocolate    |

Scenario Outline: Select a single value from Food Preference dropdown
    When User selects "<FoodValue>" from Food Preference dropdown
    Then "<FoodValue>" should be selected in the Food Preference field

  Examples:
    | FoodValue   |
    | Vegan       |
    | Vegetarian  |
    | Jain        |
    | Eggitarian  |
    | NonVeg      |

Scenario Outline: Select multiple values from Food Preference dropdown
    When User selects multiple values "<FoodValues>" from Food Preference dropdown
    Then "<FoodValues>" should be selected in the Food Preference field

  Examples:
    | FoodValues          |
    | Vegan,Vegetarian    |
    | Jain,NonVeg         |
    | Eggitarian,Vegan    |
    | Vegetarian,Jain     |

Scenario Outline: Selecting a value not present in Food Preference dropdown
    When User tries to select "<InvalidFood>" from Food Preference dropdown
    Then No selection should occur in the Food Preference field

  Examples:
    | InvalidFood |
    | Fish        |
    | Chocolate   |
    | RandomValue |
    | Pizza       |
    
Scenario Outline: Select a single value from Cuisine Category dropdown
    When User selects "<CuisineValue>" from Cuisine Category dropdown
    Then "<CuisineValue>" should be selected in the Cuisine Category field

  Examples:
    | CuisineValue    |
    | Indian          |
    | South Indian    |
    | Punjabi         |
    | Bengali         |
    | Rajasthani      |

Scenario Outline: Select multiple values from Cuisine Category dropdown
    When User selects multiple values "<CuisineValues>" from Cuisine Category dropdown
    Then "<CuisineValues>" should be selected in the Cuisine Category field

  Examples:
    | CuisineValues                  |
    | Indian,South Indian            |
    | Punjabi,Bengali,Rajasthani     |
    | Indian,Punjabi,Bengali         |

Scenario Outline: Selecting a value not present in Cuisine Category dropdown
    When User tries to select "<InvalidCuisine>" from Cuisine Category dropdown
    Then No selection should occur in the Cuisine Category field

  Examples:
    | InvalidCuisine |
    | Mexican        |
    | Japanese       |
    | RandomValue    |
 
   
Scenario Outline: Validate DOB field with different inputs
When User enters "<DOB>" in Date of Birth field
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

Scenario: Add first name field with numeric data
When User navigate to next field after entering numeric data in First name field 
Then User should  see error message in Patient first name accepts only alphabets

Scenario: Add first name field with special character data
When User navigate to next field after entering special characters in First name field 
Then User should  see error message in Patient first name accepts only alphabets

Scenario: Mandatory field check for firstname field
When User navigate to next field leaving First name field empty
Then User should see error message "Firstname field is required"

Scenario: Add last name field with numeric data
When User navigate to next field after entering numeric data in Last name field  
Then User should  see error message in Patient last name accepts only alphabets

Scenario: Add last name field with special character data
When User navigate to next field after entering numeric data in Last name field  
Then User should  see error message in Patient last name accepts only alphabets

Scenario: Mandatory field check for lastname field
When User navigate to next field leaving Last name field empty
Then User should see error message "Lastname field is required"

Scenario: Add email with  starts with number
When User navigate to next field after entering email starting with number
Then User should see the error message “Please enter a valid email address”

Scenario: Add email without @ symbol
When User navigate to next field after  entering email without @
Then User should see the error message “Please enter a valid email address”

Scenario: Add email with special characters
When User navigate to next field after entering email with special characters other than @  
Then User should see the error message “Please enter a valid email address”

Scenario: Add email without .com
When User navigate to next field after entering email without .com  
Then User should see the error message “Please enter a valid email address”

Scenario: Existing email id
When User navigate to next field after  entering existing email id  
Then User should see the error message “Email id already exists”

Scenario: Mandatory field check for email field
When User navigate to next field after entering all mandatory fields except email field
When User should see error message "Email field is required"

Scenario: Add contact number with alphabets
When User navigate to next field after  entering alphabets in contact number with valid data in other mandatory fields
Then User should see the error message “Contact number accepts only numeric values”

Scenario: Add contact number with special characters
When User navigate to next field after  entering special characters in contact number with valid data in other mandatory fields
Then User should see the error message “Contact number accepts only numeric values”

Scenario: Add contact number with less than required digits
When User navigate to next field after  entering contact number with less than required digits with valid data in other mandatory fields
Then User should see the error message “Please enter a valid contact number”

Scenario: Add contact number with greater than required digits
When User navigate to next field after entering contact number with greater than required digits with valid data in other mandatory fields
Then User should see the error message “Please enter a valid contact number”

Scenario: Existing contact number
When User navigate to next field after  entering existing contact number
Then User should see the error message “Contact number already exists”

Scenario: Mandatory field check for contact number field
When User navigate to next field after entering all mandatory fields except contact number field
Then User should see error message "Contact Number is required"

Scenario: Leaving Allergies field empty
When User navigate to next field after entering all mandatory fields without selecting Allergies
Then User should see error message "Allergies is required"

Scenario: Leaving Food Preference field empty
When User navigate to next field after  entering all mandatory fields without selecting Food Preference
Then User should see error message "Food Preference is required"

Scenario: Leaving Cusine Category field empty
When User navigate to next field after entering all mandatory fields without selecting Cuisine Category
Then User should see error message "Cusine Category is required"

Scenario: Leaving DOB field empty
When User navigate to next field after  entering all mandatory fields without selecting DOB
Then User should see error message "Date is required"

Scenario: Add weight with valid data
When User navigate to next field after  entering valid weight 
Then User is directed to My Patient Page with New Patient Details created

Scenario: Add weight with special characters
When User navigate to next field after  entering special characters in weight
Then User should see the error message “Please enter a valid weight”

Scenario: Add weight with alphabets
When User navigate to next field after  entering alphabets in weight 
Then User should see the error message “Please enter a valid weight”

Scenario: Add height with valid data
When User navigate to next field after  entering valid weight 
Then User is directed to My Patient Page with New Patient Details created

Scenario: Add height with special characters
When User navigate to next field after entering special characters in height 
Then User should see the error message “Please enter a valid height”

Scenario: Add height with alphabets
When User navigate to next field after entering alphabets in height
Then User should see the error message “Please enter a valid height”

Scenario: Add temperature with valid data
When User navigate to next field after entering valid temperature
Then User is directed to My Patient Page with New Patient Details created

Scenario: Add temperature with special characters
When User navigate to next field after  entering special characters in temperature
Then User should see the error message “Please enter a valid temperature”

Scenario: Add temperature with alphabets
When User navigate to next field after  entering alphabets in temperature 
Then User should see the error message “Please enter a valid temperature”

Scenario: Add SP,DP with valid data
When User navigate to next field after entering valid SP and DP
Then User is directed to My Patient Page with New Patient Details created

Scenario: Add SP,DP with special characters
When User navigate to next field after  entering special characters in SP and DP
Then User should see the error message “Please enter a valid SP value”

Scenario: Add SP ,DP with alphabets
When User navigate to next field after  entering alphabets in SP and DP
Then User should see the error message “Please enter a valid SP value”

Scenario: Upload valid file - pdf
When User clicks Submit after uploading a valid file 
Then User should be redirected to My Patient page with the uploaded file saved successfully
 
Scenario: File name after Upload valid file - pdf
When User uploads health report in pdf
Then User should see uploaded file name with extension

Scenario: Presence of record number after file upload
When User clicks “View Previous Test Report” after being redirected to My Patient page for newly  created patient
Then User should see new record number in test report page

Scenario: Presence of PDF file in test report after file upload
When User clicks “View Previous Test Report” after being redirected to My Patient page for newly  created patient
Then User should see PDF file in test report

Scenario: Presence of upload date info
When User clicks “View Previous Test Report” after being redirected to My Patient page for newly  created patient
Then User should see upload date info in test report

Scenario: Presence of health info detected from the health report
When User clicks “View Previous Test Report” after being redirected to My Patient page for newly  created patient
Then User should see health condition as the values in health report

Scenario: Upload invalid file type - docx,jpeg
When User uploads a file with other format
Then User should see the error message “Invalid file type. Please upload a valid file”

Scenario: Upload file exceeding size limit
When User uploads a file larger than allowed size 
Then User should see the error message “File size exceeds the allowed limit”

Scenario: Upload without selecting file
When User clicks Submit after entering valid data skips file upload
Then User is directed to My Patient Page with New Patient Details created

Scenario: Close add dialog using Close button
When User clicks Close button after submit
Then Add dialog should close and user  should be on my patient page without new patient created 
 
 