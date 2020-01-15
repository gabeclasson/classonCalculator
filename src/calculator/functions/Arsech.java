package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Arsech extends Function1Arg {
	public Arsech(Calculator calc) {
		super("arsech", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "(0, 1]", "num a: the area hyperbolic secant (inverse hyperbolic secant) of a."));
	}
	
	/**
	 * Returns the area hyperbolic secant of a.
	 */
	public String evaluate(double a) {
		if (a <= 0 || a > 1)
			return "error: domain.";
		return Double.toString(Math.log((1.0 + Math.sqrt(1 - a * a))/a));
	}
}
