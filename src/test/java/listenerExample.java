

import helpers.listenerSimple;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


    @Listeners(listenerSimple.class)//include this annotation to get listener's output
    public class listenerExample {
    WebDriver driver = new ChromeDriver();

    //TC should to pass
    @Test
    public void TestToPass(){
        System.out.println("This method to pass test");
        driver.get("https://www.softwaretestingmaterial.com/100-software-testing-interview-questions/");
        driver.getTitle();
        driver.quit();
    }

    // In the above method, we have already closed the browser.
    // So we could not get the title here. It is to forcefully fail the test
    @Test(priority=3)
    public void TestToFail()
    {
        driver.getTitle();
        System.out.println("This method to test fail");
    }


}