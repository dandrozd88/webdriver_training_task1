package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.Calculator;
import page.Cookies;
import page.EmailEstimate;
import page.yopmail.YopMail;

import static helpers.MovingThroughTabs.*;
import static properties.PropertiesReader.*;
import static utils.DriverSingleton.getDriver;

public class ProcessOfRegisteringCloudPlatformTests extends BaseTestNgSetup {


    @Test
    public void automateProcessOfRegisteringCloudPlatformPricingCalculator() {
        Cookies cookies = new Cookies();
        cookies
                .clickAcceptCookiesButton()
                .clickSearchIcon()
                .setTextInSearchInput(getProductName())
                .clickGoogleCloudPricingCalculatorOption()
                .clickComputeEngineSection()
                .setNumberOfInstances(getNumberOfInstances())
                .setInstancesReasonInput(getInstancesReasonInput())
                .clickOperatingSystemSelect()
                .setOperatingSystem()
                .clickProvisioningModelSelect()
                .setProvisioningModel(getProvisioningModel())
                .clickMachineFamilySelect()
                .setMachineFamily(getMachineFamily())
                .clickSeriesSelect()
                .setSeries(getSeries())
                .clickMachineTypeSelect()
                .setMachineType(getMachineType())
                .clickAddGpuCheckbox()
                .clickGpuTypeSelect()
                .setGpuType(getGpuType())
                .clickNumberOfGpuSelect()
                .setNumberOfGpu(getNumberOfCpu())
                .clickLocalSsdSelect()
                .setLocalSsd(getLocalSsd())
                .clickDatacenterLocationSelect()
                .setDatacenterLocation(getDataCenterLocation())
//                .clickCommittedUsageSelect()
//                .setCommittedUsage(getCommittedUsage())
                .clickAddToEstimateButton();

        Calculator calculator = new Calculator();

        String expectedCalculatedPrice = getFinalPrice();
        Assert.assertEquals(calculator.getTotalEstimatedPriceCalculated(), expectedCalculatedPrice);

        calculator.clickEmailEstimate();

        openNewTab();
        switchToNewTab();

        getDriver().get("https://yopmail.com/pl/");

        YopMail yopMail = new YopMail();
        yopMail
                .clickAcceptRecommendedCookies()
                .clickGenerateRandomEmail();

        String generatedEmail = yopMail.getGeneratedRandomEmail();
        yopMail.clickCheckEmailButton();

        switchBackToPreviousTab();

        EmailEstimate emailEstimate = new EmailEstimate();
        emailEstimate
                .setEmailLabel(generatedEmail)
                .clickSendEmailButton();

        switchBackToPreviousTab();

        yopMail
                .clickRefreshButton();

        Assert.assertEquals(yopMail.getTotalEstimatedMonthlyCostLabel(), expectedCalculatedPrice);
    }
}