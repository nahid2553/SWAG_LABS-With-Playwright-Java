package Configurations;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static Utilities.Config.*;

public class BaseTest {
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;

    //Browser Initialization
    @BeforeEach
    public void setUp(){
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadlessMode()));

        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(VIEWPORT_WIDTH,VIEWPORT_HEIGHT));
        page = context.newPage();
    }
    //Resources cleanup
    @AfterEach
    public void tearDown(){
        if (page != null){
            page.close();
        }
        if (context != null){
            context.close();
        }
        if (browser != null){
            browser.close();
        }

    }
    //Common Test Method


}
