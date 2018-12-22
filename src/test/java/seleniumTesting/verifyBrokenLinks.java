package seleniumTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.net.URL;
import java.util.List;

/*
 * class to find all broken links on page
 */
public class verifyBrokenLinks {

    public static void  main(String [] args) throws InterruptedException{
        WebDriver driver = new FirefoxDriver();
        driver.get("https://jquery.com/");
        driver.close();
        //driver.get("http://localhost:63342/Hunko/JQueryExamples.html");
    }

    //first - collect all links with attribute href
    public static List findAllLinks(WebDriver driver){

        return null;
    }

    //second - make HTTP request for all collected links
    public static String getBrokenLinks(URL url){

        return null;
    }
}
