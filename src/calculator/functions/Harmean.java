package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Harmean extends Function1Arg {
	public Harmean(Calculator calc) {
		super("harmean", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array, none of whose elements are 0.0.", "array a: The harmonic mean of every element of a."));
	}
	
	/**
	 * Erroneous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments";
	}
	
	/**
	 * Returns the harmonic mean of every element of a.
	 */
	public String evaluate(double[][] a) {
		double sum = 0.0;
		double count = 0.0;
		for (double[] row: a)
			for (double item: row) {
				if (item == 0)
					return "error: domain.";
				sum += 1.0 / item;
				count++;
			}
		return Double.toString(count / sum);
	}
}
