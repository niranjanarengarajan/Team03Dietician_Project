@MyPatientsfeature @Login
Feature: My Patient Page functionality

  Background:
    Given Signed in user has navigated to "My Patients" page

  Scenario: Verifying header on the MyPatients page
    Then Page header "My Patients" should be displayed on MyPatients page

  Scenario: Verifying display of search bar on the MyPatients page
    Then Search bar should be displayed on MyPatients page

  Scenario: Verifying display of search icon inside search bar on the MyPatients page
    Then Search icon should be displayed inside search bar on MyPatients page

  Scenario: Verifying display of placeholder text inside search bar on the MyPatients page
    Then "Search" placeholder text should be displayed inside search bar on MyPatients page

  Scenario: Verifying title of column headers in table present inside  on the MyPatients page
    Then user should see column headers in table present in  MyPatients page
      | Patient Id      |
      | Name            |
      | Details         |
      | Last Visit Date |
      | Actions         |
      | Edit/Delete     |

  Scenario Outline: Verify up and down arrow icons in "<column_name>" column header on the MyPatients page
    Then Up and down arrow icons should be displayed in the "<column_name>" column header on the MyPatients page

    Examples:
      | column_name |
      | Patient Id  |
      | Name        |

  Rule: Patient table details with record

    Background:
      Given Patient records already exist in the system for that user in MyPatients page

    Scenario: Verify Patient list is displayed on the MyPatients table
      Then All coumns should have values on the MyPatients table

    Scenario: Verify patient name is displayed for each row on the MyPatients table
      Then Patient name should be displayed for each patient record on the MyPatients table

    Scenario: Verify patient ID is displayed for each row on the MyPatients table
      Then Patient ID should be displayed for each patient record on the MyPatients table

    Scenario: Verify patient name is displayed for each row on the MyPatients table
      Then Patient name should be displayed for each patient record on the MyPatients table

    Scenario: Verify Details column displays patient information on the MyPatients table
      Then Details column should display the information for each patient record on the MyPatients table
        | Phone number  |
        | Email         |
        | Date of birth |

    Scenario: Verify Details column displays patient information on the MyPatients table
      Then Details column should display the information in seperate line for each patient record on the MyPatients table
        | Phone number  |
        | Email         |
        | Date of birth |

    Scenario Outline: Verify  field format validation in details column on the MyPatients table
      Then "<field>"details should be displayed in "<format>" for each patient record on the MyPatients table

      Examples:
        | field         | format              |
        | Email         | valid email format  |
        | Phone number  | valid digits format |
        | Date of birth | dd-mm-yyyy format   |

    Scenario: Verify last visit date format on the MyPatients table
      Then Last visit date should be displayed in "dd-mm-yyyy" format for each patient record on the MyPatients table

    Scenario: Verify 3 buttons are displayed under Actions column for each row
      Then User must see buttons displayed under Actions column for each patient record on the MyPatients table
        | View Previous Test Reports |
        | View Previous Diet Plans   |
        | Create New Report/plan     |

    Scenario Outline: Verify Edit and Delete icons are displayed for each row on the MyPatients table
      Then icon "<icon>" should be displayed for each patient record on the MyPatients table

      Examples:
        | icon   |
        | Edit   |
        | Delete |

    Scenario Outline: Verify column sorting on the MyPatients table
      When user clicks "<arrow>" arrow in "<column>" column on the MyPatients table
      Then patient records should be sorted in "<order>" order by "<column>" on the MyPatients table

      Examples:
        | column     | arrow | order      |
        | Patient Id | up    | ascending  |
        | Patient Id | down  | descending |
        | Name       | up    | ascending  |
        | Name       | down  | descending |

    Scenario Outline: Verify search functionality on the MyPatients table
      When User searches using "<scenario_type>" information on the MyPatients table
      Then Matching patient details should be displayed for "<scenario_type>" on the MyPatients table

      Examples:
        | scenario_type       |
        | SearchByPatientName |
        | SearchByPatientId   |

    Scenario: Verify the table contents when search is cleared on the MyPatients table
      Given User has entered text in search box on the MyPatients table
      When User clears the search text on the MyPatients table
      Then All patient records should be displayed again on the MyPatients table

  Rule: Patient table details without record

    Background:
      Given Signed in user has navigated to "My Patients" page
      Given Patient records are not added for that user in MyPatients page

    Scenario: Verify My Patient page loads with empty MyPatients table
      Then My Patients page should display with empty table

  Rule: Pagination management with multiple records

    Background:
      Given Patient has multiple records already exist in the system for that user in MyPatients page

    Scenario Outline: Verify pagination navigation using arrow buttons
      Given User is in "<precondition>" in My Patients table
      When User clicks the "<arrow>" page arrow in My Patients table
      Then "<expected_result>" should be displayed for user in My Patients table

      Examples:
        | precondition     | arrow | expected_result |
        | multiple pages   | >     | next set        |
        | later page       | <     | previous set    |
        | any except first | <<    | first page      |
        | any except last  | >>    | last page       |

    Scenario: Verify pagination count is updated correctly in My Patients table
      When User clicks any page navigation arrow in My Patients table
      Then Pagination text should display the correct range and total number of patients in My Patients table

    Scenario: Verify pagination is displayed when patient records exceed one page in My Patients table
      When User navigates to any page
      Then Pagination controls should be displayed

    Scenario Outline: Verify pagination arrow states on first page in My Patients table
      Given User is on first page with multiple pages of patient record in My Patients table
      When User navigates to the first page of patient record in My Patients table
      Then "<arrow>" arrow should be displayed in this "<state>" in My Patients table

      Examples:
        | arrow | state    |
        | <     | disabled |
        | <<    | disabled |
        | >     | enabled  |
        | >>    | enabled  |

    Scenario Outline: Verify pagination arrow states on pages except first page in My Patients table
      When User navigates to any page after the first page in My Patients table
      Then Pagination "<arrow>" arrow should be "<state>" in My Patients table

      Examples:
        | arrow | state   |
        | <     | enabled |
        | <<    | enabled |

    Scenario Outline: Verify pagination arrow states on pages except last page in My Patients table
      When User navigates to any page except the last page in My Patients table
      Then "<arrow>" arrow should be "<state>" in My Patients table

      Examples:
        | arrow | state   |
        | >     | enabled |
        | >>    | enabled |

    Scenario Outline: Verify pagination arrow states on last page in My Patients table
      When User navigates to the last page of patient record in My Patients table
      Then "<arrow>" arrow should be dispalyed as "<state>" in My Patients table

      Examples:
        | arrow | state    |
        | >     | disabled |
        | >>    | disabled |

  Rule: Pagination management with only one record

    Background:
      Given Patient has only one record in the system for that user in MyPatients page

    Scenario Outline: Verify all pagination arrows are disabled when Patient has only one record in My Patient table
      Then Pagination "<arrow>" arrows should be disabled in My Patient table

      Examples:
        | arrow |
        | >     |
        | >>    |
        | <     |
        | <<    |

  Rule: Pagination with No Data

    Background:
      Given No patient record present  in the system for that user in MyPatients table

    Scenario: Verify pagination text when no patient data exists in MyPatients table
      Then "Showing 0 to 0 of 0 patients" should be displayed in MyPatients table

    Scenario Outline: Verify all pagination arrows are disabled when no data exists in MyPatients table
      Then "<arrow>" arrows should be disabled in MyPatients table

      Examples:
        | arrow |
        | >     |
        | >>    |
        | <     |
        | <<    |

  Rule: Pagination with maximum record in a page

    Background:
      Given Patient has maximum records present in the system for that user in MyPatients table

    Scenario: Verify each page displays only 5 records in MyPatients table
      Then User should see only five records in each page

    Scenario: Verify newly added record moves to next page when sixth record is added in MyPatients table
      Given User is in My Patients table displays maximum of five records per page
      When User adds 6th record
      Then User should see the newly added record in the next page

    Scenario: Verify navigation to View Patient Test Reports page
      When User clicks View Previous Test Reports under action column in MyPatients table
      Then User should be navigated to "View Patient Test Reports" page from MyPatients table
