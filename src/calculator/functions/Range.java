package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Range extends Function1Arg{
	public Range(Calculator calc) {
		super("range", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: The range of a: the difference between the largest value in a and the smallest value in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the range of a: the difference between the largest and smallest values in a.
	 */
	public String evaluate(double[][] a) {
		double min = a[0][0];
		double max = a[0][0];
		for (double[] row: a)
			for (double item: row) {
				if (item < min) {
					min = item;
				}
				else if (item > max) {
					max = item;
				}
			}
		return Double.toString(max - min);
	}
}
