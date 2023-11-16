package page.yopmail;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.EmailEstimate;

import static helpers.Extractions.parseCounterNumber;
import static helpers.Extractions.parseCurrency;
import static helpers.MovingThroughTabs.changeFrame;
import static utils.DriverSingleton.getDriver;

public class YopMail {

    @FindBy(xpath = "//*[@id='accept']")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//*[@id='listeliens']/a[1]/div[2]")
    private WebElement generateRandomEmailButton;

    @FindBy(xpath = "//*[@id='geny']/span[1]")
    private WebElement generatedRandomEmailLabel;

    @FindBy(xpath = "//*[@id='geny']/span[2]")
    private WebElement generatedRandomEmailDomainNameLabel;

    @FindBy(xpath = "/html/body/div/div[2]/main/div/div[2]/div/div[1]/div[2]/button[2]")
    private WebElement checkEmailButton;

    @FindBy(xpath = "//*[@id='refresh']")
    private WebElement refreshButton;

    @FindBy(xpath = "//*[@id='nbmail']")
    private WebElement emailCounter;

    @FindBy(xpath = "//*[@id='mail']/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/h3")
    private WebElement totalEstimatedMonthlyCostLabel;

    @FindBy(xpath = "//*[@id='dismiss-button']")
    private WebElement dismissButton;

    @FindBy(xpath = "//*[@id='dismiss-button']/div/span")
    private WebElement closeButton;

    public YopMail() {
        PageFactory.initElements(getDriver(), this);
    }

    public YopMail clickAcceptRecommendedCookies() {
        acceptCookiesButton.click();
        return this;
    }

    public YopMail clickGenerateRandomEmail() {
        generateRandomEmailButton.click();
        return this;
    }

    public String getGeneratedRandomEmail() {
        String randomEmail = generatedRandomEmailLabel.getText();
        String randomEmailDomain = generatedRandomEmailDomainNameLabel.getText();
        return randomEmail + "@" + randomEmailDomain;
    }

    public EmailEstimate clickCheckEmailButton() {
        checkEmailButton.click();
        return new EmailEstimate();
    }

    public YopMail verifyIsAddPresent() {

        try {
//            changeFrame("aswift_0");
//            new WebDriverWait(getDriver(), Duration.ofSeconds(5))
//                    .until(ExpectedConditions.elementToBeClickable(dismissButton));
//            dismissButton.click();

            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");

            dismissButton.click();
        } catch (Exception e) {
        }
        return this;
    }

    public YopMail clickRefreshButton() {
        verifyIsAddPresent();
        do {
            refreshButton.click();
        } while (parseCounterNumber(emailCounter.getText()).equals("0"));
        changeFrame("ifmail");
        return this;
    }

    public String getTotalEstimatedMonthlyCostLabel() {
        return parseCurrency(totalEstimatedMonthlyCostLabel.getText());
    }
}