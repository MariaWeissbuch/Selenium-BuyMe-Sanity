import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// represents registration page in BuyMe.co.il
public class BuyMeRegistration extends BuyMeBase
{
    // construct object with external driver
    public BuyMeRegistration(WebDriver _driver)
    {
        driver = _driver;
    }

    //FillRegistration Form to enter mail and password and first name
    public void FillRegistrationForm() {
        // parse configurable values from XML
        String firstNameFromXml = "";
        String mailFromXml = "";
        String passwordFromXml = "";

        try
        {
            firstNameFromXml = XMLHandler.getData(ConstantStrings.FIRST_NAME_KEY);
            mailFromXml = XMLHandler.getData(ConstantStrings.MAIL_KEY);
            passwordFromXml = XMLHandler.getData(ConstantStrings.PASSWORD_KEY);
        }
        catch (Exception ex)
        {
            System.out.println("Exception getting value from xml");
        }

        // fill registration fields
        FillRegistrationFieldByXpath(ConstantStrings.PLACE_HOLDER_FIRST_NAME, firstNameFromXml);
        FillRegistrationFieldByXpath(ConstantStrings.PLACE_HOLDER_MAIL, mailFromXml);
        FillRegistrationFieldByXpath(ConstantStrings.PLACE_HOLDER_PASSWORD, passwordFromXml);
        FillRegistrationFieldByXpath(ConstantStrings.PLACE_HOLDER_PASSWORD_CONFIRM, passwordFromXml);

        // agree to site terms
        WebElement agreeCheckbox = driver.findElement(By.name(ConstantStrings.AGREE_CHECKBOX));
        ((JavascriptExecutor) driver).executeScript(ConstantStrings.JAVASCRIPT_CLICK, agreeCheckbox);

        // submit button for registration
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    // helper function to simplify element search
    private void FillRegistrationFieldByXpath(String tagName, String fill)
    {
        String xpathExpression = "//input[@placeholder='" + tagName + "']";
        WebElement fillElement = driver.findElement((By.xpath(xpathExpression)));
        fillElement.sendKeys(fill);
    }

}
