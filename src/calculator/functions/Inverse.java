package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Inverse extends Function1Arg{
	public Inverse(Calculator calc) {
		super("inverse", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a square matrix.", "array a: The inverse of a, if a is invertible."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the inverse of a, if it is invertible.
	 */
	public String evaluate(double[][] a) throws ArrayCastException {
		int[] dim = Function.dimensions(a);
		if (dim[0] <= 0 || dim[0] != dim[1])
			return "error: dimension.";
		if (Function.determinant(a) == 0.0) {
			return "error: singular matrix.";
		}
		return Function.arrToString(Function.inverse(a));
	}
}
