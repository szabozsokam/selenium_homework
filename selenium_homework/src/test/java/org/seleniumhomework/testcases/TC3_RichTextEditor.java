package org.seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Actions;
import org.seleniumhomework.common.TestBase;
import org.seleniumhomework.pageobjects.onlinehtmleditor.TextEditorPage;

public class TC3_RichTextEditor extends TestBase {

	@Test
	public void editText() throws Exception {

		driver.get("https://onlinehtmleditor.dev");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		TextEditorPage editorPage = new TextEditorPage(driver);
		editorPage.consentButton.click();

		driver.switchTo().frame(editorPage.textFrame);
		editorPage.textbox.sendKeys("Automation Test Example");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Rectangle rect = editorPage.textbox.getRect();
		int fontSize = 16;
		// double click at middle of the word: 'Automation' - positioning is wrong who
		// knows why
		new Actions(driver).moveToElement(editorPage.textbox, rect.x + 4 * fontSize, rect.height / 2).doubleClick()
				.perform();

		driver.switchTo().defaultContent();
		editorPage.boldIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		// double click at middle of the word: 'Test' - positioning is wrong who knows
		// why
		new Actions(driver).moveToElement(editorPage.textbox, rect.x + 13 * fontSize, rect.height / 2).doubleClick()
				.perform();

		driver.switchTo().defaultContent();
		editorPage.underlineIcon.click();

		driver.switchTo().frame(editorPage.textFrame);
		Assertions.assertEquals("Automation Test Example", editorPage.textbox.getText());
	}
}
