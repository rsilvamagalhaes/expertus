package br.com.dextra.expertus.environment;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
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

		WebDriver driver = null;

		DesiredCapabilities capabilities = new DesiredCapabilities();
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setNoProxy("localhost");
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		LocalEnvironmentBrowser localBrowser = LocalEnvironmentBrowser.valueOf(this.browser);

		switch (localBrowser) {
		case FIREFOX:
			driver = new FirefoxDriver(capabilities);
			break;
		case CHROME:
			driver = new ChromeDriver(capabilities);
			break;
		default:
			throw new IllegalArgumentException(this.browser + " is not supported by LocalEnvironment.");
		}

		return driver;
	}

	@Override
	public String getSessionTestId() {
		return "No session id to LocalEnvironment";
	}

	@Override
	protected void readEnvironmentProperties() {
		this.browser = System.getProperty(ENVIRONMENT_BROWSER_PROPERTY) != null ? System
				.getProperty(ENVIRONMENT_BROWSER_PROPERTY) : "FIREFOX";

		logger.info("Environment browser: " + this.browser);

		if (!this.isAllPropertiesOk()) {
			throw new IllegalArgumentException("You have to define browser system properties to LocalEnvironment.");
		}
	}

	@Override
	protected boolean isAllPropertiesOk() {
		return StringUtils.isNotEmpty(this.browser);
	}

	@Override
	public void setSessionName(String sessionName) {
		logger.info("Session name: " + sessionName);
	}

}
