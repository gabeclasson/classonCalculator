package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Rand extends Function{
	public Rand(Calculator calc) {
		super("rand", calc);
		setDocumentationObject(new Doc(getName(), 0, 0, "Nothing.", "nothing: Returns a randomly generated decimal from the range [0, 1)."));
	}
	
	/**
	 * Returns a randomly generated decimal value from the range [0, 1).
	 */
	public String evaluate() {
		return Double.toString(Math.random());
	}
}
