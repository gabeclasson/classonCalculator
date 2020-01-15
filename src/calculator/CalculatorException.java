package calculator;

/**
 * Represents an error while computing with the Calculator class
 * @author Gabe Classon
 *
 */
@SuppressWarnings("serial")
public class CalculatorException extends Exception {
	private String message;
	
	public CalculatorException (String message) {
		this.message = message;
	}
	
	public CalculatorException() {
		message = "error.";
	}
	
	public String toString() {
		return message;
	}
}
