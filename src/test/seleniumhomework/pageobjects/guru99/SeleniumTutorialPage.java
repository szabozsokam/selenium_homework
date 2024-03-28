package seleniumhomework.pageobjects.guru99;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTutorialPage {

	@FindBy(xpath = "//button[@title='Elfogad']")
	public WebElement consentButton;

	@FindBy(xpath = "//span/b[text()='Join Now']/ancestor::button")
	public WebElement joinNowButton;

	@FindBy(id = "sp_message_iframe_1102471")
	public WebElement consentFrame;

	private final WebDriver driver;

	public SeleniumTutorialPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		}
}
