package org.seleniumhomework.pageobjects.onlinehtmleditor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextEditorPage {

	@FindBy(xpath = "//button[@id='js-cookies-button']")
	public WebElement consentButton;

	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement textFrame;

	@FindBy(xpath = "//body[@role='textbox']/p")
	public WebElement textbox;

	@FindBy(xpath = "//span[@class='cke_button_icon cke_button__bold_icon']")
	public WebElement boldIcon;

	@FindBy(xpath = "//span[@class='cke_button_icon cke_button__underline_icon']")
	public WebElement underlineIcon;

	private final WebDriver driver;
	private String url = "https://onlinehtmleditor.dev/";

	public TextEditorPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Online html editor page did not load");
		}
	}

	public void doubleClickOnText(int x, int y) {

	}

}