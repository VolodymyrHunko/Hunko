package seleniumTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
 * class to find all broken links on page
 */
public class verifyBrokenLinks {

    public static void  main(String [] args) throws IOException {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("automation", profile);
        WebDriver driver = new FirefoxDriver(options);
        //to implement event listener
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventHandler handler = new eventHandler();
        eventDriver.register(handler);
       // driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
       // driver.get("https://jquery.com/");
        eventDriver.get("https://gobrief.com/");

        List<WebElement> allLinks = findAllLinks(driver);
        System.out.println("Total number of links: "+allLinks.size());
        for(WebElement element : allLinks){
            if(!getBrokenLinks(new URL(element.getAttribute("href"))).equals("OK")) {
                System.out.println("URL: "+ element.getAttribute("href")+" is broken, response is:"+
                        getBrokenLinks(new URL(element.getAttribute("href"))));
            }
        }
        driver.close();
    }

    //first - collect all links with attribute href
    private static List<WebElement> findAllLinks(WebDriver driver){
        List <WebElement> elements = driver.findElements(By.tagName("a"));
        elements.addAll(driver.findElements(By.tagName("img")));
        List <WebElement>fullList = new ArrayList<>();
        for(WebElement link : elements){
            if(link.getAttribute("href") != null){
                fullList.add(link);
            }
        }
        return fullList;
    }

    //second - make HTTP request for all collected links
    private static String getBrokenLinks(URL url) throws IOException {
        String response;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        response = connection.getResponseMessage();
        connection.disconnect();
        return response;
    }
}
