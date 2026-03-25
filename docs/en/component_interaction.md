# Component Interaction in SmartSchool

## Login Scenario

1. The user opens the login page.
2. `MainController` creates a `LoginForm` object and passes it to the view.
3. After submitting the form, `MainController` receives the input data.
4. The controller calls the `login()` method in `AuthService`.
5. `AuthService` interacts with `UserRepository`.
6. The repository returns a user or an empty result.
7. If authentication is successful, the user is stored in the HTTP session.
8. The controller redirects the user to the dashboard page.

## Registration Scenario

1. The user opens the registration page.
2. `MainController` passes a `RegisterForm` object and available roles to the view.
3. After submitting the form, the controller calls the `register()` method in `AuthService`.
4. `AuthService` performs validation and interacts with `UserRepository`.
5. If there are no errors, a new user is saved in the repository.
6. The controller redirects the user to the login page.

## Main Page Display Scenario

1. The user opens the main page.
2. `MainController` calls `AnnouncementService`.
3. `AnnouncementService` returns a list of current announcements.
4. The controller adds the announcements to the model.
5. The data is displayed in the `home` template.

## Logout Scenario

1. The user clicks the logout button.
2. `MainController` clears the HTTP session.
3. User data is removed from the session.
4. The user is redirected to the main page.