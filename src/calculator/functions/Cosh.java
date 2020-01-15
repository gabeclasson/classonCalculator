package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Cosh extends Function1Arg{
	public Cosh(Calculator calc) {
		super("cosh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the hyperbolic cosine of a."));
	}
	
	/**
	 * Returns the hyperbolic cosine of a.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.cosh(a));
	}
}
