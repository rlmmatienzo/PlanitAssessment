package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.ContactPage;


public class CartPageStepDef {
    CartPage cartPage = new CartPage();

    @When("user checks if {string} item price is correct")
    public void user_checks_if_item_price_is_correct(String itemName) throws Throwable {
        Thread.sleep(750);
        cartPage.verifyItemPrice(itemName);
    }
    @Then("user checks if {string} item subtotal is correct")
    public void user_checks_if_item_subtotal_is_correct(String itemName) throws Throwable {
        Thread.sleep(750);
        cartPage.verifySubTotal(itemName);
    }
    @And("user verifies if total price is correct")
    public void user_verifies_if_total_price_is_correct() throws Throwable {
        Thread.sleep(750);
        cartPage.verifyTotalPrice();
    }

}
