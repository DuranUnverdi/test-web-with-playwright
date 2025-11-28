package utilities;

import com.microsoft.playwright.*;
import org.testng.ITestResult;

public class BrowserFactory {
    private final Playwright playwright;

    public BrowserFactory() {
        this.playwright = Playwright.create();
    }

    public Browser getBrowser(String browserType, boolean isHeadless) {
        Browser browser;
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(isHeadless);

        switch (browserType.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(options);
                break;
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        return browser;
    }
    public BrowserContext createContext(Browser browser, ITestResult result) {
        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
                .setName(result.getMethod().getMethodName()));
        return context;
    }
}
