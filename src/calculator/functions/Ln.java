package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Ln extends Function1Arg{
	public Ln(Calculator calc) {
		super("ln", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(0, inf)", "num a: Returns the natural log (log base e) of a."));
	}
	
	/**
	 * Returns the natural log (log base e) of a
	 */
	public String evaluate(double a) {
		if (a <= 0)
			return "error: domain.";
		if (a <= 1.0)
			return Double.toString(Math.log(a));
		if (a < 2.0)
			return Double.toString(Math.log1p(1.0 - a));
		return Double.toString(Math.log(a));
	}
}
