package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;

/**
 * Environment is an abstract class that represents a test environment. It has
 * the objects that you need to implement your selenium tests.
 * 
 * @author leandro.guimaraes
 */
public abstract class Environment {

	/**
	 * The system property used to define the browser that you want. You can
	 * check the valid values at https://saucelabs.com/docs/browsers. You should
	 * configure this system property when you want to use LocalEnvironment or
	 * SauceLocalEnvironment.
	 */
	protected static final String ENVIRONMENT_BROWSER_PROPERTY = "expertus.environment.browser";

	protected String browser;

	protected WebDriver driver;

	public abstract WebDriver createDriver();

	public WebDriver getDriver() {
		return this.driver;
	}

	/**
	 * Every SauceEnvironment specialization must implements how the properties
	 * should be read.
	 */
	protected abstract void readEnvironmentProperties();

	/**
	 * This method checks if all properties are not empty.
	 * 
	 * @return true if all properties is not empty.
	 */
	protected abstract boolean isAllPropertiesOk();

	public abstract String getSessionTestId();

	/**
	 * This method define a session name to identify your test.
	 */
	public abstract void setSessionName(String sessionName);

}
