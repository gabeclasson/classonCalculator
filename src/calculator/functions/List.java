package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class List extends Function {
	public List(Calculator calc) {
		super("list", calc);
		setDocumentationObject(new Doc(getName(), 0, 0, "Nothing.", "nothing: Returns a numbered alphabetical list of the names of every function available to this calculator.", "These numbers can be used in conjunction with help() to view the documentation for various functions."));
	}
	
	/**
	 * Returns a numbered alphabetical list of every function available to this calculator.
	 */
	public String evaluate() {
		String str = "\\\n***FUNCTION LIST***";
		Function[] functions = getCalc().getFunctionList();
		for (int i = 0; i < functions.length; i++)
			str += "\n	" + (i + 1) + ". " + functions[i].getName();
		return str;
	}

}
