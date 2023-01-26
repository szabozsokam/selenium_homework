package org.seleniumhomework.pageobjects.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhomework.common.TestBase;

public class SauceCheckoutFirstPage extends TestBase {

	@FindBy(id = "first-name")
	private WebElement firstNameIF;

	@FindBy(id = "last-name")
	private WebElement lastNameIF;

	@FindBy(id = "postal-code")
	private WebElement postalCodeIF;

	@FindBy(id = "continue")
	public WebElement continueButton;

	private final WebDriver driver;
	private String url = "https://www.saucedemo.com/checkout-step-one.html";

	public SauceCheckoutFirstPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Checkout-step-one page did not load");
		}
	}

	public void fillData(String firstName, String lastName, String postalCode) {
		firstNameIF.sendKeys(firstName);
		lastNameIF.sendKeys(lastName);
		postalCodeIF.sendKeys(postalCode);
	}

}
