package br.com.dextra.expertus.environment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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

	@Override
	public WebDriver createDriver() {
		this.readEnvironmentProperties();

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities(this.browser, this.browserVersion,
				Platform.valueOf(this.platform.toUpperCase()));

		StringBuilder sauceURL = new StringBuilder("http://");
		sauceURL.append(this.sauceUsername);
		sauceURL.append(":");
		sauceURL.append(this.sauceKey);
		sauceURL.append("@ondemand.saucelabs.com:80/wd/hub");

		try {
			return new RemoteWebDriver(new URL(sauceURL.toString()), desiredCapabilities);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
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

		logger.info("Environment platform: " + this.platform);
		logger.info("Environment browser: " + this.browser);
		logger.info("Environment browser version: " + this.browserVersion);
		logger.info("Environment sauce username: " + this.sauceUsername);
		logger.info("Environment sauce key: " + this.sauceKey);

		if (!this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("All SauceCloudBeesEnvironment system properties must be defined.");
		}
	}

}
