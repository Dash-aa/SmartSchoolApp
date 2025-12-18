Feature: Smart School Smoke Tests

  Scenario: Display announcements on main page
    Given the user opens the Smart School main page
    Then the system displays a list of school announcements

  Scenario: Redirect unauthorized user from cabinet
    Given the user is not authenticated
    When the user tries to access the cabinet page
    Then the system redirects the user to the login page

  Scenario: Successful student login
    Given a registered student user exists
    When the user logs in with valid credentials
    Then the student dashboard is displayed
