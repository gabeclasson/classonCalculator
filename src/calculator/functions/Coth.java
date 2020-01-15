package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Coth extends Function1Arg{
	public Coth(Calculator calc) {
		super("coth", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, 0)U(0,inf)", "num a: Returns the hyperbolic cotangent of a."));
	}
	
	/**
	 * Returns the hyperbolic cotangent of a.
	 */
	public String evaluate(double a) {
		if (a == 0.0)
			return "error: domain.";
		return Double.toString(Math.cosh(a)/Math.sinh(a));
	}
}
