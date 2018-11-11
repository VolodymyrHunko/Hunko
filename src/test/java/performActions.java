import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class performActions {

    public static void main(String[] args) throws InterruptedException {
        String[] baseUrl = {"http://newtours.demoaut.com/",
                "file:\\C:\\Users\\vhunko047\\IdeaProjects\\Hunko\\src\\test\\resources\\sampleHTML.html"};
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl[1]);
        Thread.sleep(500);

/*
        // perform Mouse Over Actions
        WebElement link_Home = driver.findElement(By.linkText("Home"));
        WebElement td_Home = driver
                .findElement(By
                .xpath("//html/body/div"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr"));

        Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();

        String bgColor = td_Home.getCssValue("background-color");
        System.out.println("Before hover: " + bgColor);
        mouseOverHome.perform();
        bgColor = td_Home.getCssValue("background-color");
        System.out.println("After hover: " + bgColor);
 */

 /*
        //Multiply actions
        WebElement txt_us_name = driver.findElement(By.id("email"));
        Actions builder = new Actions(driver);
        Action series_actions = builder
        .moveToElement(txt_us_name)
        .click()
        .keyDown(txt_us_name, Keys.SHIFT)// type in UPPERCASE
        .sendKeys(txt_us_name, "hunko")
        .keyUp(txt_us_name, Keys.SHIFT)
        .doubleClick(txt_us_name) // highlight text
        .contextClick() // pop up context menu
        .build(); //action builded

        series_actions.perform(); //done
   */

        // Mouse-based interactions
        int border = 1, hight = 80, width = 100;
        //move By Offset actions
        WebElement one = driver.findElement(By.name("one"));
        WebElement three = driver.findElement(By.name("three"));
        WebElement five = driver.findElement(By.name("seven"));
        // System.out.println(" X coordinate: "+ one.getLocation(). getX() +
        // 		           " Y coordinate: "+ one.getLocation(). getY());
        //Use the coordinates to move on element
        Actions builder = new Actions(driver);
        builder.moveByOffset(8 + border * 5 + width * 2, 8 + border).click();
        builder.build().perform();
        builder.moveByOffset(border * 2 + width, border * 2 + hight).click();
        builder.build().perform();
        //Use element's name to find it
        builder.click(one).click(five).click(three);
        builder.build().perform();


        // driver.quit();
    }
}

//Keeping CONTROL pressed
/*
    Actions builder = new Actions(driver);
        builder.keyDown(Keys.CONTROL).click(someElement)
        .click(someOtherElement).keyUp(Keys.CONTROL)
        .build().perform();

        //drag and drop
        WebElement element = driver.findElement(By.name("source"));
        WebElement target = driver.findElement(By.name("target"));
        (new Actions(driver)).dragAndDrop(element, target).perform();
 */