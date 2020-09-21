package FrameworkDevelopment.EndToEnd;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	ExtentReports extent ;
	
	public ExtentReports getReportObject() {
		
		
		String reportfilepath = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportfilepath);
		reporter.config().setDocumentTitle("EndToEnd Framework result");
		reporter.config().setReportName("End to End Webautomation Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Chitra");
		return extent;
		
	}

}
