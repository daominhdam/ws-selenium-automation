package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopupOverlayJoinEmailListWidgetComponent  {
    public PopupOverlayJoinEmailListWidgetComponent(){

    }

    @FindBy(xpath="//a[contains(@class, 'stickyOverlayMinimizeButton')]")
    private WebElement buttonStickyOverlayMinimize ;

    @FindBy(xpath="//div[@class='email-campaign-form-wrapper-sticky']/form[@id='join-email']")
    private WebElement formJoinEmail ;

    @FindBy(xpath="//input[@id='emailAddr']")
    private WebElement textBoxEmailAddress ;

    public WebElement getButtonStickyOverlayMinimize() {
        return buttonStickyOverlayMinimize;
    }

    public WebElement getFormJoinEmail(){
        return formJoinEmail;
    }

    public WebElement getTextBoxEmailAddress() {
        return textBoxEmailAddress;
    }




}
