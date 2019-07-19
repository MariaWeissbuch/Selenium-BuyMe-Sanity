import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BuyMeBase {

    // these members will be common to all sub classes (actual pages in website)
    protected WebDriver driver;
    protected ExtentTest test;

    // SetSearchProperties selecting search parameters from the drop down
    // common to two separate classes - so it's implemented here in base class
    protected void SetSearchProperties(String searchElement, String classToOptions, int selectionIndex)
    {
        WebElement element = driver.findElement(By.partialLinkText(searchElement));
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).pollingEvery(1, TimeUnit.SECONDS);
        element.click();

        String xPathExpression = "//ul[@class='" + classToOptions + "']/li";
        List<WebElement> options = driver.findElements(By.xpath(xPathExpression));
        options.get(selectionIndex).click();
    }
}
