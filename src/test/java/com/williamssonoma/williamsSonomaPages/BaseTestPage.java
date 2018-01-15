package com.williamssonoma.williamsSonomaPages;

import Logger.Log;
import com.google.common.base.Function;
import com.williamssonoma.automationCore.util.verificationServices.Verifications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BaseTestPage extends Verifications{
	public static WebDriverWait wait;
	public static WebDriver driver;

	public BaseTestPage(WebDriver driver) 	{ 
	this.driver = driver; 
	wait = new WebDriverWait(driver, 10); 
	} 

	public static WebElement find(String locator)
	{ 
	return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))); 
	} 

	public static void clickElement(String locator) {
		find(locator).click();
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void open(String url) {
		driver.get(url);
	}

	public static void typeText(String locator, String keyword)
	{ 
	find(locator).sendKeys(keyword); 
	} 

	public static String getValue(String locator) {
		return find(locator).getText();
	}

	public static Boolean isDisplayed(String locator)
	{
		return find(locator).isDisplayed();
	}

	public WebElement waitForElementToBeVisible(final By elementIdentifier) {
		WebElement element=null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		try{
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(elementIdentifier);
				}
			});
		}catch(NoSuchElementException | StaleElementReferenceException | TimeoutException e){
		    Log.info("Error: Element is not visible.");
		    }

		return element;
	}

	public Boolean isElementPresent(By targetElement) throws InterruptedException{
		Boolean isPresent = driver.findElements(targetElement).size() > 0;
		return isPresent;
	}

	public boolean isClickable(WebElement el) {
		if (el == null) {
			return false;
		}
		try {
			if (!el.isDisplayed()) { //If not visible, element isn't clickable
				return false;
			}
			if (el.getSize().getHeight() <= 0 || el.getSize().getWidth() <= 0) { // If width or height is 0, element is not clickable
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}



	public boolean isAlertPresent(){
		try	{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e){
			throw new NoAlertPresentException();
		}
	}

	public static void clickWebElement(WebElement weElement, WebDriver wdDriver) {

     // Scroll the browser to the element's Y position
     ((JavascriptExecutor) wdDriver).executeScript("window.scrollTo(0,"+ weElement.getLocation().y + ")");
     // Click the element
      int iAttempts = 0;
      while (iAttempts < 5) {
         try {
            weElement.click();
             break;
             } catch (Exception e) {
             }
             iAttempts++;
      }

     }

	public void clickOnElementUsingJs(By element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void clickOnElementUsingAction(WebElement element) {
		Actions builder= new Actions(driver);

		Action clickElement = builder.moveToElement(element).sendKeys(Keys.ENTER).build();
		clickElement.perform();
	}

	public void scrollIntoViewElementUsingJs(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollIntoView(WebElement el) {
		int scrollHeight = getWindowInnerHeight();
		int y = Math.max(0, el.getLocation().getY() - scrollHeight / 2); //Subtract half the window height so its in the middle of the viewable area.
		executeJavascript(format("window.scrollTo(%d, %d)", 0, y));

	}

	private int getWindowInnerHeight() {
		Object innerHeight = executeJavascript(
				"return window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;");
		if (!(innerHeight instanceof Long)) {
			Log.info("Error getting the inner height, a null value was returned from Javascript. Using outer window height.");
			return driver.manage().window().getSize().getHeight();
		}
		return ((Long) innerHeight).intValue();
	}

	public Object executeJavascript(String script) {
		Log.info("Executing javascript: '{}'"+ script);
		try {
			return ((JavascriptExecutor) driver).executeScript(script);
		} catch (Exception e) {
			throw new RuntimeException(format("Exception executing Javascript '%s':", script), e);
		}

	}




	public void waitForPageToLoad() {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			}
		});
	}

	public void waitForPageToLoad(String PageName) {
		String pageLoadStatus;
		JavascriptExecutor js;
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String)js.executeScript("return document.readyState");
			Log.info(".");
		} while ( !pageLoadStatus.equals("complete") );
		Log.info(PageName + " page loaded successfully");

	}


}

