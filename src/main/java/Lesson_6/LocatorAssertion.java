package Lesson_6;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorAssertion {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);

        page.navigate("https://www.ebay.com/");
       System.out.println("title: " + page.title());

        //hasURL assertion
        assertThat(page).hasURL("https://www.ebay.com/");

        //hasTitle assertion
        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");

        //not
        assertThat(page).not().hasTitle("Wrong Title");

        //Locator Assertion
        //containtesText
        Locator signIn= page.getByText("Sign in");
        assertThat(signIn).containsText("Sign");

        //has attribute
        Locator searchBox= page.getByPlaceholder("Search for anything");
        assertThat(searchBox).hasAttribute("type","text");

        //hasText
        Locator register= page.getByText("register").first();
        assertThat(register).hasText("register");

        //isEditable
        assertThat(searchBox).isEditable();
        System.out.println(searchBox.isEditable());

        //isEmpty
        assertThat(searchBox).isEmpty();

        //isVisible
        assertThat(searchBox).isEditable();
       PlaywrightUtils.closeAll(page, browser, playwright);

    }
}
