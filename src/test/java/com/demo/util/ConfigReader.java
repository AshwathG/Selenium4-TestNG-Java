package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.demo.testcases.BaseClass;


public class ConfigReader {

	private static Properties pro;

	private static ConfigReader configReader = new ConfigReader();

	public ConfigReader() {
		try {

			File src = new File("Configuration/Config.properties");
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			System.out.println("Exception is ==" + e.getMessage());
		}
	}

	public static ConfigReader getInstance() {

		return configReader;

	}
	
	public static String getBrowserType() {
		String path = pro.getProperty("Browser");
		return path;
	}

	public static String getApplicationUrl() {
		String url = null;
		if(BaseClass.environment.equalsIgnoreCase("QA"))
		{
			url = pro.getProperty("QAURL");
		}
		else if(BaseClass.environment.equalsIgnoreCase("DEV"))
		{
			url = pro.getProperty("DEVURL");
		}
		else
		{
			System.out.println("Provide valid environment name in TestNG suite file - QA or DEV");
		}
		return url;
	}
	
	public static String getMongoDBConnectionString() {
		String url = null;
		if(BaseClass.environment.equalsIgnoreCase("QA"))
		{
			url = pro.getProperty("QAMongoDB");
		}
		else if(BaseClass.environment.equalsIgnoreCase("DEV"))
		{
			url = pro.getProperty("DEVMongoDB");
		}
		else
		{
			System.out.println("Provide valid environment name in TestNG suite file - QA or DEV");
		}
		return url;
	}
	
	public static String getBrowserVersion() {
		String XMLpath = pro.getProperty("BrowserVersion");
		return XMLpath;
	}
	
	public static String getHeadlessValue() {
		String HeadlessValue = pro.getProperty("Headless");
		return HeadlessValue;
	}
	
	public static boolean getScreenshotRequired() {
		Boolean ScreenshotReqd = Boolean.parseBoolean(pro.getProperty("ScreenshotRequired"));
		return ScreenshotReqd;
	}

	public static String get(String key) {
		return pro.getProperty(key);
	}

	public static String getEnvironment() {

		return pro.getProperty("environment");

	}

}