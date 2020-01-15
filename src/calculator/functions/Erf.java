package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Erf extends Function1Arg{
	public Erf(Calculator calc) {
		super("erf", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns the error function evaluated at a (erf(a)).", "This function is highly volatile and may not be extremely accurate."));
	}
	
	/**
	 * Returns the error function evaluated at a (erf(a)).
	 */
	public String evaluate(double a) {
		return Double.toString(Function.erf(a));
	}
}
