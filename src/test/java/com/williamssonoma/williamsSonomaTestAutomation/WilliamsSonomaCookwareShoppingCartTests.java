package com.williamssonoma.williamsSonomaTestAutomation;

import com.aventstack.extentreports.Status;
import com.williamssonoma.williamsSonomaPages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static com.williamssonoma.automationCore.util.verificationServices.Verifications.*;

public class  WilliamsSonomaCookwareShoppingCartTests extends BaseTestCase {
	

	@Test ()
    public void verifyProductSavedForLater() throws InterruptedException {
		test.log(Status.INFO,"Launching Williams Sonoma Main Page");
	    Reporter.log("Launching Williams Sonoma Main Page");
		WilliamsSonomaMainPage williamsSonomaMainPage= new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.launchPage();

		test.log(Status.INFO,"Clicking on Cookware link from the Menu");
		Reporter.log("Clicking on Cookware link from the Menu");
		williamsSonomaMainPage.scrollIntoView(williamsSonomaMainPage.linkCookware);
		williamsSonomaMainPage.clickProductLinkFromMenu("Cookware");

		test.log(Status.INFO,"Navigating to Cookware Page");
		Reporter.log("Navigating to Cookware Page");
		WilliamsSonomaCookwarePotsAnsPansPage williamsSonomaCookwarePage= new WilliamsSonomaCookwarePotsAnsPansPage(driver);
		williamsSonomaCookwarePage.waitForPageToLoad();

		test.log(Status.INFO,"Clicking on the menu Tea kettle");
		Reporter.log("Clicking on the menu Tea kettle");
		williamsSonomaCookwarePage.scrollIntoViewElementUsingJs(williamsSonomaCookwarePage.menuShopByCategory);
		williamsSonomaCookwarePage.clickMenuShopByCategory("Tea Kettles");

		test.log(Status.INFO,"Navigating to Tea Kettles Page");
		Reporter.log("Navigating to Tea Kettles Page");
		WilliamsSonomaTeaKettlesPage williamsSonomaTeaKettlesPage=new WilliamsSonomaTeaKettlesPage(driver);
		williamsSonomaTeaKettlesPage.waitForPageToLoad();
		williamsSonomaTeaKettlesPage.waitForElementToBeVisible(williamsSonomaTeaKettlesPage.headerTeaKettlesLocator);
		verifyTrue(williamsSonomaTeaKettlesPage.headerTeaKettles.isDisplayed(), "Tea Kettle page header is displayed");

		test.log(Status.INFO,"Clicking on a Tea Kettle Product from the Tea Kettles page");
		Reporter.log("Clicking on a Tea Kettle Product from the Tea Kettles page");
		williamsSonomaTeaKettlesPage.scrollIntoViewElementUsingJs(williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0));
		williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0).click();

		test.log(Status.INFO,"Navigating to the Tea Kettle product Page");
		Reporter.log("Navigating to the Tea Kettle product Page");
		WilliamsSonomaBrevilleOneTouchTeaMakerPage brevilleOneTouchTeaMakerPage =new WilliamsSonomaBrevilleOneTouchTeaMakerPage(driver);
		brevilleOneTouchTeaMakerPage.waitForPageToLoad();

		test.log(Status.INFO,"Clicking on 'Add to Cart' button");
		Reporter.log("Clicking on 'Add to Cart' button");
		brevilleOneTouchTeaMakerPage.waitForElementToBeVisible(brevilleOneTouchTeaMakerPage.buttonShoppingCartLocator);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(900,600)", "");
	    brevilleOneTouchTeaMakerPage.buttonShoppingCart.click();
        Thread.sleep(30000);

		test.log(Status.INFO,"Clicking on Checkout button on the confirmation overlay widget");
		Reporter.log("Clicking on Checkout button on the confirmation overlay widget");
		brevilleOneTouchTeaMakerPage.widgetCheckoutConfirmationOverlay.buttonCheckout.click();
		WilliamsSonomaShoppingCartPage shoppingCartPage= new WilliamsSonomaShoppingCartPage(driver);
		shoppingCartPage.waitForPageToLoad();
		verifyTrue(shoppingCartPage.getProductsListFromShoppingCartTable().contains("Breville One-Touch Tea Maker"),"Expected productName is displayed in the shopping cart");



	}


} 
	
	
	