package com.demo.util;

import java.io.File;
import java.util.UUID;

import org.testng.Assert;

public class FileHelper {

	static File folder;
	
	public static void createFolder()
	{
		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();
	}
	
	public static void deleteFolderFile()
	{
		if(folder.listFiles() != null)
		{
			for(File file: folder.listFiles())
			{
				file.delete();
			}
		}
			folder.delete();
	}
	
	public void fileIsDownloaded() throws InterruptedException
	{
		Thread.sleep(5000);
		File listOfFiles[] = folder.listFiles();
		Assert.assertTrue(listOfFiles.length>0);
		
		for(File file: listOfFiles)
		{
			Assert.assertTrue(file.length()>0);
		}
	}
}
