package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Csch extends Function1Arg{
	public Csch(Calculator calc) {
		super("csch", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(-inf,0)U(0, inf)", "num a: Returns the hyperbolic cosecant of a."));
	}
	
	/**
	 * Returns the hyperbolic cosecant of a.
	 */
	public String evaluate(double a) {
		if (a == 0)
			return "error: domain.";
		return Double.toString(1.0 / Math.sinh(a));
	}
}
