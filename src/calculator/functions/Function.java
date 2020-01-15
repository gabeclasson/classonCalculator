package calculator.functions;

import java.io.FileNotFoundException;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;
import calculator.IntArgumentException;
import calculator.PrimeFactorList;
import calculator.PrimeGen;

/**
 * Function objects exist to take an array of Strings and returns a number, array, or error as a String. 
 * @author Gabe Classon
 */
public abstract class Function {
	public static final double TOLERANCE = 0.0000000001; // The allowable relative error (as a fraction) between a double value and the nearest integer when converting a double to an integer.
	private static PrimeGen primeTool = new PrimeGen(); // Calculates various things related to prime numbers and number theory
	private String name; 
	private Calculator calc; // The Calculator object associated with this Function. This is necessary because some function modify or depend on the state of the more general Calculator object.
	private Doc documentation; // Represents the documentation of this Function/
	
	public Function(String name, Calculator calc) {
		this.name = name.toLowerCase();
		this.calc = calc;
		documentation = null;
	}
	
	public Function(String name, Calculator calc, Doc documentation) {
		this.name = name.toLowerCase();
		this.calc = calc;
		this.documentation = documentation;
	}
	
	/**
	 * Whether or not to use radians in calculations.
	 * @return true if the Calculator's state dictates the use of radians; false if it dictates the use of degrees. Gradians are not permitted.
	 */
	public boolean useRadians() {
		return calc.getUseRadians();
	}
	
	/**
	 * Evaluates a number of arguments according to the rules of this function.
	 * @param args An array of strings representing the inputs to this function. Each input must be an array or number.
	 * @return A String representing the result of evaluating the Function with the given inputs in args. An array, number, or error is returned in the form of a String.
	 * @throws ArrayCastException
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws NumberFormatException
	 * @throws IntArgumentException
	 * @throws CalculatorException
	 * @throws FileNotFoundException
	 */
	public String evaluate(String[] args) throws ArrayCastException, ArrayIndexOutOfBoundsException, NumberFormatException, IntArgumentException, CalculatorException, FileNotFoundException {
		int length = args.length;
		if (length == 1 && args[0].length() == 0)
			return evaluate();
		return "error: arguments.";
	}
	
	/**
	 * Evaluates the function with no arguments.
	 * @return The function evaluated with no arguments.
	 * @throws IntArgumentException
	 * @throws CalculatorException
	 * @throws FileNotFoundException
	 */
	public abstract String evaluate() throws IntArgumentException, CalculatorException, FileNotFoundException;
	
	/**
	 * Returns the documentation of this Function. By default, the body of a documentation is padded to the left with one tab.
	 * @return The documentation of this Function, according to the format defined by Doc.
	 */
	public String getDocumentation() {
		if (documentation == null)
			return "";
		return documentation.toString();
	}
	
	/**
	 * Returns the documentation of this Function, excluding the header (the function's call name and parameters). By default, the body of a documentation is padded to the left with one tab.
	 * @return the documentation of this Function, excluding the header (the function's call name and parameters).
	 */
	public String getDocumentationBody() {
		if (documentation == null)
			return "";
		return documentation.body();
	}
	
	/**
	 * Returns the documentation header (the function's call name and parameters).
	 * @return  the documentation of this Function, excluding the header (the function's call name and parameters).
	 */
	public String getDocumentationHeader() {
		if (documentation == null)
			return "";
		return documentation.header();
	}
	
	/**
	 * Returns the documentation of this Function, padded to the left with some additional tabs. By default, the body of a documentation is padded to the left with one tab.
	 * @param numTabs The number of tabs to pad.
	 * @return The documentation of this Function, according to the format defined by Doc, padded to the left with an additional numTabs tabs.
	 */
	public String getDocumentation(int numTabs) {
		if (documentation == null)
			return "";
		return documentation.toString(numTabs);
	}
	
	/**
	 * Returns the documentation body of this Function, excluding the header (the function's call name and parameters), padded to the left with some additional tabs. By default, the body of a documentation is padded to the left with one tab.
	 * @param numTabs The number of tabs to pad.
	 * @return The documentation body of this Function, according to the format defined by Doc, padded to the left with an additional numTabs tabs.
	 */
	public String getDocumentationBody(int numTabs) {
		if (documentation == null)
			return "";
		return documentation.body(numTabs);
	}
	
	/**
	 * Returns the documentation header (the function's call name and parameters), padded to the left with a number of additional tabs.
	 * @oaram numTabs The number of tabs to pad.
	 * @return the documentation of this Function, excluding the header (the function's call name and parameters), padded on the left with an additional numTabs tabs.
	 */
	public String getDocumentationHeader(int numTabs) {
		if (documentation == null)
			return "";
		return documentation.header(numTabs);
	}
	
	/**
	 * @return the primeTool
	 */
	public static PrimeGen getPrimeTool() {
		return primeTool;
	}

	/**
	 * @param primeTool the primeTool to set
	 */
	public static void setPrimeTool(PrimeGen primeTool) {
		Function.primeTool = primeTool;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the calc
	 */
	public Calculator getCalc() {
		return calc;
	}

	/**
	 * @param calc the calc to set
	 */
	public void setCalc(Calculator calc) {
		this.calc = calc;
	}

	/**
	 * @return the documentation
	 */
	public Doc getDocumentationObject() {
		return documentation;
	}

	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentationObject(Doc documentation) {
		this.documentation = documentation;
	}

	/**
	 * Determines the types of a String[] of arguments passed to a Function
	 * @param args A String array of arugments passed to a Function
	 * @return A boolean array representing the type of each corresponding member of the argument array. true means number, false means array.
	 */
	public static boolean[] typeArray(String[] args) {
		boolean[] out = new boolean[args.length];
		for (int i = 0; i < args.length; i++) {
			out[i] = !args[i].contains("{");
		}
		return out;
	}
	
	/**
	 * Parses a String into a double.
	 * @param str The String representation of a double.
	 * @return The double value represented by str.
	 * @throws NumberFormatException
	 */
	public static double toDouble(String str) throws NumberFormatException {
		return Double.parseDouble(str);
	}
	
	/**
	 * Parses a String into a two dimensional array of doubles
	 * @param str The String representation of a double[][]
	 * @return The double[][] best represented by str.
	 * @throws ArrayCastException
	 */
	public static double[][] toArray(String str) throws ArrayCastException{
		if (str.contains("{}"))
			throw new ArrayCastException();
		try {
			str = str.substring(2, str.length() - 2);
			String[] rows = str.split("\\};\\{");
			String[][] fullStrArr = new String[rows.length][];
			for (int i = 0; i < rows.length; i++) {
				fullStrArr[i] = rows[i].split(";");
			}
			double[][] out = new double[fullStrArr.length][];
			for (int r = 0; r < fullStrArr.length; r++) {
				out[r] = new double[fullStrArr[r].length];
				for (int c = 0; c < fullStrArr[r].length; c++) {
					out[r][c] = toDouble(fullStrArr[r][c]);
				}
			}
			return out;
		}
		catch (Exception e) {
			throw new ArrayCastException();
		}
	}
	
	/**
	 * Converts a double[][] into a String representation, according to the standard notation specified by the calculator class.
	 * @param arr A double[][]
	 * @return The String representation of arr according to the standard notation specified by the calculator.
	 * @throws ArrayCastException
	 */
	public static String arrToString(double[][] arr) throws ArrayCastException{
		try {
			String out = "{";
			for (int r = 0; r < arr.length; r++) {
				out += "{";
				int c;
				for (c = 0; c < arr[r].length - 1; c++) {
					out += arr[r][c] + ";";
				}
				out += arr[r][c];
				out += "};";
			}
			out = out.substring(0, out.length() - 1);
			out += "}";
			return out;
		}
		catch (Exception e) {
			throw new ArrayCastException();
		}
	}
	
	/**
	 * Converts a String[][] into a String representation, according to the standard notation specified by the calculator class.
	 * @param arr A String[][]
	 * @return The String representation of arr according to the standard notation specified by the calculator.
	 * @throws ArrayCastException
	 */
	public static String arrToString(String[][] arr) throws ArrayCastException{
		String out = "{";
		for (int r = 0; r < arr.length; r++) {
			out += "{";
			int c;
			for (c = 0; c < arr[r].length - 1; c++) {
				out += arr[r][c] + ";";
			}
			out += arr[r][c];
			out += "};";
		}
		out = out.substring(0, out.length() - 1);
		out += "}";
		return out;
	}
	
	/**
	 * Gets the dimensions of a double array
	 * @param a The double array
	 * @return an integer array representing the size of the double array: [rows, columns]. If the array is not rectangular, then [-1, -1] is returned.
	 */
	public static int[] dimensions(double[][] a) {
		int rows = a.length;
		if (rows < 1) {
			return new int[] {0, 0};
		}
		int cols = a[0].length;
		for (int i = 1; i < a.length; i++) {
			if (a[i].length != cols) {
				return new int[] {-1, -1};
			}
		}
		return new int[] {rows, cols};
	}
	
	/**
	 * Converts a double to an integer.
	 * @param a The double to convert
	 * @return The nearest integer to a, given that a/round(a) <= TOLERANCE
	 * @throws IntArgumentException
	 */
	public static int toInt(double a) throws IntArgumentException{
		int i = a > 0 ? (int) (a + 0.5) : (int) (a - 0.5);
		if (Math.abs((a - i)/a) > TOLERANCE) {
			throw new IntArgumentException(a);
		}
		return i;
	}
	
	/**
	 * Adds a row to another row with a possible scalar factor in a matrix.
	 * @param arr The matrix to operate on
	 * @param row1 The index of the row to add to another row.
	 * @param scalar A scalar multiple to multiply row1 by
	 * @param row2 The index of the row to be added to.
	 * @throws CalculatorException 
	 */
	public static void rowAdd(double[][] arr, int r1, double scalar, int r2) {
		for (int i = 0; i < arr[r1].length; i++) {
			arr[r2][i] = arr[r2][i] + scalar * arr[r1][i];
		}
	}
	
	/**
	 * Multiplies a row in a matrix by a certain value
	 * @param arr The matrix to operate on
	 * @param r The index of the row to be multiplied
	 * @param scalar The scalar double multiple for the row.
	 * @throws CalculatorException 
	 */
	public static void rowMult(double[][] arr, int r, double scalar) {
		for (int i = 0; i < arr[r].length; i++) {
			arr[r][i] *= scalar;
		}
	}
	
	/**
	 * Precondition: Square matrix.
	 * @param a A square matrix
	 * @return The determinant of matrix a
	 * @throws CalculatorException 
	 */
	public static double determinant(double[][] a) {
		if (a.length == 1)
			return a[0][0];
		double sum = 0;
		for (int r = 0; r < a.length; r++) {
			sum += a[r][0] * (r % 2 == 0 ? 1.0 : -1.0) * determinant(eliminate(a, r));
		}
		return sum;
	}
	
	/**
	 * Precondition: Square matrix. Will create a square matrix of one dimension less with column 0 and the indicated row eliminated
	 * @param arr The matrix to operate on.
	 * @param row The row of the matrix to eliminate.
	 * @return The a square matrix with column 0 and row removed.
	 * @throws CalculatorException 
	 */
	private static double[][] eliminate(double[][] arr, int row) {
		double[][] out = new double[arr.length - 1][arr.length - 1];
		int currRow = 0;
		for (int r = 0; r < arr.length; r++) {
			if (r == row)
				continue;
			for (int c = 1; c < arr[r].length; c++) {
				out[currRow][c - 1] = arr[r][c];
			}
			currRow++;
		}
		return out;
	}
	
	/**
	 * Precondition: matrix which is square or wider than tall. Row reduces a matrix.
	 * @param a The matrix to operate on.
	 * @throws CalculatorException 
	 */
	public static void rref(double[][] a) {
		for (int c = 0; c < a.length; c++) {
			Function.rowMult(a, c, 1/a[c][c]);
			for (int r = 0; r < a.length; r++) {
				if (r == c)
					continue;
				Function.rowAdd(a, c, -1.0 * a[r][c], r);
			}
		}
	}
	
	/**
	 * Precondition: matrix a and b have the same number of rows. Augments b to a and returns a new matrix.
	 * @param a Matrix a
	 * @param b Matrix b
	 */
	public static double[][] augment(double[][] a, double[][] b) {
		double[][] out = new double[a.length][a[0].length + b[0].length];
		for (int r = 0; r < out.length; r++) {
			int c1;
			for (c1 = 0; c1 < a[0].length; c1++)
				out[r][c1] = a[r][c1];
			for (int c2 = 0; c2 < b[0].length; c2++) {
				out[r][c2 + c1] = b[r][c2];
			}
		}
		return out;
	}
	
	/**
	 * Precondition: a > 0. 
	 * @param a The size of the matrix
	 * @return Returns the identity matrix of size a
	 */
	public static double[][] identity(int a) {
		double[][] out = new double[a][a];
		for (int i = 0; i < a; i++)
			out[i][i] = 1.0;
		return out;
	}
	
	/**
	 * Precondition: a is a matrix with r1 < r2 <= a.length && c1 < c2 <= a[0].length. Returns a sub matrix beginning at [r1][c1] and going to [r2 - 1][c2 - 1], inclusive 
	 * @param a The matrix to be operated on
	 * @param r1 The row number of the top leftmost 
	 * @param c1 The new number of columns
	 * @return A matrix that represents the operation of slicing matrix a with the given height and width
	 */
	public static double[][] slice (double[][] a, int r1, int c1, int r2, int c2) {
		double[][] out = new double[r2 - r1][c2 - c1];
		int rA = 0;
		for (int row = r1; row < r2; row++) {
			int cA = 0;
			for (int col = c1; col < c2; col++)
				out[rA][cA++] = a[row][col];
			rA++;
		}
		return out;
	}
	
	/**
	 * Precondition: a is a square matrix with determinant != 0.
	 * @param a The matrix to invert
	 * @return The inverse of matrix a
	 */
	public static double[][] inverse(double[][] a) {
		double[][] augmented = augment(a, identity(a.length));
		rref(augmented);
		return slice(augmented, 0, a.length, a.length, a.length * 2);
	}
	
	/**
	 * Precondition: a and b are matricies such that the number of rows in a is equal to the number of columns in b. Multiplies a and b.
	 * @param a The first matrix to multiply
	 * @param b The second matrix to multiply
	 * @return a * b
	 */
	public static double[][] matrixMult(double[][] a, double[][] b) {
		double[][] out = new double[a.length][b[0].length];
		
		for (int r = 0; r < out.length; r++) {
			for (int c = 0; c < out[r].length; c++) {
				double sum = 0;
				for (int i = 0; i < b.length; i++) {
					sum += a[r][i] * b[i][c]; 
				}
				out[r][c] = sum;
			}
		}
		
		return out;
	}
	
	/**
	 * Gets the number of elements in array a
	 * @param a The array to measure
	 * @return The number of elements in a.
	 */
	public static double size(double[][] a) {
		double count = 0.0;
		for (double[] row: a)
			count += row.length;
		return count;
	}
	
	/**
	 * Sums the elements of an array
	 * @param a The array to sum
	 * @return The sum of all the elements of a.
	 */
	public static double sum(double[][] a) {
		double sum = 0.0;
		for (double[] row: a)
			for (double item: row)
				sum += item;
		return sum;
	}
	
	/**
	 * Multiplies the elements of an array
	 * @param a The array to multiply
	 * @return The product of all the elements of a.
	 */
	public static double product(double[][] a) {
		double product = 1.0;
		for (double[] row: a)
			for (double item: row)
				product *= item;
		return product;
	}
	
	public static void sortA(double[][] a) throws IntArgumentException{
		int size = toInt(size(a));
		double[] arr = new double[size];
		int i = 0;
		for (double[] row: a)
			for (double item: row)
				arr[i++] = item;
		quickSortA(arr, 0, arr.length);
		i = 0;
		for (int r = 0; r < a.length; r++)
			for (int c = 0; c < a[r].length; c++) {
				a[r][c] = arr[i++];
			}
	}
	
	public static void sortD(double[][] a) throws IntArgumentException{
		int size = toInt(size(a));
		double[] arr = new double[size];
		int i = 0;
		for (double[] row: a)
			for (double item: row)
				arr[i++] = item;
		quickSortD(arr, 0, arr.length);
		i = 0;
		for (int r = 0; r < a.length; r++)
			for (int c = 0; c < a[r].length; c++) {
				a[r][c] = arr[i++];
			}
	}
	
	/**
	 * Quicksorts the portion of an array with indicies in the range [begin, end) in ascending order
	 * @param a The array to sort
	 * @param begin The first index of the array to sort
	 * @param end 1 + the last index of the array to sort
	 */
	public static void quickSortA(double[] a, int begin, int end) {
		if (end - begin <= 1)
			return;
		double pivot = a[end - 1];
		int i = begin;
		for (int x = begin; x < end - 1; x++)
			if (a[x] < pivot) {
				double temp = a[x];
				a[x] = a[i];
				a[i] = temp;
				i++;
			}
		double temp = a[end - 1];
		a[end - 1] = a[i];
		a[i] = temp;
		quickSortA(a, begin, i);
		quickSortA(a, i + 1, end);
	}
	
	/**
	 * Quicksorts the portion of an array with indicies in the range [begin, end) in descending order
	 * @param a The array to sort
	 * @param begin The first index of the array to sort
	 * @param end 1 + the last index of the array to sort
	 */
	public static void quickSortD(double[] a, int begin, int end) {
		if (end - begin <= 1)
			return;
		double pivot = a[end - 1];
		int i = begin;
		for (int x = begin; x < end - 1; x++)
			if (a[x] > pivot) {
				double temp = a[x];
				a[x] = a[i];
				a[i] = temp;
				i++;
			}
		double temp = a[end - 1];
		a[end - 1] = a[i];
		a[i] = temp;
		quickSortD(a, begin, i);
		quickSortD(a, i + 1, end);
	}
	
	/**
	 * Reverses the elements of array a
	 * @param a The array to reverse
	 */
	public static void reverse(double[] a) {
		recursiveReverse(a, 0, a.length - 1);
	}
	
	/**
	 * Reverses the elements of an array recursively
	 * @param a The array to reverse
	 * @param begin The current first element to flip
	 * @param end The current last element to flip
	 */
	private static void recursiveReverse(double[] a, int begin, int end) {
		if (end <= begin)
			return;
		double temp = a[begin];
		a[begin] = a[end];
		a[end] = temp;
		recursiveReverse(a, begin + 1, end - 1);
	}
	
	/**
	 * Reverses the elements of array a
	 * @param a The array to reverse
	 */
	public static void reverse (Object[] a) {
		recursiveReverse(a, 0, a.length - 1);
	}
	
	/**
	 * Reverses the elements of an array recursively
	 * @param a The array to reverse
	 * @param begin The current first element to flip
	 * @param end The current last element to flip
	 */
	public static void recursiveReverse(Object[] a, int begin, int end) {
		if (end <= begin)
			return;
		Object temp = a[begin];
		a[begin] = a[end];
		a[end] = temp;
		recursiveReverse(a, begin + 1, end - 1);
	}
	
	/**
	 * Precondition: array a has more than one element. Returns the variance of a as a sample.
	 * @param a The array to find the variance
	 * @return The variance of a.
	 */
	public static double variance(double[][] a) {
		double sum = 0;
		double size = 0;
		for (double[] row: a)
			for (double item: row) {
				sum += item;
				size++;
			}
		double mean = sum/size;
		double variance = 0;
		for (double[] row: a)
			for (double item: row) {
				double difference = item - mean;
				variance += difference * difference;
			}
		variance /= (size - 1);
		return variance;
	}
	
	/**
	 * Precondition: array a has more than one element. Returns the variance of a as a population.
	 * @param a The array to find the variance
	 * @return The variance of a.
	 */
	public static double variancePop(double[][] a) {
		double sum = 0;
		double size = 0;
		for (double[] row: a)
			for (double item: row) {
				sum += item;
				size++;
			}
		double mean = sum/size;
		double variance = 0;
		for (double[] row: a)
			for (double item: row) {
				double difference = item - mean;
				variance += difference * difference;
			}
		variance /= (size);
		return variance;
	}
	
	/**
	 * Returns the error function evaluated at a. Note that this method tends to give unrelaible results.
	 * @param a A double
	 * @return The error function evaluated at a.
	 */
	public static double erf(double a) {
		int numTerms = 20 + (int) (20 * a);
		double aSquare = a * a;
		double[] terms = new double[numTerms];
		terms[0] = a;
		for (int i = 1; i < numTerms; i++) {
			terms[i] = -(2.0 * i - 1.0) * aSquare * terms[i - 1]/ (i * (2.0 * i + 1));
		}
		double sum = 0;
		for (int i = 0; i < numTerms; i++) {
			sum += terms[i];
		}
		return sum*1.128379167095512573;
	}
	
	/**
	 * Precondition: a is in (-1,1). Gets the inverse error function evaluated at a. Note that this method gives unreliable results.
	 * @param a
	 * @return erfinv(a)
	 */
	public static double erfinv(double a) {
		a *= 0.8862269254527580136490837;
		double[] coefficients = new double[] {1.000000000000000, 0.333333333333333, 0.233333333333333, 0.201587301587302, 0.192636684303351, 0.195325476992144, 0.205935864546976, 0.223209757418752, 0.246970233142755, 0.277653825603224, 0.316142623553116, 0.363717587039692, 0.422072080843042, 0.493363265563935};
		double[] terms = new double[14];
		double aSquare = a * a;
		terms[0] = a;
		for (int i = 1; i < terms.length; i++) {
			terms[i] = terms[i-1] * aSquare;
		}
		double sum = 0;
		for (int i = 0; i < terms.length; i++) {
			sum += terms[i] * coefficients[i];
		}
		return sum;
	}
	
	/**
	 * Precondition: a >= 0. Returns the primality of a.
	 * @param a The number to test the primality of.
	 * @return True if a is prime, false if a is not.
	 */
	public static boolean isPrime(int a) {
		return primeTool.isPrime(a);
	}
	
	/**
	 * Precondition: every element of a is a positive integer. Determines the greatest common factor of every element in a.
	 * @param a An array.
	 * @return The greatest common factor of every element in a.
	 * @throws CalculatorException
	 */
	public static double gcd(double[][] a) throws CalculatorException {
		int[] args = new int[toInt(Function.size(a))];
		int i = 0;
		for (double[] row: a)
			for (double item: row) {
				int temp = Function.toInt(item);
				if (temp < 1) {
					CalculatorException e = new CalculatorException("error: domain.");
					throw e;
				}
				args[i++] = temp;
			}
		return primeTool.gcd(args);
	}
	
	/**
	 * Precondition: every element of a is a positive integer. Determines the least common multiple of every element in a.
	 * @param a An array.
	 * @return The least common multiple of every element in a.
	 * @throws CalculatorException
	 */
	public static double lcm(double[][] a) throws CalculatorException {
		int[] args = new int[toInt(Function.size(a))];
		int i = 0;
		for (double[] row: a)
			for (double item: row) {
				int temp = Function.toInt(item);
				if (temp < 1) {
					CalculatorException e = new CalculatorException("error: domain.");
					throw e;
				}
				args[i++] = temp;
			}
		return primeTool.lcm(args);
	}
	
	/**
	 * Counts the number of primes less than or equal to a certain number
	 * @param a An integer
	 * @return The number of primes less than or equal to a.
	 */
	public static double pi(int a) {
		return primeTool.pi(a);
	}
	
	/**
	 * Precondition: a > 1. Returns an array representing the prime factorization of a. The array has two columns, and each row corresponds to a prime number. The left element of a row is the prime factor of a, and the right element is that factor's exponent.
	 * @param a An integer > 1.
	 * @return The prime factorization of a.
	 */
	public static double[][] primeFactorize(int a){
		PrimeFactorList factors = Function.getPrimeTool().primeFactorize(a);
		double[][] out = new double[factors.size()][2];
		for (int x = 0; x < factors.size(); x++) {
			out[x][0] = factors.get(x).getFactor();
			out[x][1] = factors.get(x).getExponent();
		}
		return out;
	}
	
	/**
	 * Precondition: a > 1. Returns a single rowed 2D array containing every factor of a, including 1 and a.
	 * @param a An integer > 1.
	 * @return All factors of a.
	 */
	public static double[][] factorize(int a){
		int[] factors = Function.getPrimeTool().factorize(a);
		double[][] out = new double[1][factors.length];
		for (int q = 0; q < factors.length; q++)
			out[0][q] = (double) factors[q];
		return out;
	}
}