package com.williamssonoma.williamsSonomaPages;

import com.williamssonoma.automationCore.webElements.WSWebElement;
import com.williamssonoma.automationCore.webElements.element.HtmlElement;
import com.williamssonoma.automationCore.webElements.loader.HtmlElementLoader;
import com.williamssonoma.automationCore.webElements.loader.decorator.HtmlElementDecorator;
import com.williamssonoma.automationCore.webElements.loader.decorator.HtmlElementLocatorFactory;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.WilliamsSonomaCheckoutConfirmationOverlayWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WilliamsSonomaBrevilleOneTouchTeaMakerPage extends BaseTestPage{
	
	public WilliamsSonomaBrevilleOneTouchTeaMakerPage(WebDriver driver){
		super(driver);
		HtmlElementLoader.populatePageObject(this, driver);
	}

	@FindBy(xpath="//h1[text()='Breville One-Touch Tea Maker']")
	public WebElement headerBrevilleOneTouchTeaKettles;


	@FindBy(xpath="//div[@class='addcart-options']//button/span[text()='Add to Cart']")
	// @FindBy(xpath="//div[@class='addcart-options']//button[contains(@class, 'btn_addtobasket')]")
	public WebElement buttonShoppingCart;

	public By buttonShoppingCartLocator = By.xpath("//button[contains(@class, 'btn_addtobasket')]");

    @FindBy(xpath="//div[@id='racOverlay']")
    public HtmlElement widgetConfirmationOverlay;
    public By widgetConfirmationOverlayLocator = By.xpath("//div[@id='racOverlay']\"");

    @FindBy(xpath="//div[@id='racOverlay']")
    public WilliamsSonomaCheckoutConfirmationOverlayWidget widgetCheckoutConfirmationOverlay;

    public WilliamsSonomaCheckoutConfirmationOverlayWidget getWidgetCheckoutConfirmationOverlay(){
    	return widgetCheckoutConfirmationOverlay;
	}

}
