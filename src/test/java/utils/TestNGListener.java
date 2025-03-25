package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("**** Test Started --->" +result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("**** Test is successful --->" +result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("**** Test failed --->" +result.getName());
		System.out.println("****Reason: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("**** Test skippped --->" +result.getName());
		if (result.getThrowable() != null) {
            System.out.println("****Reason: " + result.getThrowable());
        }
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("**** Test Suite Finished: " + context.getName());
	
	}
	

}
