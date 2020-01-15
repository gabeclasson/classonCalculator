package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arccsc extends Function1Arg{
	public Arccsc(Calculator calc) {
		super("arccsc", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, -1]U[1, inf)", "num a: the arc cosecant (inverse cosecant) of a, angle mode dependent."));
	}
	
	/**
	 * Returns the arc cosecant of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (a < 1 && a > -1) {
			return "error: domain.";
		}
		return Double.toString(Math.asin(1/a) * (getCalc().getUseRadians() ? 1.0 : 180.0 / Math.PI));
	}
}
