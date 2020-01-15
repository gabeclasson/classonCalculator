package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Slice extends Function3Args{
	public Slice (Calculator calc) {
		super("slice", calc);
		setDocumentationObject(new Doc(getName(), 3, 3, "a is a matrix with b <= the number of rows in a and c <= the number of columns in a.", "array a, num b, num b: Returns a new matrix with b rows and c columns which contains every element of a at, above, or to the left of a[b, c]. Essentially slices a to be a certain size, preserving the top leftmost elements."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a, double b, double c) {
		return "error: arguments.";
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
	 * Returns a new matrix with b rows and c columns which contains every element of a at, above, or to the left of a[b, c]. Essentially slices a to be a certain size, preserving the top leftmost elements.
	 */
	public String evaluate(double[][] a, double b, double c) throws IntArgumentException, ArrayCastException {
		int rows = Function.toInt(b);
		int cols = Function.toInt(c);
		int[] dim = Function.dimensions(a);
		if (dim[0] < 0 || rows > dim[0] || cols > dim[1] || rows < 1 || cols < 1)
			return "error: dimension.";
		return Function.arrToString(Function.slice(a, 0, 0, rows, cols));
	}
}
