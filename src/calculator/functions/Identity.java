package calculator.functions;

import calculator.ArrayCastException;
import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Identity extends Function1Arg{
	public Identity(Calculator calc) {
		super("identity", calc);
		setDocumentationObject(new Doc(getName(), 1, 1, "a is an integer >= 1.", "num a: Returns an identity matrix of dimension a x a."));
	}
	
	/**
	 * Returns an identity matrix of size a * a.
	 */
	public String evaluate(double a) throws IntArgumentException, ArrayCastException {
		int rows = Function.toInt(a);
		if (rows < 1)
			return "error: domain.";
		double[][] out = new double[rows][rows];
		for (int i = 0; i < rows; i++)
			out[i][i] = 1;
		return Function.arrToString(out);
	}
}
