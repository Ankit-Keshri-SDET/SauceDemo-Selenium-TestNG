package listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import utils.DriverManager;
import utils.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Listener implements ITestListener, IAnnotationTransformer {
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = ExtentManager.getInstance()
                .createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        ExtentTestManager.getTest().pass("✅ Test Passed: " + result.getName() +
                " | Duration: " + duration + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String base64Screenshot = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ExtentTestManager.getTest().fail("Test Failed. Screenshot attached.")
                    .addScreenCaptureFromBase64String(base64Screenshot, result.getName());
//            test.get().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
        } else {
            ExtentTestManager.getTest().fail("Driver was null, screenshot not captured.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip(result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String base64Screenshot = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ExtentTestManager.getTest()
                    .skip("Test Skipped. Screenshot attached.")
                    .addScreenCaptureFromBase64String(base64Screenshot, "Skipped Screenshot");
        } else {
            ExtentTestManager.getTest().skip("Driver was null, screenshot not captured for skipped test.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
