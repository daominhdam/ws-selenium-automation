package com.williamssonoma.automationCore.retryAnalyzer;

import org.testng.*;

import java.util.Iterator;

public class MyTestListenerAdapter extends TestListenerAdapter {

    public void onTestFailure(ITestResult result) {
        if (result.getMethod().getRetryAnalyzer() != null) {
            RetryAnalyzer retryAnalyzer = (RetryAnalyzer)result.getMethod().getRetryAnalyzer();

            if(retryAnalyzer.isRetryAvailable()) {
                result.setStatus(ITestResult.SKIP);
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
            Reporter.setCurrentTestResult(result);
        }
    }

   public void onFinish(ITestContext context) {
     Iterator<ITestResult> failedTestCases =context.getFailedTests().getAllResults().iterator();
    while (failedTestCases.hasNext()) {
        System.out.println("failedTestCases");
        ITestResult failedTestCase = failedTestCases.next();
        ITestNGMethod method = failedTestCase.getMethod();
        if (context.getFailedTests().getResults(method).size() > 1) {
            System.out.println("failed test case remove as dup:" + failedTestCase.getTestClass().toString());
            failedTestCases.remove();
        } else {

            if (context.getPassedTests().getResults(method).size() > 0) {
                System.out.println("failed test case remove as pass retry:" + failedTestCase.getTestClass().toString());
                failedTestCases.remove();
            }
        }
    }
   }
}