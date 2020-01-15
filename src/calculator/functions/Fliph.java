package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Fliph extends Function1Arg{
	public Fliph(Calculator calc) {
		super("fliph", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns an an array whose contents are the same as a but every row has been reversed in order. Flips an array horizontally."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns an an array whose contents are the same as a but every row has been reversed in order.
	 */
	public String evaluate(double[][] a) throws ArrayCastException {
		for (double[] row: a)
			Function.reverse(row);
		return Function.arrToString(a);
	}
}
