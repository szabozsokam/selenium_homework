package java.org.seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.org.seleniumhomework.common.TestBase;
import java.org.seleniumhomework.pageobjects.onlinehtmleditor.TextEditorPage;

public class TC3_RichTextEditor extends TestBase {

	@Test
	public void editText() throws Exception {

		String url = "https://onlinehtmleditor.dev/";
		driver.get(url);
		new WebDriverWait(driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='js-cookies-button']")));
		TextEditorPage editorPage = new TextEditorPage(driver);
		editorPage.consentButton.click();

		driver.switchTo().frame(editorPage.textFrame);
		String text = "Automation Test Example";
		editorPage.textbox.sendKeys(text);
		int fontSize = 16;
		int editorWidth = 720;
		new Actions(driver).moveToElement(editorPage.textbox, (4 * fontSize) - (editorWidth / 2), 0).doubleClick()
				.build().perform(); // TODO: review positioning!
		driver.switchTo().defaultContent();
		editorPage.boldIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		new Actions(driver).moveToElement(editorPage.textbox, (13 * fontSize) - (editorWidth / 2), 0).doubleClick()
				.build().perform(); // TODO: review positioning!
		driver.switchTo().defaultContent();
		editorPage.underlineIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		Assertions.assertEquals(text, editorPage.textbox.getText());
	}
}
