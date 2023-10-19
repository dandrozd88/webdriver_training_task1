import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class GoogleCloudTest {

    private static final String COOKIES_ACCEPT_XPATH = "//button[@class='pvUife']";

    String searchText = "Google Cloud Platform Pricing Calculator";
    String operatingSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";

    String provisioningModel = "Regular";
    String machineFamily = "General purpose";
    String series = "N1";
    String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB";
    String gpuType = "NVIDIA Tesla V100";
    String localSsd = "2x375 Gb";
    String datacenterLocation = "Frankfurt (europe-west3)";
    String committedUsage = "1 Year";
    String currency = "USD";
    String expectedAmount = "1081.20";

    int numberOfInstances = 4;
    int numberOfGpus = 1;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testScript() {
        driver.get("https://cloud.google.com");
        popupAccept(COOKIES_ACCEPT_XPATH);
        GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPage.searchInputText(searchText);
        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage(driver);
        pricingCalculatorPage.calculateCloudPrice(numberOfInstances, operatingSystem, provisioningModel, machineFamily, series, machineType, gpuType, numberOfGpus, localSsd, datacenterLocation, committedUsage, currency, expectedAmount);

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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}