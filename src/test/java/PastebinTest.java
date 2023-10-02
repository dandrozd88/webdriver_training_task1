import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class PastebinTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='qc-cmp2-ui']/div[2]/div/button[2]")))
                .click();
        PastebinPage pastebinPage = new PastebinPage(driver);
        pastebinPage.createNewPaste("Hello from WebDriver", "10 Minutes", "helloweb");
        driver.quit();
    }
}
