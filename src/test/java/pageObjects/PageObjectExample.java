package pageObjects;

import helpers.eventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static pageObjects.recipePage.food_3;


public class PageObjectExample {

    public static class simplePageObjectExample {
        private static WebDriver driver;

        @BeforeTest
        public void launchBrowser() {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            String base = System.getProperty("user.dir");
            String baseURL = "file:"+base+"/src/test/resources/sampleHTML.html";

            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            eventHandler handler = new eventHandler();
            eventDriver.register(handler);
            eventDriver.get(baseURL);
        }

        /**
         * Display the third and fifth item from the above list. Your output should be:
         * Applesauce, unsweetened
         * Juice, Cranberry-apple drink
         */
        @Test (priority=1, enabled = true)
        public void validItems() throws InterruptedException {
//            Path sampleFile = Paths.get("src/test/resources/sampleHTML.html");
//            System.out.println(sampleFile.toUri().toString());

            Thread.sleep(3000);
            PageFactory.initElements(driver, recipePage.class);
            String thirdItem = food_3.getText();
            String fifthItem = recipePage.food_5.getText();

            Actions action = new Actions(driver);
            action.moveToElement(food_3).perform();

            Thread.sleep(3000);
            System.out.println(thirdItem + "\n" + fifthItem + "\n");
        }

        /**
         * Fetch each food name and its servings and store them in a Map.
         * Iterate through all the entries in the Map and print them.
         */
        @Test (priority=2, enabled = true)
        public void printMap() {

            recipePage.printRecipe(driver);
        }



        @AfterTest
        public void terminateBrowser(){
            driver.close();
            System.out.println("DONE");
        }
    }

}
