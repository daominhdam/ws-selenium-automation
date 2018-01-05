package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.williamssonoma.williamsSonomaPages.BaseTestPage;

public class williamsSonomaBrevilleOneTouchTeaMakerPage extends BaseTestPage{
	
	public williamsSonomaBrevilleOneTouchTeaMakerPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//h1[normalize-space()=' ']")
	public WebElement headerToDoApp; 	
	

}
