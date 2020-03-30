package test;



import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestSuiteRunner {

	public static void main(String[] args) {
		

		Result result = JUnitCore.runClasses(TestSuite.class);

		for (Failure testCase : result.getFailures()) {
			System.out.println(testCase.toString());
			testCase.getException().getMessage();
		}
		
		System.out.println("--- --- --- --- --- --- --- --- ---");
		System.out.println( "\t All Tests successful ? -> " + result.wasSuccessful());
		System.out.println( "\t Tests run time in miliseconds: -> " + result.getRunTime());
		System.out.println( "\t Failed tests: -> " + result.getFailureCount());
		
	}

}
