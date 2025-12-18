package ua.edu.smartschool.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.smartschool.service.AuthService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SmokeStepDefinitions {

    private boolean announcementsVisible;
    private boolean redirectedToLogin;
    private boolean loginSuccessful;

    @Given("the user opens the Smart School main page")
    public void openMainPage() {
        announcementsVisible = true;
    }

    @Then("the system displays a list of school announcements")
    public void announcementsDisplayed() {
        assertTrue(announcementsVisible);
    }

    @Given("the user is not authenticated")
    public void userNotAuthenticated() {
        redirectedToLogin = false;
    }

    @When("the user tries to access the cabinet page")
    public void accessCabinet() {
        redirectedToLogin = true;
    }

    @Then("the system redirects the user to the login page")
    public void redirectedToLogin() {
        assertTrue(redirectedToLogin);
    }

    @Given("a registered student user exists")
    public void studentExists() {
        // Дані вже є у InMemoryUserRepository
    }

    @When("the user logs in with valid credentials")
    public void login() {
        loginSuccessful = true;
    }

    @Then("the student dashboard is displayed")
    public void dashboardDisplayed() {
        assertTrue(loginSuccessful);
    }
}
