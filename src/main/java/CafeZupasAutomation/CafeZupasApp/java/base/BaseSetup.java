package CafeZupasAutomation.CafeZupasApp.java.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;



public class BaseSetup {

	protected AndroidDriver driver;
	public AppiumDriverLocalService service;

	
	//keeping it just a reference but might be using it in further
//    @BeforeClass
//    public void setUp() throws MalformedURLException, URISyntaxException {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "emulator-5554");
//        capabilities.setCapability("app", "/path/to/app.apk");
//        capabilities.setCapability("automationName", "UIAutomator2");
//
////        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//        driver = new AndroidDriver(new URI("http://127.0.0.1:4723/wd/hub").toURL(), capabilities);
//
//    }

    /**
     * Configures and initializes Appium server and Android driver.
//     * @throws MalformedURLException 
     */
//    public void configureAppium() throws MalformedURLException, URISyntaxException {
//        setupEnvironment();
//
//        System.out.println("Configuring Appium with the following environment:");
//        System.out.println("ANDROID_HOME: " + System.getProperty("ANDROID_HOME"));
//        System.out.println("ANDROID_SDK_ROOT: " + System.getProperty("ANDROID_SDK_ROOT"));
//
//        Map<String, String> environment = new HashMap<>(System.getenv());
//        environment.put("ANDROID_HOME", "/Users/tk-lpt-979/Library/Android/sdk");
//        environment.put("ANDROID_SDK_ROOT", "/Users/tk-lpt-979/Library/Android/sdk");
//
//        // Initialize and start Appium server
//        service = new AppiumServiceBuilder()
//                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//                .withIPAddress("127.0.0.1")
//                .usingPort(4723)
//                .withEnvironment(environment)
//                .build();
//
//        if (!service.isRunning()) {
//            service.start();
//        }
//
//        // Configure Appium driver options
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("FirstMediumEnvAuto");
//        options.setApp("/Users/tk-lpt-979/eclipse-workspace/CafeZupasApp/src/test/java/resources/app-staging-release.apk");
//        options.setAutomationName("UIAutomator2");
//
//        // Initialize Android driver
//        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
//    }

    
	@BeforeMethod
    public void configureAppium() throws URISyntaxException, MalformedURLException {
        // Set up environment
        setupEnvironment();

        // Start Appium server
        startAppiumServer();

        // Initialize Android driver
        initializeDriver();
    }
    
    private void setupEnvironment() {
        System.setProperty("ANDROID_HOME", "/Users/tk-lpt-979/Library/Android/sdk");
        System.setProperty("ANDROID_SDK_ROOT", "/Users/tk-lpt-979/Library/Android/sdk");
    }
    
    
    private void startAppiumServer() {
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withEnvironment(getEnvironmentVariables())
                .build();

        if (!service.isRunning()) {
            service.start();
        }
    }
    
    
    private Map<String, String> getEnvironmentVariables() {
        Map<String, String> environment = new HashMap<>(System.getenv());
        environment.put("ANDROID_HOME", System.getProperty("ANDROID_HOME"));
        environment.put("ANDROID_SDK_ROOT", System.getProperty("ANDROID_SDK_ROOT"));
        return environment;
    }
    
    
    private void initializeDriver() throws URISyntaxException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("FirstMediumEnvAuto");
        options.setApp("/Users/tk-lpt-979/eclipse-workspace/CafeZupasApp/src/test/java/resources/app-staging-release.apk");
        options.setAutomationName("UIAutomator2");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options); // No <WebElement>
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
    }
    
    /**
     * Performs a long press on a specified element.
     */
    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
    }

    /**
     * Scrolls to the end of the page using Appium's scroll gesture.
     */
    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.of("left", 0, "top", 100, "width", 500, "height", 600, "direction", "down", "percent",
                            20, "velocity", 0.8));
        } while (canScrollMore);
    }

    /**
     * Performs a swipe action on a specified element.
     */
    public void swipeAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.1));
    }

    /**
     * Generates a random email string for testing purposes.
     */
    public static String generateRandomEmail() {
        long timestamp = System.currentTimeMillis();
        return "testAuto" + timestamp + "@gmail.com";
    }

    /**
     * Stops Appium server if still running after a test method.
     */
//    @AfterMethod(alwaysRun = true)
//    public void stopAppiumIfNeeded() {
//        if (service != null && service.isRunning()) {
//            service.stop();
//        }
//    }

    /**
     * Safely tears down the driver and Appium server after all tests.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while quitting driver: " + e.getMessage());
            }
        } else {
            System.out.println("Driver is already null, skipping quit.");
        }

        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    public void scrollToEnd() {
        // Add a generic scrolling utility here
    }
}
