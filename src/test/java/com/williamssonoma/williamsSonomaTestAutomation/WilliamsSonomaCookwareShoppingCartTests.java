package com.williamssonoma.williamsSonomaTestAutomation;

import com.aventstack.extentreports.Status;
import com.williamssonoma.automationCore.util.waitServices.Wait;
import com.williamssonoma.williamsSonomaPages.*;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.WilliamsSonomaCheckoutConfirmationOverlayWidget;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static com.williamssonoma.automationCore.util.verificationServices.Verifications.*;

public class  WilliamsSonomaCookwareShoppingCartTests extends BaseTestCase {
	

	@Test ()
    public void verifyProductSavedForLater() throws InterruptedException {
		WilliamsSonomaCheckoutConfirmationOverlayWidget checkoutConfirmationOverlayWidget;
		WilliamsSonomaProductPage williamsSonomaProductPage;

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
		System.out.println(williamsSonomaTeaKettlesPage.getAllProductNames());
		String productName= williamsSonomaTeaKettlesPage.getAllProductNames().get(0);
		williamsSonomaTeaKettlesPage.linksAllTeaKettles.get(0).click();

		test.log(Status.INFO,"Navigating to the Tea Kettle product Page");
		Reporter.log("Navigating to the Tea Kettle product Page");
		williamsSonomaProductPage= new WilliamsSonomaProductPage(driver, null);
		williamsSonomaProductPage.get();
		String title= williamsSonomaProductPage.getPageTitle();
		System.out.println(title);

		test.log(Status.INFO,"Clicking on 'Add to Cart' button");
		Reporter.log("Clicking on 'Add to Cart' button");
		((JavascriptExecutor)driver).executeScript("window.scrollBy(900,400)", "");
		williamsSonomaProductPage.clickFirstSelectColorOption();
		williamsSonomaProductPage.typeNumberInQuantity(1);
		williamsSonomaProductPage.buttonShoppingCart.click();
		Wait.untilElementAppears(williamsSonomaProductPage.widgetConfirmationOverlay);


		test.log(Status.INFO,"Clicking on Checkout button on the confirmation overlay widget");
		Reporter.log("Clicking on Checkout button on the confirmation overlay widget");
		checkoutConfirmationOverlayWidget =new WilliamsSonomaCheckoutConfirmationOverlayWidget(driver,williamsSonomaProductPage );
		checkoutConfirmationOverlayWidget.get();
		checkoutConfirmationOverlayWidget.clickCheckoutButton();

		test.log(Status.INFO,"Navigating to Shopping Cart Page");
		Reporter.log("Navigating to Shopping Cart Page");
		WilliamsSonomaShoppingCartPage shoppingCartPage= new WilliamsSonomaShoppingCartPage(driver);
		shoppingCartPage.waitForPageToLoad();
		System.out.println(shoppingCartPage.getProductsListFromShoppingCartTable());
		//Assert.assertTrue(shoppingCartPage.getProductsListFromShoppingCartTable().contains(productName),"Expected productName is displayed in the shopping cart");



	}


} 
	
	
	