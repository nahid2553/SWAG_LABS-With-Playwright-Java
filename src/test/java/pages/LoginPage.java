package pages;

import configurations.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public static final String USERNAME_INPUT = "input[data-test='username']";
    public static final String PASSWORD_INPUT = "input[data-test='password']";
    public static final String LOGIN_BUTTON = "input[data-test='login-button']";
    public static final String ERROR_MESSAGE = "[data-test='error']" ;
    public static final String LOCKED_USER_ERROR_MESSAGE = "[data-test='error-button']";

    public LoginPage(Page page) {
        super(page);
    }
}
