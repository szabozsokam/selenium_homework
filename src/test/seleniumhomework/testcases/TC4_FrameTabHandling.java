package seleniumhomework.testcases;

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
import seleniumhomework.common.TestBase;
import seleniumhomework.pageobjects.guru99.GuruDemoPage;
import seleniumhomework.pageobjects.guru99.PopUpAd;
import seleniumhomework.pageobjects.guru99.SeleniumTutorialPage;

public class TC4_FrameTabHandling extends TestBase {

	@Test
	public void testFrameTab() throws Exception {

		// go to page and give consent
		TestBase.driver.manage().deleteAllCookies();
		TestBase.driver.get("http://demo.guru99.com/test/guru99home");
		new WebDriverWait(TestBase.driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("gdpr-consent-notice")));
		GuruDemoPage guruDemoPage = new GuruDemoPage(TestBase.driver);
		guruDemoPage.consentButton.click();
		TestBase.driver.switchTo().defaultContent();
		String originalPage = TestBase.driver.getWindowHandle();

		// locate image and click on it
		JavascriptExecutor js = (JavascriptExecutor) TestBase.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", guruDemoPage.iframe);
		TestBase.driver.switchTo().frame(guruDemoPage.iframe);
		guruDemoPage.image.click();

		// verify page title and new tab
		new WebDriverWait(TestBase.driver, Duration.ofSeconds(2)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> browserTabs = TestBase.driver.getWindowHandles();
		for (String tab : browserTabs) {
			if (!tab.equals(originalPage)) {
				TestBase.driver.switchTo().window(tab);
				break;
			}
		}
		String title = "Selenium Live Project: FREE Real Time Project for Practice";
		if (!TestBase.driver.getTitle().equals(title)) {
			throw new Exception("Guru99 live project page did not load");
		}

		// close tab and switch to main window
		TestBase.driver.close();
		TestBase.driver.switchTo().window(originalPage);

		// click on Selenium link
		new Actions(TestBase.driver).keyDown(Keys.HOME).build().perform();
		guruDemoPage.hoverAndClick(guruDemoPage.testingMenuItem, guruDemoPage.seleniumMenuItem);
		TestBase.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		// close ad popup (with changing content)
		if (TestBase.driver.getCurrentUrl().equals("https://demo.guru99.com/test/guru99home/#google_vignette")) {
			PopUpAd ad = new PopUpAd(TestBase.driver);
			TestBase.driver.switchTo().frame(ad.outerFrame);
			if (!driver.findElements(By.id("dismiss-button")).isEmpty()) {
				ad.xButton.click();
			} else {
				TestBase.driver.switchTo().frame(ad.innerFrame);
				if (!driver.findElements(By.xpath("//span[normalize-space(text())='Close']")).isEmpty()) {
					ad.closeButton.click();
				}
			}
			TestBase.driver.switchTo().defaultContent();
		}

		// verify Join Now button
		new WebDriverWait(TestBase.driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.urlToBe("https://www.guru99.com/selenium-tutorial.html"));
		SeleniumTutorialPage seleniumPage = new SeleniumTutorialPage(TestBase.driver);
		new WebDriverWait(TestBase.driver, Duration.ofSeconds(30))
				.until(ExpectedConditions.visibilityOf(seleniumPage.consentFrame));
		TestBase.driver.switchTo().frame(seleniumPage.consentFrame);
		seleniumPage.consentButton.click();
		TestBase.driver.switchTo().defaultContent();
		// wait for visibility of button
		WebElement joinNowButton = new WebDriverWait(TestBase.driver, Duration.ofMinutes(2))
				.until(ExpectedConditions.visibilityOf(seleniumPage.joinNowButton));
		// assert: button is orange
		Assertions.assertTrue(joinNowButton.getCssValue("background").contains("rgb(251, 160, 53)"));
		// assert: button is wide
		Assertions.assertEquals("520px", joinNowButton.getCssValue("width"));
	}
}
