package com.demo.util;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory extends FileHelper{
	
	public static WebDriver driver;
	
	public static WebDriver startApplication(String appBrowser, String appUrl)
	{
		System.out.println("inside startapplication");
		createFolder();
		
		if(appBrowser.equals("Chrome"))
		{			
			Map<String, Object> prefs = new HashMap<String, Object>();
			
			// Allow permission to send desktop notification
			prefs.put("profile.default_content_setting_values.notifications", 1);
			
			// Block browser pop ups
			prefs.put("profile.default_content_setting.popups", 0);
			
			// set download path
			prefs.put("download.default_directory", folder.getAbsolutePath());
			
			ChromeOptions options= new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			// Set chrome.exe file path
			options.setBinary(System.getProperty("user.dir")+".cache\\selenium\\chrome\\win64\\"+ConfigReader.getInstance().getBrowserVersion()+"\\chrome.exe");
			
			// Set browser version
			options.setBrowserVersion(ConfigReader.getInstance().getBrowserVersion());
			
			// To accept insecure ssl certificates
			options.setAcceptInsecureCerts(true);
			
			// Maximize window on launch
			options.addArguments("--start-maximized");
			
			if(ConfigReader.getInstance().getHeadlessValue().equalsIgnoreCase("yes"))
			{
				options.addArguments("--headless");
				driver= new ChromeDriver(options);
			}
			else
			{
				driver= new ChromeDriver(options);
			}
			
		}
		else if(appBrowser.equals("Firefox"))
		{	
			FirefoxProfile profile = new FirefoxProfile();
			
			// Allow permission to send desktop notification
		    profile.setPreference("permissions.default.desktop-notification", 1);
		    
		    // Block browser pop ups
		    profile.setPreference("dom.popup_maximum", 0);
		    
		    // set download path
		    profile.setPreference("browser.download.dir", folder.getAbsolutePath());
		    
		    FirefoxOptions  capabilities=new FirefoxOptions ();
		    // Set firefox.exe file path
//		    capabilities.setBinary(System.getProperty("user.dir")+".cache\\selenium\\Mozilla Firefox\\win64\\"+ConfigReader.getInstance().getBrowserVersion()+"\\firefox.exe");
		    capabilities.setProfile(profile);
		    
		    // To accept insecure ssl certificates
		    capabilities.setAcceptInsecureCerts(true);
		    
		    // Maximize window on launch
		    capabilities.addArguments("--start-maximized");
		    
			driver= new FirefoxDriver(capabilities);
		}
		else if(appBrowser.equals("MSEdge"))
		{
			Map<String, Object> prefs = new HashMap<String, Object>();
			
			// Allow permission to send desktop notification
			prefs.put("profile.default_content_setting_values.notifications", 1);
			
			// Block browser pop ups
			prefs.put("profile.default_content_setting.popups", 0);
			
			// set download path
			prefs.put("download.default_directory", folder.getAbsolutePath());
			
			EdgeOptions options= new EdgeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			// Set chrome.exe file path
			options.setBinary(System.getProperty("user.dir")+".cache\\selenium\\edge\\win64\\"+ConfigReader.getInstance().getBrowserVersion()+"\\edge.exe");
			
			// Set browser version
			options.setBrowserVersion(ConfigReader.getInstance().getBrowserVersion());
			
			// To accept insecure ssl certificates
			options.setAcceptInsecureCerts(true);
			
			driver= new EdgeDriver();
			driver.manage().window().maximize();
		}
		else if(appBrowser.equals("Safari"))
		{
			driver= new SafariDriver();
		}
		else
		{
			System.out.println("Please Enter Valid Browser Name");
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();	
		System.out.println("Closed Browser");
		deleteFolderFile();
	}

}
