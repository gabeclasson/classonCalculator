package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Min extends Function1Arg{
	public Min(Calculator calc) {
		super("min", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the smalled element of a."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the smallest element of a.
	 */
	public String evaluate(double[][] a) {
		double min = a[0][0];
		for (double[] row: a)
			for (double item: row)
				if (item < min)
					min = item;
		return Double.toString(min);
	}
}
