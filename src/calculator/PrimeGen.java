package calculator;


/**
 * PrimeGen is a class which allows for the computation of certain numerically theoretically relevant operations
 * @author Gabe Classon
 *
 */
public class PrimeGen {
	private PrimeBooleanList primes;
	public static final int MAX_PRIME_STORED = 46341;
	public static final double MAX_DOUBLE_INT = Math.pow(2.0, 53.0);
	
	public PrimeGen() {
		primes = new PrimeBooleanList(MAX_PRIME_STORED + 1);
	}
	
	/**
	 * Determines the primality of a
	 * @param a An integer
	 * @return true is a is prime, false if a is not prime
	 */
	public boolean isPrime(int a) {
		if (a < 2)
			return false;
		if (a <= MAX_PRIME_STORED)
			return primes.isPrime(a);
		int sqrt = (int) Math.sqrt(a) + 1;
		for (int i = 2; i <= sqrt; i++) {
			if (!primes.isPrime(i))
				continue;
			if (a % i == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Calculates pi, the prime counting function
	 * @param a An integer
	 * @return The number of primes less than or equal to a.
	 */
	public int pi(int a) {
		int count = 0;
		for (int i = 2; i <= a; i++)
			if (isPrime(i))
				count++;
		return count;
	}
	
	/**
	 * Determines the prime factorization of a
	 * @param a An integer
	 * @return The prime factorization of a as represented by a PrimeFactorList
	 */
	public PrimeFactorList primeFactorize(int a) {
		if (a < 2)
			return new PrimeFactorList();
		int remain = a;
		PrimeFactorList factors = new PrimeFactorList();
		for (int i = 2; i <= MAX_PRIME_STORED; i++) {
			if (!primes.isPrime(i))
				continue;
			int exponent = 0;
			while (remain % i == 0) {
				exponent++;
				remain /= i;
			}
			if (exponent == 0)
				continue;
			factors.add(new FactorPair(i, exponent));
		}
		if (remain > 1)
			factors.add(new FactorPair(remain, 1));
		return factors;
	}
	
	/**
	 * Determines the number of factors given a prime factorization
	 * @param primeFactorization The prime factorization of a number
	 * @return The number of factors which a number with that prime factorization would have
	 */
	public int numFactors (PrimeFactorList primeFactorization) {
		int size = 1;
		for (FactorPair fact: primeFactorization)
			size *= fact.getExponent() + 1;
		return size;
	}
	
	/**
	 * Determines all factors of a number in unsorted order.
	 * @param a An integer.
	 * @return An array of all factors of a beginning with 1 and ending with a.
	 */
	public int[] factorize(int a) {
		PrimeFactorList primeFact = primeFactorize(a);
		if (primeFact.size() <= 0)
			return new int[0];
		int[] factors = new int[numFactors(primeFact)];
		primeFact.initIterator();
		for (int i = 0; i < factors.length; i++) {
			factors[i] = primeFact.currentValue();
			primeFact.incrementIterator();
		}
		return factors;
	}
	
	/**
	 * Precondition: All elements of a are positive. Determines the greatest common divisor of a group of integers
	 * @param a An array of integers
	 * @return The greatest common divisor of the integers in a.
	 */
	public int gcd(int[] a) {
		if (a.length == 1)
			return a[0];
		int gcf = gcdHelp(a[0], a[1]);
		for (int i = 2; i < a.length; i++) {
			gcf = gcdHelp(gcf, a[i]);
		}
		return gcf;
	}
	
	/**
	 * Precondition: a and b > 0. Determines the greatest common divisor of a pair of integers through the Eucliden algorithm
	 * @param a An integer > 0
	 * @param b An integer > 0
	 * @return The greatest common divisor of a and b.
	 */
	private int gcdHelp(int a, int b) {
		if (a > b)
			return gcdHelp(a - b * (int) ((a - 0.1)/b), b);
		if (b > a)
			return gcdHelp(b - a * (int) ((b - 0.1)/a), a);
		return a;
	}
	
	/**
	 * Precondition: a and b >= 1. Determines the greatest common divisor of a pair of integers through the Eucliden algorithm
	 * @param a An integer (roughly) > 0
	 * @param b An integer (roughly) > 0
	 * @return The greatest common divisor of a and b.
	 */
	private double gcdLcmHelp(double a, double b) throws CalculatorException {
		if (a > MAX_DOUBLE_INT || b > MAX_DOUBLE_INT)
			throw new CalculatorException("error: overflow.");
		a = Math.round(a);
		b = Math.round(b);
		if (a > b)
			return gcdLcmHelp(a - b * Math.floor((a - 0.1)/b), b);
		if (b > a)
			return gcdLcmHelp(b - a * Math.floor((b - 0.1)/a), a);
		return a;
	}
	
	/**
	 * Precondition: All elements of a are positive. Determines the least common multiple of a group of integers
	 * @param a An array of integers
	 * @return The least common multiple of the integers in a.
	 */
	public double lcm(int[] a) throws CalculatorException {
		if (a.length == 1)
			return a[0];
		double lcm = (double) a[0] / gcdLcmHelp(a[0], a[1]) * a[1];
		for (int i = 2; i < a.length; i++)
			lcm = lcm / gcdLcmHelp(lcm, a[i]) * a[i];
		return lcm;
	}
}
