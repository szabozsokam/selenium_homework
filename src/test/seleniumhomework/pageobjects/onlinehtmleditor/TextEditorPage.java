package seleniumhomework.pageobjects.onlinehtmleditor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextEditorPage {

	@FindBy(xpath = "//div[@role='textbox']//p")
	public WebElement textBox;

	@FindBy(xpath = "//span[text()='Bold']/..")
	public WebElement boldButton;

	@FindBy(xpath = "//span[text()='Underline']/..")
	public WebElement underlineButton;

	private final WebDriver driver;

	public TextEditorPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
}
