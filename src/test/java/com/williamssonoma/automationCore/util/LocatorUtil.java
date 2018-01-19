package com.williamssonoma.automationCore.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.williamssonoma.automationBaseClasses.ConfigurationManager;
import org.json.JSONException;
import org.openqa.selenium.By;

import com.google.gson.Gson;
import com.google.gson.JsonElement;


public class  LocatorUtil {

	public static String getXPathLoc(String id) {
		return "//*[@id='" + id + "']";
	}

	public static String getCssLoc(String id) {
		return "css=*#" + id;
	}

	public static String getDescription(String locator) {
		if (JSONUtil.isValidJsonString(locator)) {
			try {
				Map<String, Object> map = JSONUtil.toMap(locator);
				return map.containsKey("desc") ? (String) map.get("desc")
						: map.containsKey("description") ? (String) map.get("description")
								: (String) map.get("locator");
			} catch (JSONException e) {
			}
		}
		return locator;
	}



}
