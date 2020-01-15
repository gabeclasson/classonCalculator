package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Pi extends Function1Arg{
	public Pi(Calculator calc) {
		super("pi", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer.", "num a: Returns the number of primes less than or equal to a"));
	}
	
	/**
	 * Returns the number of primes less than or equal to a.
	 */
	public String evaluate(double a) throws IntArgumentException {
		int i = Function.toInt(a);
		return Double.toString(Function.pi(i));
	}
}
