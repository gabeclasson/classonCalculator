package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Abs extends Function1Arg{
	public Abs(Calculator calc) {
		super("abs", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the absolute value of a. If a is negative, -a; if a is positive or 0, a."));
	}
	
	/**
	 * Gets the absolute value of a number
	 * @param a A number
	 * @return The absolute value of a
	 */
	public String evaluate(double a) {
		return Double.toString(a < 0 ? -1.0 * a : a);
	}
}
