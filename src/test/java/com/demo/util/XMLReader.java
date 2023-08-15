package com.demo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	static InputStream stringsis;
	
	public static HashMap<String, String> parseStringXML(InputStream file) throws Exception
	{
		HashMap<String, String> stringMap = new HashMap<String, String>();
		
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// Build Document
		Document document = builder.parse(file);
		
		// Normalize the XML Structure
		document.getDocumentElement().normalize();
		
		// here comes the root node
		Element root = document.getDocumentElement();
		
		// Get all elements
		NodeList nList = document.getElementsByTagName("string");
		
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node node = nList.item(temp);
			if(node.getNodeType() == Node.ELEMENT_NODE)
			{
				Element eElement = (Element) node;
				
				// Store each element key-value in map
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}
	
	public  static HashMap<String, String> loadFile() throws IOException
	{
		try {  
			String xmlFilePath = "./src/test/resources/xml/strings.xml";
			
			FileInputStream fileInputStream = new FileInputStream(xmlFilePath);
			strings = parseStringXML(fileInputStream);
			
		}
	catch(Exception e) {
			e.printStackTrace();
		}
	finally {	
			if (stringsis != null)
				{
					stringsis.close();
				}
			}
		return strings;
	}

}
