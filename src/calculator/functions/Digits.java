package calculator.functions;

import java.text.DecimalFormat;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;

public class Digits extends Function1Arg {
	private DecimalFormat df;
	public Digits(Calculator calc) {
		super("digits", calc);
		df = new DecimalFormat("0.#################E0");
		setDocumentationObject(new Doc(getName(), 1, 1, "a is a number.", "num a: Returns the digits of |a| such that each significant digit is an element of the first row out the output array, and the corresponding element of the second row is that digit's exponent. For example, -51.0 would output {{5;1};{1;0}} because |-51.0| = 5*10^1 + 1*10^0."));
	}
	
	/**
	 * Returns the digits of |a| such that each significant digit is an element of the first row out the output array, and the corresponding element of the second row is that digit's exponent. For example, -51.0 would output {{5;1};{1;0}} because |-51.0| = 5*10^1 + 1*10^0.
	 */
	public String evaluate(double a) throws ArrayCastException {
		a *= a > 0 ? 1.0 : -1.0;
		String str = df.format(a);
		String[] parts = str.split("E");
		String digits = parts[0];
		int dP = digits.indexOf('.');
		if (dP >= 0)
			digits = digits.substring(0, dP) + digits.substring(dP + 1);
		String[][] out = new String[2][digits.length()];
		for (int i = 0; i < digits.length(); i++)
			out[0][i] = Character.toString(digits.charAt(i));
		int exponent = Integer.parseInt(parts[1]);
		for (int i = 0; i < out[1].length; i++)
			out[1][i] = Integer.toString(exponent - i);
		return Function.arrToString(out);
	}
}
