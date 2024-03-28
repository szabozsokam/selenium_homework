package seleniumhomework.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static WebDriver getBrowser(String browserName) {
		browserName = browserName.toLowerCase();

		if (browserName.equals("chrome")) {
			return new ChromeDriver();
		} else {
			return new FirefoxDriver();
		}
	}

}