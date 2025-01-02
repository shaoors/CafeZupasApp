package CafeZupasAutomation.CafeZupasApp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class secondTest  extends BaseSetup {
	@Test
	public void LoginTestWithWrongCreds() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN IN\")")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"EMAIL_ADDRESS_FIELD\"]"))
				.sendKeys("muhammad.shaoor@tkxel.io");
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"PASSWORD_FIELD\"]")).sendKeys("Tkxel123");
		driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"LOGIN_BUTTON\"]")).click();
		WebElement toastElement = driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id=\"toastAnimatedContainer\"]/android.view.ViewGroup"));
		Assert.assertTrue(toastElement.isDisplayed(), "Toast element is not visible");
		System.out.print(toastElement.getText());

	}
}
