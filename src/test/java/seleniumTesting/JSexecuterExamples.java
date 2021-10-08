package seleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class JSexecuterExamples {
    private static WebDriver driver;
    private String baseURL = "https://jquery.com/";


    @Test
    public void main() throws InterruptedException {
        System.setProperty("webdriver.firefox.logfile","TestLog.txt");
        driver = new FirefoxDriver();
        //driver.get(baseURL);
        //to implement event listener
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventHandler handler = new eventHandler();
        eventDriver.register(handler);
        // driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
        // driver.get("https://jquery.com/");
        eventDriver.get(baseURL);


        /*
         * Syntax:
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript(Script,Arguments);
         script - The JavaScript to execute
         Arguments - The arguments to the script.(Optional)
         */

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Uncomment each scenario by using Ctrl + Shift + \ (backslash) and find the solution

        //to change attribute of elements
        WebElement element = driver.findElement(By.cssSelector(".menu-item"));
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

        //to hide element using JQuery
//        js.executeScript("return $(\".logo\").hide()");
        System.out.println("Script finished.");


        /*//to type text in Selenium WebDriver without using sendKeys() method
        js.executeScript("document.getElementById('q'),value='vhun0001@gmail.com';");
        driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER); */


        /*//to click a button in Selenium WebDriver using JavaScript
        //js.executeScript("arguments[0].click();", loginButton);
        //or
        js.executeScript("document.getElementById('enter your element id').click();");
        js.executeScript("document.getElementById('next').click();");*/

        /*//to handle checkbox
        js.executeScript("document.getElementById('enter element id').checked=false;");*/

		/*//to generate Alert Pop window in selenium
		js.executeScript("alert('hello world');");*/

		/*//to refresh browser window using Javascript
		js.executeScript("history.go(0)");*/

		/*// to get innertext of the entire webpage in Selenium
		String sText =  js.executeScript("return document.documentElement.innerText;").toString();
		System.out.println(sText);*/

		/*//to get the Title of our webpage
		String sText =  js.executeScript("return document.title;").toString();
		System.out.println(sText);*/

		/*//to get the domain
		String sText =  js.executeScript("return document.domain;").toString();
		System.out.println(sText);*/

		/*//to get the URL of our webpage
		String sText =  js.executeScript("return document.URL;").toString();
		System.out.println(sText);*/

		/*//to perform Scroll on application using  Selenium
		//Vertical scroll - down by 50  pixels
		js.executeScript("window.scrollBy(0,50)");
		// for scrolling till the bottom of the page we can use the code like
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		//to scroll until top of element will visible (true)
		js.executeScript("document.getElementById('text-8').scrolIntoView(true)");
		*/

		/* // to click on a SubMenu which is only visible on mouse hover on Menu?
		//Hover on Automation Menu on the MenuBar
	    js.executeScript("$('ul.menus.menu-secondary.sf-js-enabled.sub-menu li').hover()");*/

		/*//to navigate to different page using Javascript?
	    //Navigate to new Page
	    js.executeScript("window.location = 'https://www.softwaretestingmaterial.com");*/

        driver.quit();
    }
}
