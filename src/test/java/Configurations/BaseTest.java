package Configurations;

import Utilities.Config;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String SEPARATOR = "========================================";

    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected Playwright playwright;

    @BeforeEach
    public void setUp() {
        logger.info(SEPARATOR);
        logger.info("Browser Setup - Browser: {}, Headless: {}, Viewport: {}x{}",
                Config.BROWSER,
                Config.isHeadlessMode(),
                Config.VIEWPORT_WIDTH,
                Config.VIEWPORT_HEIGHT);
        logger.info(SEPARATOR);

        try {
            this.playwright = Playwright.create();
            browser = switch(Config.BROWSER.toLowerCase()) {
                case "firefox" -> {
                    logger.info("Launching Firefox browser");
                    yield playwright.firefox().launch(
                            new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode())
                    );
                }
                case "webkit" -> {
                    logger.info("Launching WebKit browser");
                    yield playwright.webkit().launch(
                            new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode())
                    );
                }
                default -> {
                    logger.info("Launching Chromium browser");
                    yield playwright.chromium().launch(
                            new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode())
                    );
                }
            };

            context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setViewportSize(Config.VIEWPORT_WIDTH, Config.VIEWPORT_HEIGHT)
            );
            page = context.newPage();

            logger.info("Browser initialized successfully");
            logger.info(SEPARATOR);

        } catch (Exception e) {
            logger.error("Failed to initialize browser", e);
            throw new RuntimeException("Browser initialization failed", e);
        }
    }

    @AfterEach
    public void tearDown() {
        logger.info(SEPARATOR);
        logger.info("Starting test teardown...");

        try {
            if (page != null) {
                page.close();
                logger.debug("Page closed");
            }
            if (context != null) {
                context.close();
                logger.debug("Context closed");
            }
            if (browser != null) {
                browser.close();
                logger.debug("Browser closed");
            }
            if (playwright != null) {
                playwright.close();
                logger.debug("Playwright closed");
            }

            logger.info("Teardown completed successfully");
            logger.info(SEPARATOR);

        } catch (Exception e) {
            logger.error("Error during teardown", e);
        }
    }
}