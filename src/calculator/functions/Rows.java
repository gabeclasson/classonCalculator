package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Rows extends Function1Arg{
	public Rows (Calculator calc) {
		super("rows", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: The number of rows in a."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the number of rows in a.
	 */
	public String evaluate(double[][] a) {
		return Integer.toString(a.length);
	}
}
