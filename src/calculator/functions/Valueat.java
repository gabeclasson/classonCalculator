package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Valueat extends Function3Args{
	public Valueat(Calculator calc) {
		super("valueat", calc);
		setDocumentationObject(new Doc(getName(), 2, 3, "a is an array with a single row and b is a positive integer <= the number of elements in a. a is an array with multiple rows and b is a positive integer <= the number of rows in a. a is an array and b is a positive integer <= to the number of rows and c is a positive integer <= the number of elements in the bth row.", new String[] {"array a, num b: If a has only one row, gets the bth element in a's only row. If a has multiple rows, gets the bth row of a and returns it as an array.", "array a, num b, num c: Gets the cth element in the bth row of a."}));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a, double b, double c) {
		return "error: arguments";
	}

	/**
	 * Gets a[b][c]
	 */
	public String evaluate(double[][] a, double b, double c) throws ArrayIndexOutOfBoundsException, IntArgumentException{
		return Double.toString(a[Function.toInt(b) - 1][Function.toInt(c) - 1]);
	}

	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a, double[][] b, double c) {
		return "error: arguments.";
	}

	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a, double b, double[][] c) {
		return "error: arguments.";
	}
	
	/**
	 * Gets the bth element of the only row of a if a has only one row, or gets the bth row of a if a has more than one row.
	 */
	public String evaluate(double[][] a, double b) throws ArrayIndexOutOfBoundsException, IntArgumentException, ArrayCastException {
		if (a.length <= 1)
			return Double.toString(a[0][Function.toInt(b) - 1]);
		double[][] out = new double[1][];
		out[0] = a[Function.toInt(b) - 1];
		return Function.arrToString(out);
	}
}
