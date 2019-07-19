import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// represent page in which user sends present
public class BuyMeSendPresent extends BuyMeBase{

    // construct object with external driver
    public BuyMeSendPresent(WebDriver _driver)
    {
        driver = _driver;
    }

    //Section of the event, saving and payment
    public void EventSelection() throws InterruptedException
    {
        SetSearchProperties(ConstantStrings.WHICH_EVENT, ConstantStrings.DROPDOWN_XPATH_CONVERTER, 4);
        WebElement afterPayment = driver.findElement(By.className(ConstantStrings.AFTER_PAY));
        afterPayment.click();
        Thread.sleep(1000);

        List<WebElement> sendBy = driver.findElements(By.className(ConstantStrings.MAILSENT));
        WebElement mailBtn = sendBy.get(1);
        ((JavascriptExecutor) driver).executeScript(ConstantStrings.JAVASCRIPT_SCROLL, mailBtn);
        mailBtn.click();
        Thread.sleep(1000);

        WebElement mailToPresent = driver.findElement(By.cssSelector(ConstantStrings.MAIL_TO_GIFT_CSS));
        mailToPresent.click();
        mailToPresent.sendKeys(ConstantStrings.MAIL_TO_GIFT);

        List<WebElement> submitBtns = driver.findElements(By.cssSelector("button[type='submit']"));
        WebElement saveMail = submitBtns.get(0);
        saveMail.click();
        Thread.sleep(1000);

        WebElement pay = submitBtns.get(1);
        pay.click();
    }

    //To insert a blessing
    public void Blessing()
    {
        WebElement blessing = driver.findElement(By.cssSelector(ConstantStrings.BLESSING_FIELD_CSS));
        blessing.click();
        blessing.sendKeys(ConstantStrings.BLESSING_INSERT);
    }

    // to upload image in relevant element
    public void UploadImage()
    {
        WebElement imageBtn = driver.findElement(By.cssSelector(ConstantStrings.UPLOAD_PIC_CSS));
        ((JavascriptExecutor) driver).executeScript(ConstantStrings.JAVASCRIPT_SCROLL, imageBtn);
        imageBtn.sendKeys(ConstantStrings.IMAGE_PATH);
    }

    //To whom the gift is
    public void InformationScreen()
    {
        WebElement forSomeoneElse = driver.findElement(By.className(ConstantStrings.FOR_SOMEONE));
        forSomeoneElse.click();

        List<WebElement> nameField = driver.findElements(By.cssSelector(ConstantStrings.RECEIVER_FIELD_CSS));
        WebElement name = nameField.get(0);
        name.click();

        name.sendKeys(ConstantStrings.RECEIVER_NAME);
        WebElement from = nameField.get(1);
        from.click();
        from.clear();
        from.sendKeys(ConstantStrings.SENDER_NAME);
    }
}
