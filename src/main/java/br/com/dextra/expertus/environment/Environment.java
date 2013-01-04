package br.com.dextra.expertus.environment;

import org.apache.commons.lang.StringUtils;
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

	/**
	 * The system property to define the host from this environment. It should
	 * be setted because when you are on saucelabs there is a SSH tunnel between
	 * saucelabs and cloudbees. You should configure this system property when
	 * you want to use LocalEnvironment or SauceLocalEnvironment.
	 */
	protected static final String ENVIRONMENT_HOST = "expertus.environment.host";

	/**
	 * The system property to define the port from this environment. It should
	 * be setted because when you are on saucelabs there is a SSH tunnel between
	 * saucelabs and cloudbees. You should configure this system property when
	 * you want to use LocalEnvironment or SauceLocalEnvironment.
	 */
	protected static final String ENVIRONMENT_PORT = "expertus.environment.port";

	/**
	 * The system property to define the application id from this environment.
	 * You have to set this property everytime.
	 */
	protected static final String ENVIRONMENT_APPLICATION = "expertus.environment.application";

	protected String browser;

	protected WebDriver driver;

	protected String applicationHostSystemProperty;

	protected String applicationPortSystemProperty;

	protected String applicationSystemProperty;

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

	public String getEnvironmentUrlBase() {
		StringBuilder stringBuilder = new StringBuilder(this.applicationHostSystemProperty);
		stringBuilder.append(":");
		stringBuilder.append(this.applicationPortSystemProperty);

		if (StringUtils.isNotEmpty(this.applicationSystemProperty)) {
			stringBuilder.append("/");
			stringBuilder.append(this.applicationSystemProperty);
		}

		return stringBuilder.toString();
	}

	protected String readEnvironmentApplicationProperty() {
		return System.getProperty(ENVIRONMENT_APPLICATION) != null ? System.getProperty(ENVIRONMENT_APPLICATION) : "";
	}

	protected String readEnvironmentPortProperty() {
		return System.getProperty(ENVIRONMENT_PORT) != null ? System.getProperty(ENVIRONMENT_PORT) : "8080";
	}

	protected String readEnvironmentHostProperty() {
		return System.getProperty(ENVIRONMENT_HOST) != null ? System.getProperty(ENVIRONMENT_HOST) : "localhost";
	}

}
