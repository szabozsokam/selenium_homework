package org.seleniumhomework.testcases;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.seleniumhomework.common.TestBase;
import org.seleniumhomework.pageobjects.sauce.SauceCartPage;
import org.seleniumhomework.pageobjects.sauce.SauceCheckoutCompletePage;
import org.seleniumhomework.pageobjects.sauce.SauceCheckoutFirstPage;
import org.seleniumhomework.pageobjects.sauce.SauceCheckoutOverviewPage;
import org.seleniumhomework.pageobjects.sauce.SauceLoginPage;
import org.seleniumhomework.pageobjects.sauce.SauceProductsPage;

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		SauceCartPage cartPage = new SauceCartPage(driver);
		cartPage.checkout();
		SauceCheckoutFirstPage checkoutFirstPage = new SauceCheckoutFirstPage(driver);
		checkoutFirstPage.fillData("Zsoka", "Szabo", "1011");
		checkoutFirstPage.continueButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		SauceCheckoutOverviewPage overviewPage = new SauceCheckoutOverviewPage(driver);
		overviewPage.finishButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		// Step 4: validate successful order
		SauceCheckoutCompletePage completePage = new SauceCheckoutCompletePage(driver);
		Assertions.assertEquals("THANK YOU FOR YOUR ORDER", completePage.thankYouText.getText().trim().toUpperCase());
	}

}
