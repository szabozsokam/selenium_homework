package java.org.seleniumhomework.testcases;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.org.seleniumhomework.common.TestBase;
import java.org.seleniumhomework.pageobjects.guru99.GuruDemoPage;
import java.org.seleniumhomework.pageobjects.guru99.PopUpAd;
import java.org.seleniumhomework.pageobjects.guru99.SeleniumTutorialPage;

public class TC4_FrameTabHandling extends TestBase {

	@Test
	public void testFrameTab() throws Exception {

		// go to page and give consent
		driver.manage().deleteAllCookies();
		driver.get("http://demo.guru99.com/test/guru99home");
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gdpr-consent-notice")));
		GuruDemoPage guruDemoPage = new GuruDemoPage(driver);
		guruDemoPage.consentButton.click();
		driver.switchTo().defaultContent();
		String originalPage = driver.getWindowHandle();

		// locate image and click on it
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", guruDemoPage.iframe);
		driver.switchTo().frame(guruDemoPage.iframe);
		guruDemoPage.image.click();

		// verify page title and new tab
		new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> browserTabs = driver.getWindowHandles();
		for (String tab : browserTabs) {
			if (!tab.equals(originalPage)) {
				driver.switchTo().window(tab);
				break;
			}
		}
		String title = "Selenium Live Project: FREE Real Time Project for Practice";
		if (!driver.getTitle().equals(title)) {
			throw new Exception("Guru99 live project page did not load");
		}

		// close tab and switch to main window
		driver.close();
		driver.switchTo().window(originalPage);

		// click on Selenium link
		new Actions(driver).keyDown(Keys.HOME).build().perform();
		guruDemoPage.hoverAndClick(guruDemoPage.testingMenuItem, guruDemoPage.seleniumMenuItem);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// verify Join Now button
		if (driver.getCurrentUrl().equals("https://demo.guru99.com/test/guru99home/#google_vignette")) {
			PopUpAd ad = new PopUpAd(driver);
			driver.switchTo().frame(ad.outerFrame);
			driver.switchTo().frame(ad.innerFrame);
			ad.closeButton.click();
			driver.switchTo().defaultContent();
		}
		new WebDriverWait(driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.urlToBe("https://www.guru99.com/selenium-tutorial.html"));
		SeleniumTutorialPage seleniumPage = new SeleniumTutorialPage(driver);
		new WebDriverWait(driver, Duration.ofMinutes(1))
				.until(ExpectedConditions.visibilityOf(seleniumPage.consentButton));
		// wait for visibility of button
		WebElement joinNowButton = new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(seleniumPage.joinNowButton));
		// assert: button is red (in real: orange)
		Assertions.assertTrue(joinNowButton.getCssValue("background").contains("rgb(251, 160, 53)"));
		// assert: button is wide
		Assertions.assertEquals("100%", joinNowButton.getCssValue("width"));
	}
}
