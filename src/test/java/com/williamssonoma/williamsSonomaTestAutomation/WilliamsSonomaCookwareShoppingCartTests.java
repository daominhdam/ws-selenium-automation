package com.williamssonoma.williamsSonomaTestAutomation;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import Logger.Log;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaBrevilleOneTouchTeaMakerPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCookwarePotsAnsPansPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaMainPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaTeaKettlesPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Logger.Log;

public class  WilliamsSonomaCookwareShoppingCartTests extends BaseTestCase {
	

	@Test ()
    public void verifyProductSavedForLater() throws InterruptedException {
		WilliamsSonomaMainPage williamsSonomaMainPage= new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.launchPage();
		Thread.sleep(1000);

        if(driver.getPageSource().contains(""))
		if (williamsSonomaMainPage.popupOverlayWidget.isDisplayed()) {
			Log.info("Closing the 'JoinEmailList' popup overlay widget");
			williamsSonomaMainPage.buttonStickyOverlayMinimize.click();
			}
		williamsSonomaMainPage.waitForPageToLoad();

		Reporter.log("Navigating to Cookware Page");
		williamsSonomaMainPage.clickProductLinkFromMenu("Cookware");

		WilliamsSonomaCookwarePotsAnsPansPage williamsSonomaCookwarePage= new WilliamsSonomaCookwarePotsAnsPansPage(driver);
		williamsSonomaCookwarePage.waitForPageToLoad();

		/*if(williamsSonomaCookwarePage.popupOverlayWidget.isDisplayed()) {
			williamsSonomaCookwarePage.buttonStickyOverlayMinimize.click();
		}*/

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", williamsSonomaCookwarePage.menuShopByCategory);

		williamsSonomaCookwarePage.clickMenuShopByCategory("Tea Kettles");
		WilliamsSonomaTeaKettlesPage williamsSonomaTeaKettlesPage=new WilliamsSonomaTeaKettlesPage(driver);
		williamsSonomaTeaKettlesPage.waitForPageToLoad();
		williamsSonomaTeaKettlesPage.waitForElementToBeVisible(williamsSonomaTeaKettlesPage.headerTeaKettlesLocator);
		verifyTrue(williamsSonomaTeaKettlesPage.headerTeaKettles.isDisplayed(), "Tea Kettle page header is displayed");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0));

		williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0).click();

		WilliamsSonomaBrevilleOneTouchTeaMakerPage brevilleOneTouchTeaMakerPage =new WilliamsSonomaBrevilleOneTouchTeaMakerPage(driver);
		brevilleOneTouchTeaMakerPage.waitForPageToLoad();
		brevilleOneTouchTeaMakerPage.waitForElementToBeVisible(brevilleOneTouchTeaMakerPage.buttonShoppingCartLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brevilleOneTouchTeaMakerPage.buttonShoppingCart);
		brevilleOneTouchTeaMakerPage.buttonShoppingCart.click();

	}



} 
	
	
	