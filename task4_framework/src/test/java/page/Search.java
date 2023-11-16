package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.DriverSingleton.getDriver;

public class Search {

    @FindBy(xpath = "//a[@class='gs-title']//b[text()='Google Cloud Pricing Calculator']")
    private WebElement googleCloudPricingCalculatorOption;

    public Search() {
        PageFactory.initElements(getDriver(), this);
    }

    public Calculator clickGoogleCloudPricingCalculatorOption() {
        googleCloudPricingCalculatorOption.click();
        return new Calculator();
    }
}