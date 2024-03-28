package seleniumhomework.pageobjects.sauce;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceLoginPage {

	@FindBy(id = "user-name")
	private WebElement usernameIF;

	@FindBy(id = "password")
	private WebElement passwordIF;

	@FindBy(id = "login-button")
	public WebElement loginButton;

	@FindBy(xpath = "//h3[@data-test=\"error\"]")
	public WebElement errorMessage;

	protected WebDriver driver;
	private final String path = "src/test/resources/credentials.json";
	private final String url = "https://www.saucedemo.com/";

	public SauceLoginPage(WebDriver driver) throws Exception {
		this.driver = driver;
		if (!driver.getCurrentUrl().equals(url)) {
			throw new Exception("Current page is not the login page");
		}
		PageFactory.initElements(this.driver, this);
	}

	@SuppressWarnings("unchecked")
	public void saveCredentials(String username, String password) throws Exception {
		try (FileWriter file = new FileWriter(path)) {
			JSONObject credentials = new JSONObject();
			credentials.put("username", username);
			credentials.put("password", password);
			file.append(credentials.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(String username, String password) {
		usernameIF.sendKeys(username);
		passwordIF.sendKeys(password);
		loginButton.click();
		new WebDriverWait(driver, Duration.ofSeconds(2))
				.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
	}

	public void loginWithSavedCredentials() throws Exception {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader(path);
			JSONObject user = (JSONObject) jsonParser.parse(reader);
			String username = (String) user.get("username");
			String password = (String) user.get("password");
			// log username pw with log4j
			login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
