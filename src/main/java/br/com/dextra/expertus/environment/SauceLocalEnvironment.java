package br.com.dextra.expertus.environment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should be used when you want to run a test on SauceLabs
 * environment but at your own machine. You have to define SauceLocalEnvironment
 * system variables (ENVIRONMENT_SAUCE_PLATFORM_PROPERTY,
 * ENVIRONMENT_SAUCE_BROWSER_PROPERTY,
 * ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY,
 * ENVIRONMENT_SAUCE_USERNAME_PROPERTY and ENVIRONMENT_SAUCE_KEY_PROPERTY).
 * 
 * @author leandro.guimaraes
 */
public class SauceLocalEnvironment extends SauceEnvironment {

	private static final Logger logger = LoggerFactory.getLogger(SauceLocalEnvironment.class);

	/**
	 * The system property used to define the environment platform. You can
	 * check the valid values at https://saucelabs.com/docs/browsers.
	 */
	protected static final String ENVIRONMENT_SAUCE_PLATFORM_PROPERTY = "expertus.environment.sauce.platform";

	/**
	 * The system property used to define the browser version that you want. You
	 * can check the valid values at https://saucelabs.com/docs/browsers.
	 */
	protected static final String ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY = "expertus.environment.sauce.browser.version";

	/**
	 * The system property used to define your username on saucelabs.com.
	 */
	protected static final String ENVIRONMENT_SAUCE_USERNAME_PROPERTY = "expertus.environment.sauce.username";

	/**
	 * The system property used to define your key on saucelabs.com.
	 */
	protected static final String ENVIRONMENT_SAUCE_KEY_PROPERTY = "expertus.environment.sauce.key";

	@Override
	public WebDriver createDriver() {
		this.readEnvironmentProperties();

		DesiredCapabilities desiredCapabilities = null;
		if ("firefox".equals(this.browser.trim().toLowerCase())) {
			desiredCapabilities = DesiredCapabilities.firefox();
		} else if ("chrome".equals(this.browser.trim().toLowerCase())) {
			desiredCapabilities = DesiredCapabilities.chrome();
		} else if ("internetexplorer".equals(this.browser.trim().toLowerCase())) {
			desiredCapabilities = DesiredCapabilities.internetExplorer();
		} else {
			throw new IllegalArgumentException(this.browser + " is not a valid browser.");
		}
		if (!"chrome".equals(this.browser.trim().toLowerCase())) {
			desiredCapabilities.setCapability(BROWSER_VERSION_CAPABILITY, this.browserVersion);
		}
		desiredCapabilities.setCapability(PLATFORM_CAPABILITY, this.platform);

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
	 * The properties will be read at System.getProperty();
	 */
	@Override
	protected void readEnvironmentProperties() {
		this.platform = System.getProperty(ENVIRONMENT_SAUCE_PLATFORM_PROPERTY);
		this.browser = System.getProperty(ENVIRONMENT_BROWSER_PROPERTY);
		this.browserVersion = System.getProperty(ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY);
		this.sauceUsername = System.getProperty(ENVIRONMENT_SAUCE_USERNAME_PROPERTY);
		this.sauceKey = System.getProperty(ENVIRONMENT_SAUCE_KEY_PROPERTY);
		this.applicationHostSystemProperty = this.readEnvironmentHostProperty();
		this.applicationPortSystemProperty = this.readEnvironmentPortProperty();
		this.applicationSystemProperty = this.readEnvironmentApplicationProperty();

		logger.info("Environment platform: " + this.platform);
		logger.info("Environment browser: " + this.browser);
		logger.info("Environment browser version: " + this.browserVersion);
		logger.info("Environment sauce username: " + this.sauceUsername);
		logger.info("Environment sauce key: " + this.sauceKey);
		logger.info("Environment application host: " + this.applicationHostSystemProperty);
		logger.info("Environment application port: " + this.applicationPortSystemProperty);
		logger.info("Environment application: " + this.applicationSystemProperty);

		if (!this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("You have to define all SauceLocalEnvironment system properties.");
		}
	}

}
