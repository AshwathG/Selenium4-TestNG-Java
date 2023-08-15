package com.demo.testcases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.pages.HomePage;
import com.demo.util.DatabaseConnection;

public class LoginTestcases extends BaseClass {
	
	HomePage homePage;
	
	@BeforeClass
	public void ClassSetUp() throws Exception
	{
		// Initiate Page Objects
		homePage = PageFactory.initElements(driver, HomePage.class);
	}
	
	@Test(priority=1)
	public void TC0001() throws InterruptedException
	{
		test = extent.createTest("TC0001 - Verify the customer is able to search products successfully");
		test.assignCategory("Search");
		test.assignAuthor("Ashwath");
		
		// Get Data from excel file
		String productname = excel.getAny(Home, 3, 0);
			
		Assert.assertTrue(homePage.isSearchFieldDisplayed());
		test.info("Search field is displayed");
		
		homePage.enterKeywordInSearch(productname);
		test.info("Entered product name "+productname);
		
		homePage.clickSearchIcon();
		test.info("Clicked search iconn");
		
		List<String> results =  new ArrayList<String>();
		results.addAll(homePage.getSearchResults());
		
		Boolean result = false;
		for (Iterator iterator = results.iterator(); iterator.hasNext();) 
		{
			String res = (String) iterator.next();
			if(res.contains(productname))
			{
				result = true;
			}
			else
			{
				result = false;
				break;
			}
		}
		
		Assert.assertTrue(result);
		
		test.info("All products name contains keyword "+productname);
		
		test.pass("Customer is able to search products successfully");
	}
	
	@Test(priority=2)
	public void TC0002() throws InterruptedException
	{
		test = extent.createTest("TC0001 - Different ways to fetch data");
		test.assignCategory("Fetch Data");
		test.assignAuthor("Virat");
		
		// Get Data from excel file
		String mobileNumber = excel.getAny(Home, 7, 0);
		System.out.println(mobileNumber);
		
		// Get data from xml file
		String successmsg = XmlStrings.get("SuccessMsg");
		System.out.println(successmsg);
		
		// Get data from database
		String otp = DatabaseConnection.GetOTP(mobileNumber);
		System.out.println(otp);
		
		test.pass("Successfully fetched data from excel, xml, database");
	}
	
}
