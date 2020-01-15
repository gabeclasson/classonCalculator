package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sign extends Function1Arg {
	public Sign(Calculator calc) {
		super("sign", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns -1.0 is a is negative, 0.0 is a is 0, and 1.0 if a is positive."));
	}
	
	/**
	 * Returns the sign of a, expressed as -1.0, 1, or 0.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.signum(a));
	}
}
