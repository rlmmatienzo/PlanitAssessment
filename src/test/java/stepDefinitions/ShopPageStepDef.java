package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ShopPage;


public class ShopPageStepDef {
    ShopPage shopPage = new ShopPage();

    @Given("user clicks buy {int} times for {string} item")
    public void user_clicks_buy_for_an_item(Integer quantity, String itemName) throws Throwable {
        Thread.sleep(750);
        shopPage.clickBuyBtn(quantity, itemName);
    }
}
