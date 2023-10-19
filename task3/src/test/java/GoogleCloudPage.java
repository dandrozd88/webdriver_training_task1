import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleCloudPage {
    private static final String SEARCH_INPUT_XPATH = "//input[@class='mb2a7b']";
    private static final String SEARCH_ICON_XPATH = "//div[@class='YSM5S']";
    private static final String CALCULATION_LINK_XPATH = "//*[contains(text(), \"Google Cloud Pricing Calculator\")]";


    private WebDriver driver;

    @FindBy(xpath = SEARCH_INPUT_XPATH)
    private WebElement searchInput;

    @FindBy(xpath = SEARCH_ICON_XPATH)
    private WebElement searchIcon;

    @FindBy(xpath = CALCULATION_LINK_XPATH)
    private WebElement priceCalculationLink;

    public GoogleCloudPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchInputText(String searchText) {
        searchIcon.click();
        searchInput.sendKeys(searchText);
        searchInput.submit();
        priceCalculationLink.click();

    }
}