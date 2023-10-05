import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinPage {

    private static final String TEXT_FIELD_XPATH = "//*[@id='postform-text']";
    private static final String PROGRAMING_LANGUAGE_LIST_XPATH = "//*[@id=\"w0\"]/div[5]/div[1]/div[3]/div/span/span[1]";
    private static final String EXPIRATION_TIME_XPATH = "//*[@id=\"w0\"]/div[5]/div[1]/div[4]/div/span/span[1]/span/span[2]";
    private static final String PASTE_NAME_FIELD_XPATH = "//input[@id='postform-name']";
    private static final String SUBMIT_BUTTON_XPATH = "//button[@type='submit']";
    private WebDriver driver;

    @FindBy(xpath = TEXT_FIELD_XPATH)
    private WebElement textField;

    @FindBy(xpath = EXPIRATION_TIME_XPATH)
    private WebElement expirationTime;

    @FindBy(xpath = PASTE_NAME_FIELD_XPATH)
    private WebElement pasteNameField;

    @FindBy(xpath = SUBMIT_BUTTON_XPATH)
    private WebElement submitButton;
    @FindBy(xpath = PROGRAMING_LANGUAGE_LIST_XPATH)
    private WebElement programingLanguageList;


    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewPaste(String code, String syntaxHighlighting, String pasteExpiration, String pasteName) {
        writeCode(code);
        setExpirationTime(pasteExpiration);
        setSyntaxHighlighting(syntaxHighlighting);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(EXPIRATION_TIME_XPATH)))
                .click();
        setPasteName(pasteName);
        submitButton.click();
    }

    private void setPasteName(String pasteName) {
        pasteNameField.sendKeys(pasteName);
    }

    private void setSyntaxHighlighting(String syntaxHighlighting) {
        programingLanguageList.click();
        driver.findElement(By.xpath("//li[text() ='" + syntaxHighlighting + "']")).click();
    }

    private void setExpirationTime(String pasteExpiration) {
        expirationTime.click();
        driver.findElement(By.xpath("//li[text() ='" + pasteExpiration + "']")).click();
    }

    private void writeCode(String code) {
        textField.sendKeys(code);
    }

    public String getTitle() {
        return driver.findElement(By.xpath("//div[@class='info-top']/h1")).getText();
    }

    public String getSyntaxHighlighting() {
        return driver.findElement(By.xpath("//a[@class='btn -small h_800']")).getText();
    }

    public String getDisplayedCode() {
        return driver.findElement(By.xpath("//div[@class='source bash']")).getText();
    }
}