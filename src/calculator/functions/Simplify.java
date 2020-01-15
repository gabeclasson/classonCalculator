package calculator.functions;

import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;

public class Simplify extends Function2Args{
	public Simplify(Calculator calc) {
		super("simplify", calc);
		setDocumentationObject(new Doc(getName(), 2, 2, "a and b are non-zero integers.", "num a, num b: Returns the result of simplifying the fraction a/b to simplest terms. The output will be of the form {{numerator; denominator}}"));
	}
	
	/**
	 * Returns the result of simplifying a and b to simplest terms.
	 */
	public String evaluate(double a, double b) throws CalculatorException {
		int i = Function.toInt(a);
		int j = Function.toInt(b);
		if (i == 0 || j == 0)
			return "error: domain.";
		int sign = 1;
		sign *= i < 0 ? -1 : 1;
		i *= i < 0 ? -1 : 1;
		sign *= j < 0 ? -1 : 1;
		j *= j < 0 ? -1 : 1;
		int gcf = Function.toInt(Function.gcd(new double[][] {{(double) i, (double) j}}));
		return "{{" + sign*i/gcf + ".0;" + j/gcf + ".0}}";
	}
}
