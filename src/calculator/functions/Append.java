package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Append extends Function2Args{
	public Append(Calculator calc) {
		super("append", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a is an array.", new String[] {"array a, num b: Returns array a but with b appended (added to the very end) of the final or only row of a.", "array a, array b: Returns b appended to a. a and b are, in a sense, stacked vertically as a result."}));
	}
	
	/**
	 * Erroneous call
	 */
	public String evaluate(double a, double b) {
		return "error: arguments.";
	}
	
	/**
	 * Appends b (adds b onto the end) to the last or only row of a.
	 */
	public String evaluate(double[][] a, double b) throws ArrayCastException {
		double[] temp = new double[a[a.length - 1].length + 1];
		for (int i = 0; i < a[a.length - 1].length; i++)
			temp[i] = a[a.length - 1][i];
		temp[a[a.length - 1].length] = b;
		a[a.length - 1] = temp;
		return Function.arrToString(a);
	}
	
	/**
	 * Appends array b to the end of array a, stacking them vertically. 
	 */
	public String evaluate(double[][] a, double[][] b) throws ArrayCastException {
		double[][] out = new double[a.length + b.length][];
		int r1;
		for (r1 = 0; r1 < a.length; r1++)
			out[r1] = a[r1];
		for (int r2 = r1; r2 < out.length; r2++)
			out[r2] = b[r2 - a.length];
		return Function.arrToString(out);
	}
}
