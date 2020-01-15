package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Ans extends Function1Arg{
	public Ans(Calculator calc) {
		super("ans", calc);
		setDocumentationObject(new Doc(getName(), 0, 1, "Nothing. a is an integer != 0 and |a| <= the number of statements executed thus far on this calculator, excluding the current execution.", new String[] {"nothing: Returns the most recent output of this calculator. If the output was text or an error message, returns a syntax error.", "num a: If a is positive, the ath output of tis calculator; if a is negative, the ath most recent output of this calculator."}));
	}
	
	/**
	 * Precondition: a is an integer != 0 and |a| <= the number of statement executed thus far on this calculator, excluding the current statement. Recalls the answer from the output history of the Calculator.
	 * @param a The index of the answer output to retrieve. If a is positive, the ath statement executed by the calculator; if a is negative, the ath mot recent statement executed by this calculator.
	 */
	public String evaluate(double a) throws IntArgumentException, ArrayIndexOutOfBoundsException{
			int b = Function.toInt(a);
			if (b == 0 || b > getCalc().getAnswerHistory().size() || b < -getCalc().getAnswerHistory().size())
				return "error: domain.";
			String ans = getCalc().ans(b);
			if (ans == null)
				return "error: domain.";
			return ans;
	}
	
	/**
	 * Returns the most recent output of the Calculator.
	 * @return The most recent output of the Calculator.
	 */
	public String evaluate() throws IntArgumentException {
		return evaluate(-1.0);
	}
}
