package com.williamssonoma.automationCore.listeners.testStep.client;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.williamssonoma.automationBaseClasses.ConfigurationManager;
import com.williamssonoma.automationCore.data.MetaDataScanner;
import com.williamssonoma.automationCore.util.FileUtil;
import com.williamssonoma.automationCore.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.testng.ITestContext;
import org.testng.annotations.Factory;

/**
 * Factory class for custom step client.
 * com.qmetry.qaf.automation.step.client.ScenarioFactory.java
 * 
 */
public abstract class ScenarioFactory {
	public static final String GROUPS = "groups";

	protected final Log logger = LogFactoryImpl.getLog(getClass());

	private List<Scenario> scenarios = new LinkedList<Scenario>();
	private List<String> fileExtension;
	private List<String> includeGroups = new ArrayList<String>();
	private List<String> excludeGroups = new ArrayList<String>();

	public ScenarioFactory(List<String> list) {
		this.fileExtension = list;
	}

	@Factory
	public Object[] getTestsFromFile(ITestContext context) {

		if (null != context) {
			includeGroups = Arrays.asList(context.getIncludedGroups());
			excludeGroups = Arrays.asList(context.getExcludedGroups());
		}

		String sanariosloc = MetaDataScanner.getParameter(context, "scenario.file.loc");
		if (StringUtil.isNotBlank(sanariosloc)) {
			ConfigurationManager.getBundle().setProperty("scenario.file.loc", sanariosloc);
		}

		System.out.printf("include groups %s\n exclude groups: %s Scanarios location: %s \n", includeGroups,
				excludeGroups, sanariosloc);
		logger.info("scenario.file.loc"
				+ ConfigurationManager.getBundle().getStringArray("scenario.file.loc", "./scenarios"));
		for (String fileName : ConfigurationManager.getBundle().getStringArray("scenario.file.loc", "./scenarios")) {
			process(fileName);
		}

		logger.info("total test found: " + scenarios.size());
		return scenarios.toArray(new Object[scenarios.size()]);

	}

	protected abstract ScenarioFileParser getParser();

	public void process(String fileName) {
		ScenarioFileParser parser = getParser();
		parser.setExcludeGroups(excludeGroups);
		parser.setIncludeGroups(includeGroups);

		File fileOrDir = new File(fileName);
		if (fileOrDir.isDirectory()) {
			Collection<File> files = FileUtil.listFiles(fileOrDir, fileExtension.toArray(new String[] {}), true);
			for (File scenarioFile : files) {
				try {
					parser.parse(scenarioFile.getAbsolutePath(), scenarios);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (fileExtension.contains(FileUtil.getExtention(fileName.toLowerCase()))) {
			try {
				parser.parse(fileName, scenarios);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
