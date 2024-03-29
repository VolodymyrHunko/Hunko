import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static java.time.LocalTime.now;

public class practice {

    //Scanner works only with main() method, not @Test
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter something here:");
        int s = scan.nextInt();
        System.out.println("You entered %3 = " + s % 3);
    }

    @Test(description = "format date")
    void method() {
        LocalDate currentDeviceDate = LocalDate.now();
        LocalDate strtDate = currentDeviceDate.minusDays(3);
        int currentYear = currentDeviceDate.getYear();
        int startYear = strtDate.getYear();


        //diff conditions for formatter
        String startDateAppFormat;
        String currentDateAppFormat;
        if (currentYear != startYear) {
            startDateAppFormat = "d MMM yyyy";
            currentDateAppFormat = "d MMM yyyy";
        } else {
            if (!strtDate.getMonth().equals(currentDeviceDate.getMonth())) {
                startDateAppFormat = "d MMM";
                currentDateAppFormat = "d MMM yyyy";
            } else {
                startDateAppFormat = "d";
                currentDateAppFormat = "d MMMM yyyy";
            }
        }


        DateTimeFormatter startDateAppFormatter = DateTimeFormatter.ofPattern(startDateAppFormat);
        DateTimeFormatter currDateAppFormatter = DateTimeFormatter.ofPattern(currentDateAppFormat);

        String currentDate = currentDeviceDate.format(currDateAppFormatter);
        String startDate = strtDate.format(startDateAppFormatter);

        String month = String.valueOf(currentDeviceDate.getMonth());
        int day = currentDeviceDate.getDayOfMonth();
        LocalTime time = now();
        int hour = time.getHour();
        int min = time.getMinute();

        System.out.println(month + "_" + day + "_" + hour + ":" + min);

        System.out.println("current date: " + currentDate + "; Start: " + startDate);
    }

    @Test(description = "settings of calendar")
    void method_() {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(2020, 4 - 1, 8, 03, 00);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm");
        String end = sdf.format(c.getTime());
        c.add(Calendar.DATE, -6);
        String start = sdfStart.format(c.getTime());


        System.out.println("Calendar has settings: " + end + " - " + start);
        System.out.println(c.get(Calendar.DATE));
    }

    @Test(description = "string to array")
    void method_1() {
        String x = ".14.0 ccc, iii";
        String y = x.replaceAll("\\W+", " ");
        y = y.trim();
        char[] ch = y.toCharArray();
        String[] s = y.split(" ");
        System.out.println(Arrays.toString(s));
        System.out.println(Arrays.toString(ch));
    }

    @Test(description = "work with Map")
    void method_3() {
        Map<Character, Integer> hMap = new HashMap<>();
        String str = "Hello world here!";
        String str2 = str.toLowerCase().replaceAll("\\W+", "");
        char[] arr = str2.toCharArray();
        for (char ch : arr) {
            if (hMap.containsKey(ch)) {
                hMap.put(ch, hMap.get(ch) + 1);
            } else {
                hMap.put(ch, 1);
            }
        }

        System.out.println(hMap.entrySet());
        System.out.println(hMap.get('h'));
        for (char c : hMap.keySet()) {
            System.out.println(c + "\n");
        }
    }

    @Test(description = "validate GET method")
    void m3() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee/1";
        RequestSpecification thhpr = RestAssured.given();
        Response resp = thhpr.request(Method.GET);
        String str = resp.getBody().asString();
        System.out.println(str);

        JsonPath jp = resp.jsonPath();
        String s = jp.get("data.employee_name");
        System.out.println(s);
// one more way to run GET method
        given().
                when().
                get("http://restapi.demoqa.com/utilities/weather/city").
                then().
                assertThat().
                statusCode(200);
    }

    @Test (description = "working with ConcurrentHashMap to count parts of String")
    void m5() {
        Map<String, Integer> total = new ConcurrentHashMap<>(); //to avoid
        total.put("1234.vvv.yahoo.com", 10);
        total.put("yahoo.com", 5);
        total.put("vvv.com", 1);

        for (Map.Entry<String, Integer> entry : total.entrySet()) {
            List<String> ls = new ArrayList<>();
            String s = entry.getKey();
            String[] sa = s.split("\\.");
            String z = s;
            //to get a list of String parts
            for (String value : sa) {
                z = z.replaceFirst(value + ".", "");
                if (!z.contains(value))
                    ls.add(z);
            }
            System.out.println(ls);
            // if map contains key, increase value
            // if no contains, put a new entry
            for(String lsStr : ls){
                if(total.containsKey(lsStr)){
                    total.put(lsStr, total.get(lsStr) + entry.getValue());
                }else{
                    total.put(lsStr, entry.getValue());
                }
            }
        }
        System.err.println(total);
    }

    @Test(description = "WebDriver")
    void m4() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://zoom.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement solutions = driver.findElement(By.id("btnSolutions"));
        Assert.assertEquals(solutions.getText(), "SOLUTIONS");
        solutions.click();

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Button[title = 'See Pricing']")));
            System.out.println("text ->" + element.getText());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            driver.quit();
        }


    }
}

