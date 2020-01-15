package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sec extends Function1Arg{
	public Sec(Calculator calc) {
		super("sec", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf) except for the odd integral multiples of PI/2.", "num a: Returns the secant of a, angle mode dependent."));
	}
	
	/**
	 * Returns the secant of a, angle dependent.
	 */
	public String evaluate(double a) {
		if (!useRadians())
			a = a * Math.PI / 180.0;
		if ((a - Math.PI / 2) % Math.PI == 0)
			return "error: domain.";
		return Double.toString(1.0 / Math.cos(a));
	}
}