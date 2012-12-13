package br.com.dextra.expertus;

import junit.framework.Assert;

import org.junit.Test;

import br.com.dextra.expertus.environment.EnvironmentType;
import br.com.dextra.expertus.environment.LocalEnvironment;
import br.com.dextra.expertus.environment.SauceCloudEnvironment;
import br.com.dextra.expertus.environment.SauceLocalEnvironment;

public class EnvironmentFactoryTest {

	@Test
	public void testLocal() {
		Assert.assertTrue(EnvironmentFactory.createEnvironment(EnvironmentType.local) instanceof LocalEnvironment);
	}

	@Test
	public void testSauceLocal() {
		Assert.assertTrue(EnvironmentFactory.createEnvironment(EnvironmentType.sauceLocal) instanceof SauceLocalEnvironment);
	}

	@Test
	public void testSauceCloud() {
		Assert.assertTrue(EnvironmentFactory.createEnvironment(EnvironmentType.sauceCloud) instanceof SauceCloudEnvironment);
	}

	@Test
	public void testLocalBySystemProperty() {
		System.setProperty(EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY, "local");
		Assert.assertTrue(EnvironmentFactory.createEnvironment() instanceof LocalEnvironment);
	}

	@Test
	public void testSauceLocalBySystemProperty() {
		System.setProperty(EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY, "sauceLocal");
		Assert.assertTrue(EnvironmentFactory.createEnvironment(EnvironmentType.sauceLocal) instanceof SauceLocalEnvironment);
	}

	@Test
	public void testSauceCloudBySystemProperty() {
		System.setProperty(EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY, "sauceCloud");
		Assert.assertTrue(EnvironmentFactory.createEnvironment(EnvironmentType.sauceCloud) instanceof SauceCloudEnvironment);
	}

}
