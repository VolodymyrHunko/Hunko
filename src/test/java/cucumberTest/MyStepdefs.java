package cucumberTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {
    @Given("^User is on Home Page$")
    public void userIsOnHomePage()  {
        System.out.println("1");
    }

    @When("^User Navigate to LogIn Page$")
    public void userNavigateToLogInPage()  {
        System.out.println("2");
    }

    @And("^User enters UserName and Password$")
    public void userEntersUserNameAndPassword()  {
        System.out.println("3");
    }

    @Then("^Message displayed Login Successfully$")
    public void messageDisplayedLoginSuccessfully()  {
        System.out.println("4");
    }

    @When("^User LogOut from the Application$")
    public void userLogOutFromTheApplication()  {
        System.out.println("5");
    }

    @Then("^Message displayed LogOut Successfully$")
    public void messageDisplayedLogOutSuccessfully()  {
        System.out.println("6");
    }
}
