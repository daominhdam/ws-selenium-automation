package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import com.williamssonoma.automationCore.webElements.element.HtmlElement;
import com.williamssonoma.williamsSonomaPages.BaseTestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WilliamsSonomaCheckoutConfirmationOverlayWidget{

    @FindBy(xpath="//div[@id='btn-checkout']/a[@id='anchor-btn-checkout']")
    private HtmlElement buttonCheckout ;

    @FindBy(xpath="//a[@id='overlayCloseButton']")
    private WebElement buttonOverlayClose ;

    public WebElement getButtonCheckout() {
        return buttonCheckout;
    }
    public WebElement getButtonOverlayClose() {
        return buttonOverlayClose;
    }



}
