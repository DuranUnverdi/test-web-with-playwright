package Lesson_10;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Keyboard {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://demoqa.com/login");

        Locator usernameInput = page.getByPlaceholder("UserName");
        usernameInput.click();
        //Insert text using keyboard
        page.keyboard().insertText("Duran");
        //press
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");

        //Type method
        page.keyboard().type("DURAN");
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        //down and up
        page.keyboard().down("Shift");
        page.keyboard().press("d");
        page.keyboard().up("Shift");
        page.keyboard().type("uran");
        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}
