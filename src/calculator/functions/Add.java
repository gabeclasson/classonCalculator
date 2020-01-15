package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Add extends Function2Args{
	public Add (Calculator calc) {
		super("add", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are numbers. a and b are matrices with the same dimensions.", new String[] {"num a, num b: the sum of a and b, a + b.", "num a, array b: distributes.", "array a, num b: distributes.", "array a, array b: the sum of a and b in matrix addition, such that the output[r, c] == a[r, c] + b[r, c] for all valid r, c."}));
	}
	
	/**
	 * Returns the sum of two numbers
	 * @param a A number
	 * @param b A number
	 * @return a + b
	 */
	public String evaluate(double a, double b) {
		return Double.toString(a + b);
	}
	
	/**
	 * Domain: matricies a and b which have the same dimensions. Returns the sum of two matricies
	 * @param a A matrix
	 * @param b A matrix
	 * @return a + b
	 */
	public String evaluate(double[][] a, double[][] b) throws ArrayCastException {
		int[] aDim = Function.dimensions(a);
		int[] bDim = Function.dimensions(b);
		if (aDim[0] != bDim[0] || aDim[1] != bDim[1]) {
			return "error: dimension.";
		}
		double[][] out = new double[aDim[0]][aDim[1]];
		for (int r = 0; r < aDim[0]; r++) {
			for (int c = 0; c < aDim[1]; c++) {
				out[r][c] = a[r][c] + b[r][c];
			}
		}
		return Function.arrToString(out);
	}
}
