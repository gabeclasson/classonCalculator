package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Flipv extends Function1Arg{
	public Flipv(Calculator calc) {
		super("flipv", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns an an array whose contents are the same as a but the order of the rows within the array has been reversed. Flips an array vertically."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns an an array whose contents are the same as a but the order of the rows within the array has been reversed.
	 */
	public String evaluate(double[][] a) throws ArrayCastException {
		Function.reverse(a);
		return Function.arrToString(a);
	}
}
