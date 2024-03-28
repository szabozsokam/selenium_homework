package seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumhomework.common.TestBase;
import seleniumhomework.pageobjects.sauce.*;

public class TC1_Purchase extends TestBase {

	@Test
	public void testPurchaseProcess() throws Exception {

		// Step 1: login using credentials from file
		driver.get("https://www.saucedemo.com/inventory.html");
		SauceLoginPage loginPage = new SauceLoginPage(driver);
		loginPage.saveCredentials("performance_glitch_user", "secret_sauce");
		loginPage.loginWithSavedCredentials();

		// Step 2: add products to cart and validate
		SauceProductsPage productsPage = new SauceProductsPage(driver);
		productsPage.addProductsToCart();
		Assertions.assertEquals(2, productsPage.getNumberOnCartSymbol());

		// Step 3: go through the checkout process
		productsPage.cartBadge.click();
		new WebDriverWait(driver, Duration.ofSeconds(1))
				.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html"));
		SauceCartPage cartPage = new SauceCartPage(driver);
		cartPage.checkout();
		SauceCheckoutFirstPage checkoutFirstPage = new SauceCheckoutFirstPage(driver);
		checkoutFirstPage.fillData("User", "Tester", "1011");
		checkoutFirstPage.continueButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(1))
				.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-two.html"));
		SauceCheckoutOverviewPage overviewPage = new SauceCheckoutOverviewPage(driver);
		overviewPage.finishButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(1))
				.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-complete.html"));

		// Step 4: validate successful order
		SauceCheckoutCompletePage completePage = new SauceCheckoutCompletePage(driver);
		Assertions.assertEquals("THANK YOU FOR YOUR ORDER!", completePage.thankYouText.getText().trim().toUpperCase());
	}

}
