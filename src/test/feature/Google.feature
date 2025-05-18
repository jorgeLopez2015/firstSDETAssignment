Feature: Do Google application testing

  @Regression
  Scenario: Google home page validation
    Given User launch Google application
    When User can see Google home page
    Then User validates Google logo
    Then User validates Google search box
    Then User closes Google application
