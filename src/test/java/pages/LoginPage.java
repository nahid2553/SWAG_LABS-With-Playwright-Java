package pages;

import configurations.BasePage;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utilities.Config.INVENTORY_PAGE_ENDPOINT;

public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    //Locators
    public static final String USERNAME_INPUT = "input[data-test='username']";
    public static final String PASSWORD_INPUT = "input[data-test='password']";
    public static final String LOGIN_BUTTON = "input[data-test='login-button']";
    public static final String ERROR_MESSAGE = "[data-test='error']";
    private static final String PAGE_TITLE = "div.login_logo";

    //should recheck
    public static final String LOCKED_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    private static final String INVALID_CREDENTIALS_ERROR = "Username and password do not match";
    private static final String MISSING_USERNAME_ERROR = "Username is required";
    private static final String MISSING_PASSWORD_ERROR = "Password is required";

    public LoginPage(Page page) {
        super(page);
        logger.debug("LoginPage initialized");
    }

    public void loginWithCredentials(String username, String password) {
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        waitForPageLoad();
    }

    public boolean isLoginSuccessful() {
        String currentUrl = page.url();
        boolean success = currentUrl.contains(INVENTORY_PAGE_ENDPOINT);
        logger.info("Login verification - Current URL: {}, Success: {}", currentUrl, success);
        return success;
    }

    public boolean isLoginErrorDisplayed() {
        return isVisible(ERROR_MESSAGE);
    }

    public String getErrorMessage() {
        if (isLoginErrorDisplayed()) {
            String errorText = getText(ERROR_MESSAGE);
            logger.info("Error message retrieved: {}", errorText);
            return errorText;
        }
        logger.warn("Error message not found on page");
        return "";
    }

    public boolean areAllLoginElementsVisible() {
        boolean usernameVisible = isVisible(USERNAME_INPUT);
        boolean passwordVisible = isVisible(PASSWORD_INPUT);
        boolean buttonVisible = isVisible(LOGIN_BUTTON);
        boolean allVisible = usernameVisible && passwordVisible && buttonVisible;

        logger.info("Login elements visibility - Username: {}, Password: {}, Button: {}, All: {}",
                usernameVisible, passwordVisible, buttonVisible, allVisible);

        return allVisible;
    }

    public boolean isLoginButtonEnabled() {
        boolean enabled = isEnabled(LOGIN_BUTTON);
        logger.info("Login button enabled check: {}", enabled);
        return enabled;
    }

    public boolean loginAndVerify(String username, String password) {
        try {
            loginWithCredentials(username, password);
            return isLoginSuccessful();
        } catch (Exception e) {
            logger.error("Login and verify failed: {}", e.getMessage());
            return false;
        }
    }

    public void enterUsername(String username) {
        logger.debug("📝 Entering username: {}", username);
        fill(USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        logger.debug("📝 Entering password {}", password);
        fill(PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        logger.debug("🎯 Clicking login button");
        click(LOGIN_BUTTON);
    }

    public void clearUsername() {
        logger.debug("Clearing username field");
        clearField(USERNAME_INPUT);
    }

    public void clearPassword() {
        logger.debug("Clearing password field");
        clearField(PASSWORD_INPUT);
    }

    public String getUsernameValue() {
        String value = getInputValue(USERNAME_INPUT);
        logger.debug("Retrieved username value: {}", value);
        return value;
    }

    public String getPasswordValue() {
        String value = getInputValue(PASSWORD_INPUT);
        logger.debug("Retrieved password value (length: {})", value.length());
        return value;
    }

    public String getPageTitle() {
        String title = getText(PAGE_TITLE);
        logger.info("Retrieved page title: {}", title);
        return title;
    }

    public boolean validateUsernameInput(String testValue) {
        try {
            enterUsername(testValue);
            String retrievedValue = getUsernameValue();
            boolean valid = testValue.equals(retrievedValue);
            logger.info("Username input validation: {} (Expected: {}, Got: {})",
                    valid, testValue, retrievedValue);
            clearUsername();
            return valid;
        } catch (Exception e) {
            logger.error("Username input validation failed", e);
            return false;
        }
    }

    public boolean validatePasswordInput(String testValue) {
        try {
            enterPassword(testValue);
            String retrievedValue = getPasswordValue();
            boolean valid = testValue.equals(retrievedValue);
            logger.info("Password input validation: {} (Expected: {}, Got: {})",
                    valid, testValue, retrievedValue);
            clearPassword();
            return valid;
        } catch (Exception e) {
            logger.error("Password input validation failed", e);
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        return isVisible(ERROR_MESSAGE);
    }

    public boolean isLockedUserErrorDisplayed() {
        String errorMessage = getErrorMessage();
        boolean isLockedError = errorMessage.contains(LOCKED_USER_ERROR);
        logger.info("Locked user error check: {}", isLockedError);
        return isLockedError;
    }

    public boolean isInvalidCredentialsErrorDisplayed() {
        String errorMessage = getErrorMessage();
        boolean isInvalidError = errorMessage.contains(INVALID_CREDENTIALS_ERROR);
        logger.info("Invalid credentials error check: {}", isInvalidError);
        return isInvalidError;
    }

    public boolean isMissingUsernameErrorDisplayed() {
        String errorMessage = getErrorMessage();
        boolean isMissingUsernameError = errorMessage.contains(MISSING_USERNAME_ERROR);
        logger.info("Missing username error check: {}", isMissingUsernameError);
        return isMissingUsernameError;
    }

    public boolean isMissingPasswordErrorDisplayed() {
        String errorMessage = getErrorMessage();
        boolean isMissingPasswordError = errorMessage.contains(MISSING_PASSWORD_ERROR);
        logger.info("Missing password error check: {}", isMissingPasswordError);
        return isMissingPasswordError;
    }

}