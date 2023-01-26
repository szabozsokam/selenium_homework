package org.seleniumhomework.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static WebDriver getBrowser(String browserName) {
		browserName = browserName.toLowerCase();

		if (browserName.equals("chrome")) {
			return getChromeInstance();
		} else {
			return getFFInstance();
		}
	}

	private static FirefoxDriver getFFInstance() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
		return new FirefoxDriver();
	}

	private static ChromeDriver getChromeInstance() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		return new ChromeDriver();
	}

}