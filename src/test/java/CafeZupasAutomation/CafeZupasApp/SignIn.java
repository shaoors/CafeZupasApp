package CafeZupasAutomation.CafeZupasApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class SignIn extends BaseSetup {

	@Test
	public void LoginTest() throws MalformedURLException, URISyntaxException {

		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"SIGN IN\")")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"EMAIL_ADDRESS_FIELD\"]"))
				.sendKeys("muhammad.shaoor@tkxel.io");
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"PASSWORD_FIELD\"]"))
				.sendKeys("Tkxel123@");
		driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id=\"LOGIN_BUTTON\"]")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Welcome,\")")).click();

	}

}
