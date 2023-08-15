package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	
	XSSFWorkbook wb;
	XSSFSheet sh1;
	
	public ExcelReader(String excelpath)
	{
		 try {
			 File src=new File(excelpath);
			 FileInputStream fis=new FileInputStream(src);
			 wb= new XSSFWorkbook(fis);
		 
	}
		 catch (Exception e) {
			 System.out.println(e.getMessage());
		 }
	}
	

	
	public void CloseFile(String excelpath) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(excelpath);
		 wb.write(fos);
		 fos.close();
		 
		 File src=new File(excelpath);
		 FileInputStream fis1=new FileInputStream(src);
		 wb=new XSSFWorkbook(fis1);
	}
	
	
	// To get string
	
	public String getString(int sheetNumber, int row, int column)
			{
		
			sh1= wb.getSheetAt(sheetNumber);
		
			String data= sh1.getRow(row).getCell(column).getStringCellValue();

			return data;
	
			}  
	
	//To get Integer values
	
	public int getInteger(int sheetNumber, int row, int column)
	{
	 sh1= wb.getSheetAt(sheetNumber);

	double data= sh1.getRow(row).getCell(column).getNumericCellValue();
	
	return (int) data;
	}   
	
	//To get decimal numbers
	
	public double getDouble(int sheetNumber, int row, int column)
	{
	 sh1= wb.getSheetAt(sheetNumber);

	double data= sh1.getRow(row).getCell(column).getNumericCellValue();
	
	return data;
	}
	
	// To get long number
	public long getlong(int sheetNumber, int row, int column)
	{
	 sh1= wb.getSheetAt(sheetNumber);

	long data= (long) sh1.getRow(row).getCell(column).getNumericCellValue();
	
	return(long) data;
	}
	
	// Data String
	public String getAny( int sheetNumber, int row, int column)
	{
		DataFormatter formatter = new DataFormatter();
		sh1= wb.getSheetAt(sheetNumber);		
		String data= formatter.formatCellValue(sh1.getRow(row).getCell(column));
		return data;
	} 
	
	public String getAny( String sheetName, int row, int column)
	{
		DataFormatter formatter = new DataFormatter();
		sh1= wb.getSheet(sheetName);
		String data= formatter.formatCellValue(sh1.getRow(row).getCell(column));
		return data;
		
		/*
		DataFormatter formatter = new DataFormatter();
		sh1= wb.getSheet(sheetName);
		
		String data = null;
		
		Cell cell = sh1.getRow(row).getCell(column);
				
		if(cell.getCellType() == CellType.FORMULA )
		{ 
			switch (cell.getCachedFormulaResultType()) {
	        case BOOLEAN:
	            data = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case NUMERIC:
	            data = String.valueOf((int)cell.getNumericCellValue());
	            break;
	        case STRING:
	            data = String.valueOf(cell.getRichStringCellValue());
	            break;
			default:
				break;
	    }
		}
			else
			{
				sh1= wb.getSheet(sheetName);
				data= formatter.formatCellValue(sh1.getRow(row).getCell(column));
			}
		return data; */
		} 
	
	// to write 
	public void insertData(int sheetNumber, int row, int column, String Value ) throws IOException
	{
		sh1= wb.getSheetAt(sheetNumber);
		 Row Row = sh1.createRow(row);
		 Cell cell = Row.createCell(column);
		 cell.setBlank();
		 cell.setCellValue(Value);
		 CloseFile("ExcelPathHere");
	}
	
	public int getLastRow(String sheetName)
	{
		sh1= wb.getSheet(sheetName);
		
		int lastRow= sh1.getLastRowNum();
		return lastRow;
	}

}
