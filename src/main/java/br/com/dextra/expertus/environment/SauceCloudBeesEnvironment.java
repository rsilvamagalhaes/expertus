package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should be used when you want to run a test on SauceLabs
 * environment at a cloudbees environment. When you configure your jenkins
 * instance you have to turn on SauceLabs plugin and select the WebDriver
 * information.
 * 
 * @author leandro.guimaraes
 */
public class SauceCloudBeesEnvironment extends SauceEnvironment {

	private static final Logger logger = LoggerFactory.getLogger(SauceCloudBeesEnvironment.class);

	/**
	 * This is the value of environment property. It will be set by cloudbees
	 * platform.
	 */
	private static final String ENVIRONMENT_SAUCE_PLATFORM_PROPERTY = "SELENIUM_PLATFORM";

	/**
	 * This is the value of environment property. It will be set by cloudbees
	 * platform.
	 */
	private static final String ENVIRONMENT_SAUCE_BROWSER_PROPERTY = "SELENIUM_BROWSER";

	/**
	 * This is the value of environment property. It will be set by cloudbees
	 * platform.
	 */
	private static final String ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY = "SELENIUM_VERSION";

	/**
	 * The system property used to define your username on saucelabs.com. It
	 * will be set by cloudbees platform.
	 */
	private static final String ENVIRONMENT_SAUCE_USERNAME_PROPERTY = "SAUCE_USER_NAME";

	/**
	 * The system property used to define your key on saucelabs.com. It will be
	 * set by cloudbees platform.
	 */
	private static final String ENVIRONMENT_SAUCE_KEY_PROPERTY = "SAUCE_API_KEY";

	/**
	 * The system property used to define the application host on saucelabs.com.
	 * It will be set by cloudbees platform.
	 */
	private static final String ENVIRONMENT_SAUCE_ONDEMAND_HOST_PROPERTY = "SAUCE_ONDEMAND_HOST";

	/**
	 * The system property used to define the application port on saucelabs.com.
	 * It will be set by cloudbees platform.
	 */
	private static final String ENVIRONMENT_SAUCE_ONDEMAND_PORT_PROPERTY = "SAUCE_ONDEMAND_PORT";

	@Override
	public WebDriver createDriver() {
		return null;
	}

	/**
	 * The properties will be read at System.getenv();
	 */
	@Override
	protected void readEnvironmentProperties() {
		this.platform = System.getenv(ENVIRONMENT_SAUCE_PLATFORM_PROPERTY);
		this.browser = System.getenv(ENVIRONMENT_SAUCE_BROWSER_PROPERTY);
		this.browserVersion = System.getenv(ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY);
		this.sauceUsername = System.getenv(ENVIRONMENT_SAUCE_USERNAME_PROPERTY);
		this.sauceKey = System.getenv(ENVIRONMENT_SAUCE_KEY_PROPERTY);
		this.applicationHostSystemProperty = System.getenv(ENVIRONMENT_SAUCE_ONDEMAND_HOST_PROPERTY);
		this.applicationPortSystemProperty = System.getenv(ENVIRONMENT_SAUCE_ONDEMAND_PORT_PROPERTY);
		this.applicationSystemProperty = this.readEnvironmentApplicationProperty();

		logger.debug("Environment platform: " + this.platform);
		logger.debug("Environment browser: " + this.browser);
		logger.debug("Environment browser version: " + this.browserVersion);
		logger.debug("Environment sauce username: " + this.sauceUsername);
		logger.debug("Environment sauce key: " + this.sauceKey);
		logger.debug("Environment application host: " + this.applicationHostSystemProperty);
		logger.debug("Environment application port: " + this.applicationPortSystemProperty);
		logger.debug("Environment application: " + this.applicationSystemProperty);

		if (this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("All SauceCloudBeesEnvironment system properties must be defined.");
		}
	}

}
