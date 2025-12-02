package configurations;

import utilities.Config;
import com.microsoft.playwright.*;  //Bring in Playwright tools
import org.junit.jupiter.api.AfterEach;  //Import @AfterEach annotation
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utilities.Config.*;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);  //Create a logger to record events
    private static final String SEPARATOR = "=".repeat(50);
    protected static final String TEST_FAILED_MESSAGE = "❌ TEST FAILED" + "-".repeat(50);

    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected Playwright playwright;

    @BeforeEach  //Annotation (tells JUnit to run this before each test)
    public void setUp() {
        try {
            logTestStartInfo();
            initializePlaywright();
            initializeBrowser();
            initializeBrowserContext();
            initializePage();
            logger.info("Browser initialized successfully");
            logger.info(SEPARATOR);
        } catch (Exception e) {
            logger.error("Failed to initialize browser", e);
            cleanupResources();
            throw new RuntimeException("Browser initialization failed", e);
        }
    }

    @AfterEach
    public void tearDown() {
        logger.info(SEPARATOR);
        logger.info("Starting test tear down...");

        try {
            cleanupResources();
            logger.info("Tear down completed successfully");
            logger.info(SEPARATOR);
        } catch (Exception e) {
            logger.error("Error during tear down", e);
        }
    }

    private void logTestStartInfo() {
        logger.info(SEPARATOR);
        logger.info("Test Configuration:");
        logger.info("Browser: {}", Config.DEFAULT_BROWSER);
        logger.info("Headless Mode: {}", isHeadlessMode());
        logger.info("Viewport: {}x{}", Config.VIEWPORT_WIDTH, Config.VIEWPORT_HEIGHT);
        logger.info("Base URL: {}", getBaseUrl());
    }

    private void initializePlaywright() {
        logger.debug("Creating Playwright instance");
        this.playwright = Playwright.create();
    }

    private void initializeBrowser() {
        logger.debug("Initializing {} browser", Config.DEFAULT_BROWSER);
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(isHeadlessMode());

        this.browser = switch (Config.DEFAULT_BROWSER.toLowerCase()) {
            case "firefox" -> {
                logger.info("Launching Firefox browser");
                yield playwright.firefox().launch(launchOptions);
            }
            case "webkit" -> {
                logger.info("Launching WebKit browser");
                yield playwright.webkit().launch(launchOptions);
            }
            default -> {
                logger.info("Launching Chromium browser");
                yield playwright.chromium().launch(launchOptions);
            }
        };
    }

    private void initializeBrowserContext() {
        logger.debug("Creating browser context with viewport: {}x{}",
                Config.VIEWPORT_WIDTH, Config.VIEWPORT_HEIGHT);

        this.context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(Config.VIEWPORT_WIDTH, Config.VIEWPORT_HEIGHT)
                        .setIgnoreHTTPSErrors(true)
        );
    }

    private void initializePage() {
        logger.debug("Creating new page");
        this.page = context.newPage();
        page.setDefaultTimeout(Config.BROWSER_TIMEOUT);
        page.setDefaultNavigationTimeout(Config.BROWSER_TIMEOUT);
    }

    private void cleanupResources() {
        if (page != null) {
            try {
                page.close();
                logger.debug("Page closed");
            } catch (Exception e) {
                logger.warn("Error closing page", e);
            }
        }

        if (context != null) {
            try {
                context.close();
                logger.debug("Context closed");
            } catch (Exception e) {
                logger.warn("Error closing context", e);
            }
        }

        if (browser != null) {
            try {
                browser.close();
                logger.debug("Browser closed");
            } catch (Exception e) {
                logger.warn("Error closing browser", e);
            }
        }

        if (playwright != null) {
            try {
                playwright.close();
                logger.debug("Playwright closed");
            } catch (Exception e) {
                logger.warn("Error closing Playwright", e);
            }
        }
    }

    protected void takeScreenshot(String fileName) {
        if (page != null) {
            try {
                String screenshotPath = "screenshots/" + fileName + ".png";
                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(java.nio.file.Paths.get(screenshotPath)));
                logger.info("Screenshot saved: {}", screenshotPath);
            } catch (Exception e) {
                logger.warn("Failed to take screenshot", e);
            }
        }
    }

    protected String getCurrentUrl() {
        return page != null ? page.url() : "";
    }

    protected String getPageTitle() {
        return page != null ? page.title() : "";
    }

}