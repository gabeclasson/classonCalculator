package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Floor extends Function1Arg {
	public Floor (Calculator calc) {
		super("floor", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer.", "num a: Returns the greatest integer less than or equal to a (rounds down)."));
	}
	
	/**
	 * Returns the greatest integer less than or equal to a (rounds down).
	 */
	public String evaluate(double a) {
		return Double.toString(Math.floor(a));
	}
}
