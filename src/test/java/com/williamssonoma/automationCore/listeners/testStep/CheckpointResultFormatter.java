package com.williamssonoma.automationCore.listeners.testStep;

import com.williamssonoma.automationCore.util.verificationServices.CheckpointResultBean;

import java.util.Collection;

/**
 * com.qmetry.qaf.automation.ui.selenium.CheckpointResultFormatter.java
 * 
 * @author chirag
 */
public interface CheckpointResultFormatter {
    String getResults(Collection<CheckpointResultBean> bean);
}
