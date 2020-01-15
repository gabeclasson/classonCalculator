package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sech extends Function1Arg{
	public Sech(Calculator calc) {
		super("sech", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the hyperbolic secant of a."));
	}
	
	/**
	 * Returns the hyperbolic secant of a.
	 */
	public String evaluate(double a) {
		return Double.toString(1.0 / Math.cosh(a));
	}
}
