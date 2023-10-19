import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PricingCalculatorPage {


    private static final String COMPUTE_ENGINE_ICON_XPATH = "//*[@title='Compute Engine']";
    private static final String INSTANCES_INPUT_XPATH = "//*[@name='quantity']";
    private static final String OPERATING_SYSTEM_SELECT_XPATH = "//*[@id='select_112']";
    private static final String PROVISIONING_MODEL_SELECT_XPATH = "//*[@id='select_116']";
    private static final String MACHINE_FAMILY_SELECT_XPATH = "//*[@id='select_122']";

    private static final String SERIES_SELECT_XPATH = "//*[@id='select_124']";
    private static final String MACHINE_TYPE_SELECT_XPATH = "//*[@id='select_126']";
    private static final String ADD_GPUS_XPATH = "//*[@aria-label='Add GPUs']";
    private static final String GPU_TYPE_SELECT_XPATH = "//*[@id='select_506']";
    private static final String GPUS_NUMBER_SELECT_XPATH = "//*[@id='select_508']";
    private static final String LOCAL_SSD_SELECT_XPATH = "//*[@id='select_465']";
    private static final String DATACENTER_LOCATION_SELECT_XPATH = "//*[@id='select_132']";
    private static final String COMMITTED_USAGE_SELECT_XPATH = "//*[@id='select_139']";
    private static final String ADD_TO_ESTIMATE_XPATH = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']";
    private static final String ESTIMATED_COST_XPATH = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/div[1]/h2/b";
    private static final String EMAIL_ESTIMATE_XPATH = "//button[@id='Email Estimate']";
    private static final String OPEN_RANDOM_EMAIL_XPATH = "//button[@onclick='egengo();']";

    private static final String GENERATE_RANDOM_EMAIL_XPATH = "//*[@id='listeliens']/a[1]";

    private static final String COPY_RANDOM_EMAIL_XPATH = "//*[@id='cprnd']";
    private static final String ENTER_RANDOM_EMAIL_XPATH = "//*[@id='input_616']";
    private static final String SEND_EMAIL_XPATH = "//*[@id='dialogContent_622']/form/md-dialog-actions/button[2]";
    private static final String MAIL_ESTIMATED_COST_XPATH = "//*[@id='mail']//td[2]//h3";
    private static final String REFRESH_BUTTON_XPATH = "//*[@id='refresh']";
    private static final String MAIL_COUNTER_XPATH = "//*[@id='nbmail']";
    private static final String CALCULATION_XPATH = "//*[@id='compute']/md-list";
    private WebDriver driver;

    @FindBy(xpath = COMPUTE_ENGINE_ICON_XPATH)
    private WebElement computeEngine;

    @FindBy(xpath = INSTANCES_INPUT_XPATH)
    private WebElement instanceInput;

    @FindBy(xpath = OPERATING_SYSTEM_SELECT_XPATH)
    private WebElement operatingSystem;

    @FindBy(xpath = PROVISIONING_MODEL_SELECT_XPATH)
    private WebElement provisioningModel;

    @FindBy(xpath = MACHINE_FAMILY_SELECT_XPATH)
    private WebElement machineFamily;
    @FindBy(xpath = SERIES_SELECT_XPATH)
    private WebElement series;

    @FindBy(xpath = MACHINE_TYPE_SELECT_XPATH)
    private WebElement machineType;
    @FindBy(xpath = GPU_TYPE_SELECT_XPATH)
    private WebElement gpuType;

    @FindBy(xpath = ADD_GPUS_XPATH)
    private WebElement addGpus;
    @FindBy(xpath = GPUS_NUMBER_SELECT_XPATH)
    private WebElement numberOfGpus;

    @FindBy(xpath = LOCAL_SSD_SELECT_XPATH)
    private WebElement localSsd;

    @FindBy(xpath = DATACENTER_LOCATION_SELECT_XPATH)
    private WebElement datacenterLocation;

    @FindBy(xpath = COMMITTED_USAGE_SELECT_XPATH)
    private WebElement committedUsage;

    @FindBy(xpath = ADD_TO_ESTIMATE_XPATH)
    private WebElement addToEstimate;

    @FindBy(xpath = ESTIMATED_COST_XPATH)
    private WebElement estimatedCost;

    @FindBy(xpath = EMAIL_ESTIMATE_XPATH)
    private WebElement emailEstimate;

    @FindBy(xpath = GENERATE_RANDOM_EMAIL_XPATH)
    private WebElement generateRandomEmail;
    @FindBy(xpath = COPY_RANDOM_EMAIL_XPATH)
    private WebElement randomEmail;

    @FindBy(xpath = ENTER_RANDOM_EMAIL_XPATH)
    private WebElement enterRandomEmail;

    @FindBy(xpath = SEND_EMAIL_XPATH)
    private WebElement sendEmail;

    @FindBy(xpath = OPEN_RANDOM_EMAIL_XPATH)
    private WebElement randomEmailInbox;

    @FindBy(xpath = MAIL_ESTIMATED_COST_XPATH)
    private WebElement mailEstimatedCost;

    @FindBy(xpath = REFRESH_BUTTON_XPATH)
    private WebElement refreshButton;

    @FindBy(xpath = MAIL_COUNTER_XPATH)
    private WebElement mailCounter;


    public PricingCalculatorPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void calculateCloudPrice(int numberOfInstances, String operatingSystemSelect, String provisioningModelSelect, String machineFamilySelect, String seriesSelect, String machineTypeSelect, String gpuTypeSelect, int numberOfGpusSelect, String localSsdSelect, String datacenterLocationSelect, String committedUsageSelect, String currency, String expectedAmount) {
        pageReadyForCalculation();
        setCalculationCriteria(numberOfInstances, operatingSystemSelect, provisioningModelSelect, machineFamilySelect, seriesSelect, machineTypeSelect, gpuTypeSelect, numberOfGpusSelect, localSsdSelect, datacenterLocationSelect, committedUsageSelect);
        checkEstimatedAmount(currency, expectedAmount);
        emailPricingToNewRandomMail();
        refreshInboxUntilFirstMailIncome(MAIL_ESTIMATED_COST_XPATH, refreshButton);
        checkEmailEstimatedCost(currency, expectedAmount);
    }

    public void emailPricingToNewRandomMail() {
        emailEstimate.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(ENTER_RANDOM_EMAIL_XPATH)));
        ((JavascriptExecutor) driver).executeScript("window.open();");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        generateRandomMail();
        driver.switchTo().window(tabs.get(0));
        changeNestedFrame(0, 0);
        driver.findElement(By.xpath(ENTER_RANDOM_EMAIL_XPATH))
                .sendKeys(Keys.CONTROL, "v");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SEND_EMAIL_XPATH)));
        sendEmail.click();
        driver.switchTo().window(tabs.get(1));
        randomEmailInbox.click();
    }


    private void checkEstimatedAmount(String currency, String expectedAmount) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(CALCULATION_XPATH)));
        checkEstimatedCost(currency, expectedAmount);
    }

    private void setCalculationCriteria(int numberOfInstances, String operatingSystemSelect, String provisioningModelSelect, String machineFamilySelect, String seriesSelect, String machineTypeSelect, String gpuTypeSelect, int numberOfGpusSelect, String localSsdSelect, String datacenterLocationSelect, String committedUsageSelect) {
        computeEngine.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(OPERATING_SYSTEM_SELECT_XPATH)));
        setInstanceInput(numberOfInstances);
        setOperatingSystem(operatingSystemSelect);
        setProvisioningModel(provisioningModelSelect);
        setMachineFamily(machineFamilySelect);
        setSeries(seriesSelect);
        setMachineType(machineTypeSelect);
        setAddGpu();
        setGpuType(gpuTypeSelect);
        setNumberOfGpus(numberOfGpusSelect);
        setLocalSsd(localSsdSelect);
        setDatacenterLocation(datacenterLocationSelect);
        setCommittedUsage(committedUsageSelect);
        addToEstimate.submit();
    }

    private void setCommittedUsage(String committedUsageSelect) {
        committedUsage.sendKeys(committedUsageSelect);
        committedUsage.click();
    }

    private void setDatacenterLocation(String datacenterLocationSelect) {
        datacenterLocation.sendKeys(datacenterLocationSelect);
        datacenterLocation.click();
    }

    private void setLocalSsd(String localSsdSelect) {
        localSsd.sendKeys(localSsdSelect);
        localSsd.click();
    }

    private void setNumberOfGpus(int numberOfGpusSelect) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GPUS_NUMBER_SELECT_XPATH)))
                .click();
        numberOfGpus.sendKeys(String.valueOf(numberOfGpusSelect));
        numberOfGpus.click();
    }

    private void setGpuType(String gpuTypeSelect) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GPU_TYPE_SELECT_XPATH)))
                .click();
        gpuType.sendKeys(gpuTypeSelect);
        gpuType.click();
    }

    private void setAddGpu() {
        Actions actions = new Actions(driver);
        actions.click().perform();
        addGpus.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(GPU_TYPE_SELECT_XPATH)))
                .click();
    }

    private void setInstanceInput(int numberOfInstances) {
        instanceInput.sendKeys(String.valueOf(numberOfInstances));
    }

    private void setMachineType(String machineTypeSelect) {
        machineType.sendKeys(machineTypeSelect);
        machineType.click();

    }

    private void setSeries(String seriesSelect) {
        series.sendKeys(seriesSelect);
        series.click();
    }

    private void setMachineFamily(String machineFamilySelect) {
        machineFamily.sendKeys(machineFamilySelect);
        machineFamily.click();
    }

    private void setProvisioningModel(String provisioningModelSelect) {
        provisioningModel.sendKeys(provisioningModelSelect);
        provisioningModel.click();
    }

    private void setOperatingSystem(String operatingSystemSelect) {
        operatingSystem.sendKeys(operatingSystemSelect);
        operatingSystem.click();
    }

    private void pageReadyForCalculation() {
        driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
        changeNestedFrame(0, 0);
    }

    private void changeNestedFrame(int frame1, int frame2) {
        driver.switchTo().frame(frame1);
        driver.switchTo().frame(frame2);
    }

    public void checkEstimatedCost(String currency, String expectedAmount) {
        String expectedText = "Total Estimated Cost: " + currency + " " + expectedAmount + " per 1 month";
        String actualText = estimatedCost.getText().replace(",", "");
        System.out.println(expectedText);
        System.out.println(actualText);
        assertTrue("Estimated cost did not match", actualText.contains(expectedText));
    }

    public void checkEmailEstimatedCost(String currency, String expectedAmount) {
        driver.switchTo().frame("ifmail");
        String expectedText = currency + " " + expectedAmount.toString();
        String actualText = mailEstimatedCost.getText().replace(",", "");
        assertTrue("Estimated cost did not match", actualText.contains(expectedText));
    }

    public void generateRandomMail() {

        driver.get("https://yopmail.com/");
        driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='accept']")))
                .click();
        generateRandomEmail.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomEmail);
        randomEmail.click();
    }

    public void refreshInboxUntilFirstMailIncome(String targetElementLocatorXpath, WebElement refreshButton) {

        String mailCounterText = mailCounter.getText();
        while ("0 mail".equals(mailCounterText)) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(targetElementLocatorXpath)));
                break;
            } catch (Exception e) {
                refreshButton.click();
                mailCounterText = mailCounter.getText();
            }
        }
    }
}