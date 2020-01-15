package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Tan extends Function1Arg{
	public Tan(Calculator calc) {
		super("tan", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf), except for the odd integral multiples of PI/2", "num a: Returns the tangent of a, angle mode dependent."));
	}
	
	/**
	 * Returns the tangent of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (!useRadians()) {
			a = a * Math.PI / 180.0;
		}
		if ((a - Math.PI / 2.0) % Math.PI == 0.0)
			return "error: domain.";
		return Double.toString(Math.tan(a));
	}
}
