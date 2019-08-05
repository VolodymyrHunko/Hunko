package API_Tests;

import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
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
                then().log().body().
                assertThat().
                statusCode(200).
                and().
                body("MRData.CircuitTable.Circuits", hasSize(20)).
                and().
                body("MRData.CircuitTable.Circuits[1].circuitId", equalTo("americas")).
                and().
                body("MRData.CircuitTable.Circuits.findAll{it.circuitId == 'americas'}.size()", greaterThan(0));
    }

    @Test (description = "print whole body")
    public void printBody() {

        String respBody =
                get("http://api.zippopotam.us/us/08823").
                        getBody().
                        asString();
        String sessionId = get("http://api.zippopotam.us/us/08823").sessionId();

        System.out.println("Response Body is => " + respBody + "\nSession ID: "+sessionId);
    }

    @Test (description = "parametrize a request")
    public void checkCityForZipCode() {

        given().
                pathParam("country","us").
                pathParam("zipcode","90210").
                when().
                get("http://api.zippopotam.us/{country}/{zipcode}").
                then().
                log().body().
                assertThat().
                body("places.'place name'[0]",equalTo("Beverly Hills"));
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

    @Test (description = "sample Authentication with userName and password")
    public void test_APIWithBasicAuthentication_ShouldBeGivenAccess() {

        given().
                auth().
                preemptive().
                basic("postman", "password"). //basic assess with userName & password
                when().
                get("https://postman-echo.com/basic-auth").
                then().
                log().body().
                assertThat().
                statusCode(200);
    }

    @Test (description = "authentication with form")
    public  void testFormAuthentication(){
        given().
                auth().
                form("volodymyr.hunko@gmail.com","Gunko$22",
                new FormAuthConfig("frmMain","userid","password")).
                when().
                get("https://eu1.concursolutions.com/nui/signin").
                then().
                log().body().
                assertThat().
                statusCode(200);
}

    @Test (description = "authentication with OAuth 2")
    public void test_APIWithOAuth2Authentication_ShouldBeGivenAccess() {

        given().
                auth().
                oauth2("YOUR_AUTHENTICATION_TOKEN_GOES_HERE"). //oAuth2 access with OTP
                when().
                get("https://www.googleapis.com/books/v1/volumes?q=isbn:0735619670").
                then().log().all().
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

    @Test (description = "get response time")
    public void checkResponseTimeForApiCall() {

        given().
                when().
                get("http://api.zippopotam.us/us/08823").
                then().
                assertThat().
                time(lessThan(1000L), TimeUnit.MILLISECONDS);

        System.out.println("Timeout less than 1000 L: ");
    }

    @Test(description = "request using query parameters")
    public void testQuery(){
        given().
                queryParam("text", "testcase").
                when().
                get("http://md5.jsontest.com").
                then().
                log().body().
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
                log().body().
                rootPath("MRData.DriverTable.Driver"). //use rootPath instead entering it every time
                assertThat().
                body("findAll{it.Nationality == 'Italian'}.size()", equalTo(4)).
                and().
                body("@driverId.grep(~/ad.*/).size()", equalTo(3)).
                and().
                body("[-1].Nationality", equalTo("British"));

    }

    @Test (description = "(de)-serialization POJO class POST-GET flow")
    public void deserializationPOGO(){
        Address myAddress =
                new Address("First", "Last", "First_Last1", "Amsterdam", "xxx+1@gmail.com");

        Response resp = given().
                contentType("application/json").
                body(myAddress).
                when().
                post("http://restapi.demoqa.com/customer/register");
        System.out.println("POST sent");

        //get the response body as JASON
        ResponseBody body = resp.getBody();

        if(resp.statusCode()==201){
            System.out.println("Response code: "+resp.getStatusCode());
            System.out.println(resp.body().asString());
            //deserialize response to class (one way -> resp.getBody().as(.class)
            SuccessResponse respBodyS = body.as(SuccessResponse.class);
            System.out.println("Message: "+respBodyS.Message + "; Code: "+respBodyS.SuccessCode);
        }else {
            System.out.println("Response code: "+resp.getStatusCode());
            System.out.println(resp.body().asString());
            //deserialize response to class( diff way -> resp.jasonPath.getObject(.class)
            FailureResponse respBodyF = resp.jsonPath().getObject("",FailureResponse.class);
            System.out.println("fault: "+respBodyF.fault+"; ID: "+respBodyF.FaultId);
        }
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

}


