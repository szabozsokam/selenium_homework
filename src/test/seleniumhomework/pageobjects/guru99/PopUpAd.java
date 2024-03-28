package seleniumhomework.pageobjects.guru99;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopUpAd {
	
	@FindBy(xpath = "//iframe[@title='3rd party ad content']")
	public WebElement outerFrame;
	
	@FindBy(xpath = "//iframe[@id = 'ad_iframe']")
	public WebElement innerFrame;
	
	@FindBy(xpath = "//span[normalize-space(text())='Close']")
	public WebElement closeButton;

	@FindBy(id = "dismiss-button")
	public WebElement xButton;
	
	private final WebDriver driver;

	public PopUpAd(WebDriver driver) throws Exception {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

}
