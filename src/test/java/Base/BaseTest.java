package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @BeforeClass
    public void setUpAndLogin() throws IOException, InterruptedException {
        config = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/configs/config.properties");

        config.load(fis);

        DriverFactory.initDriver(config);
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver, config);
        LoginToNdosiAutomation();

        dashboardPage = new DashboardPage(driver, config);
    }

    public void LoginToNdosiAutomation() throws InterruptedException {
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }


}
