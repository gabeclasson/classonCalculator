package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Exit extends Function{
	public Exit(Calculator calc) {
		super("exit", calc);
		setDocumentationObject(new Doc(getName(), 0, 0, "Nothing.", "nothing: Closes this calculator, preventing further evaluation of expressions. Returns the word \"terminated.\""));
	}
	
	/**
	 * Closes this calculator, preventing further evaluation of expressions. Returns the word "terminated."
	 */
	public String evaluate() {
		getCalc().exit();
		return "\\terminated.";
	}
}
