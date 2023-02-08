package org.seleniumhomework.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	private static ChromeDriver getChromeInstance() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

}