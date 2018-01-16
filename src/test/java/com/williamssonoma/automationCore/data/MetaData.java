package com.williamssonoma.automationCore.data;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.testng.annotations.Test;

/**
 * This annotation should be used with {@link Test} annotation to provide
 * additional test meta-data. This annotation enables to provide custom
 * meta-data for test case. The value for meta-data must be valid JSON map. This
 * meta data can be used for run configuration to include/exclude test cases
 * with specific meta-data combination.
 * 
 */
@Inherited
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ METHOD, TYPE })
public @interface MetaData {
	/**
	 * The value for meta-data must be valid json map. For example:
	 * <P>
	 * <CODE>
	 * "{'Brand':['A'],'Module':['M1','M2']}"</CODE>
	 * 
	 * @return value of the MetaData
	 */
	String value() default "";
}
