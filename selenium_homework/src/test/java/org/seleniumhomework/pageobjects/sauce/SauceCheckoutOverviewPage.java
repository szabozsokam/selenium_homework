package org.seleniumhomework.pageobjects.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhomework.common.TestBase;

public class SauceCheckoutOverviewPage extends TestBase {

	@FindBy(id = "finish")
	public WebElement finishButton;

	private final WebDriver driver;
	private String url = "https://www.saucedemo.com/checkout-step-two.html";

	public SauceCheckoutOverviewPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Checkout overview page did not load");
		}
	}

}
