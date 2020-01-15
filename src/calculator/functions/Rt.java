package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Rt extends Function2Args {
	public Rt(Calculator calc) {
		super("rt", calc);
		setDocumentationObject(new Doc(getName(), 1, 2, "a > 0. a < 0 and b is a positive odd integer.", new String[] {"num a: Returns the square root of a.", "num a, b: Returns the bth root of a."}));
	}
	
	/**
	 * Returns the square root of a.
	 */
	public String evaluate(double a) {
		if (a < 0.0)
			return "error: domain.";
		return Double.toString(Math.sqrt(a));
	}
	
	/**
	 * Returns the bth root of a.
	 */
	public String evaluate(double a, double b) throws IntArgumentException {
		int index = Function.toInt(b);
		if (index < 1 || (index % 2 == 0 && a < 0))
			return "error: domain.";
		return Double.toString(Math.signum(a) * Math.pow(Math.abs(a), 1.0 / index));
	}
}
