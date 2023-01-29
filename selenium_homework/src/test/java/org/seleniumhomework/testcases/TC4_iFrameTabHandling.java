package org.seleniumhomework.testcases;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhomework.common.TestBase;
import org.seleniumhomework.pageobjects.guru99.GuruDemoPage;
import org.seleniumhomework.pageobjects.guru99.PopUpAd;
import org.seleniumhomework.pageobjects.guru99.SeleniumTutorialPage;

public class TC4_iFrameTabHandling extends TestBase {

	@Test
	public void testFrameTab() throws Exception {

		// go to page and give consent
		driver.manage().deleteAllCookies();
		driver.get("http://demo.guru99.com/test/guru99home");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		GuruDemoPage guruDemoPage = new GuruDemoPage(driver);
		driver.switchTo().frame(guruDemoPage.consentFrame);
		guruDemoPage.consentButton.click();
		driver.switchTo().defaultContent();
		String originalPage = driver.getWindowHandle();

		// locate image and click on it
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", guruDemoPage.iframe);
		driver.switchTo().frame(guruDemoPage.iframe);
		guruDemoPage.image.click();

		// verify page title and new tab
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		SeleniumTutorialPage seleniumPage = new SeleniumTutorialPage(driver);
		new WebDriverWait(driver, Duration.ofMinutes(1))
				.until(ExpectedConditions.visibilityOf(seleniumPage.consentButton));
		// wait for visibility of button
		WebElement joinNowButton = new WebDriverWait(driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(seleniumPage.joinNowButton));
		// assert: button is red (in real: orange)
		Assertions.assertEquals("rgb(251, 160, 53) !important", joinNowButton.getCssValue("background").toString());
		// assert: button is wide
		Assertions.assertEquals("100%", joinNowButton.getCssValue("width").toString());
	}
}
