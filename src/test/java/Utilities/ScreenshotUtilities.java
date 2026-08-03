package Utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtilities {

    public static void captureScreenshot(AppiumDriver driver, String testName) {
        try {
            File file = driver.getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/" + testName + ".png");//create a destination were the screenshots must be stored
            dest.getParentFile().mkdirs(); //create a directory

            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
