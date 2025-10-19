package Lesson_2;

import com.microsoft.playwright.*;

import java.awt.*;

public class OpenFullScreen {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.hepsiburada.com/");
        //fullscreen açılması için Dimension sınıfı kulllanıldı
        page.setViewportSize(width, height);
        page.close();
        playwright.close();

    }
}
