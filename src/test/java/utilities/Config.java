package utilities;

public class Config {

    //Url Configuration
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_URL = getBaseUrl()+ "/inventory";  //-demo for short url

    //Browser Configuration
    public static final String BROWSER = "chromium";
    public static final boolean HEADLESS = true;
    public static final int BROWSER_TIMEOUT = 30000;

    //Wait configuration
    public static final int TIMEOUT = 5000;
    public static final int EXPLICIT_WAIT = 10000;

    //Window View Size
    public static final int VIEWPORT_WIDTH = 1080;
    public static final int VIEWPORT_HEIGHT = 800;

    //Test Credentials
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";
    public static final String INVALID_USERNAME = "locked_out_user";
    public static final String INVALID_PASSWORD = "wrong_password";
    public static final String LOCKED_USERNAME = "locked_out_user";

    public static String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : BASE_URL;  //if true : if false
    }

    public static boolean isHeadlessMode() {
        String headless = System.getenv("HEADLESS");
        if (headless != null) {
            return Boolean.parseBoolean(headless);  //Convert text to true/false
        }
        return HEADLESS;
    }
}
