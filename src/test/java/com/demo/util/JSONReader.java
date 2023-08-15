package com.demo.util;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.demo.testcases.BaseClass;

public class JSONReader extends BaseClass{
	
	InputStream datais;
	
	public void LoadJSONFile(String dataFileName) throws Exception {
		try {
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			getJsonData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(datais != null)
			{
				datais.close();
			}
		}
		
	  }
	

}
