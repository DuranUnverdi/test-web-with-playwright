package Lesson_9;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class ScreenShot {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://www.ebay.com/");

        //get ss with date time format
        String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String screenshotPath = "C:\\Users\\Emrah\\Desktop\\Java Dersleri\\PlaywrightFirstProject\\src\\main\\java\\Utilities\\ScreenShot\\"
                + dateTime + ".png";
        //get screen shot of full page
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));

        //Elemetin ss ini alma
        Locator search = page.getByPlaceholder("Search for anything");
        search.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

        PlaywrightUtils.closeAll(page, browser, playwright);
    }

}
