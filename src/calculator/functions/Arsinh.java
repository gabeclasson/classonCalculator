package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arsinh extends Function1Arg {
	public Arsinh(Calculator calc) {
		super("arsinh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: the area hyperbolic sine (inverse hyperbolic sine) of a."));
	}

	/**
	 * Returns the area hyperbolic sine of a.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.log(a + Math.sqrt(a * a + 1)));
	}
}
