package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static helpers.ScreenshotManager.makeScreenshot;
import static properties.PropertiesReader.getPageUrl;
import static utils.DriverSingleton.*;

public class BaseTestNgSetup {

    @BeforeMethod
    public void beforeMethod() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(getPageUrl());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            makeScreenshot(result.getName());
        }
        terminateDriver();

    }
}