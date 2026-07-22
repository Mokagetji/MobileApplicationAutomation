package Base;

import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;

    public void setUpAndLogin() throws IOException {
        config = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")+"src/test/resources/configs/config.properties");

        config.load(fis);

        DriverFactory.initDriver(config);
        driver = DriverFactory.getDriver();
    }

}
