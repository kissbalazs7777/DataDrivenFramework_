package utils;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportClass {
	
	public static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		if(extentReport == null) {
			extentReport = new ExtentReports(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\extentReport.html", true, DisplayOrder.OLDEST_FIRST);
			extentReport.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentreportconfig\\ReportsConfig.xml"));
		}
		return extentReport;
	}

}
