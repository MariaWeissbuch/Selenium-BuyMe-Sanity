import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

// class to manage report entries and screenshot handling
public class ReportsHandler
{
    private static ExtentReports extent;
    private static ExtentTest test;

    // initialize all objects relevant for new report
    public void InitializeReporting()
    {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\matan\\Desktop\\report\\extent.html");

        // attach reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        // name your test and add description
        test = extent.createTest("BuyMe tests", "Automation tests for www.buyme.co.il");

        // add custom system info
        extent.setSystemInfo("PC", "Win64");
        extent.setSystemInfo("Tester", "Maria");

        test.log(Status.INFO, "Initializing extent report");
    }

    // wrapper method for capturing screenshot
    public void CreateScreenShot(WebDriver driver) throws IOException
    {
        String currentTime = String.valueOf(System.currentTimeMillis());

        test.pass("details",
                MediaEntityBuilder
                        .createScreenCaptureFromPath(takeScreenShot(
                                "C:\\Users\\matan\\Desktop\\report\\screenshot_" + currentTime, driver))
                        .build());
    }

    // inner method for taking screen shot
    private String takeScreenShot(String ImagesPath, WebDriver driver)
    {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

    // wrapper function for writing log entry with relevant status
    public void Log(Status entryStatus, String logEntry)
    {
        test.log(entryStatus, logEntry);
    }

    // method for finalizing the report
    public void FinalizeExtentReport()
    {
        // build and flush report
        extent.flush();
    }
}
