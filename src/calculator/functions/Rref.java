package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Rref extends Function1Arg{
	public Rref(Calculator calc) {
		super("rref", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a matrix whose number of rows is <= the number of columns.", "array a: Returns the result of row-reducing a to reduced row eschelon form."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the result of transforming a to reduced row eschelon form.
	 */
	public String evaluate(double[][] a) throws ArrayCastException, ArrayIndexOutOfBoundsException, NullPointerException {
		int[] dim = Function.dimensions(a);
		if (dim[0] <= 0 || dim[0] > dim[1])
			return "error: dimension.";
		Function.rref(a);
		return Function.arrToString(a);
	}
}
