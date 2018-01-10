package com.williamssonoma.williamsSonomaPages;

import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.WilliamsSonomaCheckoutConfirmationOverlayWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.williamssonoma.williamsSonomaPages.BaseTestPage;

public class WilliamsSonomaBrevilleOneTouchTeaMakerPage extends BaseTestPage{
	
	public WilliamsSonomaBrevilleOneTouchTeaMakerPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[text()='Breville One-Touch Tea Maker']")
	public WebElement headerBrevilleOneTouchTeaKettles;


	@FindBy(xpath="//div[@class='addcart-options']//button[contains(@class, 'btn_addtobasket')]")
	public WebElement buttonShoppingCart;

	public By buttonShoppingCartLocator = By.xpath("//button[contains(@class, 'btn_addtobasket')]");

    @FindBy(xpath="//div[@id='racOverlay']")
    public WebElement widgetConfirmationOverlay;
    public By widgetConfirmationOverlayLocator = By.xpath("//div[@id='racOverlay']\"");

    @FindBy(xpath="//div[@id='racOverlay']")
    public WilliamsSonomaCheckoutConfirmationOverlayWidget widgetCheckoutConfirmationOverlay;

}
