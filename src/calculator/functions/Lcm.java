package calculator.functions;

import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;

public class Lcm extends Function2Args{
	public Lcm(Calculator calc) {
		super("lcm", calc);
		setDocumentationObject(new Doc(getName(), 1, 2, "a is an array whose elements are all positive integers. a and b are positive integers.", new String[] {"array a: The least common multiple of every element in a.", "num a, num b: The least common multiple of a and b."}));
	}
	
	/**
	 * Returns the least common multiple of a and b
	 */
	public String evaluate(double a, double b) throws CalculatorException {
		int i = Function.toInt(a);
		int j = Function.toInt(b);
		return Double.toString(Function.lcm(new double[][] {{i, j}}));
	}
	
	/**
	 * Returns the least common multiple of every element of a.
	 */
	public String evaluate(double[][] a) throws CalculatorException {
		return Double.toString(Function.lcm(a));
	}
}
