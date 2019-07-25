package API_Tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;

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
                //and().body("MRData.CircuitTable.Circuits",is(array(hasSize(20)))).
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

    @Test (dataProvider="seasonsAndNumberOfRaces")
    public void parametriseData(String season, int numberOfRaces){

            given().
                    pathParam("raceSeason",season).
                    when().
                    get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                    then().
                    assertThat().
                    body("MRData.CircuitTable.Circuits",hasSize(numberOfRaces));
    }
    @NotNull
    @Contract(value = " -> new", pure = true)
    @DataProvider
    public static Object[][] seasonsAndNumberOfRaces() {
        return new Object[][]{
                {"2017",20},
                {"2016",21},
                {"1966",9}
        };
    }

    @Test
    public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {

        given().
                auth().
                preemptive().
                basic("username", "password"). //basic assess with userName & password
                when().
                //get("http://path.to/basic/secured/api").
                get("http://yahoo.com").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {

        given().
                auth().
                oauth2("YOUR_AUTHENTICATION_TOKEN_GOES_HERE"). //oAuth2 access with OTP
                when().
                get("http://path.to/oath2/secured/api").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry_ShouldBeAustralia() {

        // First, retrieve the circuit ID for the first circuit of the 2017 season
        String cId = given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                extract().
                path("MRData.CircuitTable.Circuits.circuitId[0]");
        System.out.println("ID is: " + cId);

        // Then, retrieve the information known for that circuit and verify it is located in Australia
        given().
                pathParam("circuitId",cId).
                when().
                get("http://ergast.com/api/f1/circuits/{circuitId}.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.Location[0].country",equalTo("Australia"));
        System.out.println("Country is: Australia");
    }
}
