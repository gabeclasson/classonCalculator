package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Fibonacci extends Function1Arg{
	public Fibonacci(Calculator calc) {
		super("fibonacci", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a non-negative integer.", "num a: Returns the ath fibonacci number.", "This function uses Binet's formula."));
	}
	
	/**
	 * Returns the ath fibonacci number.
	 */
	public String evaluate(double a) throws IntArgumentException {
		int p = Function.toInt(a);
		if (p < 0)
			return "error: domain.";
		return Double.toString(Math.round((Math.pow(1.0 + Math.sqrt(5),p)-Math.pow(1.0 - Math.sqrt(5),p))/(Math.pow(2.0, p) * Math.sqrt(5))));
	}
}
