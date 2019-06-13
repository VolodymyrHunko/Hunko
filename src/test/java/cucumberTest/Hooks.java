package cucumberTest;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void beforeScenario(){
        System.out.println("Do something before every scenario");
    }

    @After
    public void afterScenario(){
        System.out.println("Do something after every scenario");
    }

    @Before ("@ShortTest")
    public void beforeTarget(){
        System.out.println("Do something after TARGET scenario");
    }
}
