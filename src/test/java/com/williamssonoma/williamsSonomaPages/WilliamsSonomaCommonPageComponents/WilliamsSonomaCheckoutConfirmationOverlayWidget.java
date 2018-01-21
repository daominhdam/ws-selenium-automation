package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import com.williamssonoma.automationCore.util.waitServices.Wait;
import com.williamssonoma.automationCore.webElements.element.HtmlElement;
import com.williamssonoma.automationCore.webElements.loader.HtmlElementLoader;
import com.williamssonoma.williamsSonomaPages.BasePage;
import com.williamssonoma.williamsSonomaPages.BaseTestPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaBrevilleOneTouchTeaMakerPage;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import java.util.List;

public class WilliamsSonomaCheckoutConfirmationOverlayWidget extends BasePage<WilliamsSonomaCheckoutConfirmationOverlayWidget> {
    protected LoadableComponent<?> parent = null;
    WebDriver driver;

    @FindBy(xpath="//div[@id='btn-checkout']/a[@id='anchor-btn-checkout']")
    private HtmlElement buttonCheckout ;
    public By byCheckout = By.xpath("//div[@id='btn-checkout']/a[@id='anchor-btn-checkout']");

    @FindBy(xpath="//a[@id='overlayCloseButton']")
    private HtmlElement buttonOverlayClose ;

    public WilliamsSonomaCheckoutConfirmationOverlayWidget(WebDriver driver, LoadableComponent<?> parent) {
        super(driver);
        this.parent = parent;
        this.driver=driver;
        // lastly, initialize your FindBy WebElement variables
        HtmlElementLoader.populatePageObject(this, driver);
    }

    /*
 *  We don't need to override LoadableComponent.isLoaded() because it's already
 *  implemented the way we want it in the BasePage class.
	@Override
    protected void isLoaded() throws Error { }
*/

    public void clickCheckoutButton(){
        Wait.untilElementAppears(buttonCheckout);
        buttonCheckout.click();
    }
    public void closeCheckoutOverlay() {
        Wait.untilElementAppears(buttonOverlayClose);
        buttonOverlayClose.click();
    }

    @Override
    protected void load() {
        super.load();
    }


    @Override
    protected void isLoaded() throws Error {
        try{
            Assert.assertTrue(driver.getCurrentUrl().contains("www.williams-sonoma.com/products/"));
        }catch(NoSuchElementException ex){
            throw new AssertionError();
        }
    }


}
