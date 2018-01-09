package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WilliamsSonomaCheckoutConfirmationOverlayWidget {


    public WilliamsSonomaCheckoutConfirmationOverlayWidget() {
    }


    @FindBy(xpath="//div[@id='btn-checkout']")
    public WebElement buttonCheckout ;

    @FindBy(xpath="//a[@id='overlayCloseButton']")
    public WebElement buttonOverlayClose ;

}
