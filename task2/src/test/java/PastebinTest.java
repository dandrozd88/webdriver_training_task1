import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinTest {
    String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    String syntaxHighlighting = "Bash";
    String pasteExpiration = "10 Minutes";
    String pasteName = "how to gain dominance among developers";

    private WebDriver driver;

    @Test
    public void testCreateNewPaste() {
        driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@mode='primary']")))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"vi-smartbanner\"]/vli")))
                .click();
        PastebinPage pastebinPage = new PastebinPage(driver);
        pastebinPage.createNewPaste(code, syntaxHighlighting, pasteExpiration, pasteName);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='info-top']/h1")));

        Assertions.assertEquals(pasteName, pastebinPage.getTitle(), "Page title does not match the expected title.");


        Assertions.assertEquals(syntaxHighlighting, pastebinPage.getSyntaxHighlighting(), "Syntax highlighting does not match the expected.");


        Assertions.assertEquals(code, pastebinPage.getDisplayedCode(), "Displayed code does not match the expected code.");


        driver.quit();

    }
}

