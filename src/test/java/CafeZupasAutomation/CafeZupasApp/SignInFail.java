package CafeZupasAutomation.CafeZupasApp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import CafeZupasAutomation.CafeZupasApp.java.base.BaseSetup;
import io.appium.java_client.AppiumBy;

public class SignInFail extends BaseSetup {
	
	@Test
	public void LoginTestWithWrongCreds() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN IN\")")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"EMAIL_ADDRESS_FIELD\"]"))
				.sendKeys("muhammad.shaoor@tkxel.io");
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"PASSWORD_FIELD\"]")).sendKeys("Tkxel123");
		driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"LOGIN_BUTTON\"]")).click();
		WebElement toastContainer = driver
				.findElement(By.xpath("//android.view.ViewGroup[contains(@resource-id, 'toast')]"));
		WebElement toastElement = toastContainer.findElement(By.xpath(
				".//android.widget.TextView[contains(@text, 'The email or password you entered is incorrect.')]"));
		Assert.assertTrue(toastContainer.isDisplayed(), "Toast element is not visible");
		Assert.assertTrue(toastElement.isDisplayed(), "Toast message is not visible.");

	}
}
