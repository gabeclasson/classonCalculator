package calculator;

import java.util.Scanner;

/**
 * The Driver to be used with the Calculator class
 * @author Gabe Classon
 *
 */
public class CalculatorDriver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Calculator calc = new Calculator(true);
		calc.header();
		int i = 1;
		while (calc.isOpen()) {
			System.out.print("(" + i + ") > ");
			String in = scan.nextLine();
			System.out.println("(" + i++ + ") >>> " + calc.evaluate(in));
			System.out.println();
		}
		scan.close();
	}
}
