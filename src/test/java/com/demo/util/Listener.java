package com.demo.util;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.demo.testcases.BaseClass;


public class Listener extends BrowserFactory implements ITestListener{
	
	@Override  
	public void onTestStart(ITestResult result) 
	{  
		Reporter.log("Test is about to start", true);
		try {
			driver.switchTo().alert().accept();
		}
		catch(NoAlertPresentException e){}
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {  
	System.out.println("Success of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {  
		System.out.println("Came inside failure listener");
		if(ConfigReader.getScreenshotRequired())
		{
			if (result.getStatus()==ITestResult.FAILURE)
			{
				BaseClass.test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotCapture.CaptureScreenshot(driver)).build());
			}
		}
	System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	@Override 
	public void onTestSkipped(ITestResult result) {  
	System.out.println("Skip of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onStart(ITestContext context) {  
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
	} 

}
