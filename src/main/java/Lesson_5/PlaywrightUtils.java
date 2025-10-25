package Lesson_5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class PlaywrightUtils {
    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static Browser launchBrowser(Playwright playwright, boolean headless) {
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }

    public static Page createFullScreenPage(Browser browser) {
        Dimension screen = getScreenSize();
        Page page = browser.newPage();
        page.setViewportSize((int) screen.getWidth(), (int) screen.getHeight());
        return page;
    }
    public static void closeAll(Page page, Browser browser, Playwright playwright) {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        System.out.println("✅ Playwright resources closed successfully.");
    }
}
