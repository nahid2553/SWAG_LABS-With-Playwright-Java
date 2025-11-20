package Pages;

import Configurations.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private static final String USERNAME_INPUT = "" ;
    private static final String PASSWORD_INPUT = "" ;
    private static final String LOGIN_BUTTON = "" ;
    private static final String ERROR_MESSAGE = "" ;

    public LoginPage(Page page) {
        super(page);
    }
}
