package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.AddReviewPage;
import Reports.ExtentReportManager;
import Utilities.DriverFactory;
import Utilities.ScreenshotUtilities;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected AddReviewPage addReviewPage;
    protected ExtentTest extentTest;

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

    @BeforeMethod
    public void createReportTest(Method method)
    {
        extentTest = ExtentReportManager.getExtentReports().createTest(method.getName());//creating test entry
        extentTest.info("Test execution started");
    }

    @AfterMethod(alwaysRun = true)
    public void recordTestResult(ITestResult result)
    {
        String testName = result.getMethod().getMethodName();
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                if (result.getThrowable() != null) {
                    extentTest.fail(result.getThrowable());
                } else {
                    extentTest.fail("Test failed");
                }

                AppiumDriver driver = DriverFactory.getDriver();
                if (driver!=null)
                {
                    ScreenshotUtilities.captureScreenshot(driver,testName);
                }
                File originalScreenshot = new File("screensots/"+testName+".png");
                if (originalScreenshot.exists())
                {
                    Path reportScreenshotFolder = Path.of("target","reports","screenshots");
                    Files.createDirectories(reportScreenshotFolder);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
