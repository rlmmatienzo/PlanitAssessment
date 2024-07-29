package utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

public class Hooks {
    public static HomePage driver;
    public static ChromeOptions options;
    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){
        driver.userEndsSession();
    }
}
