package stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static utilities.BrowserDriver.driver;

public class BasePageStepDef {
    HomePage homePage = new HomePage();

    @Given("^user navigates to homepage$")
    public void user_navigates_to_homepage() throws Throwable {
        homePage.userNavigatesToHomepage();
    }

    @When("^user clicks \"([^\"]*)\" menu button$")
    public void user_clicks_menu_button(String menu) throws Throwable {
        Allure.getLifecycle().getCurrentTestCase();
        Thread.sleep(750);
        homePage.clickMenuButton(menu);
    }

    @Then("^user ends the session$")
    public void user_ends_session() throws Throwable {
        Thread.sleep(750);
        homePage.userEndsSession();
    }
}
