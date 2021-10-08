import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class BaseTest {
    WebDriver driver;
    private ExtentReports extent;
    ExtentTest testReport;
    private String reportPath = System.getProperty("user.dir") + "/src/test/resources/report";
    private static int screenshotID;

    @Parameters({"siteUrl"})
    @BeforeTest
    public void initBrowser(String homePageURL) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(homePageURL);
    }

    @BeforeSuite
    public void startReports(ITestContext testContext) {
        String htmlPath = reportPath + "/report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(htmlPath);
        reporter.config(ExtentSparkReporterConfig.builder()
                .documentTitle(testContext.getName())
                .reportName(testContext.getName())
                .build());
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        screenshotID = 1;
    }

    @BeforeMethod
    public void createNewTestInReport(Method method) {
        testReport = extent.createTest(method.getAnnotation(Test.class).description());
    }

    @AfterMethod
    public void generateReport(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String MethodName = result.getMethod().getMethodName();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            testReport.fail("<b>" + MethodName + " Failed, Reason: </b><br>" + result.getThrowable().getMessage() + "<br><b>StackTrace:</b> <pre>" + sw.toString() + "</pre>");
            String path = reportPath + "/FailureScr" + screenshotID + ".png";

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(path));
            testReport.addScreenCaptureFromPath(path);
            screenshotID++;
        } else {
            testReport.pass(result.getMethod().getMethodName() + " Passed");
        }
        extent.flush();
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
