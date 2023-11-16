package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Extractions.extractCost;
import static helpers.MovingThroughTabs.changeNestedFrame;
import static utils.DriverSingleton.getDriver;

public class Calculator {

    @FindBy(xpath = "//*[@title='Compute Engine']")
    private WebElement computeEngineSection;

    @FindBy(xpath = "//*[@name='quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(xpath = "//*[@id='input_102']")
    private WebElement instancesReasonInput;

    @FindBy(xpath = "//*[@id='select_value_label_93']")
    private WebElement operatingSystemSelect;

    @FindBy(xpath = "//md-option[@id='select_option_103']")
    private WebElement pickFreeOperatingSystemOption;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_94']")
    private WebElement provisioningModelSelect;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_95']")
    private WebElement machineFamilySelect;

    @FindBy(xpath = "//*[@id='select_value_label_96']")
    private WebElement seriesSelect;

    @FindBy(xpath = "//*[@id='select_value_label_97']")
    private WebElement machineTypeSelect;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gpuTypeSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement numberOfGpuSelect;

    @FindBy(xpath = "//*[@id='select_value_label_467']")
    private WebElement localSsdSelect;

    @FindBy(xpath = "//md-select-value[@id='select_value_label_99']")
    private WebElement datacenterLocationSelect;

    @FindBy(xpath = "//*[@id='select_value_label_100']")
    private WebElement committedUsageSelect;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//h2[@ng-if='appCtrl.CartData.hasItems()']")
    private WebElement estimatePrice;

    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/div[1]/h2/b")
    private WebElement totalEstimatedCostText;

    @FindBy(xpath = "//*[@id='Email Estimate']")
    private WebElement emailIcon;

    @FindBy
    private WebElement provisioningModelOption;

    @FindBy
    private WebElement machineFamilyOption;

    @FindBy
    private WebElement seriesOption;

    @FindBy
    private WebElement machineTypeOption;

    @FindBy
    private WebElement gpuTypeOption;

    @FindBy
    private WebElement numberOfGpuOption;

    @FindBy
    private WebElement localSsdOption;

    @FindBy
    private WebElement datacenterLocationOption;

    @FindBy
    private WebElement committedUsageOption;

    public Calculator() {
        PageFactory.initElements(getDriver(), this);
    }

    public Calculator clickComputeEngineSection() {
        changeNestedFrame(0, 0);
        computeEngineSection.click();
        return this;
    }

    public Calculator setNumberOfInstances(String numberOfInstances) {
        numberOfInstancesInput.sendKeys(numberOfInstances);
        return this;
    }

    public Calculator setInstancesReasonInput(String instancesReason) {
        instancesReasonInput.sendKeys(instancesReason);
        return this;
    }

    public Calculator clickOperatingSystemSelect() {
        operatingSystemSelect.click();
        return this;
    }

    public Calculator setOperatingSystem() {
        pickFreeOperatingSystemOption.click();
        return this;
    }

    public Calculator clickProvisioningModelSelect() {
        provisioningModelSelect.click();
        return this;
    }

    public Calculator setProvisioningModel(String provisioningModel) {
        provisioningModelOption = getDriver().findElement(By.xpath("//div[@class='md-text' and contains(text(), '" + provisioningModel + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", provisioningModelOption);
        return this;
    }

    public Calculator clickMachineFamilySelect() {
        machineFamilySelect.click();
        return this;
    }

    public Calculator setMachineFamily(String machineFamily) {
        machineFamilyOption = getDriver().findElement(By.xpath("//div[@class='md-text' and contains(text(), '" + machineFamily + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", machineFamilyOption);
        return this;
    }

    public Calculator clickSeriesSelect() {
        seriesSelect.click();
        return this;
    }

    public Calculator setSeries(String series) {
        seriesOption = getDriver().findElement(By.xpath("//div[@class='md-text ng-binding' and contains(text(), '" + series + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", seriesOption);
        return this;
    }

    public Calculator clickMachineTypeSelect() {
        machineTypeSelect.click();
        return this;
    }

    public Calculator setMachineType(String machineType) {
        machineTypeOption = getDriver().findElement(By.xpath("//div[@class='md-text ng-binding' and contains(text(), '" + machineType + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", machineTypeOption);
        machineTypeOption.click();
        return this;
    }

    public Calculator clickAddGpuCheckbox() {
        addGpuCheckbox.click();
        return this;
    }

    public Calculator clickGpuTypeSelect() {
        gpuTypeSelect.click();
        return this;
    }

    public Calculator setGpuType(String gpuType) {
        gpuTypeOption = getDriver().findElement(By.xpath("//md-option[contains(@ng-repeat, 'item in listingCtrl.gpuList') and @value='" + gpuType + "']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", gpuTypeOption);
        return this;
    }

    public Calculator clickNumberOfGpuSelect() {
        numberOfGpuSelect.click();
        return this;
    }

    public Calculator setNumberOfGpu(String numberOfGpu) {
        numberOfGpuOption = getDriver().findElement(By.xpath("//div[@class='md-text ng-binding' and normalize-space(text())='" + numberOfGpu + "']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", numberOfGpuOption);
        numberOfGpuOption.click();
        return this;
    }

    public Calculator clickLocalSsdSelect() {
        localSsdSelect.click();
        return this;
    }

    public Calculator setLocalSsd(String localSsd) {
        localSsdOption = getDriver().findElement(By.xpath("//div[@class='md-text ng-binding' and normalize-space(text())='" + localSsd + "']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", localSsdOption);
        return this;
    }

    public Calculator clickDatacenterLocationSelect() {
        datacenterLocationSelect.click();
        return this;
    }

    public Calculator setDatacenterLocation(String datacenterLocation) {
        datacenterLocationOption = getDriver().findElement(By.xpath("//md-option[contains(@ng-repeat, 'item in listingCtrl.fullRegionList | filter:listingCtrl.inputRegionText.computeServer') and @value='" + datacenterLocation + "']"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", datacenterLocationOption);
        datacenterLocationOption.click();
        return this;
    }

    public Calculator clickCommittedUsageSelect() {
        committedUsageSelect.click();
        return this;
    }

    public Calculator setCommittedUsage(String committedUsage) {
        committedUsageOption = getDriver().findElement(By.xpath("//div[@class='md-text' and contains(text(), '" + committedUsage + "')]"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", committedUsageOption);
        committedUsageOption.click();
        return this;
    }

    public Calculator clickAddToEstimateButton() {
        addToEstimateButton.click();
        return this;
    }

    public String getTotalEstimatedPriceCalculated() {
        String estimatedCostText = totalEstimatedCostText.getText();
        return extractCost(estimatedCostText);
    }

    public Calculator clickEmailEstimate() {
        emailIcon.click();
        return this;
    }
}