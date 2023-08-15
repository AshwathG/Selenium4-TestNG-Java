package com.demo.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.demo.util.BrowserFactory;
import com.demo.util.ConfigReader;
import com.demo.util.ExcelReader;
import com.demo.util.ReportConfig;
import com.demo.util.XMLReader;


public class BaseClass extends SheetNames{
	
	public static WebDriver driver;
	public static ExcelReader excel;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static JSONObject getJsonData;
	public static String environment;
	protected static HashMap<String, String> XmlStrings = new HashMap<String, String>();
		
	@Parameters({"ExcelPath", "Environment"})
	@BeforeSuite
	public void setUp(String ExcelPath, String Environment) throws IOException
	{
		Reporter.log("Setting Environment...", true);
		environment = Environment;
	
		// Build Report
		
		ReportConfig.BuildReport(ConfigReader.getBrowserType(), environment);
		extent = ReportConfig.extent;
		test = ReportConfig.test;
		
		// Loading Excel Data File
		
		excel = new ExcelReader(ExcelPath);
		
		// Loading XML File
		
		XmlStrings = XMLReader.loadFile();
	}
		
	@BeforeClass
	public void launchBrowser() throws IOException 
	{
		// Launching Browser and Loading Application URL
		Reporter.log("Trying to start browser and Getting application ready", true);
		driver = BrowserFactory.startApplication(ConfigReader.getBrowserType(), ConfigReader.getApplicationUrl());
		Reporter.log("Browser and Application is up and running", true);
	}
		
	@AfterClass
	public void tearDown()
	{
		// Closing Browser after suite
		Reporter.log("Closing browser...", true);
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterSuite
	public void endSuite()
	{
		// flushing report
		ReportConfig.FlushReport();
		// Delete Created Folder
		BrowserFactory.deleteFolderFile();
	}
		
}
