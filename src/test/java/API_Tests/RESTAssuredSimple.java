package API_Tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Examples from Tools QA site
 */

//@Listeners(listenerSimple.class)
public class RESTAssuredSimple {
    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();

    /**
     * evaluation of GET method
     */
    @Test(priority = 1)
    public void GET_evaluation() {

        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response resp = httpRequest.request(Method.GET, "/Honolulu");
        String respBody = resp.getBody().asString();
        System.out.println("Response Body is => " + respBody);

        int statusCode = resp.getStatusCode();
        hardAssert.assertEquals(statusCode, 200, "Correct code returned.");

        String serverType = resp.header("Server");
        System.out.println("Server Type is: " + serverType);

        //use the JsonPath to retrieve data from body.
        JsonPath jpEvaluator = resp.jsonPath();
        String city = jpEvaluator.get("City");
        softAssert.assertEquals(city, "Honolulu", "City is rendered as: " + city);

        softAssert.assertAll();

        //second way to validate REST_assured response
        given().
                when().
                get("http://restapi.demoqa.com/utilities/weather/city").
                then().
                assertThat().
                statusCode(200);

        System.out.println("Status code is 200");
    }

    /**
     * evaluation of POST method
     */
    @Test(priority = 2)
    public void POST_evaluate() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        //create JSON object with parameters
        JSONObject requestParam = new JSONObject();
        requestParam.put("FirstName", "Volodymyr");
        requestParam.put("LastName", "Hunko");
        requestParam.put("UserName", "v0046");
        requestParam.put("Password", "Test1$");
        requestParam.put("Email", "vhun0001+46@gmail.com");

        //add a header
        request.header("Content_Type", "application/json");

        //add the JSON to body
        request.body(requestParam.toJSONString());

        //POST the request
        Response resp = request.post("/register");

        //Validate response
        int statusCode = resp.getStatusCode();
        hardAssert.assertEquals(statusCode, 201, "Status code is: " + statusCode);
        String successCode = resp.jsonPath().get("SuccessCode");
        softAssert.assertEquals(successCode, "OPERATION_SUCCESS", "returned code is: " + successCode);
        System.out.println("Response body: " + resp.body().asString());

        softAssert.assertAll();
    }

    /**
     * evaluation of authentication
     */
    @Test
    public void AutenticationBasic(){
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
        RequestSpecification requst = RestAssured.given();

        Response resp = requst.get();
        System.out.println("Code: "+ resp.getStatusLine());
        System.out.println("Message: "+resp.body().asString());

    }

    /**
     * deserialize in ArrayList
     */
    @Test
    public void convertToList(){
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
        RequestSpecification request = RestAssured.given();
        Response resp = request.get("");
        System.out.println(resp.body().asString());

        // We can convert the Json Response directly into a Java Array by using
        // JsonPath.getObject method. Here we have to specify that we want to
        // deserialize the Json into an Array of Book. This can be done by specifying
        // Book[].class as the second argument to the getObject method.
        Book[] books = resp.jsonPath().getObject("books",Book[].class );
        for(Book book : books)
        {
            System.out.println("Book title " + book.title);
        }

        //second method to get list of book
        JsonPath eval = resp.jsonPath();
        //get a list of all books
        List<String> allBooks = eval.getList("books.title");

        for(String book : allBooks){
            System.out.println("Book: "+book);
        }
    }

    /**
     * evaluation of POST method with deserialization on class
     */
    @Test(priority = 3)
    public void POST_Deserialise() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        //create JSON object with parameters
        JSONObject requestParam = new JSONObject();
        requestParam.put("FirstName", "Volodymyr");
        requestParam.put("LastName", "Hunko");
        requestParam.put("UserName", "v0089");
        requestParam.put("Password", "Test2$");
        requestParam.put("Email", "vhun0001+89@gmail.com");

        //add a header
        request.header("Content_Type", "application/json");

        //add the JSON to body request
        request.body(requestParam.toJSONString());

        //POST the request
        Response resp = request.post("/register");

        //get the response body as JASON
        ResponseBody body = resp.getBody();

        System.out.println(resp.body().asString());

        if(resp.statusCode() == 201) {
            System.out.println(resp.getStatusCode());
            //Deserialize the Response's body into class
            SuccessResponse respBody = body.as(SuccessResponse.class);
            System.out.println("Message: "+respBody.Message + "; Code: "+respBody.SuccessCode);
//            softAssert.assertEquals(respBody.SuccessCode, "OPERATION_SUCCESS",
//                    "User was not registered .");
//            softAssert.assertEquals(respBody.Message, "Operation completed successfully",
//            "All fine...");
//            softAssert.assertAll();
        }else{
            System.out.println(resp.getStatusCode());
            FailureResponse respBody = body.as(FailureResponse.class);
            System.out.println("fault: "+respBody.fault+"; ID: "+respBody.FaultId);
//            softAssert.assertEquals(respBody.fault, "FAULT_USER_ALREADY_EXISTS",
//                    "User was already registered, as expected.");
//            softAssert.assertEquals(respBody.FaultId, "User already exists",
//                    "As expected...");
        }

    }
}

/* class for deserialization of JSON response */
@JsonIgnoreProperties (ignoreUnknown = true)
class SuccessResponse{
    public String SuccessCode;
    public String Message;
}

/* failure response deserialization */
@JsonIgnoreProperties (ignoreUnknown = true)
class FailureResponse{
    public String FaultId;
    public String fault;
}

/**
 * class for deserialization book service used in this example
 */
@JsonIgnoreProperties (ignoreUnknown = true)
 class Book {
    String isbn;
    String title;
    String subtitle;
    String author;
    String published;
    String publisher;
    int pages;
    String description;
    String website;
}

