package API_Tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class TestGoogleAPIwithTestNG {
    private final Assertion hardAssert = new Assertion();
    private final SoftAssert softAssert = new SoftAssert();

    Response resp;
    Books b = new Books();
    valueInfo vi = new valueInfo();

    @Test(dataProvider = "getData")
    public void test_1(String title, int index) {
        RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
        resp = given().request(Method.GET, "volumes?q=title:"+title);
        System.out.println(resp.getHeaders());
        System.out.println(resp.getBody().asString());
        hardAssert.assertEquals(resp.getStatusCode(), 200);
        b.setTotal(resp);
        hardAssert.assertTrue(b.totalItems > 0);
    }

    @Test(dataProvider = "getData")
    public void test_2(String title, int index) {
        RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
        resp = given().request(Method.GET, "volumes?q=title:"+title);
        hardAssert.assertEquals(resp.getStatusCode(), 200);
        b.setItems(resp);
        hardAssert.assertTrue(b.itm.length > 0);
    }

    @Test (dataProvider = "getData")
    public void test_3(String title, int index) {
        RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
        resp = given().request(Method.GET, "volumes?q=title:"+title);
        hardAssert.assertEquals(resp.getStatusCode(), 200);
        vi.setTitle(resp, index);
        System.out.println(vi.title);
        hardAssert.assertTrue(vi.title.contains(title));
    }

    @DataProvider(name="getData")
    public Object[][] getData() {
        Object[][] data = new Object[2][2];

        data[0][0] = "Wizard of Oz";
        data[0][1] = 0;

        data[1][0] = "Alice in Wonderland";
        data[1][1] = 1;

        return data;
    }
}

/**
 * class for deserialization book service used in this example
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Books {
    int totalItems;
    String kind;
    items[] itm;

    public void setTotal(Response r) {
        totalItems = r.jsonPath().get("totalItems");
    }

    public void setItems(Response r) {
        itm = r.jsonPath().getObject("items", items[].class);
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class items {
    String id;
    valueInfo vi;

    public void setId(Response r, int index) {
        id = r.jsonPath().get("items[" + index + "].id");
    }
}

class valueInfo {
    String title;
    String[] authors;
    String publisher;
    String description;

    public void setTitle(Response r, int index) {
        title = r.jsonPath().get("items[" + index + "].volumeInfo.title");
    }
}
