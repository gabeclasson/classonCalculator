package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Dim extends Function1Arg{
	public Dim (Calculator calc) {
		super("dim", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a matrix.", "num a: Returns the dimensions of a in the form of an array such as {{rows};{columns}}. If a is not a matrix, then returns {{rows};{-1.0}}."));
	}
	
	/**
	 * Erroenous evaluation
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the dimensions of a in the form of an array such as {{rows};{columns}}. If a is not a matrix, then returns {{rows};{-1.0}}.
	 */
	public String evaluate(double[][] a) {
		int[] dim = Function.dimensions(a);
		if (dim[0] < 0)
			return "{{" + a.length + "};{" + "-1.0" + "}}";
		return "{{" + dim[0] + ";" + dim[1] + "}}";
	}
}
