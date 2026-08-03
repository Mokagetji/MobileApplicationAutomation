package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.AddReviewPage;
import Utilities.DriverFactory;
import Utilities.ScreenshotUtilities;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddReviewPage addReviewPage;

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

        addReviewPage = new AddReviewPage(driver, config);


    }

    public void LoginToNdosiAutomation() throws InterruptedException {
        loginPage.clickBurgerMenuButton();
        ScreenshotUtilities.captureScreenshot(driver,"Burger Menu Clicked");

        loginPage.clickSignInButton();
        ScreenshotUtilities.captureScreenshot(driver,"Sign In Clicked");

        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
        ScreenshotUtilities.captureScreenshot(driver,"Login Successful");

        Assert.assertTrue(loginPage.isLoginSuccessful(),"Login was unsuccessful");
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }


}
