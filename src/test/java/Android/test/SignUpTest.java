package Android.test;

import Android.Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import CafeZupasAutomation.CafeZupasApp.java.base.BaseSetup;

public class SignUpTest extends BaseSetup {

	@Test(priority = 1)
	public void testSignUp() {
		SignUpPage signUpPage = new SignUpPage(driver);

		signUpPage.clickSignUpWithEmail();
		signUpPage.enterFirstName("Test");
		signUpPage.enterLastName("Test");
		signUpPage.enterEmailAddress(generateRandomEmail());
		signUpPage.enterPassword("Tkxel123@");
		scrollToEndAction(); // Base method for scrolling
		sleepFunction(4000);
		signUpPage.enterConfirmPassword("Tkxel123@");
		signUpPage.selectFavouriteLocation();
		signUpPage.enterPhoneNumber("3213211231");
		signUpPage.clickSignUpButton();

		if (signUpPage.isPopupDisplayed()) {
			signUpPage.clickAllowButton();
		}

		// Add assertions if necessary
		Assert.assertTrue(driver.getPageSource().contains("Get 500 Bonus Points!"), "Sign-up popup not displayed!");
	}

	@Test(priority = 2)
	public void testSignUpFail() {
		SignUpPage signUpPage = new SignUpPage(driver);

		// Perform Sign-Up steps
		signUpPage.clickSignUpWithEmail();
		signUpPage.enterFirstName("Test");
		signUpPage.enterLastName("Test");
		signUpPage.enterEmailAddress("muhammad.shaoor@tkxel.io");
		signUpPage.enterPassword("Tkxel123@");
		scrollToEndAction();
		sleepFunction(4000);
		signUpPage.enterConfirmPassword("Tkxel123@");
		signUpPage.selectFavouriteLocation();
		signUpPage.enterPhoneNumber("3213211231");
		signUpPage.clickSignUpButton();

		signUpPage.verifyToastMessage("The email has already been taken.");
	}
}
