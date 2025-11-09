package Lesson_8;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;

public class MultibleWindow {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://demoqa.com/browser-windows");
        Page popupPage = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 2), () -> {
            page.getByText("New Window").first().click();
        });
        List<Page> allPages = popupPage.context().pages();
        allPages.forEach(p -> {
            p.waitForLoadState();
            System.out.println("All open pages URLs: " + p.title());
        });
        System.out.println("ilk sayfanın urli " + allPages.get(0).url());
        System.out.println("ikinci sayfanın urli " + allPages.get(1).url());

        Page firstPage = null;
        Page secondPage = null;
        if (allPages.get(0).url().equals("https://demoqa.com/browser-windows")) {
            firstPage = allPages.get(0);
        } else {
            secondPage = allPages.get(0);
        }
        Thread.sleep(2000);
        firstPage.bringToFront();
        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}
