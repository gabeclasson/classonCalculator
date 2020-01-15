package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Median extends Function1Arg{
	public Median(Calculator calc) {
		super("median", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an array.", "array a: Returns the median (middle) element of a. If a is of even size, returns the arithmetic mean of the two middle elements."));
	}
	
	/**
	 * Erroneous evaluation.
	 */
	public String evaluate(double a) {
		return "error: arguments.";
	}
	
	/**
	 * Returns the median (middle element) of a.
	 */
	public String evaluate(double[][] a) throws IntArgumentException {
		double[] arr = new double[toInt(Function.size(a))];
		int i = 0;
		for (double[] row: a)
			for (double item: row)
				arr[i++] = item;
		Function.quickSortA(arr, 0, arr.length);
		int half = arr.length / 2;
		return Double.toString(arr.length % 2 == 0 ? (arr[half] + arr[half - 1]) / 2.0 : arr[half]);
	}
}
