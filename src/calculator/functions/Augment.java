package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Augment extends Function2Args{
	public Augment(Calculator calc) {
		super("augment", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are matrices with the same number of rows.", "array a, array b: Returns b augmented onto a, such that the new matrix has the same number of rows as a and b as well as the contents of b to the right of the contents of a."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a, double b) {
		return "error: arguments.";
	}
	
	/**
	 * Returns b augmented onto a, such that the new matrix has the same number of rows as a and b as well as the contents of b to the right of the contents of a.
	 */
	public String evaluate(double[][] a, double[][] b) throws ArrayCastException {
		int[] dimA = Function.dimensions(a);
		int[] dimB = Function.dimensions(b);
		if (dimA[0] < 0 || dimA[0] != dimB[0]) {
			return "error: dimension.";
		}
		return Function.arrToString(Function.augment(a, b));
	}
}
