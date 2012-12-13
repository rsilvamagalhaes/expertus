package br.com.dextra.expertus.environment;

import org.openqa.selenium.WebDriver;

public abstract class Environment {

	private WebDriver driver;

	public abstract WebDriver createDriver();

	public WebDriver getDriver() {
		return this.driver;
	}

}
