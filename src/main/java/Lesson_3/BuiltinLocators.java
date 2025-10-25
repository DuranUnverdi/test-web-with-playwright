package Lesson_3;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.*;

public class BuiltinLocators {
    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://getir.com/");
        System.out.println("Sayfa Başlığı: " + page.title());

        //getByText
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1.Text: " + loginText.innerText());

        //locate in shadow DOM
        Locator shadowHost = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("2.Shadow DOM Text: " + shadowHost.innerText());

        //getByRole
        Locator roleLocator = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("3.Role TextBox Placeholder: " + roleLocator.innerText());
        roleLocator.click();
        roleLocator.fill("5551234567");
        Thread.sleep(2000);

        //getByPlaceholder
        Locator placeholderLocator = page.getByPlaceholder("Telefon Numarası");
        System.out.println("4.Placeholder TextBox Placeholder: " + placeholderLocator.innerText());

        //getbyLabel
        Locator labelLocator = page.getByLabel("login button");
        System.out.println("5.Label TextBox Placeholder: " + labelLocator.innerText());

        /*click login button
        getByRole*/
        Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        //test id
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        System.out.println("6.TestId TextBox Placeholder: " + loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("5551234567");

        //locator cancel button
        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();

        //getByAltText
        Locator altTextLocator = page.getByAltText("Su & İçecek");
        System.out.println("7.Alt Text: " + altTextLocator.getAttribute("alt"));


        //getByText and filter options
        Locator filteredLocator = page.getByTestId("text").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        System.out.println("8.Filtered Text: " + filteredLocator.innerText());

        //css selector with locator
        Locator cssLocator = page.locator("div").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        Locator cssInnerLocator = page.locator("section.sc-af32efac-3.bivGVR [data-testid='link']").filter( new Locator.FilterOptions().setHasText("Su & İçecek"));
        cssInnerLocator.click();
    }
}
