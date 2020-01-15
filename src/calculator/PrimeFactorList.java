package calculator;

import java.util.ArrayList;

/**
 * Represents the prime factorization of a number
 * @author Gabe Classon
 *
 */
@SuppressWarnings("serial")
public class PrimeFactorList extends ArrayList<FactorPair>{
	private ArrayList<Integer> currentExponents;
	private boolean hasInitIterator;
	
	public PrimeFactorList() {
		super();
		currentExponents = new ArrayList<Integer>();
		hasInitIterator = false;
	}
	
	/**
	 * Gets the value of the number represented by this prime factorization.
	 * @return The value of the number represented by this prime factorization.
	 */
	public int getValue() {
		int product = 1;
		for (FactorPair fact: this) {
			for (int i = 1; i <= fact.getExponent(); i++)
				product *= fact.getFactor();
		}
		return product;
	}
	
	/**
	 * Initializes the iterator: this will iterate through the prime factors and spit out each factor of the number, from 1 to that number, inclusive.
	 */
	public void initIterator() {
		if (!hasInitIterator) {
			for (FactorPair fact: this)
				currentExponents.add(0);
			hasInitIterator = true;
		}
	}
	
	/**
	 * Gets the "current value" of this list. If the iterator has been initialized, this will return the current factor which the iterated prime factorization represents.
	 * @return The factor currently represented in the iterator
	 */
	public int currentValue() {
		int product = 1;
		for (int x = 0; x < this.size(); x++) {
			for (int i = 1; i <= currentExponents.get(x); i++)
				product *= this.get(x).getFactor();
		}
		return product;
	}
	
	/**
	 * Increments the iterator such that the List will now represent a different factor of the number represented by this list. If the iterator has not yet been initialized, it will initialize the iterator w/o incrementing it.
	 */
	public void incrementIterator() {
		if (!hasInitIterator) {
			initIterator();
			return;
		}
		currentExponents.set(0, currentExponents.get(0).intValue() + 1);
		int e = 0;
		while(e < currentExponents.size() && Integer.compare(currentExponents.get(e),this.get(e).getExponent()) > 0) {
			currentExponents.set(e, 0);
			if (e + 1 < currentExponents.size())
				currentExponents.set(e + 1, currentExponents.get(e + 1).intValue() + 1);
			e++;
		}
	}
}
