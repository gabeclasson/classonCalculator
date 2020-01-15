package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Cols extends Function1Arg {
	public Cols(Calculator calc) {
		super("cols", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the number of columns in a if a is a matrix; if a is not a matrix (rectangular), returns -1.0."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments";
	}
	
	/**
	 * Returns the number of columns in a unless a is not rectangular, in which case it returns -1.0.
	 */
	public String evaluate(double[][] a) {
		int[] dim = Function.dimensions(a);
		if (dim[0] < 0)
			return "-1.0";
		return Integer.toString(dim[1]);
	}
}
