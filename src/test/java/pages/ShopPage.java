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

public class ShopPage extends BrowserDriver {
    java.util.logging.Logger LOGGER = Logger.getLogger(ShopPage.class.getName());

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    @FindBy(xpath = "//a[@class='btn-contact btn btn-primary' and contains(text(),'Submit')]")
    public static WebElement submitBtn;


    //Clicks buy button according to the item name
    public void clickBuyBtn (int quantity, String itemName) throws InterruptedException {
        //xPath of the buy button parent from header (item name) tag
        String xpathBuyItem = "//h4[contains(text(),'"+itemName+"')]/parent::div//p//a[@ng-click='add(item)']";

        //Wait until element is displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathBuyItem))));

        //Validate if element is displayed
        Assert.assertTrue("Item name and buy button is displayed.",driver.findElement(By.xpath(xpathBuyItem)).isDisplayed());
        for(int x=0; x<quantity; x++) {
            Thread.sleep(500);
            driver.findElement(By.xpath(xpathBuyItem)).click();
            LOGGER.info("Buy button is clicked; " + itemName + " added to cart.");
        }
        LOGGER.info("User selects " + quantity + " of " + itemName);
    }


}
