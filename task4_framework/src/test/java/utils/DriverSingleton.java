package utils;

import org.openqa.selenium.WebDriver;

import static properties.PropertiesReader.getTypeOfBrowser;
import static utils.BrowserPicker.chooseBrowserType;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = chooseBrowserType(getTypeOfBrowser());
        }
        return driver;
    }

    public static void terminateDriver() {
        driver.close();
        driver.quit();
    }
}
