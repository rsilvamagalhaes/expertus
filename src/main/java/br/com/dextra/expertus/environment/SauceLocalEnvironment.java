package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;

public class SauceLocalEnvironment extends SauceEnvironment {

	@Override
	public WebDriver createDriver() {
		return null;
	}

}
