package calculator.functions;

import calculator.Calculator;
import calculator.Doc;

public class Log extends Function2Args{
	public Log(Calculator calc) {
		super("log", calc);
		setDocumentationObject(new Doc(getName(), 1, 2, "a and b are elements of (0, inf)", new String[] {"num a: Returns the common log (log base 10) of a.", "num a, num b: Returns the log of a, base b."}));
	}
	
	/**
	 * Returns the common log (log base 10) of a
	 */
	public String evaluate(double a) {
		if (a <= 0)
			return "error: domain.";
		if (a <= 1.0)
			return Double.toString(Math.log(a) / 2.3025850929940456840179914546);
		if (a < 2.0)
			return Double.toString(Math.log1p(1.0 - a) / 2.3025850929940456840179914546);
		return Double.toString(Math.log(a) / 2.3025850929940456840179914546);
	}
	
	/**
	 * Returns the log of a base b.
	 */
	public String evaluate(double a, double base) {
		if (a <= 0 || base <= 0)
			return "error: domain.";
		// base E
		if (base == Math.E) {
			if (a <= 1.0)
				return Double.toString(Math.log(a));
			if (a < 2.0)
				return Double.toString(Math.log1p(1.0 - a));
			return Double.toString(Math.log(a));
		}
		// base 10
		if (base == 10.0) {
			return evaluate(a);
		}
		double lnA, lnBase;
		// ln(A)
		if (a <= 1.0)
			lnA = Math.log(a);
		else if (a < 2.0)
			lnA = Math.log1p(1.0 - a);
		else
			lnA = Math.log(a);
		// ln(Base)
		if (base <= 1.0)
			lnBase = Math.log(a);
		else if (base < 2.0)
			lnBase = Math.log1p(1.0 - base);
		else
			lnBase = Math.log(base);
		return Double.toString(lnA/lnBase);
	}
}
