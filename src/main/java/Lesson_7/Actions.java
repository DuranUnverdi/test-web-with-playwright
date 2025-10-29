package Lesson_7;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Actions {
    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://www.ebay.com/");

        //TextInputKeysAndShortCuts
        //Text input
        Locator searchBox = page.getByPlaceholder("Search for anything");
        searchBox.fill("laptop");
        //Keys and Shortcuts
        searchBox.press("Enter");

        //Register Button
        Locator registerBtn = page.getByText("register").first();
        registerBtn.click();
        //Business Button
        Locator businessBtn = page.locator("//*[@id=\"businessaccount-radio\"]");
        businessBtn.click();

        Locator checkAccountBtn = page.locator("#bizOnlyToBuy");
        checkAccountBtn.check();
        assertThat(checkAccountBtn).isChecked();
        Thread.sleep(2000);

        page.navigate("https://www.ebay.com/");

        //Dropdown
        Locator selectCategory = page.getByLabel("Select a category for search");
        selectCategory.selectOption("58058");

        //select by value
        selectCategory.selectOption(new SelectOption().setLabel("Computers/Tablets & Networking"));
        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}
