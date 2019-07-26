package API_Tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * examples from:
 * techbeacon.com/app-dev-testing/how-perform-api-testing-rest-assured
 */
public class HamcrestValidation {
    private static ResponseSpecification responseSpec;

    @BeforeClass (description = "validate spec before request (if called)")
    public static void createResponseSpec() {

        responseSpec =
                new ResponseSpecBuilder().
                        expectStatusCode(200).
                        expectContentType(ContentType.JSON).
                        build();
    }


    @Test (description = "sample request and assertion")
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                statusCode(200).
                and().
                body("MRData.CircuitTable.Circuits", hasSize(20)).
                //and().body("MRData.CircuitTable.Circuits",is(array(hasSize(20)))).
                and().
                body("MRData.CircuitTable.Circuits[1].circuitId", equalTo("americas"));
    }

    @Test (description = "print whole body")
    public void printBody() {

        String respBody =
                //given(). /*can be skipped*/
                get("http://api.zippopotam.us/us/08823").
                        //get("http://ergast.com/api/f1/drivers/max_verstappen.json").

        getBody().
                        asString();

        System.out.println("Response Body is => " + respBody);
    }

    @Test(dataProvider = "seasonsAndNumberOfRaces", description = "parametrise test with data provider")
    public void parametriseData(String season, int numberOfRaces) {

        given().
                pathParam("raceSeason", season).
                when().
                get("http://ergast.com/api/f1/{raceSeason}/circuits.json").
                then().
                spec(responseSpec). //check success specification
                and().
                assertThat().
                body("MRData.CircuitTable.Circuits", hasSize(numberOfRaces));
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    @DataProvider
    public static Object[][] seasonsAndNumberOfRaces() {
        return new Object[][]{
                {"2017", 20},
                {"2016", 21},
                {"1966", 9}
        };
    }

    @Test (description = "sample Authentication with userName and password" +
            "")
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

    @Test (description = "authentication with OAuth 2")
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

    @Test (description = "extract value from first request and send request with this value as parameter")
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
                pathParam("circuitId", cId).
                when().
                get("http://ergast.com/api/f1/circuits/{circuitId}.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.Location[0].country", equalTo("Australia"));
        System.out.println("Country is: Australia");
    }

    @Test
    public void checkResponseTimeForApiCall() {

        given().
                when().
                get("http://api.zippopotam.us/us/08823").
                then().
                assertThat().
                time(lessThan(1000L), TimeUnit.MILLISECONDS);

        System.out.println("Timeout less than 1000 L: ");
    }

    @Test (description = "deserialization the response to POJO")
    public void deserialization(){

        Response resp = get("http://ergast.com/api/f1/2017/circuits.json");

        // We can convert the Json Response directly into a Java Array by using
        // JsonPath.getObject method. Here we have to specify that we want to
        // deserialize the Json into an Array of Circuits. This can be done by specifying
        // Circuit[].class as the second argument to the getObject method.
        Circuit obj = resp.jsonPath().getObject("MRData.CircuitTable.Circuits[0]",Circuit.class);
        System.out.println(obj.circuitId);

        Location loc = resp.jsonPath().getObject("MRData.CircuitTable.Circuits[0].Location",Location.class);
        System.out.println(loc.toString());

        Circuit [] races = resp.jsonPath().getObject("MRData.CircuitTable.Circuits",Circuit[].class);
        for (Circuit c : races){
            System.out.println(c.circuitName);
        }
    }

    @Test(description = "request using query parameters")
    public void testQuery(){
        given().
                queryParam("text", "testcase").
                when().
                get("http://md5.jsontest.com").
                then().
                assertThat().
                body("original", equalTo("testcase")).
                and().
                body("md5", notNullValue());
    }

    @Test (description = "communicated with XML response")
    public void checkTheListContainsOneJapaneseCar() {

        given().
                when().
                get("http://ergast.com/api/f1/drivers.xml").
                then().
                assertThat().
                body("MRData.DriverTable.Driver.findAll{it.Nationality == 'Italian'}.size()", equalTo(4)).
                and().
                body("MRData.DriverTable.Driver.@driverId.grep(~/ad.*/).size()", equalTo(3));
    }
}
