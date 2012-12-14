package br.com.dextra.expertus;

import br.com.dextra.expertus.environment.Environment;
import br.com.dextra.expertus.environment.EnvironmentType;
import br.com.dextra.expertus.environment.InvalidEnvironmentTypeException;
import br.com.dextra.expertus.environment.LocalEnvironment;
import br.com.dextra.expertus.environment.SauceCloudBeesEnvironment;
import br.com.dextra.expertus.environment.SauceLocalEnvironment;

/**
 * A factory to create a new environment. See the EnvironmentType class to get
 * all options.
 * 
 * @see EnvironmentType
 * 
 * @author leandro.guimaraes
 */
public class EnvironmentFactory {

	/**
	 * System property which define environment type. Valid values at
	 * EnvironmentType.
	 */
	public static String ENVIRONMENT_TYPE_PROPERTY = "expertus.environment.type";

	public static Environment createEnvironment(EnvironmentType type) {
		return getNewEnvironment(type);
	}

	/**
	 * This method create an environment according to the
	 * ENVIRONMENT_TYPE_PROPERTY system property value. You can check the valid
	 * values at EnvironmentType.
	 * 
	 * @see EnvironmentType
	 * @return A new instance of an Environment according to to the
	 *         ENVIRONMENT_TYPE_PROPERTY system property value.
	 */
	public static Environment createEnvironment() {
		String type = System.getProperty(ENVIRONMENT_TYPE_PROPERTY);
		return getNewEnvironment(EnvironmentType.valueOf(type));
	}

	private static Environment getNewEnvironment(EnvironmentType type) {
		switch (type) {
		case local:
			return new LocalEnvironment();
		case sauceLocal:
			return new SauceLocalEnvironment();
		case sauceCloudbees:
			return new SauceCloudBeesEnvironment();
		default:
			throw new InvalidEnvironmentTypeException(type);
		}
	}

}
