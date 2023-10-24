package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GoogleCloudPage extends AbstractPage{
    private static final String COOKIES_ACCEPT_XPATH = "//button[@class='pvUife']";
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

    @Override
    public GoogleCloudPage openPage() {
        driver.get("https://cloud.google.com");
        popupAccept(COOKIES_ACCEPT_XPATH);
        return this;
    }
    public void popupAccept(String xpath) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
                    .click();
        } catch (Exception e) {
            System.out.println("Element was not clickable within 10 seconds. Continuing...");
        }
    }
}