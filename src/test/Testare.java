package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Testare {

    public static void main(String[] args) {
	Result result = JUnitCore.runClasses(TestSuite.class);
	for (Failure failure : result.getFailures()) {
	    System.out.println("Eroarea se gaseste aici: " + failure.toString());
	}
	System.out.println("Rezultatul final al test suite-ului este:" + result.wasSuccessful());
    }
}
