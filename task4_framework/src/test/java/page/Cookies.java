package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.DriverSingleton.getDriver;

public class Cookies {

    @FindBy(xpath = "//button[@class='pvUife']")
    private WebElement acceptCookiesButton;

    public Cookies() {
        PageFactory.initElements(getDriver(), this);
    }

    public Homepage clickAcceptCookiesButton() {
        acceptCookiesButton.click();
        return new Homepage();
    }
}