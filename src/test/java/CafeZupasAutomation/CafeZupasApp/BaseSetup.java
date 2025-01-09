//package CafeZupasAutomation.CafeZupasApp;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.time.Duration;
//
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.google.common.collect.ImmutableMap;
//
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//
//public class BaseSetup {
//
//	public AndroidDriver driver;
//	public AppiumDriverLocalService service;
//	
//	public void setupEnvironment() {
//        System.setProperty("ANDROID_HOME", "//Users//tk-lpt-979//Library//Android//sdk");
//        System.setProperty("ANDROID_SDK_ROOT", "//Users//tk-lpt-979//Library//Android//sdk");
//    }
//	
//
//	@BeforeClass
//	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {
//		
//		setupEnvironment();
//
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(new File("//usr//local//lib//node_modules//appium//build//lib//main.js"))
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//		service.start();
//
//		UiAutomator2Options options = new UiAutomator2Options();
//		options.setDeviceName("FirstMediumEnvAuto");
//		options.setApp(
//				"//Users//tk-lpt-979//eclipse-workspace//CafeZupasApp//src//test//java//resources//app-staging-release.apk");
//		options.setAutomationName("UIAutomator2");
//		// TO give chrome driver path to do hybrid app
////		options.setChromedriverExecutable(null)
//		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
//
//	}
//
//	public void longPressAction(WebElement ele) {
//		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
//				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()), "duration", 2000);
//	}
//
//	public void scrollToEndAction() {
//		boolean canScrollMore;
//		do {
//			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
//					ImmutableMap.of("left", 0, "top", 100, "width", 500, "height", 600, "direction", "down", "percent",
//							20, "velocity", 0.8));
//		} while (canScrollMore);
//	}
//
//	public void swipeAction(WebElement ele) {
//		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
//				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.1));
//	}
//
//	public static String generateRandomEmail() {
//		long timestamp = System.currentTimeMillis();
//		return "testAuto" + timestamp + "@gmail.com";
//	}
//
//	@AfterClass
//	public void quitDriver() {
//		driver.quit();
//		service.stop();
//	}
//}



package CafeZupasAutomation.CafeZupasApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseSetup {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    public void setupEnvironment() {
        System.setProperty("ANDROID_HOME", "/Users/tk-lpt-979/Library/Android/sdk");
        System.setProperty("ANDROID_SDK_ROOT", "/Users/tk-lpt-979/Library/Android/sdk");
    }

    @BeforeClass
    public void configureAppium() throws MalformedURLException, URISyntaxException {
        setupEnvironment();
        
        System.out.println("ANDROID_HOME: " + System.getenv("ANDROID_HOME"));
        System.out.println("ANDROID_SDK_ROOT: " + System.getenv("ANDROID_SDK_ROOT"));
        
        Map<String, String> environment = new HashMap<>(System.getenv());
        environment.put("ANDROID_HOME", "/Users/tk-lpt-979/Library/Android/sdk");
        environment.put("ANDROID_SDK_ROOT", "/Users/tk-lpt-979/Library/Android/sdk");

        // Initialize and start Appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withEnvironment(environment)
                .build();
        
        if (!service.isRunning()) {
            service.start();
        }

        // Appium driver options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("FirstMediumEnvAuto");
        options.setApp("/Users/tk-lpt-979/eclipse-workspace/CafeZupasApp/src/test/java/resources/app-staging-release.apk");
        options.setAutomationName("UIAutomator2");

        // Initialize Android driver
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
    }

    // Utility methods for common actions
    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 0, "top", 100, "width", 500, "height", 600, "direction", "down", "percent",
                            20, "velocity", 0.8));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.1));
    }

    public static String generateRandomEmail() {
        long timestamp = System.currentTimeMillis();
        return "testAuto" + timestamp + "@gmail.com";
    }

    @AfterMethod(alwaysRun = true)
    public void stopAppiumIfNeeded() {
        // Stop Appium service if running to release the port
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        // Quit driver and stop service
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}

