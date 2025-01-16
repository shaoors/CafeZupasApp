package Android.Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignUpPage {
	private AndroidDriver driver;

	// Locators
	private By signUpWithEmailButton = AppiumBy
			.androidUIAutomator("new UiSelector().textContains(\"SIGN UP WITH EMAIL\")");
	private By firstNameField = By.xpath("//android.widget.EditText[contains(@resource-id, 'FIRST_NAME')]");
	private By lastNameField = By.xpath("//android.widget.EditText[contains(@resource-id, 'LAST_NAME')]");
	private By emailAddressField = By.xpath("//android.widget.EditText[contains(@resource-id, 'EMAIL_ADDRESS')]");
	private By passwordField = By.xpath("//android.widget.EditText[contains(@resource-id, 'PASSWORD')]");
	private By confirmPasswordField = By.xpath("//android.widget.EditText[contains(@resource-id, 'CONFIRM_PASSWORD')]");
	private By favouriteLocationField = By.xpath("//android.widget.EditText[contains(@text, 'Favourite Location')]");
	private By locationOption = By.xpath("//android.widget.TextView[contains(@text, 'AZ - ARROWHEAD')]");
	private By phoneNumberField = By.xpath("//android.widget.EditText[contains(@resource-id, 'PHONE_NUMBER')]");
	private By signUpButton = By.xpath("//android.view.ViewGroup[contains(@resource-id, 'SIGNUP_BUTTON')]");
	private By popupMessage = By.xpath("//android.widget.TextView[contains(@text, 'Get 500 Bonus Points!')]");
	private By allowButton = By.xpath("//android.widget.TextView[contains(@text, 'ALLOW')]");
	private By notificationToast = By
			.xpath("//android.widget.TextView[@resource-id='com.android.permissioncontroller:id/permission_message']");
	private By allowButtonPopUp = By.xpath(
			"//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
	private By toastContainer = By.xpath("//android.view.ViewGroup[contains(@resource-id, 'toast')]");
	private By toastMessage = By
			.xpath(".//android.widget.TextView[contains(@text, 'The email has already been taken.')]");

	// Constructor
	public SignUpPage(AndroidDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void clickSignUpWithEmail() {
		driver.findElement(signUpWithEmailButton).click();
	}

	public void enterFirstName(String firstName) {
		driver.findElement(firstNameField).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(lastNameField).sendKeys(lastName);
	}

	public void enterEmailAddress(String email) {
		driver.findElement(emailAddressField).sendKeys(email);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
	}

	public void selectFavouriteLocation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.elementToBeClickable(favouriteLocationField));
		driver.findElement(favouriteLocationField).click();

		wait.until(ExpectedConditions.elementToBeClickable(locationOption));
		driver.findElement(locationOption).click();
	}

	public void enterPhoneNumber(String phoneNumber) {
		driver.findElement(phoneNumberField).sendKeys(phoneNumber);
	}

	public void clickSignUpButton() {
		driver.findElement(signUpButton).click();
	}

	public boolean isPopupDisplayed() {
		return driver.findElement(popupMessage).isDisplayed();
	}

	public void clickAllowButton() {
		driver.findElement(allowButton).click();
	}

	public void verifyToastMessage(String expectedMessage) {
		WebElement toastContainerElement = driver.findElement(toastContainer);
		WebElement toastElement = toastContainerElement.findElement(toastMessage);

		Assert.assertTrue(toastContainerElement.isDisplayed(), "Toast element is not visible.");
		Assert.assertTrue(toastElement.getText().contains(expectedMessage), "Toast message text mismatch! Expected: \""
				+ expectedMessage + "\", but found: \"" + toastElement.getText() + "\"");
	}

	public void allowNotificationpopUp() {

		if (isNotificationToastVisible()) {
			driver.findElement(allowButtonPopUp).click();
		}
	}

	public boolean isNotificationToastVisible() {
		try {
			System.out.println("Before finding notification toast");

			// Check the driver is active
			if (driver == null) {
				System.err.println("Driver is null. Cannot proceed.");
				return false;
			}

			// Check current activity (optional for debugging)
			String currentActivity = driver.currentActivity();
			System.out.println("Current activity: " + currentActivity);

			// Find elements
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			List<WebElement> notificationPopups = driver.findElements(notificationToast);
			System.out.println("After finding notification toast: " + notificationPopups.size());

			return !notificationPopups.isEmpty();
		} catch (Exception e) {
			// Log any exception
			System.err.println("Error while checking notification toast: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
