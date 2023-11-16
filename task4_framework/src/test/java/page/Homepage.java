package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.DriverSingleton.getDriver;

public class Homepage {

    @FindBy(xpath = "//div[@class='YSM5S']")
    private WebElement searchIcon;

    @FindBy(xpath = "//input[@class='mb2a7b']")
    private WebElement searchInput;

    public Homepage() {
        PageFactory.initElements(getDriver(), this);
    }

    public Homepage clickSearchIcon() {
        searchIcon.click();
        return this;
    }

    public Search setTextInSearchInput(String text) {
        searchInput.sendKeys(text + Keys.ENTER);
        return new Search();
    }
}