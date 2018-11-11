package helpers;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listenerSimple  implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("==>The name of the testcase Started is: " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("==>The name of the testcase passed is: " + iTestResult.getName());

    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testcase failed is: " + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("The context of finished TC is: " + iTestContext.getName());
    }


}

