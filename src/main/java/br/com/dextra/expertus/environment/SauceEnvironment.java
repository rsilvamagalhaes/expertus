package br.com.dextra.expertus.environment;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This is an abstract class that represents a test environment at SauceLabs.
 *
 * @author leandro.guimaraes
 *
 */
public abstract class SauceEnvironment extends Environment {

	protected static final String BROWSER_VERSION_CAPABILITY = "version";

	protected static final String PLATFORM_CAPABILITY = "platform";

	protected String platform;

	protected String browserVersion;

	protected String sauceUsername;

	protected String sauceKey;

	@Override
	public WebDriver createDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionTestId() {
		return ((RemoteWebDriver) this.driver).getSessionId().toString();
	}

	protected boolean isAllPropertiesOk() {
		return !(StringUtils.isEmpty(this.platform) || StringUtils.isEmpty(this.browser)
				|| StringUtils.isEmpty(this.browserVersion) || StringUtils.isEmpty(this.sauceUsername) || StringUtils
					.isEmpty(this.sauceKey));
	}

}
