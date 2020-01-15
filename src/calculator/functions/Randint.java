package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Randint extends Function1Arg {
	public Randint(Calculator calc) {
		super("randint", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer.", "num a: If a is positive, returns a randomly generated integer from the range [0, a); if a is negative, returns a randomly generated integer from the range [a, 0), with a minute chance of selecting 0 exactly.."));
	}
	
	/**
	 * Generates a random integer from the range [0, a)
	 */
	public String evaluate(double a) throws IntArgumentException {
		int i = Function.toInt(a);
		return Double.toString(Math.floor(i * Math.random()));
	}
}
