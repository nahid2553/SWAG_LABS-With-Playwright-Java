package Configurations;

import Utilities.Config;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected Page page;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(Page page) {
        this.page = page;
    }

    // NAVIGATION
    public void navigateTo(String url) {
        try {
            logger.info("Navigating to URL: {}", url);
            page.navigate(url);
            page.waitForLoadState();
            logger.info("Page loaded successfully: {}", url);
        } catch (PlaywrightException e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw e;
        }
    }

    public void waitForPageLoad() {
        try {
            logger.debug("Waiting for page load...");
            page.waitForLoadState();
            logger.debug("Page load completed");
        } catch (PlaywrightException e) {
            logger.error("Page load timeout", e);
            throw e;
        }
    }

    // WAIT METHODS
    protected void waitForElement(String selector) {
        try {
            logger.debug("Waiting for element: {}", selector);
            page.waitForSelector(selector,
                    new Page.WaitForSelectorOptions()
                            .setTimeout(Config.EXPLICIT_WAIT)
            );
            logger.debug("Element found: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Element not found within timeout: {}", selector, e);
            throw e;
        }
    }

    // CLICK & FILL
    public void click(String selector) {
        try {
            logger.info("Clicking element: {}", selector);
            waitForElement(selector);
            page.click(selector);
            logger.info("Element clicked successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Failed to click element: {}", selector, e);
            throw e;
        }
    }

    public void fill(String selector, String text) {
        try {
            logger.info("Filling element: {} with text (length: {})", selector, text.length());
            waitForElement(selector);
            page.fill(selector, text);
            logger.debug("Element filled successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Failed to fill element: {}", selector, e);
            throw e;
        }
    }

    // GET TEXT
    public String getText(String selector) {
        try {
            logger.debug("Getting text from element: {}", selector);
            waitForElement(selector);
            String text = page.textContent(selector);
            logger.info("Retrieved text from element: {} -> '{}'", selector, text);
            return text;
        } catch (PlaywrightException e) {
            logger.error("Failed to get text from element: {}", selector, e);
            throw e;
        }
    }

    // VISIBILITY & PRESENCE
    public boolean isVisible(String selector) {
        try {
            boolean visible = page.isVisible(selector);
            logger.debug("Element visibility check: {} -> {}", selector, visible);
            return visible;
        } catch (PlaywrightException e) {
            logger.debug("Element not visible or not found: {}", selector);
            return false;
        }
    }

    // CLEAR FIELD
    public void clearField(String selector) {
        try {
            logger.info("Clearing field: {}", selector);
            waitForElement(selector);
            page.fill(selector, "");
            logger.info("Field cleared successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Failed to clear field: {}", selector, e);
            throw e;
        }
    }

    // GET INPUT VALUE
    public String getInputValue(String selector) {
        try {
            logger.debug("Getting input value from: {}", selector);
            waitForElement(selector);
            String value = page.inputValue(selector);
            logger.debug("Retrieved input value from: {} -> '{}'", selector, value);
            return value;
        } catch (PlaywrightException e) {
            logger.error("Failed to get input value from: {}", selector, e);
            throw e;
        }
    }

    // IS ENABLED
    public boolean isEnabled(String selector) {
        try {
            boolean enabled = page.isEnabled(selector);
            logger.debug("Element enabled check: {} -> {}", selector, enabled);
            return enabled;
        } catch (PlaywrightException e) {
            logger.debug("Failed to check if element is enabled: {}", selector);
            return false;
        }
    }
}