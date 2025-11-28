package Lessons.Lesson_11;

import Lessons.Utilities.util.PlaywrightUtils;
import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class TraceViewer {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);

        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page.navigate("https://getir.com/");

        Locator loginBtn = page.getByText("Telefon numarası ile devam et").first();
        loginBtn.click();
        //Stop tracing
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace-getir.zip")));
        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}
