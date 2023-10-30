package com.dice.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    // can be run from file.xml (in SmokeTestSuite) or with annotation in bast class for all tests
    Logger log;
    String testName;
    String testMethodName;

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        this.testMethodName = result.getMethod().getMethodName();
        log.info("[Starting " + testMethodName + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        log.info("[Test " + testMethodName + " passed]");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        log.info("[Test " + testMethodName + " failed]");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        log.info("[Test " + testMethodName + " skipped]");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        this.testName = context.getCurrentXmlTest().getName();
        this.log = LogManager.getLogger(testName);
        log.info("[TEST " + testName + " STARTED]");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        log.info("[ALL " + testName + " FINISHED]");
    }
}
