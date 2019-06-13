@FullTest
Feature: Login Action
  Description: the purpose of this feature is ...

  Background: Browser Chrome launched.
    Given Chrome has launched

 @LongTest
  Scenario: Successful Login with Valid Credentials
    Given User is on Home Page
    When User Navigate to LogIn Page
    And User enters UserName and Password
    Then Message displayed Login Successfully


  @ShortTest
  Scenario Outline: Successful LogOut
    When User LogOut from the Application
    Then "<Message>" displayed LogOut Successfully "<Amount>" times

    Examples:
      | Message  | Amount|
      | Good by  |  2    |
      | Hello    |  4    |