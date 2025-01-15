package Android.test;

import Android.Pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import CafeZupasAutomation.CafeZupasApp.java.base.BaseSetup;
import io.appium.java_client.AppiumBy;

public class SignInTest extends BaseSetup {

	@Test(priority = 1)
	public void testLogin() {
		LoginPage loginPage = new LoginPage(driver);

		// Perform login actions
		loginPage.clickSignInButton();
		loginPage.enterUsername("muhammad.shaoor@tkxel.io");
		loginPage.enterPassword("Tkxel1234@");
		loginPage.clickLogin();
		loginPage.allowNotificationpopUp();
		// Verify welcome screen
		String actualWelcomeText = loginPage.getWelcomeScreenText();
		String expectedWelcomeText = "Welcome";
		Assert.assertTrue(actualWelcomeText.contains(expectedWelcomeText), "Welcome screen text mismatch! Expected: \""
				+ expectedWelcomeText + "\", but found: \"" + actualWelcomeText + "\"");
	}

	@Test(priority = 2)
	public void LoginTestWithWrongCreds() {
		LoginPage loginPage = new LoginPage(driver);

		// Perform login actions with invalid credentials
		loginPage.clickSignInButton();
		loginPage.enterUsername("muhammad.shaoor@tkxel.io");
		loginPage.enterPassword("Tkxel123");
		loginPage.clickLogin();

		// Verify toast message visibility and content
		Assert.assertTrue(loginPage.isToastVisible(), "Toast element is not visible.");
		Assert.assertTrue(loginPage.isToastMessageCorrect("The email or password you entered is incorrect."),
				"Toast message text is incorrect or not visible.");
	}
}
