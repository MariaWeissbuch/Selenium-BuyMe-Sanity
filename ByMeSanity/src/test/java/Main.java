import com.aventstack.extentreports.Status;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main {

    private static WebDriver driver;
    private static ReportsHandler reports = new ReportsHandler();


    @BeforeClass
    public static void GetBrowserInitReport() throws ParserConfigurationException, IOException, SAXException
    {
        String browser = XMLHandler.getData(ConstantStrings.BROWSER_KEY);
        String url = XMLHandler.getData(ConstantStrings.URL_KEY);

        if (browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\matan\\Desktop\\chrome new\\chromedriver.exe");
            ChromeOptions x =new ChromeOptions();
            x.addArguments("--headless");
            driver = new ChromeDriver(x);
            driver.get(url);
        }
        else if (browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\matan\\Desktop\\firefox driver\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get(url);
        }
        else
        {
            throw new IOException("Enter relevant browser name");
        }

        reports.InitializeReporting();
    }

    @Test
    public void Test01() throws InterruptedException, IOException
    {
        BuyMeMainPage mainPage = new BuyMeMainPage(driver);
        mainPage.ClickRegistration();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Entering registration screen");
    }

    @Test
    public void Test02() throws InterruptedException, IOException
    {
        BuyMeRegistration registrationPage = new BuyMeRegistration(driver);
        registrationPage.FillRegistrationForm();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Finished filling in registration information");
    }

    @Test
    public void Test03() throws InterruptedException, IOException, ParserConfigurationException, SAXException
    {
        BuyMeMainPage mainPage = new BuyMeMainPage(driver);
        mainPage.ChooseGift();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Finished choosing gift");

        mainPage.AssertWebPage();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Assertion of web address finished");
    }

    @Test
    public void Test04() throws InterruptedException, IOException
    {
        BuyMeGiftSelection giftSelection = new BuyMeGiftSelection(driver);
        giftSelection.ChooseBusiness();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Finished selecting gift");
    }

    @Test
    public void Test05() throws InterruptedException, IOException
    {
        BuyMeSendPresent sendPresent = new BuyMeSendPresent(driver);
        sendPresent.InformationScreen();
        Thread.sleep(1000);

        reports.CreateScreenShot(driver);
        reports.Log(Status.PASS, "Finished selecting sender/recipient information");
    }

    @Test
    public void Test06() throws InterruptedException, IOException
    {
        BuyMeSendPresent sendPresent = new BuyMeSendPresent(driver);
        sendPresent.Blessing();
        Thread.sleep(1000);
        reports.CreateScreenShot(driver);

        sendPresent.UploadImage();
        Thread.sleep(1000);
        reports.CreateScreenShot(driver);

        sendPresent.EventSelection();
        Thread.sleep(1000);
        reports.CreateScreenShot(driver);

        reports.Log(Status.PASS, "Finished selecting gift properties");
    }

    @AfterClass
    public static void Finalize()
    {
        reports.Log(Status.PASS, "Quitting browser after test");
        driver.quit();
        reports.FinalizeExtentReport();
    }
 }













