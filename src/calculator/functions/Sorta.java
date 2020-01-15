package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Sorta extends Function1Arg {
	public Sorta(Calculator calc) {
		super("sorta", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns an array of the same shape as a, but sorted in increasing numerical order."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Sorts a in asecnding order.
	 */
	public String evaluate(double[][] a) throws ArrayCastException, IntArgumentException {
		sortA(a);
		return Function.arrToString(a);
	}
	
}
