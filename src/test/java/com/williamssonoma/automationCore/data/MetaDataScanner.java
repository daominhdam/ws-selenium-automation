package com.williamssonoma.automationCore.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import com.williamssonoma.automationBaseClasses.ConfigurationManager;
import com.williamssonoma.automationCore.util.StringUtil;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class MetaDataScanner {

	/**
	 * Scans all annotation except @Test, and generates map.
	 * 
	 * @param MethodOrFileld
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getMetadata(AccessibleObject methodOrFileld, boolean excludeTest) {
		Map<String, Object> metadata = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);

		try {
			Annotation[] allAnnotations = methodOrFileld.getAnnotations();
			for (Annotation annotation : allAnnotations) {
				if (excludeTest && annotation instanceof Test)
					continue;

				Method[] annotationMethods = annotation.annotationType().getDeclaredMethods();
				for (Method annotationMethod : annotationMethods) {
					Object objVal = annotationMethod.invoke(annotation);
					if (annotation instanceof MetaData) {
						@SuppressWarnings("unchecked")
						Map<String, Object> map = new Gson().fromJson((String) objVal, Map.class);
						metadata.putAll(map);
					} else {
						metadata.put(annotationMethod.getName(), objVal);
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return metadata;
	}

	/**
	 * Scans all the annotation on the method and prepares a map.
	 * 
	 * @param method
	 * @return map containing all annotation parameter as key.
	 */
	public static Map<String, Object> getMetadata(AccessibleObject methodOrFileld) {
		return getMetadata(methodOrFileld, false);
	}

	/**
	 * Get parameter value from the system property, context or configuration.
	 * 
	 * @param context
	 * @param parameter
	 * @return parameter value, first preference is system property, second is
	 *         context and last is configuration/properties.
	 */
	public static String getParameter(ITestContext context, String parameter) {
		if (System.getProperties().containsKey(parameter)) {
			return System.getProperty(parameter);
		}
		String paramValue = null != context ? context.getCurrentXmlTest().getParameter(parameter) : "";
		if (StringUtil.isNotBlank(paramValue)) {
			return paramValue;
		}
		return ConfigurationManager.getBundle().getString(parameter);
	}

	/**
	 * Get parameter value from the system property, context or configuration.
	 * 
	 * @param method
	 * @param parameter
	 * @return parameter value, first preference is system property, second is
	 *         context and last is configuration/properties.
	 */
	public static String getParameter(ITestNGMethod method, String parameter) {
		if (System.getProperties().containsKey(parameter)) {
			return System.getProperty(parameter);
		}
		String paramValue = null != method && null != method.getXmlTest() ? method.getXmlTest().getParameter(parameter)
				: "";
		if (StringUtil.isNotBlank(paramValue)) {
			return paramValue;
		}
		return ConfigurationManager.getBundle().getString(parameter);
	}
}
