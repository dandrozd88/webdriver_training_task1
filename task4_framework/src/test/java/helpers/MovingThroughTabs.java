package helpers;

import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

import static utils.DriverSingleton.getDriver;

public class MovingThroughTabs {

    public static void openNewTab() {
        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
    }

    public static void switchToNewTab() {
        Set<String> handles = getDriver().getWindowHandles();
        for (String handle : handles) {
            getDriver().switchTo().window(handle);
        }
    }

    public static void switchBackToPreviousTab() {
        Set<String> handles = getDriver().getWindowHandles();
        handles.remove(getDriver().getWindowHandle());
        String previousTabHandle = handles.iterator().next();
        getDriver().switchTo().window(previousTabHandle);
    }

    public static void changeNestedFrame(int frame1, int frame2) {
        getDriver().switchTo().frame(frame1);
        getDriver().switchTo().frame(frame2);
    }

    public static void changeFrame(String frameName) {
        getDriver().switchTo().frame(frameName);
    }
}