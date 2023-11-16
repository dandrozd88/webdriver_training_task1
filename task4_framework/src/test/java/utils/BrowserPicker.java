package utils;

import exceptions.InvalidBrowserTypeException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserPicker {

    public static WebDriver chooseBrowserType(String browserType) {
        if (browserType.equals("CHROME")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browserType.equals("EDGE")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else {
            throw new InvalidBrowserTypeException("Invalid type of browser: " + browserType);
        }
    }
}