package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected HomePage homePage;
    protected WebDriver driver;
    public static ExtentReports extentReports;
    public ExtentTest extentTest;

    @BeforeSuite(groups = {"api", "regression", "smoke"})
    public void initialiseExtentReports() throws IOException {
        Constant.setUpTestEnvData();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("QA Owner", "Gulammustufa Momin");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", Constant.testEnv);
    }

    @AfterSuite(groups = {"api", "regression", "smoke"})
    public void generateReport() {
        extentReports.flush();
    }

    @Parameters("browserName")
    @BeforeMethod(groups = {"api", "regression", "smoke"})
    public void openBrowser(@Optional String browserNameFromXml, ITestContext context, Method method) {
        extentTest = extentReports.createTest(method.getAnnotation(Test.class).testName());
        String author = context.getCurrentXmlTest().getParameter("author");
        if(author!=null)extentTest.assignAuthor(author);

        // First priority of the browser name will be from command line, then xml file
        String browserName = getBrowserName() != null ? getBrowserName() : browserNameFromXml;
        if(browserName!=null)extentTest.assignDevice(browserName);
        if (browserName!=null && !browserName.equals("api")) {
            switch (browserName) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(getChromeOptions());
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
            driver.get(Constant.WEB_BASE_URL);
            goHome();
            extentTest.info("Browser opened");
        }
    }

    @AfterMethod(groups = {"api", "regression", "smoke"})
    public void checkTestStatus(Method method, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            if (driver != null) {
                String screenshotPath = captureScreenshot(result);
                extentTest.addScreenCaptureFromPath(screenshotPath);
                extentTest.info("Screenshot path: " + screenshotPath);
            }
            extentTest.fail(method.getName() + "Error: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass(method.getName() + " is passed");
        }
        extentTest.assignCategory(method.getAnnotation(Test.class).groups());
        if (driver != null) {
            closeBrowser();
        }
    }

    public void goHome() {
        homePage = new HomePage(driver);
    }

    private String captureScreenshot(ITestResult result) {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File sourceFile = camera.getScreenshotAs(OutputType.FILE);
        File destFile = new File("./screenshots/" + result.getName() + ".png");
        try {
            FileUtils.copyFile(sourceFile, destFile);
            System.out.println("Screenshot taken: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return destFile.getPath();
    }

    private void closeBrowser() {
        if (driver != null) {
            driver.quit();
            extentTest.info("Browser closed");
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-logging",
                "--log-level=3",
                "--remote-allow-origins=*");
        return options;
    }

    private String getBrowserName() {
        if (System.getProperty("browser") != null) {
            return System.getProperty("browser");
        } else {
            return null;
        }
    }

}
