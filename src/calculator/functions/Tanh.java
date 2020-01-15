package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Tanh extends Function1Arg{
	public Tanh(Calculator calc) {
		super("tanh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the hyperbolic tangent of a."));
	}
	
	/**
	 * Returns the hyperbolic tangent of a.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.tanh(a));
	}
}
