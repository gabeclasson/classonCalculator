package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Var extends Function1Arg{
	public Var(Calculator calc) {
		super("var", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array with more than one element.", "array a: Returns the sample variance for all the elements in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the sample variance for every element of a.
	 */
	public String evaluate(double[][] a) {
		if (a.length < 2 && a[0].length < 2)
			return "error: domain.";
		return Double.toString(Function.variance(a));
	}
}
