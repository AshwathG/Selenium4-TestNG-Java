package com.demo.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUiActions {
	
	public void click(WebElement element)
	{
		element.click();
	}
	
	public void click(List<WebElement> element, int index)
	{
		element.get(index).click();
	}
	
	public void clickWithJavaExecutor(WebDriver driver, WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void submitForm(WebElement element)
	{
		element.submit();
	}
	
	public void submitForm(List<WebElement> element, int index)
	{
		element.get(index).submit();
	}
	
	public void enterText(WebElement element, String text)
	{
		element.sendKeys(text);
	}
	
	public void enterText(List<WebElement> element, int index, String text)
	{
		element.get(index).sendKeys(text);
	}
	
	public void clearInputField(WebElement element) throws InterruptedException
	{
		element.clear();
		Thread.sleep(1000);
	}
	
	public void clearInputField(List<WebElement> element, int index) throws InterruptedException
	{
		element.get(index).clear();
		Thread.sleep(1000);
	}
	
	public String getText(WebElement element)
	{
		String text = element.getText();
		return text;
	}
	
	public String getText(List<WebElement> element, int index)
	{
		String text = element.get(index).getText();
		return text;
	}
	
	public String getAttributeValue(WebElement element, String attributeName)
	{
		String text = element.getAttribute(attributeName);
		return text;
	}
	
	public String getAttributeValue(List<WebElement> element, int index, String attributeName)
	{
		String text = element.get(index).getAttribute(attributeName);
		return text;
	}
	
	public String getTagName(WebElement element)
	{
		String tagName = element.getTagName();
		return tagName;
	}
	
	public String getTagName(List<WebElement> element, int index)
	{
		String tagName = element.get(index).getTagName();
		return tagName;
	}
	
	public void selectOptionFromDropdownByVisibleText(WebElement element, String visibleText)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
	public void selectOptionFromDropdownByVisibleText(List<WebElement> element, int index, String visibleText)
	{
		Select sel = new Select(element.get(index));
		sel.selectByVisibleText(visibleText);
	}
	
	public void selectOptionFromDropdownByIndex(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	public void selectOptionFromDropdownByIndex(List<WebElement> element, int eleIndex, int optionIndex)
	{
		Select sel = new Select(element.get(eleIndex));
		sel.selectByIndex(optionIndex);
	}
	
	public void selectOptionFromDropdownByValue(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public void selectOptionFromDropdownByValue(List<WebElement> element, int index, String value)
	{
		Select sel = new Select(element.get(index));
		sel.selectByValue(value);
	}
	
	public void deselectOptionFromDropdownByVisibleText(WebElement element, String visibleText)
	{
		Select sel = new Select(element);
		sel.deselectByVisibleText(visibleText);
	}
	
	public void deselectOptionFromDropdownByIndex(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.deselectByIndex(index);
	}
	
	public void deselectOptionFromDropdownByValue(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.deselectByValue(value);
	}
	
	public String getSelectedOptionInDropdown(WebElement element)
	{
		Select sel = new Select(element);
		WebElement ele = sel.getFirstSelectedOption();
		String selectedOption = ele.getText().trim();
		return selectedOption;
	}
	
	public ArrayList<String> getAllSelectedOptionsInDropdown(WebElement element)
	{
		Select sel = new Select(element);
		List<WebElement> ele = sel.getAllSelectedOptions();
		
		ArrayList<String> selectedOptions = new ArrayList<String>();

		for(int i=0; i<ele.size(); i++)
		{
			selectedOptions.add(ele.get(i).getText().trim());
		}
		return selectedOptions;
	}
	
	public ArrayList<String> getAllOptionsFromDropdown(WebElement element)
	{
		Select sel = new Select(element);
		List<WebElement> ele = sel.getOptions();
		
		ArrayList<String> allOptions = new ArrayList<String>();

		for(int i=0; i<ele.size(); i++)
		{
			allOptions.add(ele.get(i).getText().trim());
		}
		return allOptions;
	}
	
	public boolean isElementDisplayed(WebElement element)
	{
		boolean status = element.isDisplayed();
		return status;
	}
	
	public boolean isElementDisplayed(List<WebElement> element, int index)
	{
		boolean status = element.get(index).isDisplayed();
		return status;
	}
	
	public boolean elementIsEnabled(WebElement element)
	{
		boolean status = element.isEnabled();
		return status;
	}
	
	public boolean elementIsEnabled(List<WebElement> element, int index)
	{
		boolean status = element.get(index).isEnabled();
		return status;
	}
	
	public boolean elementIsSelected(WebElement element)
	{
		boolean status = element.isSelected();
		return status;
	}
	
	public boolean elementIsSelected(List<WebElement> element, int index)
	{
		boolean status = element.get(index).isSelected();
		return status;
	}
	
	public void selectOptionFromListbox(WebElement listElement, List<WebElement> options, String optionToSelect) throws InterruptedException
	{
		listElement.click();
		Thread.sleep(2000);
		for(int i=0; i< options.size(); i++)
		{
			String Name = options.get(i).getText();
			if(Name.equalsIgnoreCase(optionToSelect))
			{
				options.get(i).click();
				break;
			}
		}
	}
	
	public void explicitWait(WebDriver driver, WebElement element, int seconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void implicitWait(WebDriver driver, int seconds)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	public void fluentWait(WebDriver driver, WebElement element, int timeout, int pollingTime)
	{
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(timeout));
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Browser Actions
	
	public void navigateToUrl(WebDriver driver, String url)
	{
		driver.navigate().to(url);
	}
	
	public void loadUrl(WebDriver driver, String url)
	{
		driver.get(url);
	}
	
	public String getCurrentPageUrl(WebDriver driver)
	{
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public String getCurrentPageTitle(WebDriver driver)
	{
		String title = driver.getTitle();
		return title;
	}
	
	public void pageRefresh(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	
	public void goToPreviousPage(WebDriver driver)
	{
		driver.navigate().back();
	}
	
	public void goToForward(WebDriver driver)
	{
		driver.navigate().forward();
	}
	
	public void deleteCookies(WebDriver driver)
	{
		driver.manage().deleteAllCookies();
	}
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	public void setWindowSize(WebDriver driver)
	{
		driver.manage().window().setSize(new Dimension(1024,650));
	}
	
	public void openNewTab(WebDriver driver)
	{
		driver.switchTo().newWindow(WindowType.TAB);
	}
	
	public void openNewWindow(WebDriver driver)
	{
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	public List<String> getWindowHandles(WebDriver driver)
	{
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(windowHandles);
		return windowHandlesList;
	}
	
	public String getCurrentWindowHandle(WebDriver driver)
	{
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	
	public void switchToWindow(WebDriver driver, List<String> windowHandlesList, int index)
	{
		driver.switchTo().window(windowHandlesList.get(index));
	}
	
	public void closeCurrentWindow(WebDriver driver)
	{
		driver.close();
	}
	
	public void closeAllWindows(WebDriver driver)
	{
		driver.quit();
	}
	
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void enterTextInAlert(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	public String getTextFromAlert(WebDriver driver)
	{
		String alertText = driver.switchTo().alert().getText();
		return alertText;
	}

}
