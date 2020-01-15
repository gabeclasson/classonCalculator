package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Size extends Function1Arg{
	public Size(Calculator calc) {
		super("size", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the number of elements in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the number of elements in a.
	 */
	public String evaluate(double[][] a) {
		return Double.toString(Function.size(a));
	}
}
