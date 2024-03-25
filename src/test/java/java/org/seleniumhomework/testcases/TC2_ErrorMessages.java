package java.org.seleniumhomework.testcases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.org.seleniumhomework.common.TestBase;
import java.org.seleniumhomework.pageobjects.sauce.SauceLoginPage;
import java.org.seleniumhomework.pageobjects.sauce.SauceProductsPage;

public class TC2_ErrorMessages extends TestBase {

	@Test
	public void validateErrorMessages() throws Exception {

		// Step 1: login without user data and validate error message
		driver.get("https://www.saucedemo.com/inventory.html");
		SauceLoginPage loginPage = new SauceLoginPage(driver);
		loginPage.loginButton.click();
		String errorMessage = "Username is required";
		Assertions.assertTrue(loginPage.errorMessage.getText().contains(errorMessage), "Error message does not match");

		// Step 2: login with standard user and validate footer text
		loginPage.login("standard_user", "secret_sauce");
		SauceProductsPage productsPage = new SauceProductsPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		Assertions.assertTrue(productsPage.yearInFooter.isDisplayed(), "2023 is not displayed in footer");
		Assertions.assertTrue(productsPage.termsInFooter.isDisplayed(),
				"'Terms of Service' is not displayed in footer");
	}

}
