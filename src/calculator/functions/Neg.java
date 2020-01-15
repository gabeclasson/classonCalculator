package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Neg extends Function1Arg{
	public Neg(Calculator calc) {
		super("neg", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf, inf)", "num a: Returns -1.0 * a."));
	}
	
	/**
	 * Returns -1.0 * a.
	 */
	public String evaluate(double a) {
		return Double.toString(-1.0 * a);
	}
}
