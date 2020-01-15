package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Geomean extends Function1Arg{
	public Geomean(Calculator calc) {
		super("geomean", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array whose elements are all of the same size.", "array a: Returns the geometric mean of every element in a. If every element in a is negative, this will perform the geometric mean on the absolute values of every element of a, and then multiply the result by -1.0."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the geometric mean of every element of a.
	 */
	public String evaluate(double[][] a) {
		double sign = Math.signum(a[0][0]);
		double count = 0.0;
		double product = 1.0;
		for (double[] row : a)
			for (double item: row) {
				if (Math.signum(item) != sign)
					return "error: domain.";
				count++;
				product *= item;
			}
		return Double.toString(sign * Math.pow(Math.abs(product), 1.0 / count));
	}
}
