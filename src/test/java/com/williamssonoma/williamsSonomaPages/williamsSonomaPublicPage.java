package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class williamsSonomaPublicPage extends BaseTestPage{
	public williamsSonomaPublicPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//a[text()=\"Sign In\"]")
	public WebElement  linkSignIn;
	

}
