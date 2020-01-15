package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Factorall extends Function1Arg {
	public Factorall(Calculator calc) {
		super("factorall", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a positive integer.", "num a: A single rowed array containing every factor of a, including 1 and a, sorted in approximately ascending order.", "The actual order of the array is based on the prime factorization of the number."));
		
	}
	
	/**
	 * A single rowed array containing every factor of a, including 1 and a, sorted in approximately ascending order.
	 */
	public String evaluate(double a) throws IntArgumentException, ArrayCastException {
		int i = Function.toInt(a);
		if (i <= 0)
			return "error: domain.";
		if (i == 1)
			return "{{1.0}}";
		return Function.arrToString(Function.factorize(i));
	}
}
