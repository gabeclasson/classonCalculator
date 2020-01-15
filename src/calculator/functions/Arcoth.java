package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arcoth extends Function1Arg {
	public Arcoth(Calculator calc) {
		super("arcoth", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, -1)U(1, inf)", "num a: the area hyperbolic cotangent (inverse hyperbolic cotangent) of a."));
	}
	
	/**
	 * Returns the area hyperbolic cotangent of a.
	 */
	public String evaluate(double a) {
		if (a >= -1.0 && a <= 1.0)
			return "error: domain.";
		return Double.toString(0.5 * Math.log((a + 1)/(a - 1)));
	}
}
