package seleniumhomework.pageobjects.sauce;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceProductsPage {

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addBackpackButton;

	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement removeBackpackButton;

	@FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
	private WebElement addFleeceJacketButton;

	@FindBy(id = "remove-sauce-labs-fleece-jacket")
	private WebElement removeFleeceJacketButton;

	@FindBy(className = "shopping_cart_badge")
	public WebElement cartBadge;

	@FindBy(xpath = "//div[text()[contains(.,'2024')] and @class='footer_copy']")
	public WebElement yearInFooter;

	@FindBy(xpath = "//div[text()[contains(.,'Terms of Service')] and @class='footer_copy']")
	public WebElement termsInFooter;

	private final WebDriver driver;
	private final String url = "https://www.saucedemo.com/inventory.html";

	public SauceProductsPage(WebDriver driver) throws Exception {
		this.driver = driver;
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Product page did not load");
		}
		PageFactory.initElements(this.driver, this);
	}

	public void addProductsToCart() {
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(addBackpackButton));
		addBackpackButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(removeBackpackButton));
		addFleeceJacketButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.visibilityOf(removeFleeceJacketButton));
	}

	public int getNumberOnCartSymbol() {
		return Integer.parseInt(cartBadge.getText().trim());
	}

}
