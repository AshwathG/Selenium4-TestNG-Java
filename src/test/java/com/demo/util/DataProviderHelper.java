package com.demo.util;

import org.testng.annotations.DataProvider;

import com.demo.testcases.BaseClass;

public class DataProviderHelper {
	
	@DataProvider(name = "data-provider")
	public static Object[][] getData(String sheetName, int startRow, int totalColumn)
	{
		ExcelReader excelObject = BaseClass.excel;
		int lastRow = excelObject.getLastRow(sheetName);
		int TotalCases = lastRow - startRow + 1;
		int TotalColumns = totalColumn + 1;
	
		Object[][] Data = new Object[TotalCases][TotalColumns];
	
		for (int i = 0, k = startRow; i < TotalCases; i++, k++) 
		{
			for (int j = 0, l = 0; j < TotalColumns; j++, l++) 
			{
				Data[i][j] = excelObject.getAny(sheetName,k,l);
			}
		}
		return Data;
	}
}
