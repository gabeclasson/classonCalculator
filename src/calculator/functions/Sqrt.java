package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sqrt extends Function1Arg {
	public Sqrt(Calculator calc) {
		super("sqrt", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "[0, inf)", "num a: Returns the square root of a."));
	}
	
	/**
	 * Returns the square root of a.
	 */
	public String evaluate(double a) {
		if (a < 0.0)
			return "error: domain.";
		return Double.toString(Math.sqrt(a));
	}

}
