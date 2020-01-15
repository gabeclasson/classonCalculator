package calculator;

/**
 * Represents an error made because a double was not close enough to an integer or not in the size range of an integer value.
 * @author Gabe Classon
 *
 */
@SuppressWarnings("serial")
public class IntArgumentException extends CalculatorException {
	private double d;
	
	public IntArgumentException(double d) {
		super("error: int argument.");
		this.d = d;
	}

	/**
	 * @return the d
	 */
	public double getD() {
		return d;
	}
}
