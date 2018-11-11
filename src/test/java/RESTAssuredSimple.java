import helpers.listenerSimple;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

@Listeners(listenerSimple.class)
public class RESTAssuredSimple {
    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();

    /**evaluation of GET method*/
    @Test (priority = 1)
    public void GET_evaluation() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response resp = httpRequest.request(Method.GET, "/Honolulu");
        String respBody = resp.getBody().asString();
        System.out.println("Response Body is => "+respBody);

        int statusCode = resp.getStatusCode();
        hardAssert.assertEquals(statusCode,200, "Correct code returned.");

        String serverType = resp.header("Server");
        System.out.println("Server Type is: "+serverType);

        //use the JsonPath to retrieve data from body.
        JsonPath jpevaluator = resp.jsonPath();
        String city = jpevaluator.get("City");
        softAssert.assertEquals(city, "Honolulu", "City is rendered as: "+ city);

        softAssert.assertAll();
    }

    /**evaluation of POST method*/
    @Test (priority = 2)
    public void POST_evaluate() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        //create JSON object with parameters
        JSONObject requestParam = new JSONObject();
        requestParam.put("FirstName", "Volody");
        requestParam.put("LastName", "Hunko");
        requestParam.put("UserName", "v002");
        requestParam.put("Password", "Test1$");
        requestParam.put("Email", "vhun0001+2@gmail.com");

        //add a header
        request.header("Content_Type", "application/json");

        //add the JSON to body
        request.body(requestParam.toJSONString());

        //POST the request
        Response resp = request.post("/register");

        //Validate response
        int statusCode = resp.getStatusCode();
        hardAssert.assertEquals(statusCode, 200, "Status code is: "+statusCode);
        String successCode = resp.jsonPath().get("SuccessCode");
        softAssert.assertEquals(successCode, "Correct Success code was returned", "returned code is: "+successCode);
        System.out.println("Response body: " + resp.body().asString());

        softAssert.assertAll();
    }

}
