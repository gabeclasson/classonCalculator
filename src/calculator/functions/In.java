package calculator.functions;

import java.io.FileNotFoundException;

import calculator.Calculator;
import calculator.CalculatorException;
import calculator.Doc;

public class In extends Function1Arg{
	public In(Calculator calc) {
		super("in", calc);
		setDocumentationObject(new Doc(getName(), 0, 1, "Nothing. a is an integer such that a != 0 and |a| <= the number of statements executed by the calculator thus far.", new String[] {"nothing: Returns the result of reinputting the most recent input.", "num a: If a < 0, returns the result of reinputting the |a|th most statement executed by the calculator; if a > 0, returns the result of reinputting the ath statement executed by this calculator. "}, "If no input is recieved by the calculator because the user enters nothing, by default it runs the command in(). Because in can refer to itself, a number of novel operation to a scientific calcualator may be performed."));
	}
	
	/**
	 * If a < 0, returns the result of reinputting the |a|th most statement executed by the calculator; if a > 0, returns the result of reinputting the ath statement executed by this calculator.
	 */
	public String evaluate(double a) throws CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		int b = Function.toInt(a);
		if (b == 0 || b > getCalc().getInputHistory().size() || b < -getCalc().getInputHistory().size())
			return "error: domain.";
		if (getCalc().getCurrNumInCalls() > Calculator.MAXIMUM_RECURSIVE_DEPTH)
			return "error: maximum recursive depth reached.";
		String in = getCalc().in(b);
		if (in == null)
			return "error: domain.";
		getCalc().incrementCurrNumInCalls();
		return getCalc().inEvaluate(in);
	}
	
	/**
	 * Returns the result of reinputting the most recent statement executed by the calculator.
	 */
	public String evaluate() throws CalculatorException, NumberFormatException, IndexOutOfBoundsException, FileNotFoundException {
		return evaluate(-1.0);
	}
}
