package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Npr extends Function2Args{
	public Npr(Calculator calc) {
		super("npr", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are positive integers with b >= a.", "num a, num b: Returns a permute b, the number of ways to arrange b objects out of a objects."));
	}
	
	/**
	 * Returns a permute b.
	 */
	public String evaluate(double a, double b) throws IntArgumentException {
		int n = Function.toInt(a);
		int r = Function.toInt(b);
		if (r > n || n < 0 || r < 0)
			return "error: domain.";
		double product = 1.0;
		for (int i = r; i > 0; i--) {
			product = product * n--;
		}
		return Double.toString(product);
	}
}
