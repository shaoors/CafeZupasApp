package CafeZupasAutomation.CafeZupasApp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SignUp extends BaseSetup {
	
	
	@Test
	public void SignUpTest() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN UP WITH EMAIL\")"))
				.click();

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'FIRST_NAME')]"))
				.sendKeys("Test");

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'LAST_NAME')]")).sendKeys("Test");

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'EMAIL_ADDRESS')]"))
				.sendKeys(generateRandomEmail());

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'PASSWORD')]"))
				.sendKeys("Tkxel123@");

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'CONFIRM_PASSWORD')]"))
				.sendKeys("Tkxel123@");

		scrollToEndAction();

		driver.findElement(By.xpath("//android.widget.EditText[contains(@text, 'Favourite Location')]")).click();

		driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'AZ - ARROWHEAD')]")).click();

		driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id, 'PHONE_NUMBER')]"))
				.sendKeys("3213211231");

		driver.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id, 'SIGNUP_BUTTON')]")).click();

		WebElement element = driver
				.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Get 500 Bonus Points!')]"));
		Boolean popup = element.isDisplayed();
		if (popup) {
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'ALLOW')]")).click();
		}

	}

}
