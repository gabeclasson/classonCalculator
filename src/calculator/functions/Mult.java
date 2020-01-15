package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Mult extends Function2Args{
	public Mult(Calculator calc) {
		super("mult", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are numbers. a and b are matrices such that the number of columns in a is equal to the number of rows in b.", new String[] { "num a, num b: Returns the product of a and b, a * b.", "num a, array b: distributes.", "array a, num b: distributes.", "array a, array b: Returns the result of multiplying a and b with matrix multiplication."} ));
	}
	
	/**
	 * Returns a * b.
	 */
	public String evaluate(double a, double b) {
		return Double.toString(a * b);
	}
	
	/**
	 * Returns a * b as evaluated by matrix multiplcation.
	 */
	public String evaluate(double[][] a, double[][] b) throws ArrayCastException {
		int[] aDim = Function.dimensions(a);
		int[] bDim = Function.dimensions(b);
		if (aDim[0] <= 0 || bDim[0] <= 0 || aDim[1] != bDim[0]) {
			return "error: dimension.";
		}
		return Function.arrToString(Function.matrixMult(a, b));
	}
}
