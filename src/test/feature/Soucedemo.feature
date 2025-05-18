Feature: Do validation for Sauce demo application


  @Smoke
  Scenario: Do sign in and sign out of application
    When User gives correct valid credential
    Then User can see SauceLab welcome page
    When User clicks on hamburger menu
    Then User can see logout link
    When User clicks on logout link
    Then User successfully log out from the application

  @Regression
  Scenario: Place an order using SauceDemo application
    When User gives correct valid credential
    Then User can see SauceLab welcome page
    Then User selects three items
    Then User can see three count in Cart icon
    When User clicks on Cart icon
    Then User moves to Cart page
    Then User clicks on Checkout button
    When User fills customer details
    Then User clicks on Continue button
    Then User completes the order

  @E2E
  Scenario Outline:  Place an order using SauceDemo application with DD concept
    When Login with username as "<username>" and "<password>"
    Then User can see SauceLab welcome page
    Then User selects three items
    Then User can see three count in Cart icon
    When User clicks on Cart icon
    Then User moves to Cart page
    Then User clicks on Checkout button
    When User fills customer details
    Then User clicks on Continue button
    Then User completes the order
    Examples:
      | username | password |
      | standard_user | secret_sauce |