package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Config {
    protected static final Logger logger = LoggerFactory.getLogger(Config.class);

    //URL CONFIGURATION
    public static final String DEFAULT_BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_PAGE_ENDPOINT = "/inventory";

    //BROWSER CONFIGURATION
    public static final String DEFAULT_BROWSER = "chromium";
    public static final boolean DEFAULT_HEADLESS_MODE = true;

    //TIMEOUT FOR PAGE NAVIGATION AND LOADING
    public static final int BROWSER_TIMEOUT = 30000;

    //TIMEOUT FOR EXPLICIT WAITS ON ELEMENTS
    public static final int EXPLICIT_WAIT = 10000;

    //TIMEOUT FOR IMPLICIT WAIT
    public static final int IMPLICIT_WAIT = 5000;

    //VIEWPORT CONFIGURATION
    public static final int VIEWPORT_WIDTH = 1080;
    public static final int VIEWPORT_HEIGHT = 800;

    //VALID CREDENTIALS
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";

    //INVALID CREDENTIALS
    public static final String INVALID_USERNAME = "locked_out_user";
    public static final String INVALID_PASSWORD = "wrong_password";

    //LOCKED CREDENTIALS
    public static final String LOCKED_USERNAME = "locked_out_user";
    public static final String LOCKED_USER_PASSWORD = "secret_sauce";

    //NUMBER OF TEST FOR FLAKY TESTS
    public static final int MAX_RETRIES = 3;

    // DIRECTORY FOR STORING SCREENSHOTS
    public static final String SCREENSHOT_DIR = "screenshots";

    //ENABLE DETAILED LOGGING
    public static final boolean ENABLE_DETAILED_LOGGING = true;

    public static String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        if (envUrl != null && !envUrl.trim().isEmpty()) {
            return envUrl;
        }
        return DEFAULT_BASE_URL;
    }

    public static String getBrowser() {
        String envBrowser = System.getenv("BROWSER");
        if (envBrowser != null && !envBrowser.trim().isEmpty()) {
            return envBrowser.toLowerCase();
        }
        return DEFAULT_BROWSER;
    }

    public static String BROWSER = getBrowser();

    public static boolean isHeadlessMode() {
        String headlessEnv = System.getenv("HEADLESS");
        if (headlessEnv != null && !headlessEnv.trim().isEmpty()) {
            return Boolean.parseBoolean(headlessEnv);
        }
        return DEFAULT_HEADLESS_MODE;
    }

    public static String getInventoryUrl() {
        return getBaseUrl() + INVENTORY_PAGE_ENDPOINT;
    }

    //Useful for debugging and CI/CD verification
    public static void logConfiguration() {
        logger.info("=".repeat(20)+ "TEST CONFIGURATION" +"=".repeat(20));
        logger.info("Base URL: " + getBaseUrl());
        logger.info("Browser: " + getBrowser());
        logger.info("Headless Mode: " + isHeadlessMode());
        logger.info("Viewport: " + VIEWPORT_WIDTH + "x" + VIEWPORT_HEIGHT);
        logger.info("Browser Timeout: " + BROWSER_TIMEOUT + "ms");
        logger.info("Explicit Wait: " + EXPLICIT_WAIT + "ms");
        logger.info("Max Retries: " + MAX_RETRIES);
        logger.info("=".repeat(60));
    }

}
