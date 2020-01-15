package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Stddevpop extends Function1Arg{
	public Stddevpop(Calculator calc) {
		super("stddevpop", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "num a: Returns the population standard deviation of every element in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the population standard deviation of every element in a.
	 */
	public String evaluate(double[][] a) {
		if (a.length < 2 && a[0].length < 2)
			return "error: domain.";
		return Double.toString(Math.sqrt(Function.variancePop(a)));
	}
}
