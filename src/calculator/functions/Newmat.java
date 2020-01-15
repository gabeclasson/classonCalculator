package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Newmat extends Function2Args{
	public Newmat(Calculator calc) {
		super("newmat", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are positive integers.", "array a, array b: Returns a matrix with a rows and b columns, filled with 0's."));
	}
	
	/**
	 * Returns a matrix with a rows and b columns.
	 */
	public String evaluate(double a, double b) throws IntArgumentException, ArrayCastException {
		int rows = Function.toInt(a);
		int cols = Function.toInt(b);
		if (rows < 1 || cols < 1)
			return "error: dimension.";
		return Function.arrToString(new double[rows][cols]);
	}
}
