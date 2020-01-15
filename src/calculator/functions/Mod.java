package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Mod extends Function2Args{
	public Mod (Calculator calc) {
		super("mod", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are numbers with b != 0.", "num a, num b: Returns the remainder when a is divided by b, often known as the modulus operation. Always returns a number in the range [0, |b|), where || indicates the absolute value operation."));
	}
	
	/**
	 * Returns a % b.
	 */
	public String evaluate (double a, double b) {
		if (b == 0)
			return "error: domain.";
		return Double.toString(a % b);
	}
}
