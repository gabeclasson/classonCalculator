package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Stddev extends Function1Arg{
	public Stddev(Calculator calc) {
		super("stddev", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the sample standard deviation of every elemet in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the sample standard deviation of every element in a.
	 */
	public String evaluate(double[][] a) {
		if (a.length < 2 && a[0].length < 2)
			return "error: domain.";
		return Double.toString(Math.sqrt(Function.variance(a)));
	}
}
