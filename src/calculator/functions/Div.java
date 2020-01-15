package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Div extends Function2Args{
	public Div (Calculator calc) {
		super("div", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "b != 0.", "num a, num b: Returns a divided by b (a/b)."));
	}
	
	/**
	 * Returns a divided by b (a/b).
	 */
	public String evaluate(double a, double b) {
		if (b == 0) {
			return "error: domain.";
		}
		return Double.toString(a/b);
	}
}
