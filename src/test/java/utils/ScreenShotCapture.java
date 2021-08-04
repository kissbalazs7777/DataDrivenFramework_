package utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class ScreenShotCapture extends TestBase {
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenShot() throws IOException {
		Date date = new Date();
		File screenshoFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotName = "failure_" + date.toString().replaceAll(" ", "_").replaceAll(":", "_") + ".jpg";
		FileUtils.copyFile(screenshoFile, new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));
		
	}

}
