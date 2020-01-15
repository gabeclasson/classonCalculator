package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Fact extends Function1Arg{
	public Fact (Calculator calc) {
		super("fact", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer >= 0.", "num a: Returns the factorial of a, where a! == a * (a - 1) * (a - 2) * ... * 2 * 1."));
	}
	
	/**
	 * Returns a!
	 */
	public String evaluate(double a) throws IntArgumentException {
		int b = Function.toInt(a);
		if (b < 0)
			return "error: domain.";
		double product = 1;
		for (int i = b; i > 0; i--) {
			product *= i;
		}
		return Double.toString(product);
	}
}
