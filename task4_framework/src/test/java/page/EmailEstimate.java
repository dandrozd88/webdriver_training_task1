package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.MovingThroughTabs.changeNestedFrame;
import static utils.DriverSingleton.getDriver;

public class EmailEstimate {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailLabel;

    @FindBy(xpath = "//button[@ng-click='emailQuote.emailQuote(true); emailQuote.$mdDialog.hide()']")
    private WebElement sendEmailButton;

    public EmailEstimate() {
        PageFactory.initElements(getDriver(), this);
    }

    public EmailEstimate setEmailLabel(String email) {
        changeNestedFrame(0, 0);
        emailLabel.sendKeys(email);
        return this;
    }

    public EmailEstimate clickSendEmailButton() {
        sendEmailButton.click();
        return this;
    }
}