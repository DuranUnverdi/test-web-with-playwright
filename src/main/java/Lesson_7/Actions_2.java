package Lesson_7;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.MouseButton;

public class Actions_2 {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://demoqa.com/buttons");
        Thread.sleep(2000);

        // Click Button
        Locator clickBtn = page.locator("(//button[@type='button'])[4]");
        clickBtn.click();

        // Double Click Button
        Locator doubleClickBtn = page.locator("//button[@id='doubleClickBtn']");
        doubleClickBtn.dblclick();

//        //Hoover Over Element
//        Locator btn = page.locator("#rightClickBtn");
//        btn.hover();
//        btn.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));

        //Drag and Drop
        page.navigate("https://demoqa.com/dragabble");
        page.getByText("Drag me").first().dragTo(page.getByText("Drop here").first());

    }
}
