package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arctan extends Function1Arg{
	public Arctan(Calculator calc) {
		super("arctan", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: the arc tangent (inverse tangent) of a, angle mode dependent."));

	}
	
	/**
	 * Returns the arc tangent of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.atan(a) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
	}
}
