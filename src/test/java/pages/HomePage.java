package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserDriver;

import java.time.Duration;


public class HomePage extends BrowserDriver {
    private final String url = "https://jupiter.cloud.planittesting.com";

    public void userEndsSession()
    {
        driver.quit();
    }

    public void userNavigatesToHomepage()
    {
        initializeBrowser();
        driver.get(url);
        System.out.println("Navigating to Planit site, URL: " + url);
        //Allure.addAttachment("HTML Snapshot","text/html", driver.getPageSource(), "html");
    }

    public void clickMenuButton (String menu)
    {
        String xpath = "";
        if(menu.contains("Start Shopping"))
        {
            xpath = "//a[contains(text(),'"+menu+"')]";
        }
        else
        {
            menu = menu.toLowerCase();
            xpath = "//li[contains(@id,'" + menu + "')]";
        }

        try {
            //validate if element button is displayed
            if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
                //clicks menu button
                driver.findElement(By.xpath(xpath)).click();
                System.out.println("Element clicked.");
            } else {
                System.out.println("Element not clicked");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Element is not displayed.");
        }
    }
}
