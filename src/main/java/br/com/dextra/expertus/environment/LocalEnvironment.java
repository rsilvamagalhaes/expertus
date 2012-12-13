package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;

public class LocalEnvironment extends Environment {

	@Override
	public WebDriver createDriver() {
		return null;
	}

}
