package calculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the documentation for one function of a calculator
 * @author Gabe Classon
 * Standard documentation:
 * <function name>(a[, b, c]) {different numbers of arguments are allowed for some functions, so a[, b, c] indicates that 1, 2, 3 arguments may be accepted.
			Domain: <specifies for what values the function will return data. The domain may be a list of statements followed by periods. If the input satisfies any one of these conditions, then it is in the domain of the function. "Nothing" indicates that no input is an acceptable input.>
			nothing: <Descirption of what the function returns if no arguments are passed.>
			num a: <Description of what the function returns if one argument is passed which is a number.>
			num a, num b: <Description of what the function returns if two arguments are passed which are a numbers.>
			num a, array b: ...
			...
			Notes: ....
 */
public class Doc {
	private String name;
	private int minArgs;
	private int maxArgs;
	private String domain;
	private ArrayList<String> lines; // lines that detail the different functionalities of a function
	private String notes;
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 */
	public Doc(String name) {
		this.name = name;
		domain = "";
		minArgs = -1;
		maxArgs = -1;
		lines = new ArrayList<String>();
		notes = "";
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 */
	public Doc(String name, int minArgs, int maxArgs) {
		this.name = name;
		domain = "";
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		lines = new ArrayList<String>();
		notes = "";
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 * @param domain A series of statements (excluding the word "Domain: " separated by periods which detail allowable inputs to the function. If just one of them is satisfied, the function should run. For functions that have a distributive property across arrays, it is not necessary to detail this distributive property in the domain. If no inputs are allowable, include "Nothing."
	 */
	public Doc(String name, int minArgs, int maxArgs, String domain) {
		this.name = name;
		this.domain = domain;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		lines = new ArrayList<String>();
		notes = "";
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 * @param domain A series of statements (excluding the word "Domain: " separated by periods which detail allowable inputs to the function. If just one of them is satisfied, the function should run. For functions that have a distributive property across arrays, it is not necessary to detail this distributive property in the domain. If no inputs are allowable, include "Nothing."
	 * @param lines An array of Strings which represent the outcomes for each distinct possible input. Each line should begin with "type a, type b, etc." For example, one might type "num a, array b: Gets a * b." for just one of the lines of the array. For functions that have a defined behavior that only takes numbers as inputs (e.g. num a, num b), it is not neccesary to detail what occurs if one number input is replaced with an array, because it is assumed that the function will be "distributed" over the array. However, if, along with an input that only takes numbers, a function also has a separately defined behavior that takes an array as input, then this distributive behavior must be detailed in the documentation. 
	 */
	public Doc(String name, int minArgs, int maxArgs, String domain, String[] lines) {
		this.name = name;
		this.domain = domain;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.lines = new ArrayList<String>(Arrays.asList(lines));
		notes = "";
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 * @param domain A series of statements (excluding the word "Domain: " separated by periods which detail allowable inputs to the function. If just one of them is satisfied, the function should run. For functions that have a distributive property across arrays, it is not necessary to detail this distributive property in the domain. If no inputs are allowable, include "Nothing."
	 * @param notes Additional notes describing the function.
	 * @param lines An array of Strings which represent the outcomes for each distinct possible input. Each line should begin with "type a, type b, etc." For example, one might type "num a, array b: Gets a * b." for just one of the lines of the array. For functions that have a defined behavior that only takes numbers as inputs (e.g. num a, num b), it is not neccesary to detail what occurs if one number input is replaced with an array, because it is assumed that the function will be "distributed" over the array. However, if, along with an input that only takes numbers, a function also has a separately defined behavior that takes an array as input, then this distributive behavior must be detailed in the documentation. 
	 */
	public Doc(String name, int minArgs, int maxArgs, String domain, String[] lines, String notes) {
		this.name = name;
		this.domain = domain;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.lines = new ArrayList<String>(Arrays.asList(lines));
		this.notes = notes;
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 * @param domain A series of statements (excluding the word "Domain: " separated by periods which detail allowable inputs to the function. If just one of them is satisfied, the function should run. For functions that have a distributive property across arrays, it is not necessary to detail this distributive property in the domain. If no inputs are allowable, include "Nothing."
	 * @param line A string which represents the outcome for the possible input. Each line should begin with "type a, type b, etc." For example, one might type "num a, array b: Gets a * b." for just one of the lines of the array. For functions that have a defined behavior that only takes numbers as inputs (e.g. num a, num b), it is not necessary to detail what occurs if one number input is replaced with an array, because it is assumed that the function will be "distributed" over the array. However, if, along with an input that only takes numbers, a function also has a separately defined behavior that takes an array as input, then this distributive behavior must be detailed in the documentation. 
	 */
	public Doc(String name, int minArgs, int maxArgs, String domain, String line) {
		this.name = name;
		this.domain = domain;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.lines = new ArrayList<String>();
		lines.add(line);
		notes = "";
	}
	
	/**
	 * Creates a Doc object
	 * @param name The name (phrase which calls a function)
	 * @param minArgs The minimum number of allowable arguments of the function
	 * @param maxArgs The maximum number of allowable arguments of the function
	 * @param domain A series of statements (excluding the word "Domain: " separated by periods which detail allowable inputs to the function. If just one of them is satisfied, the function should run. For functions that have a distributive property across arrays, it is not necessary to detail this distributive property in the domain. If no inputs are allowable, include "Nothing."
	 * @param line A string which represents the outcome for the possible input. Each line should begin with "type a, type b, etc." For example, one might type "num a, array b: Gets a * b." for just one of the lines of the array. For functions that have a defined behavior that only takes numbers as inputs (e.g. num a, num b), it is not necessary to detail what occurs if one number input is replaced with an array, because it is assumed that the function will be "distributed" over the array. However, if, along with an input that only takes numbers, a function also has a separately defined behavior that takes an array as input, then this distributive behavior must be detailed in the documentation. 
	 * @param notes Additional notes describing the function.
	 */
	public Doc(String name, int minArgs, int maxArgs, String domain, String line, String notes) {
		this.name = name;
		this.domain = domain;
		this.minArgs = minArgs;
		this.maxArgs = maxArgs;
		this.lines = new ArrayList<String>();
		lines.add(line);
		this.notes = notes;
	}
	
	/**
	 * Gets a string representation of this Doc object.
	 * @return A string containing the full documentation of this Doc object and its corresponding function.
	 */
	public String toString() {
		return header() + "\n" + body();
	}
	
	/**
	 * Gets a string representation of this Doc object, padded with tabs on the left as necessary/
	 * @param numTabs The number of additional tabs to add on the left of every line of the documentation. By default, the header is padded with no tabs and the body is padded with one tab.
	 * @returnA string containing the full documentation of this Doc object and its corresponding function, padded correctly for display purposes.
	 */
	public String toString(int numTabs) {
		return header(numTabs) + "\n" + body(numTabs);
	}
	
	/**
	 * Gets the header (function name and parameters) of this documentation
	 * @return The header
	 */
	public String header() {
		String out = name + "(";
		int i;
		char current = 'a';
		for (i = 0; i < minArgs - 1; i++)
			out += current+++", ";
		if (minArgs > 0)
			out += current++;
		i++;
		if (maxArgs > minArgs) {
			out += "[";
			if (minArgs > 0)
				out += ", ";
			for (int j = i; j < maxArgs - 1; j++)
				out += current+++", ";
			out += current;
			out += "]";
		}
		out += ")";
		return out;
	}
	
	/**
	 * Gets the header (function name and parameters) of this documentation, padded correctly one the left with tabs.
	 * @param numTabs The number of additional tabs to add on the left of every line of the documentation. By default, the header is padded with no tabs and the body is padded with one tab.
	 * @return The header padded with the appropriate number of tabs on the left.
	 */
	public String header(int numTabs) {
		String out = generateTabPad(numTabs) + name + "(";
		int i;
		char current = 'a';
		for (i = 0; i < minArgs - 1; i++)
			out += current+++", ";
		if (minArgs > 0)
			out += current++;
		i++;
		if (maxArgs > minArgs) {
			out += "[";
			if (minArgs > 0)
				out += ", ";
			for (int j = i; j < maxArgs - 1; j++)
				out += current+++", ";
			out += current;
			out += "]";
		}
		out += ")";
		return out;
	}
	
	/**
	 * Gets the body (domain, lines detailing input and output, and additional notes) of this documentation
	 * @return The body padded on the left with the specified number of tabs.
	 */
	public String body() {
		String out = "";
		if (!domain.equals(""))
			out = "	Domain: " + domain;
		if (!(lines == null || lines.size() <= 0))
			for (String str: lines)
				out += "\n	" + str;
		if (!notes.equals(""))
			out += "\n	Notes: " + notes;
		return out;
	}
	
	/**
	 * Gets the body (domain, lines detailing input and output, and additional notes) of this documentation, padded on the left with a specified number of tabs.
	 * @param numTabs The number of additional tabs to add on the left of every line of the documentation. By default, the header is padded with no tabs and the body is padded with one tab.
	 * @return The body padded on the left with the specified number of tabs.
	 */
	public String body(int numTabs) {
		String out = "";
		if (!domain.equals(""))
			out = generateTabPad(numTabs) + "	Domain: " + domain;
		if (!(lines == null || lines.size() <= 0))
			for (String str: lines)
				out += "\n	" + generateTabPad(numTabs) + str;
		if (!notes.equals(""))
			out += "\n" + generateTabPad(numTabs) + "	Notes: " + notes;
		return out;
	}
	
	/**
	 * Generates a string containing a number of tabs, for purposes of padding and display.
	 * @param numTabs The number of tabs the output should contain.
	 * @return A string containing only numTabs tabs.
	 */
	private String generateTabPad(int numTabs) {
		String out = "";
		for (int i = 0; i < numTabs; i++)
			out += "	";
		return out;
	}
}
