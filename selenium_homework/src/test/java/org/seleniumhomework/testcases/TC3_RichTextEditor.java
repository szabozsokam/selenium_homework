package org.seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhomework.common.TestBase;
import org.seleniumhomework.pageobjects.onlinehtmleditor.TextEditorPage;

public class TC3_RichTextEditor extends TestBase {

	private String url = "https://onlinehtmleditor.dev/";
	private String text = "Automation Test Example";

	@Test
	public void editText() throws Exception {

		driver.get(url);
		new WebDriverWait(driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='js-cookies-button']")));
		TextEditorPage editorPage = new TextEditorPage(driver);
		editorPage.consentButton.click();

		driver.switchTo().frame(editorPage.textFrame);
		editorPage.textbox.sendKeys(text);
		int fontSize = 16;
		int editorWidth = 720;
		// double click at middle of the word: 'Automation' - positioning is wrong who
		// knows why
		new Actions(driver).moveToElement(editorPage.textbox, (4 * fontSize) - (editorWidth / 2), 0).doubleClick()
				.build().perform();
		driver.switchTo().defaultContent();
		editorPage.boldIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		// double click at middle of the word: 'Test' - positioning is wrong who knows
		// why
		new Actions(driver).moveToElement(editorPage.textbox, (13 * fontSize) - (editorWidth / 2), 0).doubleClick()
				.build().perform();
		driver.switchTo().defaultContent();
		editorPage.underlineIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		Assertions.assertEquals(text, editorPage.textbox.getText());
	}
}
