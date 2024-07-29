package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserDriver;

import java.time.Duration;
import java.util.logging.Logger;

public class CartPage extends BrowserDriver {
    java.util.logging.Logger LOGGER = Logger.getLogger(CartPage.class.getName());

    private static final double teddyBearPrice = 12.99;
    private static final double stuffedFrogPrice = 10.99;
    private static final double handmadeDollPrice = 10.99;
    private static final double fluffyBunnyPrice = 9.99;
    private static final double smileyBearPrice = 14.99;
    private static final double funnyCowPrice = 10.99;
    private static final double valentineBearPrice = 14.99;
    private static final double smileyFacePrice = 9.99;
    private static final double DELTA = 1e-15;

    public static final String TEDDY_BEAR = "Teddy Bear";
    public static final String STUFFED_FROG = "Stuffed Frog";
    public static final String HANDMADE_DOLL = "Handmade Doll";
    public static final String FLUFFY_BUNNY = "Fluffy Bunny";
    public static final String SMILEY_BEAR = "Smiley Bear";
    public static final String FUNNY_COW = "Funny Cow";
    public static final String VALENTINE_BEAR = "Valentine Bear";
    public static final String SMILEY_FACE = "Smiley Face";

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    public void verifySubTotal (String itemName) throws InterruptedException {
        double quantity = 0;
        double price = 0;
        double subTotal = 0;
        String priceString = "";
        String subTotalString = "";

        //xPath of the quantity button parent from the item name)
        String xpathQuantity = "//td[contains(text(),'"+itemName+"')]/following-sibling::td//input";
        String xpathPrice = "//td[contains(text(),'"+itemName+"')]/following-sibling::td[1][contains(text(),'')]";
        String xpathSubtotal = "//td[contains(text(),'"+itemName+"')]/following-sibling::td[3][contains(text(),'')]";

        //Validate if all elements are displayed
        Assert.assertTrue("Quantity is displayed.",driver.findElement(By.xpath(xpathQuantity)).isDisplayed());
        Assert.assertTrue("Price is displayed.",driver.findElement(By.xpath(xpathPrice)).isDisplayed());
        Assert.assertTrue("Subtotal is displayed.",driver.findElement(By.xpath(xpathSubtotal)).isDisplayed());

        //Get item price
        priceString = driver.findElement(By.xpath(xpathPrice)).getText();

        //Convert price to double, removing currency sign
        price = Double.parseDouble(priceString.replace("$",""));

        //Convert quantity to double
        quantity = Double.parseDouble(driver.findElement(By.xpath(xpathQuantity)).getAttribute("value"));

        //Subtotal is equal to price time quantity
        subTotal = price * quantity;

        //Add currency sign and compare the value to the subtotal element
        subTotalString = "$" + String.valueOf(subTotal);

        //Compare subtotal extracted from other column to extracted quantity multiplied to price
        Assert.assertEquals("Subtotal is correct.", subTotalString, driver.findElement(By.xpath(xpathSubtotal)).getText());

        LOGGER.info("Subtotal of " + itemName + " is " + subTotalString);
    }

    public void verifyItemPrice (String itemName) throws InterruptedException {
        double price = 0;
        String priceString = "";
        //xPath of the price parent from the item name
        String xpathPrice = "//td[contains(text(),'"+itemName+"')]/following-sibling::td[1][contains(text(),'')]";

        //Wait until element is displayed
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathPrice))));

        //Validate if element are displayed
        Assert.assertTrue("Price is displayed.",driver.findElement(By.xpath(xpathPrice)).isDisplayed());

        //Get item price
        priceString = driver.findElement(By.xpath(xpathPrice)).getText();

        //Convert price to double, removing currency sign
        price = Double.parseDouble(priceString.replace("$",""));

        switch (itemName)
        {
            case TEDDY_BEAR:
                Assert.assertEquals(teddyBearPrice, price, DELTA);
                break;
            case STUFFED_FROG:
                Assert.assertEquals(stuffedFrogPrice, price, DELTA);
                break;
            case HANDMADE_DOLL:
                Assert.assertEquals(handmadeDollPrice, price, DELTA);
                break;
            case FLUFFY_BUNNY:
                Assert.assertEquals(fluffyBunnyPrice, price, DELTA);
                break;
            case SMILEY_BEAR:
                Assert.assertEquals(smileyBearPrice, price, DELTA);
                break;
            case FUNNY_COW:
                Assert.assertEquals(funnyCowPrice, price, DELTA);
                break;
            case VALENTINE_BEAR:
                Assert.assertEquals(valentineBearPrice, price, DELTA);
                break;
            case SMILEY_FACE:
                Assert.assertEquals(smileyFacePrice, price, DELTA);
                break;
            default:
                throw new IllegalArgumentException("Product does not exist.");
        }
        //Validate if element are displayed
        Assert.assertTrue("Price is displayed.",driver.findElement(By.xpath(xpathPrice)).isDisplayed());
        LOGGER.info("The price of " + itemName + " is " + price);

    }

    public void verifyTotalPrice () throws InterruptedException {
        int totalItems = 8; //Currently the total items can be added on a new row is 8
        double totalPrice = 0;
        double subTotal = 0;

        String priceString = "";
        String subTotalString = "";
        String xpathTotalPrice = "//strong[@class='total ng-binding']";

        //Validate if total price element are displayed
        Assert.assertTrue("Total price is displayed.",driver.findElement(By.xpath(xpathTotalPrice)).isDisplayed());

        //Creates a loop to count how many items listed in the row
        for(int x=1; x<totalItems; x++)
        {
            //Validates if item is displayed in the row then fetch its subtotal
            if(driver.findElements(By.xpath("//tr["+x+"]//td[1][contains(text(),'')]/following-sibling::td[3][contains(text(),'')]")).size() == 1) {
                subTotalString = driver.findElement(By.xpath("//tr[" + x + "]//td[1][contains(text(),'')]/following-sibling::td[3][contains(text(),'')]")).getText();
                subTotal = Double.parseDouble(subTotalString.replace("$",""));
                totalPrice = totalPrice + subTotal; //This adds subtotal
                LOGGER.info("No more item left!");
            }
            //If there's no more item listed beyond what is added
            else
            {
                LOGGER.info("No more item left!");
            }
        }

        LOGGER.info("Total price after adding subtotals: " + totalPrice);
        priceString = String.valueOf(totalPrice);

        Assert.assertTrue("Total price is correct.", driver.findElement(By.xpath("//strong[@class='total ng-binding' and contains(text(),'"+priceString+"')]")).isDisplayed());
    }
}
