package Lesson_5;

import com.microsoft.playwright.*;

public class IframeLocator {
    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);

        page.navigate("https://the-internet.herokuapp.com/iframe");
        Locator title = page.locator("//h3");
        System.out.println("Title: " + title.innerText());

        //Frame locator
        FrameLocator iframe = page.frameLocator("#mce_0_ifr");
        Locator body = iframe.getByText("Your content goes here.");
        body.click();
        body.clear();

        Locator inputText=iframe.getByLabel("Rich Text Area. Press ALT-0 for help.");
        inputText.fill("Hello, I am learning Playwright with Java!");

        Thread.sleep(2000);

        page.close();
        playwright.close();
    }
}
