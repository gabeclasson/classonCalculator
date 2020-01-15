package calculator;

/**
 * Represents one part of the factorization of a number. The factor pair represents the pair of a prime number and its exponent in a factorization.
 * @author Gabe Classon
 *
 */
public class FactorPair {
	private int factor;
	private int exponent;
	
	public FactorPair(int factor, int exponent) {
		this.factor = factor;
		this.exponent = exponent;
	}
	
	/**
	 * Two FactorPairs are equal if they have the same factor (not necessarily the same exponent)
	 */
	public boolean equals(Object other) {
		if (other instanceof FactorPair)
			return factor == ((FactorPair) other).getFactor();
		return false;
	}
	
	/**
	 * Returns a new factor pair object with the same factor and exponent
	 */
	public FactorPair clone() {
		return new FactorPair(factor, exponent);
	}
	
	public String toString() {
		return factor + "^" + exponent;
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * @return the exponent
	 */
	public int getExponent() {
		return exponent;
	}

	/**
	 * @param exponent the exponent to set
	 */
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
}
