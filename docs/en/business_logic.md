# SmartSchool Business Logic

## User Authentication

Authentication is handled by `AuthService`.

Main flow:
1. The user enters login and password in the login form.
2. The controller passes this data to `AuthService`.
3. The service searches for the user by login in the repository.
4. If the user is found, the password is verified.
5. If the login and password are correct, the user is considered authenticated.
6. After successful login, the user is stored in the HTTP session.

If the login or password is incorrect, the system returns an error message.

## User Registration

Registration is also handled by `AuthService`.

Registration flow:
1. The user fills in the registration form.
2. Form validation is performed.
3. The system checks if a user with the same login already exists.
4. It verifies that a role is selected.
5. It checks that both password and confirmation password are provided.
6. It verifies that the password and confirmation password match.
7. If all checks pass, a new user is created and saved in the repository.

If validation fails, the service returns an error message.

## Announcement Handling

Current announcements are generated in `AnnouncementService`.

Flow:
1. The service creates a list of announcements.
2. The controller retrieves this list.
3. The announcements are added to the model.
4. They are displayed on the main page.

## Session Management

After login, the user is stored in the HTTP session. This allows:
- displaying the user dashboard only to authenticated users;
- identifying the current user;
- performing logout by clearing the session.