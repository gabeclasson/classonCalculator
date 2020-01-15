package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Det extends Function1Arg{
	public Det(Calculator calc) {
		super("det", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a square matrix.", "array a: Returns the determinant of a."));

	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the determinant of a.
	 */
	public String evaluate(double[][] a) {
		int[] dim = Function.dimensions(a);
		if (dim[0] != dim[1] || dim[0] == 0) {
			return "error: dimension.";
		}
		return Double.toString(Function.determinant(a));
	}
}
