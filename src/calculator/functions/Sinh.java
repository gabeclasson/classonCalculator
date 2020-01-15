package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sinh extends Function1Arg{
	public Sinh(Calculator calc) {
		super("sinh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the hyperbolic sine of a."));
	}
	
	/**
	 * Returns the hyperbolic sine of a.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.sinh(a));
	}
}
