package calculator.functions;

import java.io.FileNotFoundException;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;

public abstract class Function2Args extends Function1Arg{
	public Function2Args(String name, Calculator calc) {
		super(name, calc);
	}
	
	public Function2Args(String name, Calculator calc, Doc documentation) {
		super(name, calc, documentation);
	}
	
	/**
	 * Evaluates the function for a number of String arguments, where the number of such arguments is <= 2.
	 */
	public String evaluate(String[] args) throws CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		int length = args.length;
		boolean[] typeArray = Function.typeArray(args);
		if (length == 1 && args[0].length() == 0) {
			return evaluate();
		}
		if (length == 1 && args[0].length() != 0) {
			if (typeArray[0]) { // double
				return evaluate(Function.toDouble(args[0]));
			}
			else { // array
				return evaluate(Function.toArray(args[0]));
			}
		}
		if (length == 2) {
			if (typeArray[0]) { // double
				if (typeArray[1]) { // double double
					return evaluate(Function.toDouble(args[0]), Function.toDouble(args[1]));
				}
				else {
					return evaluate(Function.toDouble(args[0]), Function.toArray(args[1]));
				}
			}
			else { // array
				if (typeArray[1]) { // array double
					return evaluate(Function.toArray(args[0]), Function.toDouble(args[1]));
				}
				else { // array array
					return evaluate(Function.toArray(args[0]), Function.toArray(args[1]));
				}
			}
		}
		return "error: arguments.";
	}
	
	/**
	 * By default, one argument is an erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * By default, one argument is an erroneous evaluation.
	 */
	public String evaluate(double[][] a) throws CalculatorException {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function at two numerical inputs.
	 * @param a 
	 * @param b
	 * @return The function evaluated at a and b.
	 * @throws CalculatorException
	 */
	public abstract String evaluate(double a, double b) throws CalculatorException;
	
	/**
	 * By default, two array arguments is an erroneous evaluation.
	 * @param a
	 * @param b
	 * @return The function evaluated at a and b.
	 * @throws ArrayCastException
	 */
	public String evaluate(double[][] a, double[][] b) throws ArrayCastException {
		return "error: arguments";
	}
	
	/**
	 * Evaluates the function at a numerical and an array input. By default, This distributes evaluate(a, ) over each element of b.
	 * @param a
	 * @param b
	 * @return The function evaluated at a and b.
	 * @throws CalculatorException
	 */
	public String evaluate(double a, double[][] b) throws CalculatorException {
		return distribute(a, b);
	}
	
	/**
	 * Evaluates the function at an array and a numerical input. By default, This distributes evaluate(, b) over each element of a.
	 * @param a
	 * @param b
	 * @return The function evaluated at a and b.
	 * @throws CalculatorException
	 */
	public String evaluate(double[][] a, double b) throws CalculatorException {
		return distribute(a, b);
	}
	
	/**
	 * Distributes evaluate( , b) over each element of a.
	 * @param a
	 * @param b
	 * @return The String representation of an array whose elements correspond to the original elements of a such that each element e in a is transformed to evaluate(e, b).
	 * @throws CalculatorException
	 */
	public String distribute(double[][] a, double b) throws CalculatorException {
		String[][] out = new String[a.length][];
		for (int r = 0; r < a.length; r++) {
			out[r] = new String[a[r].length];
			for (int c = 0; c < a[r].length; c++) {
				out[r][c] = evaluate(a[r][c], b);
			}
		}
		return Function.arrToString(out);
	}
	
	/**
	 * Distributes evaluate(a , ) over each element of b.
	 * @param a
	 * @param b
	 * @return The String representation of an array whose elements correspond to the original elements of b such that each element e in b is transformed to evaluate(a, e).
	 * @throws CalculatorException
	 */
	public String distribute(double a, double[][] b) throws CalculatorException {
		String[][] out = new String[b.length][];
		for (int r = 0; r < b.length; r++) {
			out[r] = new String[b[r].length];
			for (int c = 0; c < b[r].length; c++) {
				out[r][c] = evaluate(a, b[r][c]);
			}
		}
		return Function.arrToString(out);
	}

}
