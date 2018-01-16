package com.williamssonoma.automationCore.listeners.testStep.client;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.internal.TestNGMethod;
import org.testng.internal.annotations.IAnnotationFinder;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import static com.williamssonoma.automationCore.data.MetaDataScanner.getMetadata;

public class TestNGScenario extends TestNGMethod {

	private static final long serialVersionUID = 6225163528424712337L;
	private Scenario scenario;
	private Map<String, Object> metadata;
	private String qualifiledName;

	public TestNGScenario(Method method, IAnnotationFinder finder, XmlTest xmlTest, Object instance) {
		super(method, finder, xmlTest, instance);
		init(instance);
	}

	private void init(Object instance) {
		if (Scenario.class.isAssignableFrom(getRealClass())) {
			scenario = (Scenario) instance;
			if (scenario.getPriority() < 1000 || getXmlTest().getParallel().equals(ParallelMode.TESTS)) {
				setPriority(scenario.getPriority());
			}
			setGroups(scenario.getM_groups());
			setGroupsDependedUpon(scenario.getM_groupsDependedUpon(), new ArrayList<String>());
			setMethodsDependedUpon(scenario.getM_methodsDependedUpon());
			setDescription(scenario.getDescription());
			setEnabled(scenario.isM_enabled());
			setAlwaysRun(scenario.isM_isAlwaysRun());
			setIgnoreMissingDependencies(scenario.getIgnoreMissingDependencies());
			metadata = scenario.getMetadata();
			qualifiledName = scenario.getTestName();
			setTimeOut(scenario.getTimeOut());
			
		} else {
			metadata = getMetadata(getMethod(), true);
			qualifiledName = getRealClass().getName() + "." + getMethodName();
		}
		metadata.put("name", getMethodName());
		metadata.put("sign", getSignature());

	}

	@Override
	public String getMethodName() {
		return scenario != null ? scenario.getTestName() : super.getMethodName();
	}

	@Override
	public String getSignature() {
		return scenario != null ? computeSign() : super.getSignature();
	}

	private String computeSign() {
		StringBuilder result = new StringBuilder(scenario.getSignature());

		result.append("[pri:").append(getPriority()).append(", instance:").append(getInstance()).append("]");
		return result.toString();
	}

	public Map<String, Object> getMetaData() {
		return metadata;
	}


	public String getQualifiedName() {
		return qualifiledName;
	}

}
