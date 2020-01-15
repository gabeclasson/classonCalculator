package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Ceiling extends Function1Arg {
	public Ceiling(Calculator calc) {
		super("ceiling", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the smallest integer greater than or equal to a (rounds up)."));
	}
	
	/**
	 * Returns the smallest integer greater than or equal to a.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.ceil(a));
	}
}
