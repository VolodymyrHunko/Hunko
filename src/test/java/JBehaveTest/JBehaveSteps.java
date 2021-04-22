package JBehaveTest;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.Random;

import static org.junit.Assert.assertEquals;


public class JBehaveSteps {
    private int counter;
    private int previousValue;
    int i,  j , result ;

    @BeforeScenario
    public void allClear()
    {
        i =0 ;
        j =0 ;
        result = 0 ;
    }

    @Given("a counter")
    public void aCounter() {
    }

    @Given("the counter has any integral value")
    public void counterHasAnyIntegralValue() {
        counter = new Random().nextInt();
        previousValue = counter;
    }

    @When("the user increases the counter")
    public void increasesTheCounter() {
        counter++;
    }

    @Then("the value of the counter must be 1 greater than previous value")
    public void theValueOfTheCounterMustBe1Greater() {
        assertEquals(1, counter - previousValue);
    }

    @Then("verify the difference")
    public void thenVerifyTheDifference() {
        assertEquals("Sample Assert", 15, result);
    }

    @When("we subtract i from j")
    public void whenWeSubtractIFromJ() {
        result = i - j ;
    }

    @Given("numbers i and j")
    public void givenNumbersIAndJ() {
        i= 5;
        j = 10;
    }

    @Then("verify the sum")
    public void thenVerifyTheSum() {
        assertEquals("Sample Assert", 15, result);
    }

    @When("we add them")
    public void whenWeAddThem() {
        result = i + j ;
    }
}