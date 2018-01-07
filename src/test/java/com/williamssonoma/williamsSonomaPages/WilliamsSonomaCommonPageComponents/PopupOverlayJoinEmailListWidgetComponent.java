package com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents;

import com.williamssonoma.williamsSonomaPages.BaseTestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupOverlayJoinEmailListWidgetComponent  {
    public PopupOverlayJoinEmailListWidgetComponent(WebDriver driver){

    }

    @FindBy(xpath="//a[contains(@class, 'stickyOverlayMinimizeButton')]")
    public WebElement buttonStickyOverlayMinimize ;

    @FindBy(xpath="//div[@class='email-campaign-form-wrapper-sticky']/form[@id='join-email']")
    public WebElement formJoinEmail ;

    @FindBy(xpath="//input[@id='emailAddr']")
    public WebElement textBoxEmailAddress ;


}
