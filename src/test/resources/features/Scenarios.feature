Feature: Planit Test Automation Assessment Scenarios

@TestScenario1 @TestRegression
  Scenario: Validate error messages and validate error messages gone after populating mandatory fields
    Given user navigates to homepage
    When user clicks "contact" menu button
    Then user clicks submit button
    And user validates error message is displayed for "Forename" mandatory field
    And user validates error message is displayed for "Email" mandatory field
    And user validates error message is displayed for "Message" mandatory field
    And user validates header error message
    When user inputs "Juan" in "forename" field
    Then user inputs "Dela Cruz" in "surname" field
    When user inputs "jdelacruz" in "email" field
    Then user inputs "abc" in "telephone" field
    When user validates error message is displayed for "EmailInvalid" mandatory field
    Then user validates error message is displayed for "TelephoneInvalid" mandatory field
    And user validates header error message
    And user clears "Email" textbox field
    And user clears "Telephone" textbox field
    When user inputs "jdelacruz@test.com" in "email" field
    Then user inputs "12345678" in "telephone" field
    Then user inputs "Test" in "message" field
    And user validates error message is not displayed for "Forename" mandatory field
    And user validates error message is not displayed for "Email" mandatory field
    And user validates error message is not displayed for "Message" mandatory field
    And user ends the session

  @TestScenario2 @TestRegression
  Scenario Outline: Validate successful submission message
    Given user navigates to homepage
    When user clicks "contact" menu button
    Then user inputs "<Forename>" in "forename" field
    Then user inputs "<Surname>" in "surname" field
    Then user inputs "<Email>" in "email" field
    Then user inputs "<Telephone>" in "telephone" field
    Then user inputs "<Message>" in "message" field
    And user clicks submit button
    When user verifies form is submitted successfully
    Then user ends the session
    Examples:
      | Forename | Surname  | Email                 | Telephone | Message             |
      | John     | Test     | johntest@test.com     | 12345678  | Test purposes one   |
      | Juan     | John     | juanjohn@test.com     | 12345671  | Test purposes two   |
      | Juan     | Delacruz | juandelacruz@test.com | 12345672  | Test purposes three |
      | John     | John     | johnjohn@test.com     | 12345673  | Test purposes four  |
      | John     | Delecruz | johndelacruz@test.com | 12345674  | Test purposes five  |

  @TestScenario3 @TestRegression
  Scenario: Validate prices for the given products
    Given user navigates to homepage
    When user clicks "Start Shopping" menu button
    Then user clicks buy 2 times for "Stuffed Frog" item
    Then user clicks buy 5 times for "Fluffy Bunny" item
    And user clicks buy 3 times for "Valentine Bear" item
    When user clicks "Cart" menu button
    Then user checks if "Stuffed Frog" item price is correct
    And user checks if "Stuffed Frog" item subtotal is correct
    When user checks if "Fluffy Bunny" item price is correct
    Then user checks if "Fluffy Bunny" item subtotal is correct
    When user checks if "Valentine Bear" item price is correct
    Then user checks if "Valentine Bear" item subtotal is correct
    When user verifies if total price is correct
    Then user ends the session