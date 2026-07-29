package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {

    static AppiumDriver driver;

    //initializing the driver
    public static void initDriver(Properties config) throws MalformedURLException {
        if (driver !=null) return;
        String platformName = config.getProperty("platformName").trim();
        String executionType = config.getProperty("executionType").trim();
        String appiumUrl = config.getProperty("appiumServer").trim();

        if (platformName.equalsIgnoreCase("Android")) {
            initAndroidDriver(config, executionType, appiumUrl);
        }
        else if (platformName.equalsIgnoreCase("iOS")){
            initIOSDriver(config,executionType,appiumUrl);
        }
        else {
            throw new RuntimeException("Unsupported platformName: " + platformName);
        }

    }
    //initializing the Android driver
    private static void initAndroidDriver(Properties config, String executionType, String appiumUrl) throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.getProperty("platformName"))
                .setAutomationName(config.getProperty("automationName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
            options.setCapability(
                    "appium:chromedriverExecutable",
                    "C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\chromedriver\\lib\\chromedriver\\chromedriver.exe"
            );
            //options.setCapability("appium:chromedriverExecutable", config.getProperty("chromedriverPath"));


            //options.setCapability("appium:noReset", true);

            System.out.println("Launching the Android Chrome browser");
        }

        else if (executionType.equalsIgnoreCase("nativeApp")) {
                String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
                options.setApp(appPath);
                System.out.println("Launching Android native app");
            }

        else {
                throw new RuntimeException("Unsupported executionType for Android: " + executionType);
            }

            driver = new AppiumDriver(URI.create(appiumUrl).toURL(),options);
            if (executionType.equalsIgnoreCase("mobileWeb")){
                String webUrl = config.getProperty("webUrl");
                driver.get(webUrl);
            }

        }
    //initializing the IOS driver
        private static void initIOSDriver(Properties config, String executionType, String appiumUrl) throws MalformedURLException {

            XCUITestOptions options = new XCUITestOptions()
                    .setPlatformName(config.getProperty("platformName"))
                    .setAutomationName(config.getProperty("automationName"));

            if (executionType.equalsIgnoreCase("mobileWeb")) {
                options.withBrowserName(config.getProperty("browserName"));
                System.out.println("Launching the edge browser");
            }

            else if (executionType.equalsIgnoreCase("nativeApp")) {
                String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
                options.setApp(appPath);
                System.out.println("Launching iOS native app");
            }

            else {
                throw new RuntimeException("Unsupported executionType for iOS: " + executionType);
            }

            driver = new AppiumDriver(URI.create(appiumUrl).toURL(),options);
            if (executionType.equalsIgnoreCase("mobileWeb")){
                String webUrl = config.getProperty("webUrl");
                driver.get(webUrl);
            }
    }

    //this method will be used by other classes to get the driver
    public static AppiumDriver getDriver(){
        return driver;
    }

    //this method quits or closes the driver after every desired test
    public static void quitDriver(){
        if (driver!=null){
            driver.quit();
            driver = null;
        }

    }

}
