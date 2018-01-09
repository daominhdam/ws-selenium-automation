package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import com.williamssonoma.williamsSonomaPages.BaseTestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
