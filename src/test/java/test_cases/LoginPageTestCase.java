package test_cases;

import configurations.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
        assertTrue(loginPage.areAllLoginElementsVisible(),
                "Login page elements should be visible on initial load");
    }

    // ==================== POSITIVE TEST CASES ====================

    @Test
    @Tag("smoke")
    @Tag("critical")
    @Tag("functional")
    @Tag("quick")
    @DisplayName("TC001: User should login successfully with valid credentials")
    public void testLoginWithValidCredentials() {
        logger.info("🔷 TC001: Starting - Login with valid credentials");
        //Act
        loginPage.loginWithCredentials(Config.VALID_USERNAME,Config.VALID_PASSWORD);
        //Assert
        assertTrue(loginPage.isLoginSuccessful(),"User should be redirected to inventory page after successful login");
        String currentUrl = page.url();
        assertTrue(currentUrl.contains("inventory"),"URL should contain 'inventory' after successful login, but was: " + currentUrl);
        logger.info("✅ TC001: PASSED - User successfully logged in ");
    }

    @Test
    @Tag("smoke")
    @Tag("critical")
    @Tag("ui")
    @DisplayName("TC002: Login button is enabled and clickable on login page")
    public void testLoginButtonEnabled() {
        logger.info("🔷 TC002: Starting - Login button enabled check");
        assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled on login page");
        logger.info("✅ TC002: PASSED - Login button is enabled");
    }

    @Test
    @Tag("smoke")
    @Tag("ui")
    @DisplayName("TC003: All login page elements are visible")
    public void testLoginPageElementsVisibility() {
        logger.info("🔷 TC003: Starting - Login page elements visibility check");
        assertTrue(loginPage.areAllLoginElementsVisible(),"All login form elements should be visible (username, password, button)");
        logger.info("✅ TC003: PASSED - All login page elements are visible");
    }

    // ==================== NEGATIVE TEST CASES ====================

    @Test
    @Tag("functional")
    @Tag("critical")
    @DisplayName("TC004: Error message appears with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        logger.info("🔷 TC004: Starting - Login with invalid credentials");
        loginPage.loginWithCredentials(Config.INVALID_USERNAME, Config.INVALID_PASSWORD);
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");
        String errorMessage = loginPage.getErrorMessage();
        assertNotNull(errorMessage, "Error message should not be null");
        assertTrue(errorMessage.contains("Username and password do not match"), "Error message should indicate invalid credentials, but was: " + errorMessage);
        logger.info("✅ TC004: PASSED - Error message displayed for invalid credentials");
    }

    @Test
    @Tag("functional")
    @Tag("critical")
    @DisplayName("TC005: Locked user cannot login")
    public void testLockedUserCannotLogin() {
        logger.info("🔷 TC005: Starting - Locked user login attempt");
        loginPage.loginWithCredentials(Config.LOCKED_USERNAME, Config.LOCKED_USER_PASSWORD);
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for locked user");
        assertTrue(loginPage.isLockedUserErrorDisplayed(), "Error message should indicate user is locked out");
        assertFalse(loginPage.isLoginSuccessful(), "Locked user should not be redirected to inventory page");
        logger.info("✅ TC005: PASSED - Locked user cannot login");
    }

    @Test
    @Tag("functional")
    @DisplayName("TC006: Error message appears with empty username")
    public void testLoginWithEmptyUsername() {
        logger.info("🔷 TC006: Starting - Login with empty username");

        //Only fill password and leave username empty
        loginPage.enterPassword(Config.VALID_PASSWORD);
        loginPage.clickLoginButton();
        loginPage.waitForPageLoad();
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for empty username");
        String errorMessage = loginPage.getErrorMessage();
        assertNotNull(errorMessage,
                "Error message should not be null");
        assertFalse(loginPage.isLoginSuccessful(),
                "User should not login with empty username");
        logger.info("✅ TC006: PASSED - Error displayed for empty username");
    }

    @Test
    @Tag("functional")
    @DisplayName("TC007: Error message appears with empty password")
    public void testLoginWithEmptyPassword() {
        logger.info("🔷 TC007: Starting - Login with empty password");

        //Only fill username and leave password empty
        loginPage.enterUsername(Config.VALID_USERNAME);
        loginPage.clickLoginButton();
        loginPage.waitForPageLoad();
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for empty password");
        String errorMessage = loginPage.getErrorMessage();
        assertNotNull(errorMessage,
                "Error message should not be null");
        assertFalse(loginPage.isLoginSuccessful(),
                "User should not login with empty password");
        logger.info("✅ TC007: PASSED - Error displayed for empty password");
    }

    @Test
    @Tag("functional")
    @DisplayName("TC008: Error message appears with both fields empty")
    public void testLoginWithBothFieldsEmpty() {
        logger.info("🔷 TC008: Starting - Login with both fields empty");

        //Click login without filling any fields
        loginPage.clickLoginButton();
        loginPage.waitForPageLoad();
        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed when both fields are empty");
        assertFalse(loginPage.isLoginSuccessful(),
                "User should not login with empty credentials");
        logger.info("✅ TC008: PASSED - Error displayed for empty credentials");
    }

    //==================== FIELD INTERACTION TEST CASES ====================

    @Test
    @Tag("ui")
    @DisplayName("TC009: User can clear username field")
    public void testClearUsernameField() {
        logger.info("🔷 TC009: Starting - Clear username field");
        String testUsername = "test_user_123";
        loginPage.enterUsername(testUsername);
        String filledValue = loginPage.getUsernameValue();
        assertEquals(testUsername, filledValue,
                "Username should be filled with entered value");
        loginPage.clearUsername();
        String clearedValue = loginPage.getUsernameValue();
        assertTrue(clearedValue.isEmpty(),
                "Username field should be empty after clearing, but was: " + clearedValue);
        logger.info("✅ TC009: PASSED - Username field cleared successfully");
    }

    @Test
    @Tag("ui")
    @DisplayName("TC010: User can clear password field")
    public void testClearPasswordField() {
        logger.info("🔷 TC010: Starting - Clear password field");
        String testPassword = "test_password_123";
        loginPage.enterPassword(testPassword);
        String filledValue = loginPage.getPasswordValue();
        assertEquals(testPassword, filledValue,
                "Password should be filled with entered value");
        loginPage.clearPassword();
        String clearedValue = loginPage.getPasswordValue();
        assertTrue(clearedValue.isEmpty(),
                "Password field should be empty after clearing, but was: " + clearedValue);
        logger.info("✅ TC010: PASSED - Password field cleared successfully");
    }

    @Test
    @Tag("ui")
    @Tag("quick")
    @DisplayName("TC011: Username field accepts and retains input")
    public void testUsernameFieldInput() {
        logger.info("🔷 TC011: Starting - Username field input validation");
        String testUsername = "input_test_user";
        loginPage.enterUsername(testUsername);
        String retrievedValue = loginPage.getUsernameValue();
        assertEquals(testUsername, retrievedValue,
                "Username field should retain the entered value");
        logger.info("✅ TC011: PASSED - Username field input validation successful");
    }

    @Test
    @Tag("ui")
    @Tag("quick")
    @DisplayName("TC012: Password field accepts and retains input")
    public void testPasswordFieldInput() {
        logger.info("🔷 TC012: Starting - Password field input validation");
        String testPassword = "input_test_password";
        loginPage.enterPassword(testPassword);
        String retrievedValue = loginPage.getPasswordValue();
        assertEquals(testPassword, retrievedValue,
                "Password field should retain the entered value");
        logger.info("✅ TC012: PASSED - Password field input validation successful");
    }

    // ==================== PAGE ELEMENT TEST CASES ====================

    @Test
    @Tag("ui")
    @DisplayName("TC013: Login page title is present and valid")
    public void testLoginPageTitle() {
        logger.info("🔷 TC013: Starting - Login page title validation");
        String pageTitle = page.title();
        assertNotNull(pageTitle,
                "Page title should not be null");
        assertFalse(pageTitle.isEmpty(),
                "Page title should not be empty");
        logger.info("✅ TC013: PASSED - Login page title is valid: {}", pageTitle);
    }

    @Test
    @Tag("ui")
    @DisplayName("TC014: Login page header/logo is visible")
    public void testLoginPageLogoVisibility() {
        logger.info("🔷 TC014: Starting - Login page logo visibility");
        String pageTitle = loginPage.getPageTitle();
        assertNotNull(pageTitle,
                "Page title/logo should not be null");
        assertFalse(pageTitle.isEmpty(),
                "Page title/logo should not be empty");
        logger.info("✅ TC014: PASSED - Login page logo is visible");
    }

    @ParameterizedTest
    @CsvSource({
            "user1, pass1",
            "user2, pass2",
            "testuser, testpass"
    })
    @Tag("functional")
    @DisplayName("TC015: Invalid credentials display error (Data-driven)")
    public void testMultipleInvalidCredentials(String username, String password) {
        logger.info("🔷 TC015: Testing with username: {}, password: {}", username, password);

        loginPage.loginWithCredentials(username, password);

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid credentials");

        logger.info("✅ TC015: PASSED - Error displayed for credentials: {}/{}", username, password);
    }

}
