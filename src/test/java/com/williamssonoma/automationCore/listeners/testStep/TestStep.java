package com.williamssonoma.automationCore.listeners.testStep;

import java.util.Map;

public interface TestStep extends TestStepCaller {
	Object execute();

	String getDescription();

	void setDescription(String description);

	String getName();

	String getSignature();

	void setActualArgs(Object... args);

	Object[] getActualArgs();

	public TestStep clone();

	StepExecutionTracker getStepExecutionTracker();

	int getThreshold();

	Map<String, Object> getMetaData();
}
