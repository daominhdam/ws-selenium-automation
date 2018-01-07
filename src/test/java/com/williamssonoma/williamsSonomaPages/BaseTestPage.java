package com.williamssonoma.williamsSonomaPages;

import com.google.common.base.Function;
import com.williamssonoma.automationCore.util.VerificationService.Verifications;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

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

	public static WebElement waitForPresenceOfTheElement(final By elementIdentifier) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(elementIdentifier);
			}
		});
	}
	public void waitForPageToLoad() {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			}
		});
	}

	}

