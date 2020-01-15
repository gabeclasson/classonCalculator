package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sin extends Function1Arg {
	public Sin(Calculator calc) {
		super("sin", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the sine of a, angle mode dependent."));
	}
	
	/**
	 * Returns the sine of a, angle mode dependent.
	 */
	public String evaluate(double a) {
		if (useRadians()) {
			return Double.toString(Math.sin(a));
		}
		return Double.toString(Math.sin(a * Math.PI / 180));
	}
}
