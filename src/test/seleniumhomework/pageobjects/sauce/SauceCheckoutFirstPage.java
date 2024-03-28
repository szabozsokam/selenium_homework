package seleniumhomework.pageobjects.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumhomework.common.TestBase;

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

	public SauceCheckoutFirstPage(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void fillData(String firstName, String lastName, String postalCode) {
		firstNameIF.sendKeys(firstName);
		lastNameIF.sendKeys(lastName);
		postalCodeIF.sendKeys(postalCode);
	}

}
