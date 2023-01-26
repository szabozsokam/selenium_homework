package org.seleniumhomework.common;

import java.time.Duration;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {

	public static String sessionId;
	public static WebDriver driver;

	@Rule
	public final TestName name = new TestName() {
		public String getMethodName() {
			return String.format("%s", super.getMethodName());
		}
	};

	@BeforeEach
	public void setUp() throws Exception {

		driver = BrowserFactory.getBrowser("chrome");
		sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
