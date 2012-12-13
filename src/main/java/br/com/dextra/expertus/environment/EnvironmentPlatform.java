package br.com.dextra.expertus.environment;

/**
 * This enum should be use only you want to run your tests on sauce environment.
 * This enum is the valid values for
 * SauceEnvironment.ENVIRONMENT_SAUCE_PLATFORM_PROPERTY system property.
 * 
 * @author leandro.guimaraes
 * 
 */
public enum EnvironmentPlatform {

	Linux("Linux"), Mac_10_6("Mac 10.6"), Mac_10_8("Mac 10.8"), Windows_2003("Windows 2003"), Windows_2008(
			"Windows 2008"), Windows_2012("Windows 2012");

	private String sauceLabsIdentifier;

	private EnvironmentPlatform(String sauceLabsIdentifier) {
		this.sauceLabsIdentifier = sauceLabsIdentifier;
	}

	protected String getSauceLabsIdentifier() {
		return this.sauceLabsIdentifier;
	}

}
