package Lessons.Lesson_8;

import Lessons.Utilities.util.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MultibleWindowsTab {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://the-internet.herokuapp.com/windows");
        Thread.sleep(2000);

        //Get page after a specific action that opens a new tab or window
        Page newPage = page.context().waitForPage(() -> {
            page.getByText("Click  Here").click();
        });
        newPage.waitForLoadState();
        System.out.println("New page URL: " + newPage.url());
        System.out.println("New page Title: " + newPage.title());
        // return to the original page
        PlaywrightUtils.createFullScreenPage(browser);
        page.bringToFront();

        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}

