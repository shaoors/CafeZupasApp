package Android.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	private AndroidDriver driver;

	// Locators
	private By signInButton = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN IN\")");
	private By usernameField = By.xpath("//android.widget.EditText[@resource-id=\"EMAIL_ADDRESS_FIELD\"]");
	private By passwordField = By.xpath("//android.widget.EditText[@resource-id=\"PASSWORD_FIELD\"]");
	private By loginButton = By.xpath("//android.view.ViewGroup[@resource-id=\"LOGIN_BUTTON\"]");
	private By welcomeScreen = By.xpath("//android.widget.TextView[contains(@text, 'Welcome')]");
	private By toastContainer = By.xpath("//android.view.ViewGroup[contains(@resource-id, 'toast')]");
	private By toastMessage = By
			.xpath(".//android.widget.TextView[contains(@text, 'The email or password you entered is incorrect.')]");
	private By notificationToast = By
			.xpath("//android.widget.TextView[@resource-id='com.android.permissioncontroller:id/permission_message']");
	private By allowButtonPopUp = By.xpath(
			"//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeScreen));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return element.getText();
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

	public void allowNotificationpopUp() {

		if (isNotificationToastVisible()) {
			driver.findElement(allowButtonPopUp).click();
		}
	}

	public void loginTestFunction() {
//		try {
			clickSignInButton();
			enterUsername("muhammad.shaoor@tkxel.io");
			enterPassword("Tkxel1234@");
			clickLogin();
			sleepFunction(10000);
			allowNotificationpopUp();
			sleepFunction(5000);
			// Verify welcome screen
			String actualWelcomeText = getWelcomeScreenText();
			String expectedWelcomeText = "Welcome";
			Assert.assertTrue(actualWelcomeText.contains(expectedWelcomeText),
					"Welcome screen text mismatch! Expected: \"" + expectedWelcomeText + "\", but found: \""
							+ actualWelcomeText + "\"");

//		} catch (Exception e) {
//			System.err.println("FAIL IN LOGIN: " + e.getMessage());
//		}
	}

	public boolean isNotificationToastVisible() {
		try {
			System.out.println("Before finding notification toast in Login");

			// Check the driver is active
			if (driver == null) {
				System.err.println("Driver is null. Cannot proceed.");
				return false;
			}

			// Check current activity (optional for debugging)
			String currentActivity = driver.currentActivity();
			System.out.println("Current activity: " + currentActivity);

			// Find elements
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			List<WebElement> notificationPopups = driver.findElements(notificationToast);
			System.out.println("After finding notification toast: " + notificationPopups.size());
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			return !notificationPopups.isEmpty();
		} catch (Exception e) {
			// Log any exception
			System.err.println("Error while checking notification toast: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void sleepFunction(int value) {
		try {
			Thread.sleep(value); // Pause for 2 seconds (2000 milliseconds)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
