package Configurations;


import Utilities.Config;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class BasePage {
    protected Page page;
    public BasePage(Page page){
        this.page = page;
    }
    // NAVIGATION
    public void navigateTo(String url) {
        page.navigate(url);
        page.waitForLoadState();
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    //WAIT METHODS
    protected void waitForElement(String selector) {
        page.waitForSelector(selector,
                new Page.WaitForSelectorOptions()
                        .setTimeout(Config.EXPLICIT_WAIT)
        );
    }

    // CLICK & FILL
    public void click(String selector) {
        waitForElement(selector);
        page.click(selector);
    }
    public void fill(String selector, String text) {
        waitForElement(selector);
        page.fill(selector, text);
    }

    // GET TEXT
    public String getText(String selector) {
        waitForElement(selector);
        return page.textContent(selector);
    }

    // VISIBILITY & PRESENCE
    public boolean isVisible(String selector) {
        try {
            return page.isVisible(selector);
        } catch (PlaywrightException e) {
            return false;
        }
    }

    // CLEAR FIELD
    public void clearField(String selector) {
        waitForElement(selector);
        page.fill(selector, "");
    }

    // GET INPUT VALUE
    public String getInputValue(String selector) {
        waitForElement(selector);
        return page.inputValue(selector);
    }

    // IS ENABLED
    public boolean isEnabled(String selector) {
        try {
            return page.isEnabled(selector);
        } catch (PlaywrightException e) {
            return false;
        }
    }

}
