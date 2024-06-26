package seleniumhomework.pageobjects.sauce;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceCartPage {

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	private final WebDriver driver;

	public SauceCartPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void checkout() {
		checkoutButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
	}
}
