package API_Tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * examples from:
 * techbeacon.com/app-dev-testing/how-perform-api-testing-rest-assured
 */
public class HamcrestValidation {
    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                statusCode(200).
                and().
                body("MRData.CircuitTable.Circuits",hasSize(20)).
                and().
                body("MRData.CircuitTable.Circuits[1].circuitId",equalTo("americas"));
    }

    @Test
    public void printBody(){

        String respBody =
                //given(). /*can be skipped*/
                get("http://ergast.com/api/f1/2017/circuits.json").
                getBody().
                asString();

        System.out.println("Response Body is => " + respBody);
    }
}
