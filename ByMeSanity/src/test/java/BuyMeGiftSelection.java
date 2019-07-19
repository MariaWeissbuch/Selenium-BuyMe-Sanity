import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BuyMeGiftSelection extends BuyMeBase
{
    public BuyMeGiftSelection(WebDriver _driver)
    {
        driver = _driver;
    }

    //To select a gift
    public void ChooseBusiness()
    {
        driver.findElement(By.cssSelector("a[href*='" + ConstantStrings.GIFT_LINK + "']")).click();
    }
}
