package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport  implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    String repName;

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time Stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter  = new ExtentSparkReporter(".\\reports\\" + repName); //Specify location of the report

        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of report

        sparkReporter.config().setReportName("RestAssuredAutomationProject"); //Name of the report

        sparkReporter.config().setTheme(Theme.STANDARD); // Them of the report

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application","Pet Store Users API");
        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment","Testing Environment");
        extentReports.setSystemInfo("user","Amir Ahmed");
    }

    public void onTestSuccess(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.createNode(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.log(Status.PASS,"Test Passed");
    }

    public void onTestFailure(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.createNode(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.log(Status.FAIL,"Test Failed");
        extentTest.log(Status.FAIL, iTestResult.getThrowable().getMessage());

    }

    public void onTestSkipped(ITestResult iTestResult){
        extentTest = extentReports.createTest(iTestResult.getName());
        extentTest.createNode(iTestResult.getName());
        extentTest.assignCategory(iTestResult.getMethod().getGroups());
        extentTest.log(Status.SKIP,"Test Skipped");
        extentTest.log(Status.SKIP, iTestResult.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context){
        extentReports.flush();
    }
}
