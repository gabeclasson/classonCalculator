package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Anglemode extends Function1Arg{
	public Anglemode(Calculator calc) {
		super("anglemode", calc);
		setDocumentationObject(new Doc(getName(), 0, 1, "Nothing. a is a number != 0.", new String[] {"nothing: Returns the angle mode of the calculator, either -1 (degrees) or 1 (radians)","num a: Sets the anngle mode of the calculator. If a > 0, sets the calculator to angle mode 1 (radians); if a < 0, sets the calculator to angle mode -1 (degrees). Returns the new angle mode of the calculator."}));
	}
	
	/**
	 * Gets the angle mode of the calculator
	 * @return The angle mode of the calculator. Returns "\\1: radians" if the calculator is in radian mode, and  "\\-1: degrees" if it is in degree mode. There are no other modes. \\ is a control character indicating that the calculator is to print a string output.
	 */
	public String evaluate() {
		return getCalc().getUseRadians() ? "\\1: radians" : "\\-1: degrees";
	}
	
	/**
	 * Domain: a != 0. Sets the angle mode of the calculator.
	 * @param a A double value. A negative number will set the calculator to degree mode, and a positive number will set the calculator to radian mode.
	 * @return "\\Set to angle mode " + the angle mode that has been set.
	 */
	public String evaluate(double a) {
		if (a == 0) {
			return "error: domain.";
		}
		getCalc().setUseRadians(a > 0.0);
		return "\\Set to angle mode " + (a > 0.0 ? "1: radians" : "-1: degrees");
	}
}
