package com.williamssonoma.williamsSonomaPages;

import com.williamssonoma.automationCore.util.waitServices.Wait;
import com.williamssonoma.automationCore.webElements.element.HtmlElement;
import com.williamssonoma.automationCore.webElements.loader.HtmlElementLoader;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.WilliamsSonomaCheckoutConfirmationOverlayWidget;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import java.util.List;

public class WilliamsSonomaProductPage extends BasePage<WilliamsSonomaProductPage>{

    public WebDriver driver;
    String pageURL="https://www.williams-sonoma.com/shop/electrics/electrics-teakettles/";
    @FindBy(xpath="//div[@class='addcart-options']//button/span[text()='Add to Cart']")
    public HtmlElement buttonShoppingCart;

    public By  byShoppingCartLocator = By.xpath("//button[contains(@class, 'btn_addtobasket')]");

    @FindBy(xpath="//div[@id='racOverlay']")
    public HtmlElement widgetConfirmationOverlay;
    public By widgetConfirmationOverlayLocator = By.xpath("//div[@id='racOverlay']\"");

    @FindBy(xpath="//h4[text()='Select Color: ']")
    private HtmlElement labelSelectColor;

    @FindBy(xpath="//h4[text()='Select Color: ']/following-sibling::ul/li")
    private List<WebElement> linkSelectColorAllOptions;

    @FindBy(xpath="//input[@aria-label='Quantity'][@type='number']")
    private HtmlElement textBoxQuantity;

    @FindBy(xpath="//div[@id='racOverlay']")
    public WilliamsSonomaCheckoutConfirmationOverlayWidget widgetCheckoutConfirmationOverlay;

    public WilliamsSonomaCheckoutConfirmationOverlayWidget getWidgetCheckoutConfirmationOverlay(){
        return widgetCheckoutConfirmationOverlay;
    }
    public WilliamsSonomaProductPage(WebDriver driver, LoadableComponent<?> parent) {

        // first, make sure you save the base class driver and parent values
        super(driver);
        this.parent = parent;
        this.driver=driver;
        // lastly, initialize your FindBy WebElement variables
        HtmlElementLoader.populatePageObject(this, driver);
    }


    @Override
    protected void load() {
        super.setPageURL(pageURL);
        driver.get(getPageURL());
       }

    @Override
    protected void isLoaded() throws Error {
        try{
            Assert.assertTrue(driver.getCurrentUrl().contains("williams-sonoma"));
        }catch(NoSuchElementException ex){
            throw new AssertionError();
        }
    }

    public void clickFirstSelectColorOption(){
        Wait.untilElementAppears(labelSelectColor);
        linkSelectColorAllOptions.get(0).findElement(By.tagName("a")).click();
    }

    public void typeNumberInQuantity(int num){
        Wait.untilElementAppears(labelSelectColor);
        textBoxQuantity.clear();
        textBoxQuantity.sendKeys(String.valueOf(num));
    }
}
