package com.williamssonoma.automationCore.util.waitServices;

import com.google.common.base.Predicate;
import com.williamssonoma.automationCore.webElements.WSWebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;



public class Wait {



	public static boolean untilElementAppears(WSWebElement obj){

		long maxTimeout = 5000;

		return untilElementAppears(obj, maxTimeout);

	}

	

	public static boolean untilElementDisappears(WSWebElement obj){

		long maxTimeout = 5000;

		return untilElementDisappears(obj, maxTimeout);

	}

	

	public static boolean untilElementAppears(WSWebElement obj, long maxTimeout){
		FluentWait<WSWebElement> fluentWait = new FluentWait<WSWebElement>(obj);
	 	fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);
        fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);
		try{

			fluentWait.until(new Predicate<WSWebElement>() {

				public boolean apply(WSWebElement obj) {

					try {

						return obj.isDisplayed();

					} catch (NoSuchElementException | StaleElementReferenceException e) {

						return false;

					}

				}

			});

          return obj.isDisplayed();

        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){

        	return false;

        }



	}

	

	public static boolean untilElementDisappears(WSWebElement obj, long maxTimeout){

		

		FluentWait<WSWebElement> fluentWait = new FluentWait<WSWebElement>(obj);



		fluentWait.pollingEvery(100, TimeUnit.MILLISECONDS);

		fluentWait.withTimeout(maxTimeout, TimeUnit.MILLISECONDS);



		try{



			fluentWait.until(new Predicate<WSWebElement>() {

				public boolean apply(WSWebElement obj) {

					try {

						return !obj.isDisplayed();

					} catch (NoSuchElementException | StaleElementReferenceException e) {

						return true;

					}

				}

			});

			

			//Wait.sleep(1000);

			return !obj.isDisplayed();

		}catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){

			return true;

		}

		

	}



	

	public static boolean untilElementIsEnabled(WSWebElement obj){

		long maxTimeout = 3000;

		return untilElementIsEnabled(obj, maxTimeout);

	}

	

	public static boolean untilElementIsDisabled(WSWebElement obj){

		long maxTimeout = 3000;

		return untilElementIsDisabled(obj, maxTimeout);

	}

	

	public static boolean untilElementIsEnabled(WSWebElement obj, long maxTimeout){

		

		boolean enabled = false;

		for (int i = 0; i < (maxTimeout/1000); i++) {

			if(obj.isEnabled()){

				enabled = true;

				break;

			}

			sleep(1000);

		}

		return enabled;

	}

	

	public static boolean untilElementIsDisabled(WSWebElement obj, long maxTimeout){

		

		boolean disabled = false;

		for (int i = 0; i < (maxTimeout/1000); i++) {

			if(!obj.isEnabled()){

				disabled = true;

				break;

			}

			sleep(1000);

		}

		return disabled;

	}

	

	public static void sleep(long millis){



		try {

			Thread.sleep(millis);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}



}