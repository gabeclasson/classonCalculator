package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Sum extends Function1Arg {
	public Sum(Calculator calc) {
		super("sum", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the sum of every element in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the sum of every element in a.
	 */
	public String evaluate(double[][] a) {
		return Double.toString(Function.sum(a));
	}
}
