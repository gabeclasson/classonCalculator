package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Product extends Function1Arg {
	public Product(Calculator calc) {
		super("product", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the product of every element in a."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the product of every element in a.
	 */
	public String evaluate(double[][] a) {
		return Double.toString(Function.product(a));
	}
}
