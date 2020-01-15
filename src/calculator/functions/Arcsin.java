package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arcsin extends Function1Arg{
	public Arcsin(Calculator calc) {
		super("arcsin", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "[-1, 1]", "num a: the arc sine (inverse sine) of a, angle mode dependent."));

	}
	
	/**
	 * Returns the arc sine of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (a > 1 || a < -1) {
			return "error: domain.";
		}
		return Double.toString(Math.asin(a) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
	}
}
