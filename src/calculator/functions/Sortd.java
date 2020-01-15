package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Sortd extends Function1Arg {
	public Sortd(Calculator calc) {
		super("sortd", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns an array of the same shape as a, but sorted in decreasing numerical order."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Sorts a in decreasing order.
	 */
	public String evaluate(double[][] a) throws ArrayCastException, IntArgumentException {
		sortD(a);
		return Function.arrToString(a);
	}
	
}
