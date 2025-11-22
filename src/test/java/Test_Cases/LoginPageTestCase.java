package Test_Cases;

import Configurations.BaseTest;
import Pages.LoginPage;
import Utilities.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Page Test Cases")
public class LoginPageTestCase extends BaseTest {  // Add extends BaseTest

    private LoginPage loginPage;

    @BeforeEach
    public void initLoginPage() {
        loginPage = new LoginPage(page);  // Now page is available from BaseTest
        loginPage.navigateTo(Config.getBaseUrl());
        loginPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Test 1: User should login successfully with valid credentials")
    public void testSuccessfulLogin() {
        System.out.println("📝 Starting Test: testSuccessfulLogin");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering username: " + Config.VALID_USERNAME);
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.VALID_USERNAME);

        System.out.println("→ Acting: Entering password: " + Config.VALID_PASSWORD);
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.VALID_PASSWORD);

        System.out.println("→ Acting: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        System.out.println("✓ Asserting: Checking if redirected to inventory page");
        String currentUrl = page.url();  // page is now available
        assertTrue(currentUrl.contains("inventory"),
                "Expected URL to contain 'inventory', but got: " + currentUrl);

        System.out.println("✅ TEST PASSED: User logged in successfully");
        System.out.println("Current URL: " + currentUrl);
    }

    @Test
    @DisplayName("Test 2: Error message appears with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        System.out.println("📝 Starting Test: testLoginWithInvalidCredentials");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering invalid username");
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.INVALID_USERNAME);

        System.out.println("→ Acting: Entering invalid password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.INVALID_PASSWORD);

        System.out.println("→ Acting: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        System.out.println("✓ Asserting: Checking if error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.ERROR_MESSAGE),"Error message should be displayed for invalid credentials");

        String errorMessage = loginPage.getText(LoginPage.ERROR_MESSAGE);
        System.out.println("Error message: " + errorMessage);
        assertNotNull(errorMessage, "Error message should not be null");

        System.out.println("✅ TEST PASSED: Error message displayed for invalid credentials");
    }

    @Test
    @DisplayName("Test 3: Locked out user cannot login")
    public void testLockedOutUserCannotLogin() {
        System.out.println("📝 Starting Test: testLockedOutUserCannotLogin");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering locked out username");
        loginPage.fill(LoginPage.USERNAME_INPUT, Config.LOCKED_USERNAME);

        System.out.println("→ Acting: Entering password");
        loginPage.fill(LoginPage.PASSWORD_INPUT, Config.VALID_PASSWORD);

        System.out.println("→ Acting: Clicking login button");
        loginPage.click(LoginPage.LOGIN_BUTTON);
        loginPage.waitForPageLoad();

        System.out.println("✓ Asserting: Checking if error message is displayed");
        assertTrue(loginPage.isVisible(LoginPage.LOCKED_USER_ERROR_MESSAGE),
                "Error message should be displayed for locked out user");

        String errorMessage = loginPage.getText("[data-test='error']");
        System.out.println("Error message: " + errorMessage);
        assertTrue(errorMessage.toLowerCase().contains("locked"),
                "Error message should mention account is locked");

        System.out.println("✅ TEST PASSED: Locked out user cannot login");
    }

    @Test
    @DisplayName("Test 4: Error message appears with empty username")
    public void testLoginWithEmptyUsername() {
        System.out.println("📝 Starting Test: testLoginWithEmptyUsername");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Leaving username empty");
        System.out.println("→ Acting: Entering password");
        loginPage.fill("input[data-test='password']", Config.VALID_PASSWORD);

        System.out.println("→ Acting: Clicking login button");
        loginPage.click("input[data-test='login-button']");
        loginPage.waitForPageLoad();

        System.out.println("✓ Asserting: Checking if error message is displayed");
        assertTrue(loginPage.isVisible("[data-test='error']"),
                "Error message should be displayed for empty username");

        String errorMessage = loginPage.getText("[data-test='error']");
        System.out.println("Error message: " + errorMessage);

        System.out.println("✅ TEST PASSED: Error message for empty username");
    }

    @Test
    @DisplayName("Test 5: Error message appears with empty password")
    public void testLoginWithEmptyPassword() {
        System.out.println("📝 Starting Test: testLoginWithEmptyPassword");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering username");
        loginPage.fill("input[data-test='username']", Config.VALID_USERNAME);

        System.out.println("→ Acting: Leaving password empty");
        System.out.println("→ Acting: Clicking login button");
        loginPage.click("input[data-test='login-button']");
        loginPage.waitForPageLoad();

        System.out.println("✓ Asserting: Checking if error message is displayed");
        assertTrue(loginPage.isVisible("[data-test='error']"),
                "Error message should be displayed for empty password");

        String errorMessage = loginPage.getText("[data-test='error']");
        System.out.println("Error message: " + errorMessage);

        System.out.println("✅ TEST PASSED: Error message for empty password");
    }

    @Test
    @DisplayName("Test 6: All login page elements are visible")
    public void testLoginPageElementsAreVisible() {
        System.out.println("📝 Starting Test: testLoginPageElementsAreVisible");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("✓ Asserting: Checking if username field is visible");
        assertTrue(loginPage.isVisible("input[data-test='username']"),
                "Username field should be visible");

        System.out.println("✓ Asserting: Checking if password field is visible");
        assertTrue(loginPage.isVisible("input[data-test='password']"),
                "Password field should be visible");

        System.out.println("✓ Asserting: Checking if login button is visible");
        assertTrue(loginPage.isVisible("input[data-test='login-button']"),
                "Login button should be visible");

        System.out.println("✅ TEST PASSED: All login page elements are visible");
    }

    @Test
    @DisplayName("Test 7: User can clear username field")
    public void testClearUsernameField() {
        System.out.println("📝 Starting Test: testClearUsernameField");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering username");
        loginPage.fill("input[data-test='username']", "test_user");

        System.out.println("→ Acting: Clearing username field");
        loginPage.clearField("input[data-test='username']");

        System.out.println("✓ Asserting: Checking if username field is empty");
        String username = loginPage.getInputValue("input[data-test='username']");
        assertTrue(username.isEmpty(), "Username field should be empty after clearing");

        System.out.println("✅ TEST PASSED: Username field cleared successfully");
    }

    @Test
    @DisplayName("Test 8: User can clear password field")
    public void testClearPasswordField() {
        System.out.println("📝 Starting Test: testClearPasswordField");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("→ Acting: Entering password");
        loginPage.fill("input[data-test='password']", "test_password");

        System.out.println("→ Acting: Clearing password field");
        loginPage.clearField("input[data-test='password']");

        System.out.println("✓ Asserting: Checking if password field is empty");
        String password = loginPage.getInputValue("input[data-test='password']");
        assertTrue(password.isEmpty(), "Password field should be empty after clearing");

        System.out.println("✅ TEST PASSED: Password field cleared successfully");
    }

    @Test
    @DisplayName("Test: Login page title is correct")
    public void testLoginPageTitle() {
        System.out.println("📝 Starting Test: testLoginPageTitle");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("✓ Asserting: Checking page title");
        String pageTitle = page.title();
        System.out.println("Page title: " + pageTitle);

        assertNotNull(pageTitle, "Page title should not be null");
        assertFalse(pageTitle.isEmpty(), "Page title should not be empty");

        System.out.println("✅ TEST PASSED: Login page has valid title: " + pageTitle);
    }

    @Test
    @DisplayName("Test 10: Login button is enabled")
    public void testLoginButtonIsEnabled() {
        System.out.println("📝 Starting Test: testLoginButtonIsEnabled");
        System.out.println("✓ Arranged: Navigated to login page");

        System.out.println("✓ Asserting: Checking if login button is enabled");
        boolean isButtonEnabled = loginPage.isEnabled("input[data-test='login-button']");
        assertTrue(isButtonEnabled, "Login button should be enabled");

        System.out.println("✅ TEST PASSED: Login button is enabled");
    }
}