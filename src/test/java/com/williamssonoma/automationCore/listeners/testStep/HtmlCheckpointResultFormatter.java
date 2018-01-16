package com.williamssonoma.automationCore.listeners.testStep;

import java.util.Collection;

import com.williamssonoma.automationCore.util.verificationServices.CheckpointResultBean;
import com.williamssonoma.automationCore.util.verificationServices.MessageTypes;

/**
 * com.qmetry.qaf.automation.ui.selenium.HtmlCheckpointResultFormatter.java
 * 
 * @author chirag
 */
public class  HtmlCheckpointResultFormatter implements CheckpointResultFormatter {
    private static final String SEC_Header = "<div style=\"display:block;margin-left:10px;\">";
	private static final String SEC_Footer = "</div>";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qmetry.qaf.automation.ui.selenium.CheckpointResultFormatter#
	 * getResults (java.util.Collection)
	 */
	@Override
	public String getResults(Collection<CheckpointResultBean> bean) {
		StringBuilder sb = new StringBuilder(SEC_Header);
		for (CheckpointResultBean checkpointResultBean : bean) {
			String msg = checkpointResultBean.getMessage();
			sb.append(MessageTypes.valueOf(checkpointResultBean.getType()).formatMessage(msg));
			if (!checkpointResultBean.getSubCheckPoints().isEmpty()) {
				sb.append(getResults(checkpointResultBean.getSubCheckPoints()));
			}
		}
		sb.append(SEC_Footer);
		return sb.toString();
	}

	private String formatScreenshot(String sc) {
		return " <a href=\"" + sc + "\" target=\"_blank\">[View Screenshot]</a> ";
	}
}