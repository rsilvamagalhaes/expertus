package br.com.dextra.expertus.environment;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.dextra.expertus.EnvironmentFactory;

public class SauceLocalEnvironmentTest {

	private WebDriver sauceBrowser;

	public SauceLocalEnvironmentTest() {
		System.setProperty(EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY, EnvironmentType.sauceLocal.toString());
	}

	@Test
	public void testCreateLinuxFirefox() {
		String browser = "firefox";
		String browserVersion = "17";
		String platform = Platform.LINUX.toString();

		this.setSystemPropertiesValues(browser, browserVersion, platform.toString());
		Capabilities sauceBrowserCapabilities = this.createBrowserAndGetCapabilities();
		this.doAsserts(browser, browserVersion + ".0", platform, sauceBrowserCapabilities);
	}

	@Test
	public void testCreateInternetExplorer() {
		String browser = "internetexplorer";
		String browserVersion = "8";
		String platform = Platform.WINDOWS.toString();

		this.setSystemPropertiesValues(browser, browserVersion, platform.toString());
		Capabilities sauceBrowserCapabilities = this.createBrowserAndGetCapabilities();
		this.doAsserts("internet explorer", browserVersion, platform, sauceBrowserCapabilities);
	}

	@Test
	public void testCreateLinuxChrome() {
		String browser = "chrome";
		// this property should be not setted on chrome + linux env @ saucelabs
		String browserVersion = "0";
		String platform = Platform.LINUX.toString();

		this.setSystemPropertiesValues(browser, browserVersion, platform.toString());
		Capabilities sauceBrowserCapabilities = this.createBrowserAndGetCapabilities();
		this.doAsserts(browser, platform, sauceBrowserCapabilities);
	}

	@Test
	public void testApplicationUrl() {
		String applicationHost = "192.168.1.1";
		String applicationPort = "445";
		String applicationId = "myApplication";

		System.setProperty(Environment.ENVIRONMENT_HOST, applicationHost);
		System.setProperty(Environment.ENVIRONMENT_PORT, applicationPort);
		System.setProperty(Environment.ENVIRONMENT_APPLICATION, applicationId);

		this.setSystemPropertiesValues("firefox", "17", Platform.LINUX.toString());

		Environment sauceLocalEnvironment = EnvironmentFactory.createEnvironment();
		sauceLocalEnvironment.readEnvironmentProperties();

		Assert.assertEquals(applicationHost + ":" + applicationPort + "/" + applicationId,
				sauceLocalEnvironment.getEnvironmentUrlBase());
	}

	private void setSystemPropertiesValues(String browser, String browserVersion, String platform) {
		System.setProperty(Environment.ENVIRONMENT_BROWSER_PROPERTY, browser);
		System.setProperty(SauceLocalEnvironment.ENVIRONMENT_SAUCE_BROWSER_VERSION_PROPERTY, browserVersion);
		System.setProperty(SauceLocalEnvironment.ENVIRONMENT_SAUCE_PLATFORM_PROPERTY, platform);
		System.setProperty(SauceLocalEnvironment.ENVIRONMENT_SAUCE_USERNAME_PROPERTY, "cloudbees_dextra-con");
		System.setProperty(SauceLocalEnvironment.ENVIRONMENT_SAUCE_KEY_PROPERTY, "b6f5241c-0038-404a-81d6-7bd848b8e56b");
	}

	private Capabilities createBrowserAndGetCapabilities() {
		Environment sauceEnvironment = EnvironmentFactory.createEnvironment();
		this.sauceBrowser = sauceEnvironment.createDriver();
		return ((RemoteWebDriver) sauceBrowser).getCapabilities();
	}

	private void doAsserts(String browser, String browserVersion, String platform, Capabilities sauceBrowserCapabilities) {
		try {
			Assert.assertEquals(platform, sauceBrowserCapabilities.getCapability(SauceEnvironment.PLATFORM_CAPABILITY)
					.toString());
			Assert.assertEquals(browser, sauceBrowserCapabilities.getCapability("browserName"));
			Assert.assertEquals(browserVersion,
					sauceBrowserCapabilities.getCapability(SauceEnvironment.BROWSER_VERSION_CAPABILITY));
		} finally {
			if (this.sauceBrowser != null) {
				this.sauceBrowser.close();
			}
		}
	}

	private void doAsserts(String browser, String platform, Capabilities sauceBrowserCapabilities) {
		try {
			Assert.assertEquals(platform, sauceBrowserCapabilities.getCapability(SauceEnvironment.PLATFORM_CAPABILITY)
					.toString());
			Assert.assertEquals(browser, sauceBrowserCapabilities.getCapability("browserName"));
		} finally {
			if (this.sauceBrowser != null) {
				this.sauceBrowser.close();
			}
		}
	}

}
