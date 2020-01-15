package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Pow extends Function2Args{
	public Pow (Calculator calc) {
		super("pow", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a is a positive number and b is a number. a is a negative number and b is an integer (in this case only, the integer must be exact, and the minute error bound as noted above does not apply). a is an array and b is the integer 1. a is a square matrix and b is a positive integer. a is an invertible square matrix and b is a negative integer.", 	new String[] {"num a, num b: Returns a raised to the power b.", "num a, array b: Distributes.", "array a, num b: Represents a multiplied by itself b times. If b is 0, this returns the identity matrix which has the same number of rows as a. If b is negative, returns (the inverse of a) multiplied by itself -b times."}));
	}
	
	/**
	 * Returns a ^ b.
	 */
	public String evaluate(double a, double b) {
		if (a == 0.0 && b == 0.0 || a < 0 && b != (int) b)
			return "error: domain";
		return Double.toString(Math.pow(a, b));
	}
	
	/**
	 * Returns a ^ b using matricies.
	 */
	public String evaluate(double[][] a, double b) throws IntArgumentException, ArrayCastException {
		int pow = Function.toInt(b);
		if (pow == 1)
			return Function.arrToString(a);
		int[] dim = Function.dimensions(a);
		if (dim[0] <= 0 || dim[0] != dim[1])
			return "error: dimension.";
		if (pow == 0) {
			return Function.arrToString(Function.identity(a.length));
		}
		if (pow < 0) {
			if (Function.determinant(a) == 0)
				return "error: singular matrix.";
			a = Function.inverse(a);
			pow *= -1;
		}
		double[][] out = new double[a.length][a.length];
		for (int r = 0; r < a.length; r++)
			for (int c = 0; c < a[r].length; c++)
				out[r][c] = a[r][c];
		for (int i = 1; i < pow; i++) {
			out = matrixMult(out, a);
		}
		return Function.arrToString(out);
	}
	
	
	
	

}
