package com.williamssonoma.automationBaseClasses;

import org.apache.commons.lang.StringUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * TO get properties key/value While reading value First preference will be
 * System property if set before run
 */
public enum ApplicationProperties {
	/**
	 * <b>key</b>: <code>test.results.dir </code><br/>
	 * <b>value</b>: dir to place generated result files
	 */
	REPORT_DIR("test.results.dir"), JSON_REPORT_ROOT_DIR("json.report.root.dir"), JSON_REPORT_DIR("json.report.dir"),
	/**
	 * <b>key</b>: <code> selenium.screenshots.dir </code><br/>
	 * <b>value</b>: dir to place screen-shots
	 */
	SCREENSHOT_DIR("selenium.screenshots.dir"),
	/**
	 * <b>key</b>: <code> selenium.screenshots.relative.path </code><br/>
	 * <b>value</b>: screen-shots relative path for reporting
	 */
	SCREENSHOT_RELATIVE_PATH("selenium.screenshots.relative.path"),
	/**
	 * <b>key</b>: <code>selenium.failure.screenshots </code><br/>
	 * <b>value</b>: set this flag to 1 if you want to capture screen-shots for
	 * assertion/verification failures. Default value is 1
	 */
	FAILURE_SCREENSHOT("selenium.failure.screenshots"),
	/**
	 * <b>key</b>: <code>selenium.wait.timeout </code><br/>
	 * <b>value</b>: wait time to be used by WaitService}
	 */
	SELENIUM_WAIT_TIMEOUT("selenium.wait.timeout"),
	/**
	 * <b>key</b>: <code>selenium.auto.shutdown </code><br/>
	 * <b>value</b>: flag to auto shutdown selenium server
	 */
	SELENIUM_AUTO_SHUTDOWN("selenium.auto.shutdown"),

	/**
	 * <b>key</b>: <code> tng.context </code><br/>
	 * <b>value</b>: {@link ITestContext} object for current running
	 * thread/test-case.
	 *
	 */
	CURRENT_TEST_CONTEXT("tng.context"),
	/**
	 * <b>key</b>: <code> current.testcase.name </code><br/>
	 * <b>value</b>: name of the current running test case.
	 *
	 */
	CURRENT_TEST_NAME("current.testcase.name"),
	/**
	 * <b>key</b>: <code> current.testcase.desc </code><br/>
	 * <b>value</b>: description of the current running test case.
	 *
	 */
	CURRENT_TEST_DESCRIPTION("current.testcase.desc"),
	/**
	 * <b>key</b>: <code> current.testcase.result </code><br/>
	 * <b>value</b>: {@link ITestResult} object for the current running test
	 * case.
	 *
	 * @since 2.1.9
	 */
	CURRENT_TEST_RESULT("current.testcase.result"),
	/**
	 * <b>key</b>: <code> driver.name </code><br/>
	 * <b>value</b>: driver to be used, for instance firefoxDriver or
	 * firefoxRemoteDriver etc...
	 *
	 * @since 2.1.6
	 */
	DRIVER_NAME("driver.name"),
	/**
	 * <b>key</b>: <code> driver.resources.format </code><br/>
	 * <b>value</b>: file or directory to load driver specific resources, for
	 * instance driver specific locators. If you specified driver.name
	 * <code>androidDriver</code> or <code>androidRemoteDriver</code> you can
	 * specify resources for android driver by using
	 * <code>android.resources</code> key.
	 *
	 * @since 2.1.12
	 */
	DRIVER_RESOURCES_FORMAT("%s.resources"),
	/**
	 * <b>key</b>: <code> driver.init.retry.timeout </code><br/>
	 * <b>value</b>: duration in multiplication of 10 seconds for example 50.
	 *
	 * @since 2.1.9
	 */
	DRIVER_INIT_TIMEOUT("driver.init.retry.timeout"),

	/**
	 * <b>key</b>: <code> driver.additional.capabilities </code><br/>
	 * <b>value</b>: specify multiple additional capabilities as map that can
	 * applicable for any driver.
	 *
	 * @see {@link #DRIVER_CAPABILITY_PREFIX} to provide individual capability
	 *      <br/>
	 *      {@link #DRIVER_ADDITIONAL_CAPABILITIES_FORMAT} to provide driver
	 *      specific capability
	 */
	DRIVER_ADDITIONAL_CAPABILITIES("driver.additional.capabilities"),
	/**
	 * <b>key</b>: <code> driver.capabilities </code><br/>
	 * <b>value</b>: specify additional capability by name with this prefix that
	 * can applicable for any driver. For example,
	 * driver.capabilities.&lt;capabilityName&gt;=&lt;value&gt;
	 *
	 * @see {@link #DRIVER_ADDITIONAL_CAPABILITIES} to provide multiple
	 *      additional capabilities
	 */
	DRIVER_CAPABILITY_PREFIX("driver.capabilities"),

	/**
	 * <b>key</b>: <code> &lt;drivername&gt;.additional.capabilities </code>
	 * <br/>
	 * <b>value</b>: specify multiple additional capabilities as map that can
	 * applicable for specific driver.
	 *
	 * @see {@link #DRIVER_CAPABILITY_PREFIX} to provide single capability <br/>
	 *      {@link #DRIVER_ADDITIONAL_CAPABILITIES} to provide capability for
	 *      all drivers
	 */
	DRIVER_ADDITIONAL_CAPABILITIES_FORMAT("%s.additional.capabilities"),

	/**
	 * <b>key</b>: <code> &lt;drivername&gt;.capabilities </code> <br/>
	 * <b>value</b>: specify additional capability by name with this prefix that
	 * can applicable for specific driver. For example,
	 * &lt;drivername&gt;.capabilities.&lt;capabilityName&gt;=&lt;value&gt;
	 *
	 * @see {@link #DRIVER_CAPABILITY_PREFIX} to provide individual capability
	 *      <br/>
	 *      {@link #DRIVER_ADDITIONAL_CAPABILITIES_FORMAT} to provide driver
	 *      specific capability
	 */
	DRIVER_CAPABILITY_PREFIX_FORMAT("%s.capabilities"),

	/**
	 * <b>key</b>: <code> driverClass </code> <br/>
	 * <b>value</b>: capability name to specify driver class name.
	 */
	CAPABILITY_NAME_DRIVER_CLASS("driverClass"),

	/**
	 * <b>key</b>: <code> remote.server</code><br/>
	 * <b>value</b>: remote server url, which will be considered if configured
	 * remote driver.
	 *
	 * @since 2.1.6
	 */
	REMOTE_SERVER("remote.server"),

	/**
	 * <b>key</b>: <code> remote.port</code><br/>
	 * <b>value</b>: remote server port, which will be considered if configured
	 * remote driver.
	 *
	 * @since 2.1.6
	 */
	REMOTE_PORT("remote.port"),

	/**
	 * <b>key</b>: <code> env.baseurl </code><br/>
	 * <b>value</b>: base URL of AUT to be used.
	 */
	SELENIUM_BASE_URL("env.baseurl"), // selenium.browser.url

    /**
	 * <b>key</b>: <code>selenium.skip.autowait </code><br/>
	 * <b>value</b>:flag to skip auto wait for locator used in command, default
	 * value is false
	 */

	SKIP_AUTO_WAIT("selenium.skip.autowait"),
	/**
	 * <b>key</b>: <code> selenium.skip.autowait </code><br/>
	 * <b>value</b>: comma separated command list to be excluded from auto-wait.
	 */
	AUTO_WAIT_EXCLUDE_CMD("auto.wait.exclude.commands"),
	/**
	 * <b>key</b>: <code>auto.wait.include.commands </code><br/>
	 * <b>value</b>: additional comma separated command list to be included for
	 * auto-wait.
	 */
	AUTO_WAIT_INCLUDE_CMD("auto.wait.include.commands"),

	/**
	 * <b>key</b>: <code>reporter.log.exclude.commands </code><br/>
	 * <b>value</b>: additional comma separated command list to be excluded form
	 * selenium log in HTML report.
	 */
	REPORTER_LOG_EXCLUDE_CMD("reporter.log.exclude.commands"),

	/**
	 * <b>key</b>: <code>commands.execution.interval </code><br/>
	 * <b>value</b>: Set execution interval between two selenium commands.
	 */
	CMD_EXECUTION_INTERVAL("commands.execution.interval"),

	/**
	 * <b>key</b>: <code> webdriver.remote.session </code><br/>
	 * <b>value</b>: Set existing session of web-driver.
	 * <p>
	 * This feature can be used with remote web-driver
	 * </p>
	 */
	WEBDRIVER_REMOTE_SESSION("webdriver.remote.session"),
	/**
	 * <b>key</b>: <code> webdriver.chrome.driver </code><br/>
	 * <b>value</b>: Set Chrome driver path.
	 */
	CHROME_DRIVER_PATH("webdriver.chrome.driver"),
	/**
	 * <b>key</b>: <code> qc.testset.name </code><br/>
	 * <b>value</b>: Set QC test set name.
	 */
	QC_TS_NAME("qc.testset.name"),
	/**
	 * <b>key</b>: <code> qc.testset.folder.path</code><br/>
	 * <b>value</b>: Set QC test set folder path.
	 */
	QC_TS_FOLDER_PATH("qc.testset.folder.path"),
	/**
	 * <b>key</b>: <code> qc.testcase.folder.path</code><br/>
	 * <b>value</b>: Set QC test case folder path.
	 */
	QC_TC_FOLDER_PATH("qc.testcase.folder.path"),
	/**
	 * <b>key</b>: <code> qc.run.name</code><br/>
	 * <b>value</b>: Set QC run name.
	 */
	QC_RUN_NAME("qc.run.name"),
	/**
	 * <b>key</b>: <code> qc.timezone</code><br/>
	 * <b>value</b>: (Optional)Set QC server time - that will be used while
	 * setting date/time in run result.
	 */
	QC_TIMEZONE("qc.timezone"),
	/**
	 * <b>key</b>: <code> qc.runname.generator.impl</code><br/>
	 * <b>value</b>: qualified class name that implements
	 * com.qmetry.qaf.automation.integration.qc.QCRunNameGenerator interface. It
	 * can be used to generate name in custom format for auto-generated set and
	 * run name
	 */
	QC_RUN_NAME_GENERATOR("qc.runname.generator.impl"),
	/**
	 * <b>key</b>: <code> qc.user</code><br/>
	 * <b>value</b>: Set QC user name that will be used to access QC
	 */
	QC_USER("qc.user"),
	/**
	 * <b>key</b>: <code> qc.domain</code><br/>
	 * <b>value</b>: Set QC domain name that will be used to lookup QC project
	 */
	QC_DOMAIN("qc.domain"),
	/**
	 * <b>key</b>: <code> qc.project</code><br/>
	 * <b>value</b>: Set QC project name that where test case defined
	 */
	QC_PROJECT("qc.project"),
	/**
	 * <b>key</b>: <code> qc.pwd</code><br/>
	 * <b>value</b>: Set QC user password
	 */
	QC_PWD("qc.pwd"),
	/**
	 * <b>key</b>: <code> qc.service.url</code><br/>
	 * <b>value</b>: QC service URL, for example:
	 * http://10.20.30.252:8080/qcbin/
	 */
	QC_SERVICE_URL("qc.service.url"),
	/**
	 * <b>key</b>: <code> env.load.locales</code><br/>
	 * <b>value</b>: list of local names to be loaded
	 */
	LOAD_LOCALES("env.load.locales"),

	/**
	 * <b>key</b>: <code> env.default.locale</code><br/>
	 * <b>value</b>: local name from loaded locals that need to treated as
	 * default local
	 */
	DEFAULT_LOCALE("env.default.locale"), LOCALE_CHAR_ENCODING("locale.char.encoding"),


	INTEGRATION_PARAM_JIRA_SERVICE_URL("integration.param.jira.baseurl"),
	/**
	 * <b>key</b>: <code>integration.param.jira.password </code><br/>
	 * <b>value</b>: Set JIRA Password.
	 */
	INTEGRATION_PARAM_JIRA_PWD("integration.param.jira.pwd"),
	/**
	 * <b>key</b>: <code>integration.param.jira.project </code><br/>
	 * <b>value</b>: Set JIRA Projects.
	 */
	INTEGRATION_PARAM_JIRA_PROJECT("integration.param.jira.project"),
	/**
	 * <b>key</b>: <code>integration.param.jira.username </code><br/>
	 * <b>value</b>: Set JIRA Username.
	 */
	INTEGRATION_PARAM_JIRA_USER("integration.param.jira.user"),
	/**
	 * <b>key</b>: <code>step.provider.pkg </code><br/>
	 * <b>value</b>: one or more package name from where test-step should be
	 * loaded. When more than one package provided and same step defined in
	 * multiple package than it will be loaded based on the package order in
	 * "step.provider.pkg", the last package has highest priority.
	 */
	STEP_PROVIDER_PKG("step.provider.pkg"),
	/**
	 * <b>key</b>: <code>retry.count </code><br/>
	 * <b>value</b>: integer to specify how many times test should be retried on
	 * failure by default retry analyzer. This will not take effect if custom
	 * retry analyzer is provided using
	 * {@link ApplicationProperties#RETRY_ANALYZER retry.analyzer }
	 */
	RETRY_CNT("retry.count"),
	/**
	 * <b>key</b>: <code>retry.analyzer</code><br/>
	 * <b>value</b>: fully qualified class name that implements
	 * {@link IRetryAnalyzer}. Provide this property to use your custom retry
	 * analyzer.
	 */
	RETRY_ANALYZER("retry.analyzer"),

	DRY_RUN_MODE("dryrun.mode"),

	REST_CLIENT_FACTORY_IMPL("rest.client.impl");

	public String key;

	private ApplicationProperties(String key) {
		this.key = key;
	}

	/**
	 * @param defaultVal
	 *            optional
	 * @return
	 */
	public String getStringVal(String... defaultVal) {
		return System.getProperty(key, ConfigurationManager.getBundle().getString(key,
				(null != defaultVal) && (defaultVal.length > 0) ? defaultVal[0] : ""));
	}

	/**
	 * @param defaultVal
	 *            optional
	 * @return
	 */
	public int getIntVal(int... defaultVal) {
		try {
			int val = Integer.parseInt(getStringVal());
			return val;
		} catch (Exception e) {
			// just ignore
		}
		return (null != defaultVal) && (defaultVal.length > 0) ? defaultVal[0] : 0;
	}

	/**
	 * @param defaultVal
	 *            optional
	 * @return
	 */
	public boolean getBoolenVal(boolean... defaultVal) {
		try {
			String sVal = getStringVal().trim();
			boolean val = StringUtils.isNumeric(sVal) ? (Integer.parseInt(sVal) != 0) : Boolean.parseBoolean(sVal);
			return val;
		} catch (Exception e) {
			// just ignore
		}
		return (null != defaultVal) && (defaultVal.length > 0) && defaultVal[0];
	}

	public Object getObject(Object... defaultVal) {
		Object objToReturn = ConfigurationManager.getBundle().getObject(key);
		return null != objToReturn ? objToReturn
				: (null != defaultVal) && (defaultVal.length > 0) ? defaultVal[0] : null;
	}
}
