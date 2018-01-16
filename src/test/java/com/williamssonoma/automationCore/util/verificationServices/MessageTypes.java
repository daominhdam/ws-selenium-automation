package com.williamssonoma.automationCore.util.verificationServices;

public enum  MessageTypes {
    Pass, Fail, Info, TestStep, TestStepPass, TestStepFail, Warn;

	/**
	 * Get message in HTML format for the type.
	 * 
	 * @param message
	 *            : message to be formated
	 * @return HTML formated message for the type
	 */
	public String formatMessage(String message) {
		return String.format(MSG_FORMAT, name().toLowerCase(), name().toLowerCase(), name().charAt(0), message);
	}

	/**
	 * Get message in text format for this type.
	 * 
	 * @param message
	 *            : message to be formated
	 * @return Text formated message for the type
	 */
	public String formatText(String message) {
		return String.format("%s: %s", name(), message);

	}

	private static final String MSG_FORMAT = "<div  class=\"%s\"><span class=\"%s-label\">%s </span>%s</div>";

	public boolean isFailure() {
		return name().toUpperCase().contains("FAIL");
	}

}