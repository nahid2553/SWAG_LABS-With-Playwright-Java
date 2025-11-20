package Configurations;

import Utilities.Config;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;
    protected Playwright playwright;

    //Browser Initialization
    @BeforeEach
    public void setUp(){
        this.playwright = Playwright.create();
        browser = switch(Config.BROWSER.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode()));
            case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode()));
            default -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Config.isHeadlessMode()));
        };
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(Config.VIEWPORT_WIDTH, Config.VIEWPORT_HEIGHT));
        page = context.newPage();
    }

    //Resources cleanup
    @AfterEach
    public void tearDown(){
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    //Common Test Method


}
