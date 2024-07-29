package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class ContactPage extends BrowserDriver {

    java.util.logging.Logger LOGGER = Logger.getLogger(ContactPage.class.getName());
    public static WebElement element;

    @FindBy(xpath = "//a[@class='btn-contact btn btn-primary' and contains(text(),'Submit')]")
    public static WebElement submitBtn;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    public static final String MESSAGE = "message";
    public static final String ERRMSG_HEX_CLR = "#b94a48";
    public static final String IS_REQUIRED = "is required";

    String xpathMsgTxtBox = "//textarea[@id='message']";
    String xpathSubmitBtn = "//a[@class='btn-contact btn btn-primary' and contains(text(),'Submit')]";
    String xpathSendingFeedbackModal = "//div[@class='popup modal hide ng-scope in']";
    String xpathFormSubmitSuccessHeader = "//div[@ui-if='contactValidSubmit']//div[@class='alert alert-success']";

    public void clearTxtBoxFld (String txtBoxFld)
    {
        txtBoxFld = txtBoxFld.toLowerCase();
        String xpath = "//input[@id='"+txtBoxFld+"']";
        if(txtBoxFld.equalsIgnoreCase(MESSAGE))
        {
            Assert.assertTrue("Message textbox is displayed",driver.findElement(By.xpath(xpathMsgTxtBox)).isDisplayed());
            driver.findElement(By.xpath(xpathMsgTxtBox)).clear();
        }
        else if (txtBoxFld.equalsIgnoreCase("forename") || txtBoxFld.equalsIgnoreCase("surname") || txtBoxFld.equalsIgnoreCase("email") || txtBoxFld.equalsIgnoreCase("telephone"))
        {
            Assert.assertTrue("Element is displayed",driver.findElement(By.xpath(xpath)).isDisplayed());
            driver.findElement(By.xpath(xpath)).clear();
        }
        else
        {
            Assert.fail("Element is not found");
        }
    }

    public void inputTxtField (String value, String txtBoxFld)
    {
        txtBoxFld = txtBoxFld.toLowerCase();
        String xpath = "//input[@id='"+txtBoxFld+"']";
        if(txtBoxFld.equalsIgnoreCase(MESSAGE))
        {
            Assert.assertTrue("Message textbox is displayed",driver.findElement(By.xpath(xpathMsgTxtBox)).isDisplayed());
            driver.findElement(By.xpath(xpathMsgTxtBox)).sendKeys(value);
        }
        else if (txtBoxFld.equalsIgnoreCase("forename") || txtBoxFld.equalsIgnoreCase("surname") || txtBoxFld.equalsIgnoreCase("email") || txtBoxFld.equalsIgnoreCase("telephone"))
        {
            Assert.assertTrue("Element is displayed",driver.findElement(By.xpath(xpath)).isDisplayed());
            driver.findElement(By.xpath(xpath)).sendKeys(value);
        }
        else
        {
            Assert.fail("Element is not found");
        }
    }

    //User validates header error message
    public void validateHeaderErrMsg ()
    {
        String headerErrMsg = "//div[@class='alert alert-error ng-scope']";
        Assert.assertTrue("Header error message is displayed.",driver.findElement(By.xpath(headerErrMsg)).isDisplayed());
        LOGGER.info("Header error message is displayed.");
    }

    public void validateErrMessage (String errMsg)
    {
        String fontClr = "";
        String errMsgXpath = "";
        if(errMsg.contains("Invalid"))
        {
            errMsg = errMsg.replace("Invalid","");
            errMsgXpath = "//span[@id='"+errMsg.toLowerCase()+"-err' and contains(text(),'Please enter a valid "+errMsg.toLowerCase()+"')]";
        }
        else
        {
            errMsgXpath = "//span[@id='"+errMsg.toLowerCase()+"-err' and contains(text(),'"+errMsg+" "+IS_REQUIRED+"')]";
        }
        //Validate if error message is displayed
        driver.findElement(By.xpath(errMsgXpath)).isDisplayed();

        //Validate if error message color is correct
        fontClr = validateFontColor(errMsgXpath);
        Assert.assertEquals(ERRMSG_HEX_CLR, fontClr);
        LOGGER.info(errMsg+" error message is displayed.");
    }

    public String validateFontColor (String xpathElement)
    {
        String fontColor = "";
        fontColor = driver.findElement(By.xpath(xpathElement)).getCssValue("color");
        fontColor = Color.fromString(fontColor).asHex();
        LOGGER.info("Color is: " + fontColor);
        return fontColor;
    }


    public void verifyErrMsgNotDisplayed (String errMsg)
    {
        //If Email is required
        String errMsgXpath = "//span[@id='"+errMsg.toLowerCase()+"-err' and contains(text(),'"+errMsg+" "+IS_REQUIRED+"')]";
        Assert.assertTrue("Error Message is not displayed",driver.findElements(By.xpath(errMsgXpath)).size() == 0);

        //If Email is invalid
        errMsgXpath = "//span[@id='"+errMsg.toLowerCase()+"-err' and contains(text(),'Please enter a valid "+errMsg.toLowerCase()+"')]";
        Assert.assertTrue("Error Message is not displayed",driver.findElements(By.xpath(errMsgXpath)).size() == 0);
        LOGGER.info(errMsg+" error message is not displayed.");
    }

    //Clicks submit button
    public void clickSubmitBtn ()
    {
        Assert.assertTrue("Submit button is displayed.",driver.findElement(By.xpath(xpathSubmitBtn)).isDisplayed());
        driver.findElement(By.xpath(xpathSubmitBtn)).click();
        LOGGER.info("Submit button is clicked");
    }

    public void verifySuccessfulFormSubmission()
    {
        //Validate if sending progress is displayed
        Assert.assertTrue("Sending feedback progress is displayed.",driver.findElement(By.xpath(xpathSendingFeedbackModal)).isDisplayed());

        //Wait until sending feedback progress disappears
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(xpathSendingFeedbackModal))));
        Assert.assertTrue("Sending feedback progress is done.",driver.findElements(By.xpath(xpathSendingFeedbackModal)).size() == 0);

        //Validate header form submission is success
        Assert.assertTrue("Sending feedback progress is displayed.",driver.findElement(By.xpath(xpathFormSubmitSuccessHeader)).isDisplayed());
        LOGGER.info("Form submitted successfully.");
    }
}
