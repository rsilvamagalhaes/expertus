package br.com.dextra.expertus;

import br.com.dextra.expertus.environment.Environment;
import br.com.dextra.expertus.environment.EnvironmentType;
import br.com.dextra.expertus.environment.InvalidEnvironmentTypeException;
import br.com.dextra.expertus.environment.LocalEnvironment;
import br.com.dextra.expertus.environment.SauceCloudEnvironment;
import br.com.dextra.expertus.environment.SauceLocalEnvironment;

/**
 * A factory to create a new environment. See the EnvironmentType class to get all options.
 * 
 * @see EnvironmentType
 *
 * @author leandro.guimaraes
 */
public class EnvironmentFactory {

	public static Environment createEnvironment(EnvironmentType type) {
		switch (type) {
		case local:
			return new LocalEnvironment();
		case sauceLocal:
			return new SauceLocalEnvironment();
		case sauceCloud:
			return new SauceCloudEnvironment();
		default:
			throw new InvalidEnvironmentTypeException(type);
		}
	}

}
