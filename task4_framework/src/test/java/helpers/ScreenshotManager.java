package helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static helpers.Extractions.getCurrentDateTime;
import static utils.DriverSingleton.getDriver;

public class ScreenshotManager {

    public static void makeScreenshot(String testName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();

        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String screenshotPath = "src/main/resources/screenshots/" + testName + "-" + getCurrentDateTime() + ".png";

        try {
            org.apache.commons.io.FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Zrzut ekranu został zapisany: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania zrzutu ekranu: " + e.getMessage());
        }
    }
}
