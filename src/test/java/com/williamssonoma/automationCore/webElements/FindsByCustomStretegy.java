package com.williamssonoma.automationCore.webElements;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author Chirag
 */
public interface FindsByCustomStretegy {
    WebElement findElementByCustomStretegy(String stretegy, String loc);

	List<WebElement> findElementsByCustomStretegy(String stretegy, String loc);
}
