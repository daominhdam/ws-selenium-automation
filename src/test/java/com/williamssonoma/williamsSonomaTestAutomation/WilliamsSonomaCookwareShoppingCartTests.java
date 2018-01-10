package com.williamssonoma.williamsSonomaTestAutomation;

import com.williamssonoma.williamsSonomaPages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static com.williamssonoma.automationCore.util.verificationServices.Verifications.verifyTrue;

public class  WilliamsSonomaCookwareShoppingCartTests extends BaseTestCase {
	

	@Test ()
    public void verifyProductSavedForLater() throws InterruptedException {
		Reporter.log("Launching Williams Sonoma Main Page");
		WilliamsSonomaMainPage williamsSonomaMainPage= new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.launchPage();

		Reporter.log("Clicking on Cookware link from the Menu");
		williamsSonomaMainPage.scrollIntoView(williamsSonomaMainPage.linkCookware);
		williamsSonomaMainPage.clickProductLinkFromMenu("Cookware");

		Reporter.log("Navigating to Cookware Page");
		WilliamsSonomaCookwarePotsAnsPansPage williamsSonomaCookwarePage= new WilliamsSonomaCookwarePotsAnsPansPage(driver);
		williamsSonomaCookwarePage.waitForPageToLoad();

		Reporter.log("Clicking on the menu Tea kettle");
		williamsSonomaCookwarePage.scrollIntoViewElementUsingJs(williamsSonomaCookwarePage.menuShopByCategory);
		williamsSonomaCookwarePage.clickMenuShopByCategory("Tea Kettles");

		Reporter.log("Navigating to Tea Kettles Page");
		WilliamsSonomaTeaKettlesPage williamsSonomaTeaKettlesPage=new WilliamsSonomaTeaKettlesPage(driver);
		williamsSonomaTeaKettlesPage.waitForPageToLoad();
		williamsSonomaTeaKettlesPage.waitForElementToBeVisible(williamsSonomaTeaKettlesPage.headerTeaKettlesLocator);
		verifyTrue(williamsSonomaTeaKettlesPage.headerTeaKettles.isDisplayed(), "Tea Kettle page header is displayed");

		Reporter.log("Clicking on a Tea Kettle Product from the Tea Kettles page");
		williamsSonomaTeaKettlesPage.scrollIntoViewElementUsingJs(williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0));
		williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0).click();

		Reporter.log("Navigating to the Tea Kettle product Page");
		WilliamsSonomaBrevilleOneTouchTeaMakerPage brevilleOneTouchTeaMakerPage =new WilliamsSonomaBrevilleOneTouchTeaMakerPage(driver);
		brevilleOneTouchTeaMakerPage.waitForPageToLoad();

		Reporter.log("Clicking on 'Add to Cart' button");
		brevilleOneTouchTeaMakerPage.waitForElementToBeVisible(brevilleOneTouchTeaMakerPage.buttonShoppingCartLocator);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(400,600)", "");
		//brevilleOneTouchTeaMakerPage.clickOnElementUsingAction(brevilleOneTouchTeaMakerPage.buttonShoppingCart);
		brevilleOneTouchTeaMakerPage.scrollIntoViewElementUsingJs(brevilleOneTouchTeaMakerPage.buttonShoppingCart);
        brevilleOneTouchTeaMakerPage.buttonShoppingCart.click();

		brevilleOneTouchTeaMakerPage.waitForElementToBeVisible(brevilleOneTouchTeaMakerPage.widgetConfirmationOverlayLocator);
		brevilleOneTouchTeaMakerPage.scrollIntoViewElementUsingJs(brevilleOneTouchTeaMakerPage.widgetCheckoutConfirmationOverlay.buttonCheckout);

		Reporter.log("Clicking on Checkout button on the confirmation overlay widget");
		brevilleOneTouchTeaMakerPage.widgetCheckoutConfirmationOverlay.buttonCheckout.click();
		WilliamsSonomaShoppingCartPage shoppingCartPage= new WilliamsSonomaShoppingCartPage(driver);
		shoppingCartPage.waitForPageToLoad();
		verifyTrue(shoppingCartPage.getProductsListFromShoppingCartTable().contains("Breville One-Touch Tea Maker"),"Expected productName is displayed in the shopping cart");



	}


} 
	
	
	