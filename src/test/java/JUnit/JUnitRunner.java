package JUnit;


import junit.framework.TestResult;
import junit.framework.TestSuite;

public class JUnitRunner {
    public  static  void main(String[]args){
        TestSuite suite = new TestSuite(TestJUnit.class, TestUnit1.class);
        TestResult result = new TestResult();

        suite.run(result);
        System.out.println("Number of test cases = " + result.runCount());

    }

}
