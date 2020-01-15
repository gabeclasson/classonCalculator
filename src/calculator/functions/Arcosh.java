package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arcosh extends Function1Arg {
	public Arcosh(Calculator calc) {
		super("arcosh", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "[1, inf)", "num a: the area hyperbolic cosine (inverse hyperbolic cosine) of a"));
	}
	
	/**
	 * The area hyperbolic cosine of a.
	 */
	public String evaluate(double a) {
		if (a < 1)
			return "error: domain.";
		return Double.toString(Math.log(a + Math.sqrt(a * a - 1.0)));
	}
}
