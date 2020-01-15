package calculator.functions;

import java.io.FileNotFoundException;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;
import calculator.IntArgumentException;

public abstract class Function3Args extends Function2Args{
	public Function3Args(String name, Calculator calc) {
		super(name, calc);
	}
	
	public Function3Args(String name, Calculator calc, Doc documentation) {
		super(name, calc, documentation);
	}
	
	/**
	 * Evaluates the function for a number of String arguments, where the number of such arguments is <= 3.
	 */
	public String evaluate(String[] args) throws NumberFormatException, CalculatorException, IndexOutOfBoundsException, FileNotFoundException {
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
		if (length == 3) { // TO DO
			if (typeArray[0]) { // double
				if (typeArray[1]) { // double double
					if (typeArray[2]) { // double double double
						return evaluate(Function.toDouble(args[0]), Function.toDouble(args[1]), Function.toDouble(args[2]));
					}
					else { // double double array
						return evaluate(Function.toDouble(args[0]), Function.toDouble(args[1]), Function.toArray(args[2]));
					}
				}
				else { // double array
					if (typeArray[2]) { // double array double
						return evaluate(Function.toDouble(args[0]), Function.toArray(args[1]), Function.toDouble(args[2]));
					}
					else { // double array array
						return evaluate(Function.toDouble(args[0]), Function.toArray(args[1]), Function.toArray(args[2]));
					}
				}
			}
			else { // array
				if (typeArray[1]) { // array double
					if (typeArray[2]) { // array double double
						return evaluate(Function.toArray(args[0]), Function.toDouble(args[1]), Function.toDouble(args[2]));
					}
					else { // array double array
						return evaluate(Function.toArray(args[0]), Function.toDouble(args[1]), Function.toArray(args[2]));
					}
				}
				else { // array array
					if (typeArray[2]) { // array array double
						return evaluate(Function.toArray(args[0]), Function.toArray(args[1]), Function.toDouble(args[2]));
					}
					else { // array array array
						return evaluate(Function.toArray(args[0]), Function.toArray(args[1]), Function.toArray(args[2]));
					}
				}
			}
		}
		return "error: arguments.";
	}
	
	/**
	 * By default, two arguments is erroneous.
	 */
	public String evaluate(double a, double[][] b) {
		return "error: arguments.";
	}
	
	/**
	 * By default, two arguments is erroneous.
	 */
	public String evaluate(double[][] a, double b) throws ArrayIndexOutOfBoundsException, IntArgumentException, ArrayCastException {
		return "error: arguments.";
	}
	
	/**
	 * By default, two arguments is erroneous.
	 */
	public String evaluate(double a, double b) {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function at three numerical inputs.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c
	 */
	public abstract String evaluate(double a, double b, double c);
	
	/**
	 * Evaluates the function at an array, a number, and a number. By default, replacing one of the numerical argument of evaluate(double, double, double) with an array arguments results in the distribution of the function evaluated at the remaining numerical arguments and each element of the array.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c
	 * @throws ArrayCastException
	 * @throws IntArgumentException
	 */
	public String evaluate(double a[][], double b, double c) throws ArrayCastException, IntArgumentException {
		return distribute(a, b, c); // TO DO
	}
	
	/**
	 * Evaluates the function at a number, an array, and a number. By default, replacing one of the numerical argument of evaluate(double, double, double) with an array arguments results in the distribution of the function evaluated at the remaining numerical arguments and each element of the array.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c
	 * @throws ArrayCastException
	 */
	public String evaluate(double a, double b[][], double c) throws ArrayCastException {
		return distribute(a, b, c); // TO DO
	}
	
	/**
	 * Evaluates the function at a number, a number, and an array. By default, replacing one of the numerical argument of evaluate(double, double, double) with an array arguments results in the distribution of the function evaluated at the remaining numerical arguments and each element of the array.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c
	 * @throws ArrayCastException
	 */
	public String evaluate(double a, double b, double c[][]) throws ArrayCastException {
		return distribute(a, b, c); // TO DO
	}
	
	/**
	 * Evaluates the function at an array, an array, and a number. By default, this is an erroneous evaluation.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c.
	 */
	public String evaluate(double a[][], double b[][], double c) {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function at an array, an array, and an array. By default, this is an erroneous evaluation.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c.
	 */
	public String evaluate(double a[][], double b, double c[][]) {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function at a number, an array, and an array. By default, this is an erroneous evaluation.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c.
	 */
	public String evaluate(double a, double b[][], double c[][]) {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function at an array, an array, and an array. By default, this is an erroneous evaluation.
	 * @param a
	 * @param b
	 * @param c
	 * @return The function evaluated at a, b, and c.
	 */
	public String evaluate(double a[][], double b[][], double c[][]) {
		return "error: arguments.";
	}
	
	/**
	 * Distributes evaluate( , b, c) over each element of a.
	 * @param a
	 * @param b
	 * @param c
	 * @return The string representation of the result of distributing evaluate(number, number, number) over the array argument.
	 * @throws ArrayCastException
	 */
	public String distribute(double a[][], double b, double c) throws ArrayCastException {
		double[][] arr = a;
		
		String[][] out = new String[arr.length][];
		for (int r = 0; r < arr.length; r++) {
			out[r] = new String[arr[r].length];
			for (int col = 0; col < arr[r].length; col++) {
				out[r][col] = evaluate(arr[r][col], b, c);
			}
		}
		return Function.arrToString(out);
	}
	
	/**
	 * Distributes evaluate(a, , c) over each element of b.
	 * @param a
	 * @param b
	 * @param c
	 * @return The string representation of the result of distributing evaluate(number, number, number) over the array argument.
	 * @throws ArrayCastException
	 */
	public String distribute(double a, double b[][], double c) throws ArrayCastException  {
		double[][] arr = b;
		
		String[][] out = new String[arr.length][];
		for (int r = 0; r < arr.length; r++) {
			out[r] = new String[arr[r].length];
			for (int col = 0; col < arr[r].length; col++) {
				out[r][col] = evaluate(a, arr[r][col], c);
			}
		}
		return Function.arrToString(out);
	}
	
	/**
	 * Distributes evaluate(a, b, ) over each element of c.
	 * @param a
	 * @param b
	 * @param c
	 * @return The string representation of the result of distributing evaluate(number, number, number) over the array argument.
	 * @throws ArrayCastException
	 */
	public String distribute(double a, double b, double c[][]) throws ArrayCastException {
		double[][] arr = c;
		
		String[][] out = new String[arr.length][];
		for (int r = 0; r < arr.length; r++) {
			out[r] = new String[arr[r].length];
			for (int col = 0; col < arr[r].length; col++) {
				out[r][col] = evaluate(a, b, arr[r][col]);
			}
		}
		return Function.arrToString(out);
	}
}
