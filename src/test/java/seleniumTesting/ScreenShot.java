package seleniumTesting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

/*
 * We should implement the 'TakesScreenshot' interface
 * and .getScreenshotAs(OutputType.FILE) method
 */

public class ScreenShot {
    private WebDriver driver = new FirefoxDriver();
    String base = System.getProperty("user.dir");

    @Test (description = "take screen in try/catch block")
    public  void captureScreen() throws Exception{
        driver.get("https://zoom.com");
        try{
            driver.findElement(By.id("invalidID"));
        }catch (Exception e){
            // implement 'TakesScreenshots' interface
            File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //save it in project
            FileUtils.copyFile(image, new File(base+"/test-output/testScreen.png"));
            System.out.println("Screenshot captured successful.");
        }finally {
            driver.quit();
        }
    }
    @Test (description = "screenshots by using 'ITestResult' interface")
    public void captureScreen2()throws Exception{
        driver.get("https://zoom.com");
        driver.findElement(By.id("invalidID"));
        System.out.println("@After method should catch a screenshots ");
    }
    @AfterMethod
    public void getScreenshots(ITestResult result){
        // if method FAILS...
        if(ITestResult.FAILURE == result.getStatus()){
            // make a screenshots
            try {
                TakesScreenshot screen = (TakesScreenshot)driver;
                File image = screen.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(image,
                        new File(base+"/test-output/" + result.getName() + ".png"));
                System.out.println("Screenshot captured successful.");
            }catch (Exception e){
                System.out.println("Screenshot did not captured."+e.getMessage());
            }finally {
                driver.quit();
            }
        }

    }
}
