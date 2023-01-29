package org.seleniumhomework.pageobjects.guru99;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuruDemoPage {
	
	@FindBy(id = "gdpr-consent-notice")
	public WebElement consentFrame;

	@FindBy(xpath = "//span[normalize-space(text())='Összes elfogadása']")
	public WebElement consentButton;

	@FindBy(id = "a077aa5e")
	public WebElement iframe;

	@FindBy(xpath = "//img[@src='Jmeter720.png']")
	public WebElement image;
	
	@FindBy(xpath = "//a[@class='item' and normalize-space(text())='Testing']")
	public WebElement testingMenuItem;
	
	@FindBy(xpath = "//a[@class='item' and normalize-space(text())='Selenium']")
	public WebElement seleniumMenuItem;

	private final WebDriver driver;
	private String url = "https://demo.guru99.com/test/guru99home/";

	public GuruDemoPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Guru99 demo page did not load");
		}
	}
	
	public void hoverAndClick(WebElement elementToHover, WebElement elementToClick) {
		new Actions(driver).
		moveToElement(elementToHover).
		moveToElement(elementToClick).
		click().build().perform();
	}
}
