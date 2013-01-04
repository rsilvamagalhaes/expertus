package br.com.dextra.expertus.environment;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class should be used when you want to run a selenium test using your
 * local webbrowser. This environment support the LocalEnvironmentBrowsers.
 * 
 * @author leandro.guimaraes
 */
public class LocalEnvironment extends Environment {

	private static final Logger logger = LoggerFactory.getLogger(LocalEnvironment.class);

	@Override
	public WebDriver createDriver() {
		this.readEnvironmentProperties();

		LocalEnvironmentBrowser localBrowser = LocalEnvironmentBrowser.valueOf(this.browser);
		switch (localBrowser) {
		case FIREFOX:
			return new FirefoxDriver();
		case CHROME:
			return new ChromeDriver();
		default:
			throw new IllegalArgumentException(this.browser + " is not supported by LocalEnvironment.");
		}
	}

	@Override
	public String getSessionTestId() {
		return "No session id to LocalEnvironment";
	}

	@Override
	protected void readEnvironmentProperties() {
		this.browser = System.getProperty(ENVIRONMENT_BROWSER_PROPERTY) != null ? System
				.getProperty(ENVIRONMENT_BROWSER_PROPERTY) : "FIREFOX";
		this.applicationHostSystemProperty = this.readEnvironmentHostProperty();
		this.applicationPortSystemProperty = this.readEnvironmentPortProperty();
		this.applicationSystemProperty = this.readEnvironmentApplicationProperty();

		logger.debug("Environment browser: " + this.browser);
		logger.debug("Environment application host: " + this.applicationHostSystemProperty);
		logger.debug("Environment application port: " + this.applicationPortSystemProperty);
		logger.debug("Environment application: " + this.applicationSystemProperty);

		if (!this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("You have to define browser system properties to LocalEnvironment.");
		}
	}

	@Override
	protected boolean isAllPropertiesOk() {
		return StringUtils.isNotEmpty(this.browser);
	}

}
