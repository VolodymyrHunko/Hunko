package JUnit;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestJUnit extends TestCase{
    private String a = "Java";
    int i = 5;
    String b = null;

    @Before
    public void setUp() {
        a = "JUnit";
        b = "C++";
    }

    @Test
    public void testAdd() {
        //count the number of test cases
        System.out.println("No of Test Case = "+ this.countTestCases());

        //test getName
        String name = this.getName();
        System.out.println("Test Case Name = "+ name);

        //test setName
        this.setName("testNewAdd");
        String newName = this.getName();
        System.out.println("Updated Test Case Name = "+ newName);
    }

    //tearDown used to close the connection or clean up activities
    public void tearDown(  ) {
    }

}

