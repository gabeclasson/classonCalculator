package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Cos extends Function1Arg{
	public Cos(Calculator calc) {
		super("cos", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the cosine of a, angle mode dependent."));
	}
	
	/**
	 * Returns the cosine of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (useRadians()) {
			return Double.toString(Math.cos(a));
		}
		return Double.toString(Math.cos(a * Math.PI / 180));
	}
}
