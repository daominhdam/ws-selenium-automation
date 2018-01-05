package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.williamssonoma.williamsSonomaPages.BaseTestPage;


public class williamsSonomaCookwarePotsAnsPansPage extends BaseTestPage{
	
	public williamsSonomaCookwarePotsAnsPansPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}


}
