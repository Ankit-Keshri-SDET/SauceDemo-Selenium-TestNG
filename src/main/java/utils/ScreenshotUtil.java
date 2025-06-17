package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) {
        String dateFolder = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
        String timestamp = new SimpleDateFormat("HHmmss").format(new Date());

        // Build folder path: projectDir/screenshots/dd_MM_yyyy
        String folderPath = "screenshots/" + dateFolder;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String screenshotPath = folderPath + "/" + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
