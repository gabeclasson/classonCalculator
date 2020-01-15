package calculator.functions;

import calculator.Calculator;
import calculator.Doc;
import calculator.IntArgumentException;

public class Help extends Function1Arg{
	public Help(Calculator calc) {
		super("help", calc);
		setDocumentationObject(new Doc(getName(), 0, 1, "Nothing. a is a positive integer <= the number of functions available to this calculator.", new String[] {"nothing: Returns important information about the use of the calculator.", "num a: Gets key information about the ath function alphabetically available to this calculator."}, "Run list() to see an alphabetical list of all functions."));
	}
	
	/**
	 * Returns important information about the use of the calculator.
	 */
	public String evaluate() {
		return "\\\n" + getCalc().getHelp();
	}
	
	/**
	 * Returns information about the ath alphabetically listed function.
	 */
	public String evaluate(double a) throws IntArgumentException {
		int i = Function.toInt(a);
		if (i < 0 || i > getCalc().getFunctionList().length)
			return "error: domain.";
		return "\\\n" + getCalc().getFunctionList()[i - 1].getDocumentation();
	}
}
