package testNG;

import org.testng.annotations.*;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class TestNG_Examples {
    private Assertion hardAssert = new Assertion();
    private SoftAssert softAssert = new SoftAssert();

    // test case 1
    @Test (priority = 2, groups = {"fast","fool"})
    public void testCase1() {
        System.out.println("in test case 1");
    }

    // test case 2
    @Test (priority = 1, groups = {"fool"})
    public void testCase2() {
        System.out.println("in test case 2");
    }

    // test case 3
    @Test (priority = 3, enabled = false, groups = {"fast"})
    public void testCase3() {
        System.out.println("in test case3");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("in beforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("in afterMethod");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("in beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("in afterClass");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("in beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("in afterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("in beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("in afterSuite");
    }
}

