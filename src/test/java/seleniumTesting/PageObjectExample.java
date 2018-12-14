package seleniumTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pageObjects.recipePage;

import static pageObjects.recipePage.food_3;


public class PageObjectExample {

    public static class simplePageObjectExample {
        private static WebDriver driver;

        @BeforeTest
        public void launchBrowser() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            String baseURL = "file:///C://Users//vhunko047//IdeaProjects//Hunko//src//test//resources//sampleHTML.html";
            driver.get(baseURL);
        }

        /**
         * Display the third and fifth item from the above list. Your output should be:
         * Applesauce, unsweetened
         * Juice, Cranberry-apple drink
         */
        @Test (priority=1, enabled = true)
        public void validItems() throws InterruptedException {
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
