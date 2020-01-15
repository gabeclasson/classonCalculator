package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arccos extends Function1Arg{
	public Arccos(Calculator calc) {
		super("arccos", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "[-1, 1]", "num a: the arc cosine (inverse cosine) of a, angle mode dependent."));
	}
	
	/**
	 * Returns the arccos of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (a > 1 || a < -1) {
			return "error: domain.";
		}
		return Double.toString(Math.acos(a) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
	}
}
