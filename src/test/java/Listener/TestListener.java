package Listener;

import Utilities.DriverFactory;
import Utilities.ScreenshotUtilities;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result)
    {
        takeScreenshot(result, "FAILED");
        System.out.println("Test Failed: "+result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Test Passed: "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        System.out.println("Test Skipped: "+result.getMethod().getMethodName());
    }

    private void takeScreenshot(ITestResult result,String status)
    {
        ScreenshotUtilities.captureScreenshot(DriverFactory.getDriver(),
                result.getTestName() +"-"+status);
    }
}
