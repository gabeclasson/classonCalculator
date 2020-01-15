package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Factor extends Function1Arg{
	public Factor(Calculator calc) {
		super("factor", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a positive integer.", "num a: Returns the prime factorization of a, expressed as a two column array. Each row of the array is in the form {p; n}, where p is a prime number and n is its factorization. For example, factor(587832) returns {{2.0;3.0};{3.0;1.0};{7.0;1.0};{3499.0;1.0}} because 587832 == 2^3 * 3^1 * 7^1 * 3499^1."));
	}
	
	/**
	 * Returns the prime factorization of a, expressed as a two column array. Each row of the array is in the form {p; n}, where p is a prime number and n is its factorization. For example, factor(587832) returns {{2.0;3.0};{3.0;1.0};{7.0;1.0};{3499.0;1.0}} because 587832 == 2^3 * 3^1 * 7^1 * 3499^1.
	 */
	public String evaluate(double a) throws IntArgumentException, ArrayCastException {
		int i = Function.toInt(a);
		if (i < 1)
			return "error: domain.";
		if (i == 1)
			return "{{1.0}}";
		return Function.arrToString(Function.primeFactorize(i));
	}
}
