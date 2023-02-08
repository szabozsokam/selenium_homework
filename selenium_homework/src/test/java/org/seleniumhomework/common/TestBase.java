package org.seleniumhomework.common;

import java.time.Duration;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

public class TestBase {

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}
}
