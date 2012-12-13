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

}
