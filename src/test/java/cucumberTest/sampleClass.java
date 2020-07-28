package cucumberTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class sampleClass {

    public static void main(String[] args) {
        WebDriver driver;
        //Set up driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        //possible to run on particular Chrome version or in HeadLess mode
//        options.addArguments("--user-agent=Chrome/60.0.3112.78");
//        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Step #1: navigate to PHP.net site, validate Title
        driver.get("http://php.net/index");
        Assert.assertEquals(driver.getTitle(),"PHP: Hypertext Preprocessor");

        //Step #2: enter 'eval' text in search box and hit Enter
        driver.findElement(By.cssSelector("input[type='search']")).sendKeys("eval");
        driver.findElement(By.cssSelector("input[type='search']")).sendKeys(Keys.ENTER);

        //Step #3:validate page was redirected
        Assert.assertEquals( driver.getCurrentUrl(),"https://php.net/manual/en/function.eval.php",
                "browser should redirect to https://php.net/manual/en/function.eval.php");

        //Step #4: get a list of elements with class '.caution' and validate it is !=0
        List<WebElement> caution = driver.findElements(By.cssSelector("div.caution"));
        Assert.assertNotEquals(0,caution);
        System.out.println("Page has Cautions: "+caution.size());
        //Step #5: Log Out
        System.out.println("LogOut Successfully");
        driver.quit();
    }
}
