import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

// class representing main page in BuyMe.co.il
public class BuyMeMainPage extends BuyMeBase
{
    // construct main page with external driver
    public BuyMeMainPage(WebDriver _driver)
    {
        driver = _driver;
    }

    //RegClick module clicks on register button and selects register.
    public void ClickRegistration()
    {
        WebElement RegBtn = driver.findElement(By.partialLinkText(ConstantStrings.REGISTRATION_TEXT));
        ((JavascriptExecutor) driver).executeScript(ConstantStrings.JAVASCRIPT_CLICK, RegBtn);

        WebElement RegSelect = driver.findElement(By.className(ConstantStrings.TO_REGISTRATE));
        ((JavascriptExecutor) driver).executeScript(ConstantStrings.JAVASCRIPT_CLICK, RegSelect);
    }

    //ChoseGift selects from each drop down
    public void ChooseGift()
    {
        SetSearchProperties(ConstantStrings.GIFT_PRICE, ConstantStrings.DROPDOWN_XPATH_CONVERTER, 2);
        SetSearchProperties(ConstantStrings.AREA_SELECTED, ConstantStrings.DROPDOWN_XPATH_CONVERTER, 3);
        SetSearchProperties(ConstantStrings.CATEGORY_SELECTED, ConstantStrings.DROPDOWN_XPATH_CONVERTER, 3);

        driver.findElement(By.cssSelector("a[href*='" + ConstantStrings.SEARCH_GIFT + "']")).click();
    }

    //Assert WebPage checkinng the current URL if it as it was before
    public void AssertWebPage() throws ParserConfigurationException, IOException, SAXException
    {
        String currentURL;
        currentURL = driver.getCurrentUrl();
        String expectedURL = "";
        expectedURL = XMLHandler.getData(ConstantStrings.URL_KEY);
        Assert.assertNotEquals(expectedURL, currentURL);
    }
}
