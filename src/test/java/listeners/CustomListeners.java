package listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utils.ScreenShotCapture;

public class CustomListeners extends TestBase implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult arg0) {
		extentTest = extentReports.startTest(arg0.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult arg0) {
		try {
			ScreenShotCapture.captureScreenShot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.log(LogStatus.FAIL, arg0.getName() + " Failure - " + arg0.getThrowable());
		extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(ScreenShotCapture.screenshotName));
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Screenshot captured!");
		Reporter.log("<a target='_blank' href='" + ScreenShotCapture.screenshotName + "'> --> Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + ScreenShotCapture.screenshotName + "'><img src='" + ScreenShotCapture.screenshotName + "' height=200 style='border: 2px solid black;'></a>");
		
		extentReports.endTest(extentTest);
		extentReports.flush();
	}
	
	@Override
	public void onTestSuccess(ITestResult arg0) {
		extentTest.log(LogStatus.PASS, arg0.getName() + " PASS");
		extentReports.endTest(extentTest);
		extentReports.flush();
	}

}
