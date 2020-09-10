package seleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class SetWaits {
    WebDriver driver = new FirefoxDriver();
    @Test
    public void implicitWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://zoom.com");
    }

    @Test
    public void explicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("https://zoom.com");

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nnnn")));
        element.getText();
    }

    @Test
    public void fluentWait(){
        Wait <WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        driver.get("https://zoom.com");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nnnn")));
        element.getText();
    }

    @Test
    public void jsExecut(){
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //use JS query as condition
        Predicate<WebDriver> pageLoaded  = new Predicate<WebDriver>() {
            @Override
            public boolean test(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");
            }
        };
//        wait.until(pageLoaded);
    }
}
