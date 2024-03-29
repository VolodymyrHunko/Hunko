package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
Class able to run only with tesng.xml file configured as 'browser' value. To run as single test, need to
define browser in 'BeforeTest'
 */
public class TestNGparemetrize {
        private static WebDriver driver;
        private String baseURL = "http://zoom.com";

//        @BeforeTest
//        @Parameters("browser")
//        public void launchBrowser(String browser) {
//            //parameter 'browser' accepted from testNG.xml file
//            if(browser.equals("firefox")) {
//                driver = new FirefoxDriver();
//                System.out.println("Browser is: " + browser);
//            }
//            else {
//                driver = new ChromeDriver();
//                System.out.println("Browser is: " + browser);
//            }
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            driver.manage().deleteAllCookies();
//            driver.manage().window().maximize();
//            driver.get(baseURL);
//            System.out.println("Before Test completed.");
//        }

        @Test(priority=2, enabled = true, dataProvider = "getData", singleThreaded = true)
        public void printTitle(String UID, String PSW) {
            System.out.println("Title is: "+driver.getTitle());
            System.out.println("UID is: "+ UID);
            System.out.println("PSW is: "+ PSW);
        }

//        @AfterTest
//        public void terminateBrowser(){
//            driver.close();
//            System.out.println("DONE with After Test");
//        }

        //If the name is not supplied, the data provider’s name automatically defaults to the method’s name.
        //A data provider returns an array of objects.
        @DataProvider(name="getData", parallel = true)
        public Object[][] getData() {
            //Object [][] data = new Object [rowCount][colCount];
            Object[][] data = new Object[2][2];

            data[0][0] = "FirstUid";
            data[0][1] = "FirstPWD";

            data[1][0] = "SecondUid";
            data[1][1] = "SecondPWD";

            return data;
        }

    }


