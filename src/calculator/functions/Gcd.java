package calculator.functions;

import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;

public class Gcd extends Function2Args{
	public Gcd(Calculator calc) {
		super("gcd", calc);
		setDocumentationObject(new Doc(getName(), 1, 2, "a is an array whose elements are all positive integers. a and b are integers > 0.", new String[] {"array a: The greatest common divisor of every element of a.", "num a, num b: The greatest common divisor of a and b."}));
	}
	
	/**
	 * Returns the greatest common divisor of a and b.
	 */
	public String evaluate(double a, double b) throws CalculatorException {
		int i = Function.toInt(a);
		int j = Function.toInt(b);
		return Double.toString(Function.gcd(new double[][] {{i, j}}));
	}
	
	/**
	 * Returns the greatest common divisor of every element in a.
	 */
	public String evaluate(double[][] a) throws CalculatorException {
		return Double.toString(Function.gcd(a));
	}
}
