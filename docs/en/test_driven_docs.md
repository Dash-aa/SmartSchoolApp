# Test-Driven Documentation

In the SmartSchool project, tests are used not only to verify correctness but also as examples of how to use system components.

## AuthService

Tests for AuthService demonstrate:

- how user authentication is performed;
- what happens when an incorrect password is provided;
- how user registration works;
- what errors are returned for invalid input data.

Thus, the test methods show how to call the `login()` and `register()` methods and what results to expect.

## AnnouncementService

Tests for AnnouncementService demonstrate:

- how to retrieve the list of current announcements;
- the structure of the data returned by the service;
- how the `getActualAnnouncements()` method works.

## Advantages of the Approach

Using tests as documentation provides several benefits:

- tests remain up-to-date вместе with the code;
- they demonstrate real usage examples;
- they help new developers understand the system more quickly;
- they allow automatic verification of system behavior.

Therefore, tests act as "living" documentation that reflects the actual behavior of the system.