package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arccot extends Function1Arg{
	public Arccot(Calculator calc) {
		super("arccot", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: the arc cotangent (inverse cotangent) of a, angle mode dependent."));
	}
	
	/**
	 * Returns the arccot of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (a > 0)
			return Double.toString(Math.atan(1/a) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
		else if (a < 0)
			return Double.toString((Math.atan(1/a) + Math.PI) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
		return Double.toString(Math.PI / 2);
	}
}
