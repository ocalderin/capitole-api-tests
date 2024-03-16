@api
Feature: User API

  Scenario Outline: Create User
    Given A request to created an user is executed
      | id   | username   |  | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> |  | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    And The status code should be 200
    When A request to get the user with username "<username>" is executed
    Then The status code should be 200
    And The user information should be:
      | id   | username   |  | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> |  | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    And The user information is saved on the UserData file
    Examples:
      | id  | username     |  | firstName     | lastName     | email                | password     | phone     | userStatus |
      | 100 | fakeUserName |  | fakeFirstName | fakeLastName | fakeEmail@google.com | fakePassword | fakePhone | 0          |


