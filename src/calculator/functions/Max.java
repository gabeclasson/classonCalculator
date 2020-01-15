package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Max extends Function1Arg{
	public Max(Calculator calc) {
		super("max", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the greatest element of a."));
		
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the maximum value within a.
	 */
	public String evaluate(double[][] a) {
		double max = a[0][0];
		for (double[] row: a)
			for (double item: row)
				if (item > max)
					max = item;
		return Double.toString(max);
	}
}
