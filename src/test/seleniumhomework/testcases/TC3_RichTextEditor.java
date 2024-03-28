package seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumhomework.common.TestBase;
import seleniumhomework.pageobjects.onlinehtmleditor.TextEditorPage;

public class TC3_RichTextEditor extends TestBase {

	@Test
	public void editText() throws Exception {

		// Open the url
		String url = "https://onlinehtmleditor.dev/";
		TestBase.driver.get(url);
		new WebDriverWait(TestBase.driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.urlToBe(url));
		TextEditorPage editorPage = new TextEditorPage(TestBase.driver);

		// Type the following text to the editor: Automation Test Example
			// Automation text is typed bold format
			editorPage.boldButton.click();
			editorPage.textBox.sendKeys("Automation ");
			editorPage.boldButton.click();
			// Test text is typed underline format
			editorPage.underlineButton.click();
			editorPage.textBox.sendKeys("Test");
			editorPage.underlineButton.click();
			// Example is typed without formatting
			editorPage.textBox.sendKeys(" Example");

		// Validate the text is appearing in the rich text editor
		Assertions.assertEquals("Automation Test\u2060\u2060\u2060\u2060\u2060\u2060\u2060 Example", editorPage.textBox.getText());
	}
}
