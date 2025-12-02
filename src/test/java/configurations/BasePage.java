package configurations;

import com.microsoft.playwright.options.WaitForSelectorState;
import utilities.Config;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected Page page;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(Page page) {
        if(page == null){
            throw new IllegalArgumentException("Page object cannot be null");
        }
        this.page = page;
    }

    public void navigateTo(String url) {
        try {
            if (url == null || url.isEmpty()) {
                throw new IllegalArgumentException("URL cannot be null or empty");
            }
            logger.info("Navigating to URL: {}", url);  //{}Placeholder for variable
            page.navigate(url);
            waitForPageLoad();
            logger.info("Page loaded successfully: {}", url);
        } catch (PlaywrightException e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw new RuntimeException("Navigation failed to: " + url, e);
        }
    }

    public void waitForPageLoad() {
        try {  //Try to do something
            logger.debug("⏳ Waiting for page load...");
            page.waitForLoadState();  //Wait until page is fully loaded
            logger.debug("Page load completed");  // DEBUG helps understand what happened, DEBUG info helps trace the problem
        } catch (PlaywrightException e) {  //If it fails, catch the error and handle it
            logger.error("Page load timeout", e);
            throw new RuntimeException("Page load timeout", e);  //Throw RuntimeException and stop test
        }
    }

    protected Locator getLocator(String selector) { //explain.....
        if (selector == null || selector.isEmpty()) {
            throw new IllegalArgumentException("Selector cannot be null or empty");
        }
        return page.locator(selector).first();
    }

    public void click(String selector) {
        try {
            logger.info("Clicking element: {}", selector);
            Locator locator = getLocator(selector);
            waitForElementVisibility(locator);
            locator.click();
            logger.info("Element clicked successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("⚠️ Failed to click element: {}", selector, e);
            throw new RuntimeException("Click failed on: " + selector, e);
        }
    }

    public void fill(String selector, String text) {
        try {
            if (text == null) {
                logger.warn("Filling with null text: {}", selector);
                text = "";
            }

            if (text.isEmpty()) {
                logger.warn("Filling with empty text: {}", selector);
            } else {
                logger.info("Filling element: {} with text of length: {}", selector, text.length());
            }
            Locator locator = getLocator(selector);
            waitForElementVisibility(locator);
            locator.clear();
            locator.fill(text);
            logger.debug("Element filled successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Failed to fill element: {}", selector, e);
            throw new RuntimeException("Fill failed on: " + selector, e);
        }
    }

    public void clearField(String selector) {
        try {
            logger.info("Clearing field: {}", selector);
            Locator locator = getLocator(selector);
            waitForElementVisibility(locator);
            locator.clear();
            logger.info("Field cleared successfully: {}", selector);
        } catch (PlaywrightException e) {
            logger.error("Failed to clear field: {}", selector, e);
            throw new RuntimeException("Clear failed on: " + selector, e);
        }
    }

    public String getText(String selector) {
        try {
            logger.debug("Getting text from element: {}", selector);
            Locator locator = getLocator(selector);
            waitForElementVisibility(locator);
            String text = locator.textContent();
            logger.info("Retrieved text from element: {} -> '{}'", selector, text);
            return text != null ? text.trim() : "";
        } catch (PlaywrightException e) {
            logger.error("Failed to get text from element: {}", selector, e);
            throw new RuntimeException("getText failed on: " + selector, e);
        }
    }

    public String getInputValue(String selector) {
        try {
            logger.debug("Getting input value from: {}", selector);
            Locator locator = getLocator(selector);
            waitForElementVisibility(locator);
            String value = locator.inputValue();
            logger.debug("Retrieved input value from: {} (length: {})", selector, value != null ? value.length() : 0);
            return value != null ? value : "";
        } catch (PlaywrightException e) {
            logger.error("Failed to get input value from: {}", selector, e);
            throw new RuntimeException("getInputValue failed on: " + selector, e);
        }
    }

    public boolean isVisible(String selector) {
        try {
            boolean visible = page.locator(selector).first().isVisible();
            logger.debug("Element visibility check: {} -> {}", selector, visible);
            return visible;
        } catch (PlaywrightException e) {
            logger.debug("Element not visible or not found: {}", selector);
            return false;
        }
    }

    public boolean isEnabled(String selector) {
        try {
            boolean enabled = getLocator(selector).isEnabled();
            logger.debug("Element enabled check: {} -> {}", selector, enabled);
            return enabled;
        } catch (PlaywrightException e) {
            logger.debug("Failed to check if element is enabled: {}", selector);
            return false;
        }
    }

    protected void waitForElementVisibility(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setTimeout(Config.EXPLICIT_WAIT));
        } catch (PlaywrightException e) {
            logger.warn("Element did not become visible within timeout");
            throw e;
        }
    }

//    public void takeScreenshot(String screenshotName) {
//        try {
//            String fileName = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
//            page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(fileName)));
//            logger.info("Screenshot taken: {}", fileName);
//        } catch (Exception e) {
//            logger.error("Failed to take screenshot: {}", screenshotName, e);
//        }
//    }

}