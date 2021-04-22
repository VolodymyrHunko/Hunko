package cucumberTest;

//import coreJava.SingletonClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.TestContext;
//import pageObjects.PageObjectExample;

public class MyStepdefs {
    WebDriver driver;
//    PageObjectExample page;

    public MyStepdefs(TestContext context){
//        page = context.getPage();
        System.out.println("Page madel instantiated...");
    }

    @Given("^User is on Home Page$")
    public void user_is_on_Home_Page()  {
//        String s = SingletonClass.getInstance().getReader().getBrowser();
        String s = "firefox"; //hardcoded instead of reading prop
        System.out.println(s);
        if(s.equals("firefox")){
            driver = new FirefoxDriver();
        }else
            driver = new ChromeDriver();
//        driver.get(SingletonClass.getInstance().getReader().getUrlZoom());
        driver.quit();
    }

    @When("^User Navigate to LogIn Page$")
    public void user_Navigate_to_LogIn_Page()  {
        System.out.println("2");
    }

    @When("^User enters UserName and Password$")
    public void user_enters_UserName_and_Password()  {
        System.out.println("3");
    }

    @Then("^Message displayed Login Successfully$")
    public void message_displayed_Login_Successfully()  {
        System.out.println("4");
    }

    @When("^User LogOut from the Application$")
    public void user_LogOut_from_the_Application()  {
        System.out.println("5...\n");
    }

    @Then("^\"([^\"]*)\" displayed LogOut Successfully \"([^\"]*)\" times$")
    public void displayed_LogOut_Successfully_times(String arg1, String arg2)  {
        System.out.println("Parameters: "+arg1+"=>"+arg2+"\n");
    }

    @Given("^Chrome has launched$")
    public void chromeHasLaunched() {
        System.out.println("browser launched");
    }
}
