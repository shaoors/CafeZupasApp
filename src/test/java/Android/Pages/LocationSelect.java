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

import CafeZupasAutomation.CafeZupasApp.BaseSetup;

public class LocationSelect {
	private AndroidDriver driver;

	// Locators
	private By navigationBar = By
			.xpath("//android.widget.LinearLayout[@resource-id=\"com.cafezupasres.co:id/action_bar_root\"]");
	private By locationField = By.xpath("//android.widget.EditText[@text=\"Search City and Zip Code\"]");
	private By locationCard = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.ScrollView\").instance(1)");
	private By notificationToast = By
			.xpath("//android.widget.TextView[@resource-id='com.android.permissioncontroller:id/permission_message']");
	// Constructor
	private By whileUsingAppButton = By.xpath(
			"//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
	private By deliveryTab = By.xpath("//android.widget.TextView[@text=\"Delivery\"]");
	private By deliverySelect = By
			.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]");
	private By dropDownData = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup");
	private By closeDropdown = By.xpath("//android.widget.LinearLayout");
	private By addNewAddress = By.xpath("//android.widget.TextView[@text=\"Add a New Address\"]");
	private By deliveryHeading = By.xpath("//android.widget.TextView[@text=\"Add Delivery Address\"]");
	private By addressName = By.xpath("//android.widget.EditText[@text=\"Address Name\"]");

	private By addressArea = By.xpath("//android.widget.EditText[@text=\"Select Address from here\"]");
	private By addressAreaSelect = By.xpath(
			"//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
	private By checkBox = By.xpath("//android.widget.CheckBox");
	private By continueButton = By.xpath("//android.widget.TextView[@text=\"CONTINUE\"]");
	private By deliveryAtText = By.xpath("//android.widget.TextView[@text=\"DELIVERY AT\"]");

	public LocationSelect(AndroidDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void openlocationSelectView() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(navigationBar));
		element.click();
	}

	public void enterLocationString(String locatioName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationField));
		element.sendKeys(locatioName);
	}

	public String selectLocation() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locationCard));
		String data = element.getText();
		element.click();

		return data;
	}

	public void allowNotificationpopUp() {

		if (isNotificationToastVisible()) {
			driver.findElement(whileUsingAppButton).click();
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
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			List<WebElement> notificationPopups = driver.findElements(notificationToast);
			System.out.println("After finding notification toast: " + notificationPopups.size());
			if (notificationPopups.size() > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			// Log any exception
			System.err.println("Error while checking notification toast: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void selectDeliveryTab() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryTab));
		element.click();
	}

	public void clickDeliverySelect() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverySelect));
		element.click();
	}

	public void selectDraperFromDown() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropDownData));
		boolean draperFound = false;

		// Iterate through the elements to find the one with the text "draper"
		for (WebElement element : elements) {
			if (element.getText().equalsIgnoreCase("draper")) {
				element.click(); // Click on the element if text matches
				System.out.println("Element with text 'draper' clicked.");
				draperFound = true;
				break; // Exit the loop once clicked
			}
		}

		// If no matching element is found, perform an alternative action
		if (!draperFound) {
			driver.findElement(closeDropdown).click();
			System.out.println("No element with text 'draper' found.");
			WebElement addNewAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addNewAddress));
			addNewAddressButton.click();
			WebElement deliveryHeadingText = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryHeading));
			Assert.assertTrue(deliveryHeadingText.isDisplayed(), "Delivery From not Shown");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
	}

	public void addDeliveryAddress(String name, String area) {
//		Draper Utah Temple, Canyon Vista Lane, Draper, UT, USA
		BaseSetup baseFunction = new BaseSetup();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement addAddressName = wait.until(ExpectedConditions.visibilityOfElementLocated(addressName));
		addAddressName.sendKeys(name);
		WebElement addAddressArea = wait.until(ExpectedConditions.visibilityOfElementLocated(addressArea));
		addAddressArea.sendKeys(area);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement selectAddressArea = wait.until(ExpectedConditions.visibilityOfElementLocated(addressAreaSelect));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		selectAddressArea.click();
		driver.findElement(checkBox).click();
		baseFunction.scrollToEndAction();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement continueButtonEle = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
		continueButtonEle.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void deliveryTextEnd() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement deliveryAtTextAtTop = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryAtText));
		Assert.assertTrue(deliveryAtTextAtTop.isDisplayed(), "Delivery At not Shown");

	}

	public String pickupLocationTestFunction() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			openlocationSelectView();
			allowNotificationpopUp();
			enterLocationString("Draper");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			String data = selectLocation();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			return data;

		} catch (Exception e) {
			System.err.println("FAIL IN LOCATION" + e.getMessage());
			return "";
		}

	}

	public String deliveryLocationTestFunction() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			openlocationSelectView();
			allowNotificationpopUp();
			selectDeliveryTab();
			clickDeliverySelect();
			selectDraperFromDown();
			addDeliveryAddress("Draper", "Draper Utah Temple, Canyon Vista Lane, Draper, UT, USA");
			deliveryTextEnd();
			return "";

		} catch (Exception e) {
			System.err.println("FAIL IN LOCATION" + e.getMessage());
			return "";
		}

	}

}
