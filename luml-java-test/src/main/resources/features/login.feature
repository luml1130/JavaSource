Feature: User Login Functionality
  As a registered user
  I want to log in fto the application
  So that I can access my dashboard

  Scenario: Successful Login with Valid Credentials
    Given the user is on the login page
    When the user enters valid username "admin" and password "password123"
    And clicks the login button
    Then the user should be redirected to the dashboard
    And the welcome message "Welcome, Admin" should be displayed

  Scenario: Failed Login with Invalid Credentials
    Given the user is on the login page
    When the user enters invalid username "user" and password "wrong"
    And clicks the login button
    Then an error message "Invalid credentials" should be displayed
