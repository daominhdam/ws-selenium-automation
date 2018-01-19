package com.williamssonoma.automationCore.webElements;

import java.util.List;

import com.williamssonoma.automationCore.util.StringMatcher;
import org.openqa.selenium.WebElement;


public interface WSWebElement extends WebElement, FindsByCustomStretegy {

	// explicit wait
	void waitForVisible(long... timeout);

	void waitForNotVisible(long... timeout);

	void waitForDisabled(long... timeout);

	void waitForEnabled(long... timeout);

	void waitForPresent(long... timeout);

	void waitForNotPresent(long... timeout);

	void waitForText(String text, long... timeout);

	void waitForText(StringMatcher matcher, long... timeout);

	void waitForNotText(String text, long... timeout);

	void waitForNotText(StringMatcher matcher, long... timeout);

	void waitForValue(Object value, long... timeout);

	void waitForNotValue(Object value, long... timeout);

	void waitForSelected(long... timeout);

	void waitForNotSelected(long... timeout);

	void waitForAttribute(String attr, String value, long... timeout);

	void waitForNotAttribute(String attr, String value, long... timeout);

	void waitForAttribute(String attr, StringMatcher value, long... timeout);

	void waitForNotAttribute(String attr, StringMatcher value, long... timeout);

	void waitForCssClass(String className, long... timeout);

	void waitForNotCssClass(String className, long... timeout);

	void waitForCssStyle(String prop, String value, long... timeout);

	void waitForNotCssStyle(String prop, String value, long... timeout);

	// verifications
	boolean verifyPresent(String... label);

	boolean verifyNotPresent(String... label);

	boolean verifyVisible(String... label);

	boolean verifyNotVisible(String... label);

	boolean verifyEnabled(String... label);

	boolean verifyDisabled(String... label);

	boolean verifyText(String text, String... label);

	boolean verifyText(StringMatcher matcher, String... label);

	boolean verifyNotText(String text, String... label);

	boolean verifyNotText(StringMatcher matcher, String... label);

	<T> boolean verifyValue(T t, String... label);

	<T> boolean verifyNotValue(T t, String... label);

	boolean verifySelected(String... label);

	boolean verifyNotSelected(String... label);

	boolean verifyAttribute(String attr, String value, String... label);

	boolean verifyAttribute(String attr, StringMatcher matcher, String... label);

	boolean verifyNotAttribute(String attr, String value, String... label);

	boolean verifyNotAttribute(String attr, StringMatcher matcher, String... label);

	boolean verifyCssClass(String className, String... label);

	boolean verifyNotCssClass(String className, String... label);

	boolean verifyCssStyle(String prop, String value, String... label);

	boolean verifyNotCssStyle(String prop, String value, String... label);

	// preconditions
	void givenPresent();

	void givenNotPresent(String... label);

	// assertions
	void assertPresent(String... label);

	void assertNotPresent(String... label);

	void assertVisible(String... label);

	void assertNotVisible(String... label);

	void assertEnabled(String... label);

	void assertDisabled(String... label);

	void assertText(String text, String... label);

	void assertNotText(String text, String... label);

	void assertText(StringMatcher matcher, String... label);

	void assetNotText(StringMatcher matcher, String... label);

	<T> void assertValue(T t, String... label);

	<T> void assertNotValue(T t, String... label);

	void assertSelected(String... label);

	void assertNotSelected(String... label);

	void assertAttribute(String attr, String value, String... label);

	void assertAttribute(String attr, StringMatcher matcher, String... label);

	void assertNotAttribute(String attr, String value, String... label);

	void assertNotAttribute(String attr, StringMatcher matcher, String... label);

	void assertCssClass(String className, String... label);

	void assertNotCssClass(String className, String... label);

	void assertCssStyle(String prop, String value, String... label);

	void assertNotCssStyle(String prop, String value, String... label);

	// other
	void setAttribute(String attr, String value);

	boolean isPresent();

	WSWebElement findElement(String loc);

	List<WSWebElement> findElements(String loc);

	<T> T executeScript(String sctipt);

	<T> T executeAsyncScript(String sctipt);
}
