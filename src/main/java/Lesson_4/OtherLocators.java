package Lesson_4;

import com.microsoft.playwright.*;

import java.awt.*;

public class OtherLocators {
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://getir.com/");

        //css has-text
        Locator login= page.locator("h5:has-text('Giriş yap veya kayıt ol')");
        System.out.println("CSS Locator Text: " + login.innerText());

        //css text
        Locator login2= page.locator("h5:text('Giriş yap veya kayıt ol')");
        System.out.println("CSS Text Locator Text: " + login2.innerText());

        //css: elements matching one of the conditions
        Locator continiuButton= page.locator("button:has-text('Telefon numarası ile devam et'), button:has-text('login button')");
        System.out.println("CSS OR Locator Text: " + continiuButton.innerText());

        //css: picking  nth match from the query result
        Locator nthLogin= page.locator(":nth-match(:text('Giriş yap'),1)");
        System.out.println("CSS Nth Match Locator Text: " + nthLogin.innerText());
        nthLogin.click();

        //id,data-test id, data-test selectors
        Locator phoneNumber= page.locator("data-testid=modal").locator("data-testid=input");
        System.out.println("Data Test Id Locator Placeholder: " + phoneNumber.innerText());

        //xpath
        Locator xpathLogin= page.locator("(//button[@type='submit'])[2]");
        System.out.println("XPath Locator Text: " + xpathLogin.innerText());

        page.close();
        playwright.close();


    }
}
