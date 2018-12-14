package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Example2 {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Example2 in beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Example2 in afterClass");
    }
    // test case 1
    @Test (dependsOnMethods = "testCase2")
    public void testCase1() {
        System.out.println("Example2 in test case 1");
    }

    // test case 2
    @Test (groups = "fool")
    public void testCase2() {
        System.out.println("Example2 in test case 2");
    }
}
