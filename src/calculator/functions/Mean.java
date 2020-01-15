package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Mean extends Function1Arg{
	public Mean(Calculator calc) {
		super("mean", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the arithmetic mean of every element of a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the arithmetic mean of every element of a.
	 */
	public String evaluate(double[][] a) {
		return Double.toString(Function.sum(a)/Function.size(a));
	}
}
