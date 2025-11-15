package Lesson_11;

import Lesson_5.PlaywrightUtils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Download {
    public static void main(String[] args) throws InterruptedException, IOException {
        Playwright playwright = Playwright.create();
        Browser browser = PlaywrightUtils.launchBrowser(playwright, false);
        Page page = PlaywrightUtils.createFullScreenPage(browser);
        page.navigate("https://demoqa.com/upload-download");
        //wait for the download to start
        com.microsoft.playwright.Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();
        });
        System.out.println("Page: " + download.page().title());
        String filePath = System.getProperty("user.home") + "\\downloads\\" + download.suggestedFilename() + "_myDownload";
        download.saveAs(Paths.get(filePath, download.suggestedFilename()));
        System.out.println("Downloaded file path: " + download.path());
        boolean isFileDownloaded = Files.exists(Paths.get(filePath, download.suggestedFilename()));
        assert isFileDownloaded;
        System.out.println("File downloaded successfully: " + isFileDownloaded);
        Files.deleteIfExists(Paths.get(filePath,download.suggestedFilename()));
        boolean isFileDeleted = !Files.exists(Paths.get(filePath, download.suggestedFilename()));
        assert isFileDeleted;
        PlaywrightUtils.closeAll(page, browser, playwright);
    }
}
