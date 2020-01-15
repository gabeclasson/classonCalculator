package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Isprime extends Function1Arg {
	public Isprime(Calculator calc) {
		super("isprime", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer.", "num a: Returns 1 if a is prime, 0 if it is not."));
	}
	
	/**
	 * Returns 1 if a is prime, 0 if it is not.
	 */
	public String evaluate(double a) throws IntArgumentException {
		int i = Function.toInt(a);
		if (i < 2)
			return "0.0";
		return Function.isPrime(i) ? "1.0" : "0.0";
	}
}
