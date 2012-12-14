package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;

/**
 * This class should be used when you want to run a test on SauceLabs
 * environment at a cloudbees environment. When you configure your jenkins
 * instance you have to turn on SauceLabs plugin and select the WebDriver
 * information.
 * 
 * @author leandro.guimaraes
 */
public class SauceCloudBeesEnvironment extends SauceEnvironment {

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

		if (this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("All SauceCloudBeesEnvironment system properties must be defined.");
		}
	}

}
