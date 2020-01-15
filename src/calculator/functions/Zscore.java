package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Zscore extends Function1Arg{
	public Zscore(Calculator calc) {
		super("zscore", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(0, 1)", "num a: Returns the z score (number of standard deviations above the mean) given a percentile a in decimal form."));
	}
	
	/**
	 * Returns the z-score given percentile a.
	 */
	public String evaluate(double a) {
		if (a <= 0 || a >= 1)
			return "error: domain.";
		return Double.toString(Math.sqrt(2) * Function.erfinv(2 * a - 1.0));
	}
}
