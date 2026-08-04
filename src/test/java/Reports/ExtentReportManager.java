package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extentReports;//creating extent report object

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            new File("target/reports").mkdirs();// creating a folder for reports
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/reports/AddReview.html");

            sparkReporter.config().setDocumentTitle("Automation Test Report");//setting the document title
            sparkReporter.config().setReportName("Android and iOS Automation Results");//setting the report name

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Framework", "Appium Hybrid Automation Framework");
            extentReports.setSystemInfo("Test Runner", "TestNG");
            extentReports.setSystemInfo("Platforms", "Android and iOS");
            extentReports.setSystemInfo("Execution Types", "Native App and Mobile Web");
        }

        return extentReports;
    }
}
