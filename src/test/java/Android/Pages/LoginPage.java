package Android.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private AndroidDriver driver;

	// Locators
	private By signInButton = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN IN\")");
	private By usernameField = By.xpath("//android.widget.EditText[@resource-id=\"EMAIL_ADDRESS_FIELD\"]");
	private By passwordField = By.xpath("//android.widget.EditText[@resource-id=\"PASSWORD_FIELD\"]");
	private By loginButton = By.xpath("//android.view.ViewGroup[@resource-id=\"LOGIN_BUTTON\"]");
	private By welcomeScreen = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Welcome\")");
	private By toastContainer = By.xpath("//android.view.ViewGroup[contains(@resource-id, 'toast')]");
	private By toastMessage = By
			.xpath(".//android.widget.TextView[contains(@text, 'The email or password you entered is incorrect.')]");

	// Constructor
	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void clickSignInButton() {
		driver.findElement(signInButton).click();
	}

	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public String getWelcomeScreenText() {
		return driver.findElement(welcomeScreen).getText();
	}

	public boolean isToastVisible() {
		return driver.findElement(toastContainer).isDisplayed();
	}

	public boolean isToastMessageCorrect(String expectedMessage) {
		try {
			// Use WebDriverWait to wait for the toast container to be visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement toastContainerElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));

			// Find the toast message within the toast container
			WebElement toastMessageElement = toastContainerElement.findElement(toastMessage);

			// Validate the toast message
			return toastMessageElement.isDisplayed() && toastMessageElement.getText().contains(expectedMessage);
		} catch (NoSuchElementException e) {
			// Handle if the toast container or message elements are not found
			System.out.println("Toast message element not found.");
			return false;
		}
	}
}
