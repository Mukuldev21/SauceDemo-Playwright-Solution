package tests;


import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import utils.TestListeners;
import org.testng.annotations.*;
import config.Config;
import java.nio.file.Paths;

@Listeners({TestListeners.class, io.qameta.allure.testng.AllureTestNg.class})
//@Listeners(TestListeners.class)
public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();

        /*BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(Config.HEADLESS)
                .setSlowMo(100);*/

        browser = playwright.chromium().launch(); //put launchOptions ->()

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setRecordVideoDir(Paths.get("videos/")));

        // Enable tracing for debugging
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
        page.setDefaultTimeout(Config.DEFAULT_TIMEOUT);
    }

    @AfterClass
    public void tearDown() {
        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot();
        }
        resetBrowserState();
    }

    public void resetBrowserState() {
        page.navigate("about:blank");
        //context.clearCookies();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public void captureScreenshot() {
        page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }


}
