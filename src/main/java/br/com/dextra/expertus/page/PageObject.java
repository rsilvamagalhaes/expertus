package br.com.dextra.expertus.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This is a class that represents a PageObject. It's a page with some methods
 * can help you at your tests.
 * 
 * @author leandro.guimaraes
 */
public class PageObject {

	protected static final int DEFAULT_SLEEP_TIME = 2000;

	/**
	 * time to wait to check element
	 */
	protected static final int TIME_TO_WAIT = 100;

	/**
	 * max number to wait some element
	 */
	protected static final int MAX_ATTEMPT_TO_WAIT = 100;

	protected WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateTo(String url) {
		this.driver.navigate().to(url);
		this.waitToLoad();
	}

	public PageObject click(String cssSelector) {
		this.driver.findElement(By.cssSelector(cssSelector)).click();
		this.waitToLoad();
		return this;
	}

	public String getValueFromElement(String cssSelector) {
		return this.driver.findElement(By.cssSelector(cssSelector)).getAttribute("value");
	}

	public WebElement getElement(String cssSelector) {
		return this.driver.findElement(By.cssSelector(cssSelector));
	}

	public String getElementAttribute(String cssSelector, String attribute) {
		return this.driver.findElement(By.cssSelector(cssSelector)).getAttribute(attribute);
	}

	public String getElementContent(String cssSelector) {
		return this.driver.findElement(By.cssSelector(cssSelector)).getText();
	}

	public PageObject writeInputText(String cssSelector, String elementValue) {
		WebElement element = this.driver.findElement(By.cssSelector(cssSelector));
		element.clear();
		element.sendKeys(elementValue);

		return this;
	}

	public PageObject writeTextArea(String cssSelector, String elementValue) {
		return this.writeInputText(cssSelector, elementValue);
	}

	public void waitToLoad() {
		this.waitToLoad(DEFAULT_SLEEP_TIME);
	}

	protected void waitToLoad(int timeToSleep) {
		try {
			Thread.sleep(timeToSleep);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected void waitForElement(String cssSelector) {
		this.waitToLoad(TIME_TO_WAIT);

		int attempts = 1;

		while (attempts < MAX_ATTEMPT_TO_WAIT) {
			try {
				this.driver.findElement(By.cssSelector(cssSelector));
			} catch (NoSuchElementException nsee) {
			}
			attempts++;

			this.waitToLoad(TIME_TO_WAIT);
		}

		if (attempts >= MAX_ATTEMPT_TO_WAIT) {
			throw new TimedOutException(cssSelector + " element did not find.");
		}
	}
}
