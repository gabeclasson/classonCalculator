package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Percentile extends Function1Arg {
	public Percentile(Calculator calc) {
		super("percentile", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the percentile (in decimal form) of a given z-score a, where a represents the number of standard deviations above the mean.", "Please note that this function may be unreliable."));
	}
	
	/**
	 * Returns the percentile of a given z-score a.
	 */
	public String evaluate(double a) {
		return Double.toString(0.5 * (Function.erf(a / Math.sqrt(2.0)) + 1.0));
	}
}
