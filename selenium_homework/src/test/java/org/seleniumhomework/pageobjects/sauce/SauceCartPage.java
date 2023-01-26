package org.seleniumhomework.pageobjects.sauce;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceCartPage {

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	private final WebDriver driver;
	private String url = "https://www.saucedemo.com/cart.html";

	public SauceCartPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Cart page did not load");
		}
	}

	public void checkout() {
		checkoutButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
}
