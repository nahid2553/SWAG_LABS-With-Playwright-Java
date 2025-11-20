package Configurations;


import Utilities.Config;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class BasePage {
    protected Page page;
    public BasePage(Page page){
        this.page = page;
    }















//
//    // ===== NAVIGATION =====
//    protected void navigateTo(String url) {
//        page.navigate(url);
//        page.waitForLoadState();
//    }
//
//    protected void goBack() {
//        page.goBack();
//    }
//
//    protected void refresh() {
//        page.reload();
//    }
//
//    // ===== CLICK & FILL =====
//    protected void click(String selector) {
//        waitForElement(selector);
//        page.click(selector);
//    }
//
//    protected void fill(String selector, String text) {
//        waitForElement(selector);
//        page.fill(selector, text);
//    }
//
//    protected void type(String selector, String text) {
//        page.locator(selector).type(text);
//    }
//
//    protected void clearField(String selector) {
//        page.fill(selector, "");
//    }
//
//    // ===== GET TEXT =====
//    protected String getText(String selector) {
//        waitForElement(selector);
//        return page.textContent(selector);
//    }
//
//    protected String getInputValue(String selector) {
//        return page.inputValue(selector);
//    }
//
//    // ===== VISIBILITY & PRESENCE =====
//    protected boolean isVisible(String selector) {
//        try {
//            return page.isVisible(selector);
//        } catch (PlaywrightException e) {
//            return false;
//        }
//    }
//
//    protected boolean isPresent(String selector) {
//        try {
//            return page.locator(selector).count() > 0;
//        } catch (PlaywrightException e) {
//            return false;
//        }
//    }
//
//    protected boolean isEnabled(String selector) {
//        try {
//            return page.isEnabled(selector);
//        } catch (PlaywrightException e) {
//            return false;
//        }
//    }
//
//    protected boolean isChecked(String selector) {
//        try {
//            return page.isChecked(selector);
//        } catch (PlaywrightException e) {
//            return false;
//        }
//    }
//
//    // ===== WAIT METHODS =====
//    protected void waitForElement(String selector) {
//        page.waitForSelector(selector,
//                new Page.WaitForSelectorOptions()
//                        .setTimeout(Config.EXPLICIT_WAIT)
//        );
//    }
//
//    protected void waitForPageLoad() {
//        page.waitForLoadState();
//    }
//
//    protected void waitForTime(int milliseconds) {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    // ===== DROPDOWN =====
//    protected void selectDropdown(String selector, String value) {
//        waitForElement(selector);
//        page.selectOption(selector, value);
//    }
//
//    // ===== CHECKBOX & RADIO =====
//    protected void check(String selector) {
//        page.check(selector);
//    }
//
//    protected void uncheck(String selector) {
//        page.uncheck(selector);
//    }
//
//    // ===== SCROLL =====
//    protected void scrollToElement(String selector) {
//        page.locator(selector).scrollIntoViewIfNeeded();
//    }
//
//    protected void scrollToBottom() {
//        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
//    }
//
//    protected void scrollToTop() {
//        page.evaluate("window.scrollTo(0, 0)");
//    }
//
//    // ===== MOUSE ACTIONS =====
//    protected void hover(String selector) {
//        page.hover(selector);
//    }
//
//    protected void doubleClick(String selector) {
//        page.dblclick(selector);
//    }
//
////    protected void rightClick(String selector) {
////        page.click(selector, new Page.ClickOptions().setButton("right"));
////    }
//
//    // ===== PAGE INFO =====
//    protected String getCurrentUrl() {
//        return page.url();
//    }
//
//    protected String getPageTitle() {
//        return page.title();
//    }

}
