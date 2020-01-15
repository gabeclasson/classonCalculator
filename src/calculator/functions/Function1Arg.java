package calculator.functions;

import java.io.FileNotFoundException;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;
import calculator.IntArgumentException;

/**
 * Represents a Function that takes up to one argument. 
 * @author gabri
 *
 */
public abstract class Function1Arg extends Function {
	public Function1Arg(String name, Calculator calc) {
		super(name, calc);
	}
	
	public Function1Arg(String name, Calculator calc, Doc documentation) {
		super(name, calc, documentation);
	}
	
	/**
	 * Evaluates a number of arguments according to the rules of this function. The number of arguments (args.length) must be <= 1.
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
		return "error: arguments.";
	}
	
	/**
	 * By default, zero arguments is erroneous.
	 */
	public String evaluate() throws IntArgumentException, CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		return "error: arguments.";
	}
	
	/**
	 * Evaluates function for one numerical input.
	 * @param a
	 * @return The function evaluated at a.
	 * @throws IntArgumentException
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws ArrayCastException
	 * @throws CalculatorException
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	public abstract String evaluate(double a) throws IntArgumentException, ArrayIndexOutOfBoundsException, ArrayCastException, CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException;
	
	/**
	 * Evaluates function for one array input. By default, this will return an array's String representation where each element e of the original array is transformed to evaluate(e).
	 * @param a
	 * @return The function evaluated for array a.
	 * @throws ArrayCastException
	 * @throws IntArgumentException
	 * @throws CalculatorException
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	public String evaluate(double[][] a) throws ArrayCastException, IntArgumentException, CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		return distribute(a);
	}
	
	/**
	 * Distributes a function over an array. An array [[a, b , c...]...] is transformed to [[evaluate(a), evaluate(b), evaluate(c)...]...]
	 * @param a
	 * @return The function distributes over a.
	 * @throws CalculatorException
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 * @throws FileNotFoundException
	 */
	public String distribute(double[][] a) throws CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		String[][] out = new String[a.length][];
		for (int r = 0; r < a.length; r++) {
			out[r] = new String[a[r].length];
			for (int c = 0; c < a[r].length; c++) {
				out[r][c] = evaluate(a[r][c]);
			}
		}
		return Function.arrToString(out);
	}
}
