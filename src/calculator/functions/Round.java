package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Round extends Function1Arg {
	public Round(Calculator calc) {
		super("round", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Rounds a to the nearest integer. If a is between two integers (i.e. it ends with 0.5) then it is rounded up."));
	}
	
	/**
	 * Rounds a to the nearest integer.
	 */
	public String evaluate(double a) {
		return Double.toString(Math.signum(a)*Math.round(Math.abs(a)));
	}
}
