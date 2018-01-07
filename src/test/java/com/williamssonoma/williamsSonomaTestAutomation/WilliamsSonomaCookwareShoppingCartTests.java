package com.williamssonoma.williamsSonomaTestAutomation;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCookwarePotsAnsPansPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaMainPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaTeaKettlesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class  WilliamsSonomaCookwareShoppingCartTests extends BaseTestCase {
	

	@Test ()
    public void verifyProductSavedForLater() throws InterruptedException {
		WilliamsSonomaMainPage williamsSonomaMainPage= new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.launchPage();
		Thread.sleep(1000);

		if(williamsSonomaMainPage.popupOverlayWidget.isDisplayed()) {
			williamsSonomaMainPage.buttonStickyOverlayMinimize.click();
		}

		williamsSonomaMainPage.waitForPageToLoad();
		williamsSonomaMainPage.clickProductLinkFromMenu("Cookware");

		WilliamsSonomaCookwarePotsAnsPansPage williamsSonomaCookwarePage= new WilliamsSonomaCookwarePotsAnsPansPage(driver);
		williamsSonomaCookwarePage.waitForPageToLoad();

		/*if(williamsSonomaCookwarePage.popupOverlayWidget.isDisplayed()) {
			williamsSonomaCookwarePage.buttonStickyOverlayMinimize.click();
		}*/

		williamsSonomaCookwarePage.clickMenuShopByCategory("Tea Kettles");
		WilliamsSonomaTeaKettlesPage williamsSonomaTeaKettlesPage=new WilliamsSonomaTeaKettlesPage(driver);
		williamsSonomaTeaKettlesPage.waitForPageToLoad();

		verifyTrue(williamsSonomaTeaKettlesPage.headerTeaKettles.isDisplayed(), "Tea Kettle page header is displayed");


	}



} 
	
	
	