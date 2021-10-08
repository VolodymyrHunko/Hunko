package testNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

@Listeners
public class listenerSimple  implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("==>The name of the testCase Started is (from listener file): " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("==>The name of the testCase passed is(from listener file): " + iTestResult.getName());

    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testCase failed is: " + iTestResult.getName());
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
        System.out.println("The context of finished TC is (from listener file): " + iTestContext.getName());
    }


}

