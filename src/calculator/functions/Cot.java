package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Cot extends Function1Arg{
	public Cot(Calculator calc) {
		super("cot", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf) except for integral multiples of PI.", "num a: Returns the cotangent of a, angle mode dependent."));
	}
	
	/**
	 * Returns the cotangent of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (!useRadians())
			a = a * Math.PI / 180.0;
		if (a % Math.PI == 0)
			return "error: domain.";
		return Double.toString(Math.cos(a)/Math.sin(a));
	}
}