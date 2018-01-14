package com.williamssonoma.automationCore.reportng;

import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.HTMLReporter;

import java.util.List;



public class CustomReporter extends HTMLReporter {



	@Override

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,

			String outputDirectoryName) {

		super.generateReport(xmlSuites, suites, outputDirectoryName);



		try {

			SendEmail.execute();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}