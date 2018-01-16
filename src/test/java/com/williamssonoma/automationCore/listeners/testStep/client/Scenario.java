package com.williamssonoma.automationCore.listeners.testStep.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.williamssonoma.automationBaseClasses.ApplicationProperties;
import com.williamssonoma.automationCore.listeners.testStep.StepExecutionTracker;
import com.williamssonoma.automationCore.listeners.testStep.TestStep;
import com.williamssonoma.automationCore.listeners.testStep.TestStepCompositer;
import com.williamssonoma.automationCore.util.verificationServices.CheckpointResultBean;
import com.williamssonoma.automationCore.util.verificationServices.MessageTypes;
import org.testng.annotations.Test;

import static com.williamssonoma.automationBaseClasses.ConfigurationManager.getBundle;

public class Scenario implements TestStepCompositer {

	protected String scenarioName;
	protected String description = "";
	protected Collection<TestStep> steps;
	private static final int SCANARIOBASEINDEX = 1000;
	private static int scanariocount = 0;
	private int priority;
	protected String[] m_groups = {};
	protected String[] m_groupsDependedUpon = {};
	protected String[] m_methodsDependedUpon = {};
	protected String[] m_beforeGroups = {};
	protected String[] m_afterGroups = {};
	protected long timeOut;
	private String signature;
	protected String status = "";
	private Map<String, Object> metadata =
			new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);

	public Scenario(String testName, Collection<TestStep> steps) {
		this(testName, steps, null);
	}

	public Scenario(String testName, Collection<TestStep> steps,
			Map<String, Object> metadata) {
		priority = SCANARIOBASEINDEX + scanariocount++;
		scenarioName = testName.trim();
		this.steps = steps;
		if (null != metadata)
			this.metadata.putAll(metadata);
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		signature = comuteSign();

		if (metadata.containsKey(ScenarioFactory.GROUPS)) {
			m_groups = ((List<String>) metadata.get(ScenarioFactory.GROUPS))
					.toArray(new String[]{});
		}
		if (metadata.containsKey("dependsOnGroups")) {
			m_groupsDependedUpon = ((List<String>) metadata.get("dependsOnGroups"))
					.toArray(new String[]{});
		}

		if (metadata.containsKey("dependsOnMethods")) {
			m_methodsDependedUpon = ((List<String>) metadata.get("dependsOnMethods"))
					.toArray(new String[]{});
		}
		if (metadata.containsKey("desc"))
			description = (String) metadata.get("desc");
		if (metadata.containsKey("description"))
			description = (String) metadata.get("description");

		if (metadata.containsKey("priority")) {
			priority = ((Number) metadata.get("priority")).intValue();
		}
		if (metadata.containsKey("timeOut")) {
			timeOut = ((Number) metadata.get("timeOut")).longValue();
		}

	}

	protected String comuteSign() {
		return getPackage() + "." + scenarioName + "()";
	}

	public String getTestName() {
		return scenarioName;
	}



	public String getDescription() {
		return description;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	@Override
	public int hashCode() {
		return ((Integer) getPriority()).hashCode();
	}

	public int getPriority() {
		return priority;
	}

	public String[] getM_groups() {
		return m_groups;
	}

	public String[] getM_groupsDependedUpon() {
		return m_groupsDependedUpon;
	}

	public String[] getM_methodsDependedUpon() {
		return m_methodsDependedUpon;
	}

	public long getTimeOut() {
		return timeOut;
	}
	public boolean isM_isAlwaysRun() {
		return !metadata.containsKey("alwaysRun") || (Boolean) metadata.get("alwaysRun");// m_isAlwaysRun;
	}

	public boolean isM_enabled() {
		return !metadata.containsKey("enabled") || (Boolean) metadata.get("enabled");// m_enabled;
	}

	protected void beforeScanario() {
		status = "NOTRUN";
		getBundle().setProperty(ApplicationProperties.CURRENT_TEST_NAME.key,
				scenarioName);
		getBundle().setProperty(ApplicationProperties.CURRENT_TEST_DESCRIPTION.key,
				description);
	}

	public String getSignature() {
		return signature;
	}

	@Override
	public String getFileName() {
		if (metadata.containsKey("fileName"))
			return (String) metadata.get("fileName");
		return "";
	}

	@Override
	public int getLineNumber() {
		if (metadata.containsKey("lineNumber"))
			return (Integer) metadata.get("lineNumber");
		return 0;
	}

	/**
	 * default is false - don't ignore missing dependencies
	 * 
	 * @return
	 */
	public boolean getIgnoreMissingDependencies() {
		return metadata.containsKey("ignoreMissingDependencies")
				&& (Boolean) metadata.get("ignoreMissingDependencies");
	}

	@Override
	public Collection<TestStep> getSteps() {
		return steps;
	}

	protected String getPackage() {
		if (null == metadata || !metadata.containsKey("referece")) {
			return "";
		}
		String filePath = (String) metadata.get("referece");

		return filePath.replaceAll("/", ".");
	}

}
