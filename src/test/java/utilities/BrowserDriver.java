package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserDriver {
    public static WebDriver driver;
    public static ChromeOptions options;
    //public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));


    //Initialize browser driver
    public void initializeBrowser()
    {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        System.out.println("Launching google chrome....");
        this.driver.manage().window().maximize();
    }
}
