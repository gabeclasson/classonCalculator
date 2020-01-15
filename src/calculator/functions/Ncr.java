package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Ncr extends Function2Args{
	public Ncr(Calculator calc) {
		super("ncr", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are non-negative integers with b >= a.", "num a, num b: Returns a choose b, the number of ways that b objects can be chosen from a objects."));
	}
	
	/**
	 * Returns b choose a.
	 */
	public String evaluate(double a, double b) throws IntArgumentException {
		int n = Function.toInt(a);
		int r = Function.toInt(b);
		if (r > n || n < 0 || r < 0)
			return "error: domain.";
		if (r > n/2)
			r = n - r;
		double product = 1.0;
		for (int i = r; i > 0; i--) {
			product = product * n-- / i;
		}
		return Double.toString(product);
	}
	
}
