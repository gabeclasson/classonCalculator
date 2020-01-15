package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Transpose extends Function1Arg{
	public Transpose(Calculator calc) {
		super("transpose", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a matrix.", "array a: The result of transposing a, flipping a over its main diagonal such that for all valid r, c, a[r, c] becomes a[c, r]."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the result of transposing a.
	 */
	public String evaluate(double[][] a) throws ArrayIndexOutOfBoundsException, ArrayCastException{
		int[] dim = Function.dimensions(a);
		if (dim[0] < 0)
			return "error: dimension.";
		double[][] out = new double[dim[1]][dim[0]];
		for (int r = 0; r < out.length; r++)
			for (int c = 0; c < out[r].length; c++)
				out[r][c] = a[c][r];
		return Function.arrToString(out);
	}
}
