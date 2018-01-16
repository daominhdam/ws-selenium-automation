package com.williamssonoma.automationCore.listeners.testStep.client;

import java.util.List;

public interface ScenarioFileParser {
	public void parse(String scenarioFile, List<Scenario> scenarios);

	void setExcludeGroups(List<String> excludeGroups);

	void setIncludeGroups(List<String> includeGroups);
}
