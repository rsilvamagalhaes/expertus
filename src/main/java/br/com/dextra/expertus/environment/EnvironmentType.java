package br.com.dextra.expertus.environment;

import br.com.dextra.expertus.EnvironmentFactory;

/**
 * This enum is the valid values for
 * EnvironmentFactory.ENVIRONMENT_TYPE_PROPERTY system property.
 * 
 * @see EnvironmentFactory
 * @author leandro.guimaraes
 * 
 */
public enum EnvironmentType {
	local, sauceLocal, sauceCloud;
}
