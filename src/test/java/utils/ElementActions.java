package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class ElementActions extends TestBase{
	
	public WebElement findElementBy(String locator) {
		WebElement element = null;
		if(locator.endsWith("_XPATH")) {
			element = driver.findElement(By.xpath(locators.getProperty(locator)));
		} else if(locator.endsWith("_CSS")) {
			element = driver.findElement(By.cssSelector(locators.getProperty(locator)));
		} else if(locator.endsWith("_ID")) {
			element = driver.findElement(By.id(locators.getProperty(locator)));
		} else if(locator.endsWith("_NAME")) {
			element = driver.findElement(By.name(locators.getProperty(locator)));
		} else if(locator.endsWith("_TAGNAME")) {
			element = driver.findElement(By.tagName(locators.getProperty(locator)));
		} else if(locator.endsWith("_CLASS")) {
			element = driver.findElement(By.className(locators.getProperty(locator)));
		}
		return element;
	}
	
	public void click(String locator) {
		findElementBy(locator).click();
		extentTest.log(LogStatus.INFO, "Clicked on " + locator);
	}
	
	public void type(String locator, String value, boolean isType) {
		findElementBy(locator).sendKeys(value);
		if(isType) {
			extentTest.log(LogStatus.INFO, "Typed in " + locator + " with the value: " + value);
		}else {
			extentTest.log(LogStatus.INFO, "Uploaded file with " + locator + " with the path: " + value);
		}
		
	}
	
	public void switchToIframe(String locator) {
		driver.switchTo().frame(findElementBy(locator));
		extentTest.log(LogStatus.INFO, "Switched to iFrame with locator: " + locator);
	}
	
	public void switchBackFromIFrame() {
		driver.switchTo().defaultContent();
		extentTest.log(LogStatus.INFO, "Switched back from IFrame");
	}
	
	public void scrollDown(String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", findElementBy(locator));
		extentTest.log(LogStatus.INFO, "Scrolled down to " + locator);
	}
	
	public void clearInput(String locator) {
		findElementBy(locator).clear();
		extentTest.log(LogStatus.INFO, "Cleared " + locator);
	}
	
	public void navigateTo(String url) {
		driver.get(url);
		extentTest.log(LogStatus.INFO, "Navigated to: " + url);
	}
	
}
