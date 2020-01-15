package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Erfinv extends Function1Arg{
	public Erfinv(Calculator calc) {
		super("erfinv", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-1, 1)", "num a: Returns the inverse error function evaluated at a (erfinv(a)).", "This function is highly volatile and may not be extremely accurate."));
	}
	
	/**
	 * Returns the inverse error function evaluated at a (erfinv(a)).
	 */
	public String evaluate(double a) {
		if (a <= -1.0 || a >= 1.0)
			return "error: domain.";
		return Double.toString(Function.erfinv(a));
	}
}
