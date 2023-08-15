package com.demo.util;

import java.io.File;

import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportConfig {
	
	public static ExtentTest test;
	public static ExtentReports extent;
	
	public static void BuildReport(String Browser, String Environment)
	{
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/AutomationReport_"+ ScreenshotCapture.getCurrentDateTime()+ ".html"));
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		extent.setSystemInfo("Browser", Browser);
		if(ConfigReader.getBrowserType().equalsIgnoreCase("Chrome"))
		{
			extent.setSystemInfo("Browser Version", ConfigReader.getBrowserVersion());
		}
		extent.setSystemInfo("Environment", Environment);
	}
	
	public static void FlushReport()
	{
		Reporter.log("Flushing extent report and stopping suite...", true);
		extent.flush();
	}

}
