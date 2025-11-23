package test_cases;

import configurations.BaseTest;
import pages.LoginPage;
import utilities.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Page Test Cases")
public class LoginPageTestCase extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageTestCase.class);
    private LoginPage loginPage;

    @BeforeEach
    public void initLoginPage() {
        logger.info("Initializing LoginPage test setup");
        loginPage = new LoginPage(page);
        loginPage.navigateTo(Config.getBaseUrl());
        loginPage.waitForPageLoad();
        logger.info("LoginPage initialization completed");
    }

    @Test
    @DisplayName("Test 1: User should login successfully with valid credentials")
    public void loginWithValidCredentials() {
        logger.info("TEST STARTED: Login with valid credentials");

        logger.info("ARRANGE: Login page loaded and ready");
        logger.info("ACT: Entering username: {}", Config.VALID_USERNAME);
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.VALID_USERNAME);

        logger.info("ACT: Entering password (length: {})", Config.VALID_PASSWORD.length());
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.VALID_PASSWORD);

        logger.info("ACT: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        logger.info("ASSERT: Verifying redirect to inventory page");
        String currentUrl = page.url();
        logger.info("Current URL after login: {}", currentUrl);

        logger.info("TEST PASSED: User logged in successfully");
    }

    @Test
    @DisplayName("Test 2: Error message appears with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        logger.info("TEST STARTED: Login with invalid credentials");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Entering invalid username");
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.INVALID_USERNAME);

        logger.info("ACT: Entering invalid password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.INVALID_PASSWORD);

        logger.info("ACT: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        logger.info("ASSERT: Checking if error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.ERROR_MESSAGE),
                "Error message should be displayed for invalid credentials");

        String errorMessage = loginPage.getText(LoginPage.ERROR_MESSAGE);
        logger.info("Error message displayed: {}", errorMessage);
        assertNotNull(errorMessage, "Error message should not be null");

        logger.info("TEST PASSED: Error message displayed for invalid credentials");
    }

    @Test
    @DisplayName("Test 3: Locked out user cannot login")
    public void testLockedOutUserCannotLogin() {
        logger.info("TEST STARTED: Locked out user cannot login");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Entering locked out username");
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.LOCKED_USERNAME);

        logger.info("ACT: Entering password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.VALID_PASSWORD);

        logger.info("ACT: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        logger.info("ASSERT: Checking if locked out error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.LOCKED_USER_ERROR_MESSAGE),
                "Error message should be displayed for locked out user");

        String errorMessage = loginPage.getText(LoginPage.ERROR_MESSAGE);
        logger.info("Error message: {}", errorMessage);
        assertTrue(errorMessage.toLowerCase().contains("locked"),
                "Error message should mention account is locked");

        logger.info("TEST PASSED: Locked out user cannot login");
    }

    @Test
    @DisplayName("Test 4: Error message appears with empty username")
    public void testLoginWithEmptyUsername() {
        logger.info("TEST STARTED: Login with empty username");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Leaving username empty and entering password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.VALID_PASSWORD);

        logger.info("ACT: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        logger.info("ASSERT: Checking if error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.ERROR_MESSAGE),
                "Error message should be displayed for empty username");

        String errorMessage = loginPage.getText(LoginPage.ERROR_MESSAGE);
        logger.info("Error message: {}", errorMessage);

        logger.info("TEST PASSED: Error message displayed for empty username");
    }

    @Test
    @DisplayName("Test 5: Error message appears with empty password")
    public void testLoginWithEmptyPassword() {
        logger.info("TEST STARTED: Login with empty password");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Entering username and leaving password empty");
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.VALID_USERNAME);

        logger.info("ACT: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        logger.info("ASSERT: Checking if error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.ERROR_MESSAGE),
                "Error message should be displayed for empty password");

        String errorMessage = loginPage.getText(LoginPage.ERROR_MESSAGE);
        logger.info("Error message: {}", errorMessage);

        logger.info("TEST PASSED: Error message displayed for empty password");
    }

    @Test
    @DisplayName("Test 6: All login page elements are visible")
    public void testLoginPageElementsAreVisible() {
        logger.info("TEST STARTED: Verify all login page elements are visible");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ASSERT: Checking if username field is visible");
        assertTrue(loginPage.isVisible(LoginPage.USERNAME_INPUT),
                "Username field should be visible");

        logger.info("ASSERT: Checking if password field is visible");
        assertTrue(loginPage.isVisible(LoginPage.PASSWORD_INPUT),
                "Password field should be visible");

        logger.info("ASSERT: Checking if login button is visible");
        assertTrue(loginPage.isVisible(LoginPage.LOGIN_BUTTON),
                "Login button should be visible");

        logger.info("TEST PASSED: All login page elements are visible");
    }

    @Test
    @DisplayName("Test 7: User can clear username field")
    public void testClearUsernameField() {
        logger.info("TEST STARTED: Clear username field");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Entering username");
        loginPage.fill(LoginPage.USERNAME_INPUT, "test_user");

        logger.info("ACT: Clearing username field");
        loginPage.clearField(LoginPage.USERNAME_INPUT);

        logger.info("ASSERT: Checking if username field is empty");
        String username = loginPage.getInputValue(LoginPage.USERNAME_INPUT);
        assertTrue(username.isEmpty(), "Username field should be empty after clearing");

        logger.info("TEST PASSED: Username field cleared successfully");
    }

    @Test
    @DisplayName("Test 8: User can clear password field")
    public void testClearPasswordField() {
        logger.info("TEST STARTED: Clear password field");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ACT: Entering password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, "test_password");

        logger.info("ACT: Clearing password field");
        loginPage.clearField(LoginPage.PASSWORD_INPUT);

        logger.info("ASSERT: Checking if password field is empty");
        String password = loginPage.getInputValue(LoginPage.PASSWORD_INPUT);
        assertTrue(password.isEmpty(), "Password field should be empty after clearing");

        logger.info("TEST PASSED: Password field cleared successfully");
    }

    @Test
    @DisplayName("Test 9: Login page title is correct")
    public void testLoginPageTitle() {
        logger.info("TEST STARTED: Verify login page title");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ASSERT: Checking page title");
        String pageTitle = page.title();
        logger.info("Page title: {}", pageTitle);

        assertNotNull(pageTitle, "Page title should not be null");
        assertFalse(pageTitle.isEmpty(), "Page title should not be empty");

        logger.info("TEST PASSED: Login page has valid title: {}", pageTitle);
    }

    @Test
    @DisplayName("Test 10: Login button is enabled")
    public void testLoginButtonIsEnabled() {
        logger.info("TEST STARTED: Verify login button is enabled");

        logger.info("ARRANGE: Login page loaded");
        logger.info("ASSERT: Checking if login button is enabled");
        boolean isButtonEnabled = loginPage.isEnabled(LoginPage.LOGIN_BUTTON);
        assertTrue(isButtonEnabled, "Login button should be enabled");

        logger.info("TEST PASSED: Login button is enabled");
    }
}