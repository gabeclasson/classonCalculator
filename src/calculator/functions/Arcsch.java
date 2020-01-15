package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arcsch extends Function1Arg {
	public Arcsch(Calculator calc) {
		super("arcsch", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a != 0", "num a: the area hyperbolic cosecant (inverse hyperbolic cosecant) of a."));
	}
	
	/**
	 * Returns the area hyperbolic cosecant of a.
	 */
	public String evaluate(double a) {
		if (a == 0.0) {
			return "error: domain.";
		}
		return Double.toString(Math.log(1.0 / a + Math.sqrt(1 / (a * a) + 1.0)));
	}
}
