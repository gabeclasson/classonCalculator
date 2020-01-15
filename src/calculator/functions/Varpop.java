package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Varpop extends Function1Arg{
	public Varpop(Calculator calc) {
		super("varpop", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the population variance for every element in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the population variance for every element in a.
	 */
	public String evaluate(double[][] a) {
		return Double.toString(Function.variancePop(a));
	}
}
