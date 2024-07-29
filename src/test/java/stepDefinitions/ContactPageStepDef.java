package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

public class ContactPageStepDef extends HomePage {

    ContactPage contactPage = new ContactPage();

    @Then("^user clears \"([^\"]*)\" textbox field$")
    public void user_clears_textbox_field(String textBox) throws Throwable {
        Thread.sleep(750);
        contactPage.clearTxtBoxFld(textBox);
    }

    //Input in textbox on specific fields
    @Then("^user inputs \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void user_inputs_forename(String input, String field) throws Throwable {
        Thread.sleep(750);
        contactPage.inputTxtField(input, field);
    }

    @Then("^user clicks submit button$")
    public void user_clicks_submit_button() throws Throwable {
        Thread.sleep(750);
        contactPage.clickSubmitBtn();
    }

    @Then("^user validates header error message$")
    public void user_validates_header_error_message() throws Throwable {
        Thread.sleep(750);
        contactPage.validateHeaderErrMsg();
    }

    @Then("^user validates error message is displayed for \"([^\"]*)\" mandatory field$")
    public void user_validates_error_message_is_displayed_for_mandatory_field(String field) throws Throwable {
        Thread.sleep(750);
        contactPage.validateErrMessage(field);
    }
    @Then("^user validates error message is not displayed for \"([^\"]*)\" mandatory field$")
    public void user_validates_error_message_is_not_displayed_for_mandatory_field(String field) throws Throwable {
        Thread.sleep(750);
        contactPage.verifyErrMsgNotDisplayed(field);
    }

    @And("^user verifies form is submitted successfully$")
    public void user_verify_form_is_submitted_successfully() throws Throwable {
        Thread.sleep(750);
        contactPage.verifySuccessfulFormSubmission();
    }
}
