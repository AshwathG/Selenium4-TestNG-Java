package com.demo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.demo.util.WebUiActions;


public class HomePage {
	
	WebUiActions webUiActions = new WebUiActions();
			
	@FindBy(name="search") WebElement SearchField; 
	@FindBy(xpath=".//div[@id='search']//button") WebElement SearchIcon;
	@FindBy(xpath=".//h4//a") List<WebElement> SearchResultsNames;
		
	public void clickSearchField()
	{
		webUiActions.click(SearchField);
	}
	
	public boolean isSearchFieldDisplayed()
	{
		return webUiActions.isElementDisplayed(SearchField);
	}
	
	public void enterKeywordInSearch(String keyword)
	{
		webUiActions.enterText(SearchField, keyword);
	}
	
	public void clickSearchIcon()
	{
		webUiActions.click(SearchIcon);
	}
	
	public List<String> getSearchResults()
	{
		List<String> results =  new ArrayList<String>();
		for(int i=0; i<SearchResultsNames.size(); i++)
		{
			String result = webUiActions.getText(SearchResultsNames, i);
			results.add(result);
		}
		return results;
	}
		
}