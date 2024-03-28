package seleniumhomework.pageobjects.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumhomework.common.TestBase;


public class SauceCheckoutCompletePage extends TestBase {

	@FindBy(xpath = "//h2[@class=\"complete-header\"]")
	public WebElement thankYouText;

	private final WebDriver driver;

	public SauceCheckoutCompletePage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

}
