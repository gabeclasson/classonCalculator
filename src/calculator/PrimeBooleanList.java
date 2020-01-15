package calculator;

/**
 * Represents a list of boolean values determining the primality of the nth integer. Using the space efficient BooleanList, many bytes can be saved. Storing a list of every prime representable as an int in java takes ~4.2 × 10^8 bytes. Storing a list of boolean values takes only 6.7 × 10^7. These size savings are even more drastic for lower numbers as we use in this project.
 * @author Gabe Classon
 *
 */
public class PrimeBooleanList extends BooleanList{
	private int contentSize; // this PrimeBooleanList has determined the primality of all nonnegative integers less than contentSize
	
	public static final int MAX_INT_SIZE = 2147483647;
	
	public PrimeBooleanList(int contentSize) {
		super(contentSize, true);
		this.contentSize = contentSize;
		set(0, false);
		set(1, false);
		int sqrt = (int) Math.sqrt(contentSize) + 1;
		for (int i = 0; i <= sqrt; i++) {
			if (!get(i)) // checks if i is prime
				continue;
			for (int j = 2 * i; j > 0 && j < contentSize; j += i) {
				set(j, false); // all multiples of i are composite
			}
		}
	}
	
	
	/**
	 * determines the primality of an integer
	 * @param n the integer to test the primality of 
	 * @return whether or not n is prime
	 */
	public boolean isPrime(int n) {
		if (n < contentSize && n >= 0)
			return get(n);
		throw new IndexOutOfBoundsException();
	}
}
