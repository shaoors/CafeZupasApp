package Android.test;

import Android.Pages.LocationSelect;
import Android.Pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import CafeZupasAutomation.CafeZupasApp.java.base.BaseSetup;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class locationSelectTest extends BaseSetup {

//	@Test(priority = 1)
//	public void TestPickUpLocation() {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.loginTestFunction();
//		System.out.print("After Login");
//		LocationSelect location = new LocationSelect(driver);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		String data = location.pickupLocationTestFunction();
//		System.out.print(data);
//		sleepFunction(10000);

//	}

	@Test(priority = 1)
	public void TestDeliveryLocation() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginTestFunction();
		System.out.print("After Login");
		LocationSelect location = new LocationSelect(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String data = location.deliveryLocationTestFunction();
		System.out.print(data);
		sleepFunction(10000);
	}

}
