package br.com.dextra.expertus.environment;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.dextra.expertus.EnvironmentFactory;

public class LocalEnvironmentTest {

	private boolean runTests = false;

	public LocalEnvironmentTest() {
		runTests = StringUtils.isNotEmpty(System.getProperty("expertus.environment.local.test"));

		System.setProperty(EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY, EnvironmentType.local.toString());
	}

	@Test
	public void testCreateFirefoxBrowser() {
		if (runTests) {
			System.setProperty(Environment.ENVIRONMENT_BROWSER_PROPERTY, LocalEnvironmentBrowser.FIREFOX.toString());
			WebDriver firefoxDriver = EnvironmentFactory.createEnvironment().createDriver();
			Assert.assertTrue(firefoxDriver instanceof FirefoxDriver);
			firefoxDriver.close();
		}
	}

	@Test
	public void testApplicationUrlDefaultValues() {
		Environment localEnvironment = EnvironmentFactory.createEnvironment();
		localEnvironment.readEnvironmentProperties();

		Assert.assertEquals("localhost:8080", localEnvironment.getEnvironmentUrlBase());
	}

	@Test
	public void testApplicationUrl() {
		String applicationHost = "192.168.1.1";
		String applicationPort = "445";
		String applicationId = "myApplication";

		System.setProperty(Environment.ENVIRONMENT_HOST, applicationHost);
		System.setProperty(Environment.ENVIRONMENT_PORT, applicationPort);
		System.setProperty(Environment.ENVIRONMENT_APPLICATION, applicationId);

		Environment localEnvironment = EnvironmentFactory.createEnvironment();
		localEnvironment.readEnvironmentProperties();

		Assert.assertEquals(applicationHost + ":" + applicationPort + "/" + applicationId,
				localEnvironment.getEnvironmentUrlBase());
	}
}
