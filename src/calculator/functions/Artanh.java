package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Artanh extends Function1Arg {
	public Artanh(Calculator calc) {
		super("artanh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-1, 1)", "num a: the area hyperbolic tangent (inverse hyperbolic tangent) of a."));
	}
	
	/**
	 * Returns the area hyperbolic tangent of a.
	 */
	public String evaluate(double a) {
		if (a < 1.0 && a > -1.0)
			return Double.toString(0.5 * Math.log((1 + a)/(1 - a)));
		return "error: domain.";
	}
}
