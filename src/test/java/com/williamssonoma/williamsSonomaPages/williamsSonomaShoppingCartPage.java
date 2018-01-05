package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class williamsSonomaShoppingCartPage extends BaseTestPage{
	
	public williamsSonomaShoppingCartPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}


}
