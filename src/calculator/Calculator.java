package calculator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import calculator.functions.*;

/**
 * A Calculator object must be instantiated to perform any scientific calculations. Each calculator has its own history, memory, and settings.
 * @author Gabe Classon
 *
 */
public class Calculator {
	public static final int MAXIMUM_RECURSIVE_DEPTH = 1000; // The maximum allowable number of in() calls in a calculation cycle.
	
	private boolean open; // represents whether this calculator is still in use
	private int currNumInCalls; // the number of times the function In() has been called in this cycle
	private boolean useRadians; // whether or not to use radians
	private Function[] functionList; // a list of all Function object available for this calculator to use, stored in alphabetical order
	private RollingList<String> answerHistory; // history of all outputs
	private RollingList<String> inputHistory; // history of all inputs
	private String help; // the help file.
	
	public Calculator(boolean useRadians) {
		this.open = true;
		this.currNumInCalls = 0;
		this.useRadians = useRadians;
		this.functionList = new Function[] {
				new Abs(this),
				new Add(this),
				new Anglemode(this),
				new Ans(this),
				new Append(this),
				new Arccos(this),
				new Arccot(this),
				new Arccsc(this),
				new Arcosh(this),
				new Arcoth(this),
				new Arcsch(this),
				new Arcsec(this),
				new Arcsin(this),
				new Arctan(this),
				new Arsech(this),
				new Arsinh(this),
				new Artanh(this),
				new Augment(this),
				new Ceiling(this),
				new Cols(this),
				new Cos(this),
				new Cosh(this),
				new Cot(this),
				new Coth(this),
				new Csc(this),
				new Csch(this),
				new Det(this),
				new Digits(this),
				new Dim(this),
				new Div(this),
				new Erf(this),
				new Erfinv(this),
				new Exit(this),
				new Fact(this),
				new Factor(this),
				new Factorall(this),
				new Fibonacci(this),
				new Fliph(this),
				new Flipv(this),
				new Floor(this),
				new Gcd(this),
				new Geomean(this),
				new Harmean(this),
				new Help(this),
				new Identity(this),
				new In(this),
				new Inverse(this),
				new Isprime(this),
				new Lcm(this),
				new List(this),
				new Ln(this),
				new Log(this),
				new Max(this),
				new Mean(this),
				new Median(this),
				new Min(this),
				new Mod(this),
				new Mult(this),
				new Ncr(this),
				new Neg(this),
				new Newmat(this),
				new Npr(this),
				new Percentile(this),
				new Pi(this),
				new Pow(this),
				new Product(this),
				new Rand(this),
				new Randint(this),
				new Range(this),
				new Round(this),
				new Rows(this),
				new Rref(this),
				new Rt(this),
				new Sec(this),
				new Sech(this),
				new Sign(this),
				new Simplify(this),
				new Sin(this),
				new Sinh(this),
				new Size(this),
				new Slice(this),
				new Sorta(this),
				new Sortd(this),
				new Sqrt(this),
				new Stddev(this),
				new Stddevpop(this),
				new Sum(this),
				new Tan(this),
				new Tanh(this),
				new Transpose(this),
				new Valueat(this),
				new Var(this),
				new Varpop(this),
				new Zscore(this)
		};
		this.answerHistory = new RollingList<String>();
		this.inputHistory = new RollingList<String>();
		generateHelp();
	}
	
	public String evaluate(String in) {
		boolean hasSavedInput = false;
		this.currNumInCalls = 0;
		if (answerHistory.size() >= 10000) // the answer history (number of computations) of a single calculator object is capped at 10,000 to prevent stack overflow.
			exit();
		if (!open)
			return "terminated.";
		try {
			// begin input standardization
			in = in.replaceAll("\\s",""); // removing white space
			in = in.replace("\\","/").replaceAll("[:']", ","); // changing alternate division symbols - \ is used for special outputs
			in = in.replace("[", "[(").replace("]", ")"); // making array operators single characters ( [a, b] -> [(a, b) )
			in = in.replaceAll("(\\d\\.{0,1})e(\\-{0,1}\\d)","$1?$2"); // making it such that "e" scientific notation is not affected by making implicit multiplications explicit
			in = in.replaceAll("E", "(" + Math.E + ")");
			in = in.replaceAll("PI", "(" + Math.PI + ")");
			in = in.toLowerCase();
			in = in.replaceAll("([\\}\\)!\\]\\d\\.])\\-", "$1+-"); // changing minus operation to plus and negative operation (i.e. - = +-)
			while (in.contains("--")) { // double negative = +
				in = in.replace("--", "");
			}
			in = in.replaceAll("([}\\)!])([\\(\\-\\w{])", "$1*$2").replaceAll("(\\d)([\\([a-z]{])", "$1*$2"); // making implicit multiplications explicit
			in = in.replaceAll("\\?", "e"); 
			if (in.contains("error"))
				return "error: syntax.";
			// Begin operator parsing. Every operator is turned into a parenthetical function
			// [ operator (gets item in array)
			for (int i = 0; i < in.length(); i++) {
				if (in.charAt(i) == '[') {
					int oldLength = in.length();
					int[] firstChunkBounds = chunkEndingAt(in, i - 1);
					int[] lastChunkBounds = chunkStartingAt(in, i + 1);
					String firstChunk = in.substring(firstChunkBounds[0], firstChunkBounds[3]);
					String lastChunk = in.substring(lastChunkBounds[1], lastChunkBounds[2]);
					in = chunkReplace(in, firstChunkBounds[0], lastChunkBounds[3], "valueat(" + firstChunk + "," + lastChunk + ")");
					i += in.length() - oldLength; 
				}
			}
			// ! (factorial)
			for (int i = 0; i < in.length(); i++) {
				if (in.charAt(i) == '!') {
					int oldLength = in.length();
					int[] firstChunkBounds = chunkEndingAt(in, i - 1);
					String firstChunk = in.substring(firstChunkBounds[0], firstChunkBounds[3]);
					in = chunkReplace(in, firstChunkBounds[0], i + 1, "fact(" + firstChunk + ")");
					i += in.length() - oldLength;
				}
			}
			// ^ (power), - (negative, NOT minus. All minuses are actually +-).
			for (int i = in.length() - 1; i >= 0; i--) {
				if (in.charAt(i) == '-') {
					if (i > 0 && in.charAt(i-1) == 'e') // -'s in the middle of 'e' formatted doubles are not the negative operation
						continue;
					int oldLength = in.length();
					int[] lastChunkBounds = chunkStartingAt(in, i + 1);					
					String lastChunk = in.substring(lastChunkBounds[0], lastChunkBounds[3]);
					in = chunkReplace(in, i, lastChunkBounds[3], "neg(" + lastChunk + ")");
					i += in.length() - oldLength;
				}
				else if (in.charAt(i) == '^') {
					int oldLength = in.length();
					in = binaryOperatorReplace(in, i, "pow");
					i += in.length() - oldLength;
				}
			}
			// *, /, %
			for (int i = 0; i < in.length(); i++) {
				if (in.charAt(i) == '*') {
					int oldLength = in.length();
					in = binaryOperatorReplace(in, i, "mult");
					i += in.length() - oldLength;
				}
				else if (in.charAt(i) == '/') {
					int oldLength = in.length();
					in = binaryOperatorReplace(in, i, "div");
					i += in.length() - oldLength;
				}
				else if (in.charAt(i) == '%') {
					int oldLength = in.length();
					in = binaryOperatorReplace(in, i, "mod");
					i += in.length() - oldLength;
				}
			}
			// +
			for (int i = 0; i < in.length(); i++) {
				if (in.charAt(i) == '+') {
					int oldLength = in.length();
					in = binaryOperatorReplace(in, i, "add");
					i += in.length() - oldLength;
				}
			}
			if (in.equals("")) // Empty inputs simply reinput the last input
				in = inputHistory.get(-1);
			inputHistory.add(in);
			hasSavedInput = true;
			// Being parsing expression
			ArrayList<Integer> indexesOfOpenParens = new ArrayList<Integer>();
			for (int i = 0; i < in.length(); i++) { // check for errors
				if (in.contains("error"))
					throw new CalculatorException();
				if (in.charAt(i) == '(') {
					indexesOfOpenParens.add(i);
				}
				else if (in.charAt(i) == ')') {
					int oldLength = in.length();
					int openIndex = indexesOfOpenParens.get(indexesOfOpenParens.size() - 1);
					if (openIndex == 0 || !Character.isAlphabetic(in.charAt(openIndex - 1))) { // parens WITHOUT a function are simply removed
						in = chunkReplace(in, openIndex, i + 1, in.substring(openIndex + 1, i));
					}
					else { // parens with a function preceding
						int[] functionNameBounds = chunkEndingAt(in, openIndex - 1);
						String functionName = in.substring(functionNameBounds[0], functionNameBounds[3]);
						Function f = searchFunctions(functionName);
						if (f == null) {
							in = "error: name.";
							throw new CalculatorException();
						}
						String fOut = f.evaluate(in.substring(functionNameBounds[3] + 1, i).split(",")); // evaluate the function
						if (fOut.length() > 0 && fOut.charAt(0) == '\\') { // covers the special case where the output begins with a control character, indicates that something is to be printed to the output, so that no numerical processing further continues.
							fOut = fOut.substring(1);
							answerHistory.add(fOut);
							return fOut;
						}
						in = chunkReplace(in, functionNameBounds[0], i + 1, fOut);
					}
					indexesOfOpenParens.remove(indexesOfOpenParens.size() - 1);
					i += in.length() - oldLength;
				}
			}
			// begin error checking
			if (in.matches(".*(NaN|infinity).*")) {
				answerHistory.add("error: unreal.");
				return "error: unreal output.";
			}
			in = in.replace('E', 'e');
			String d = "\\-?\\d*(\\d\\.|\\.\\d|\\d)\\d*(e\\-?\\d*(\\.0*)?)?"; // regex for a single number
			if (in.matches("error:?[\\w\\s]*\\.|" + d + "|\\{\\{" + d + "(;" + d + ")*(\\};\\{" + d + "(;" + d + ")*)*\\}\\}")) { // in contains an error message, a single number, or an array of numbers
				answerHistory.add(in);
				return in;
			}
			answerHistory.add("error: syntax.");
			return "error: syntax.";
		}
		catch (Exception e) { 
			if (!hasSavedInput)
				inputHistory.add(in);
			int err = in.indexOf("error");
			if (err >= 0) { // if error message
				String out = in.substring(err, in.indexOf('.', err) + 1);
				answerHistory.add(out);
				return out;
			}
			if (e instanceof ArrayIndexOutOfBoundsException) {
				answerHistory.add("error: array bounds.");
				return "error: array bounds.";
			}
			if (e instanceof IndexOutOfBoundsException) {
				answerHistory.add("error: syntax.");
				return "error: syntax.";
			}
			if (e instanceof NumberFormatException) {
				answerHistory.add("error: data type.");
				return "error: data type.";
			}
			answerHistory.add(e.toString());
			return e.toString();
		}
	}
	
	/**
	 * Will evaluate an expression without initialing parsing it or error checking
	 * @param in
	 * @return
	 * @throws FileNotFoundException 
	 * @throws NumberFormatException 
	 * @throws Exception 
	 */
	public String inEvaluate(String in) throws IndexOutOfBoundsException, CalculatorException, NumberFormatException, FileNotFoundException{
		if (!open)
			return "terminated.";
		ArrayList<Integer> indexesOfOpenParens = new ArrayList<Integer>();
		for (int i = 0; i < in.length(); i++) { 
			int err = in.indexOf("error");
			if (err >= 0) { // if error message
				String out = in.substring(err, in.indexOf('.', err) + 1);
				throw new CalculatorException(out);
			}
			if (in.charAt(i) == '(') {
				indexesOfOpenParens.add(i);
			}
			else if (in.charAt(i) == ')') {
				int oldLength = in.length();
				int openIndex = indexesOfOpenParens.get(indexesOfOpenParens.size() - 1);
				if (openIndex == 0 || !Character.isAlphabetic(in.charAt(openIndex - 1))) { // parens WITHOUT a function
					in = chunkReplace(in, openIndex, i + 1, in.substring(openIndex + 1, i));
				}
				else { // parens with a function
					int[] functionNameBounds = chunkEndingAt(in, openIndex - 1);
					String functionName = in.substring(functionNameBounds[0], functionNameBounds[3]);
					Function f = searchFunctions(functionName);
					if (f == null) {
						throw new CalculatorException("error: name.");
					}
					String fOut = f.evaluate(in.substring(functionNameBounds[3] + 1, i).split(","));
					if (fOut.length() > 0 && fOut.charAt(0) == '\\') { // covers the special case where the output begins with a control character, indicates that somehing is to be printed to the output.
						fOut = fOut.substring(1);
						return fOut;
					}
					in = chunkReplace(in, functionNameBounds[0], i + 1, fOut);
				}
				indexesOfOpenParens.remove(indexesOfOpenParens.size() - 1);
				i += in.length() - oldLength;
			}
		}
		return in;
	}
	
	/**
	 * Searches the list of functions for a given key (name)
	 * @param key The name of the function to be called. E.g.: "sum"
	 * @return The Function object corresponding to the given name
	 */
	public Function searchFunctions(String key) {
		key = key.toLowerCase();
		int low = 0;
		int high = functionList.length - 1;
		int mid;
		
		while(low <= high) {
			mid = (low + high)/2;
			int compare = key.compareTo(functionList[mid].getName());
			if (compare == 0) {
				return functionList[mid];
			}
			if (compare < 0) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return null;
	}
	
	public void generateHelp() {
		help = "***DATA TYPES***\r\n" + 
				"	The calculator is capable of manipulating two types of data: decimal values and arrays of decimal values. \r\n" + 
				"	Decimal values (numbers) are bounded in magnitude by ~10^308 and ~10^-308 The following are examples of acceptable formats:\r\n" + 
				"		- 123.454\r\n" + 
				"		- -1.234232e-232.000\r\n" + 
				"		- 4.00000000000000000000000000000000000000000000\r\n" + 
				"		- 49857493057297840968756897\r\n" + 
				"		\r\n" + 
				"	The following are unacceptable formats:\r\n" + 
				"		- 343.-34\r\n" + 
				"		- 4.5875E5\r\n" + 
				"		- infinity\r\n" + 
				"		- NaN\r\n" + 
				"	\r\n" + 
				"	It is important to note that whitespace is totally ignored by the calculator, so putting random spaces between characters will result in the same result as if those spaces were not there at all.\r\n" + 
				"	The user may input any decimal within the stated magnitude of any level of precision. However, every decimal digit in excess of ~15 significant figures will be rounded off. If at any point in the calculation a computation leaves the domain of the real numbers or is too large or small in magnitude, the calculator will return an error. Please note that this calculator is prone to floating point rounding errors.\r\n" + 
				"	For the purposes of this calculator, any number input within " + Function.TOLERANCE * 100 + "% of an integer may be considered an integer, to make the calculator more forgiving of rounding errors. If a function or operator requires an integer input, that input must be bounded in magnitude by ~2.14748364 * 10^9.\r\n" + 
				"	 \r\n" + 
				"	All arrays are two dimensional in size, and every array must have at least one element. All elements of an array must be numbers. No gaps are permitted in arrays. Here are some acceptable formats of arrays:\r\n" + 
				"		- {{5}}\r\n" + 
				"		- {{5;3};{sin(66)}}\r\n" + 
				"		- {{5.3983;-9e5};{5; -3.4}}\r\n" + 
				"		\r\n" + 
				"	The following are examples of unacceptable formats:\r\n" + 
				"		- {{}}\r\n" + 
				"		- {{4, 6}}\r\n" + 
				"		- {{5;4},{}}\r\n" + 
				"		- [[45;45][2;5]]\r\n" + 
				"		- {{;;7}}\r\n" + 
				"	\r\n" + 
				"	As you can see, every array begins and ends with a curly brace {}. Each row in the array is also bounded by curly braces. Each row is delimited from other rows by semicolons, and each number is delimited from other numbers by a semicolon.\r\n" + 
				"	For the purposes of this calculator, an array whose rows each have the same number of elements is referred to as a matrix. This calculator is capable of performing some operations on matricies which it cannot perform on arrays in general.\r\n\r\n" + 
				"***FORMAT OF DOCUMENTATION***\r\n" + 
				"	All documentation for functions will be in the following form:\r\n" + 
				"	\r\n" + 
				"	<function name>(a[, b, c]) {different numbers of arguments are allowed for some functions, so a[, b, c] indicates that 1, 2, or 3 arguments may be accepted.}\r\n" + 
				"			Domain: <specifies for what values the function will return data. The domain may be a list of statements followed by periods. If the input satisfies any one of these conditions, then it is in the domain of the function. \"Nothing\" indicates that no input is an acceptable input.>\r\n" + 
				"			nothing: <Descirption of what the function returns if no arguments are passed.>\r\n" + 
				"			num a: <Description of what the function returns if one argument is passed which is a number.>\r\n" + 
				"			num a, num b: <Description of what the function returns if two arguments are passed which are a numbers.>\r\n" + 
				"			num a, array b: ...\r\n" + 
				"			...\r\n" + 
				"			Notes: ....\r\n" + 
				"		\r\n" + 
				"	All functions which normally take only numbers as inputs will also work if exactly one of their inputs is replaced with an array. For example, sin({{5;6}}) is equivalent to {{sin(5);sin(6)}}. Functions that take two inputs, say div, will function in a similar manner. div(5,{{5;3}}) is equivalent to {{div(5,5);div(5;3}}. It is important to note that if a function has a specific behavior defined for an array input, then this distributive behavior does not work unless specified. For example, pow(array a, num b) is specifically defined to have non-distributive behavior, so pow(num a, array b) is not automatically defined to have any particular behavior. As it turns out, pow(num a, array b) is defined to exhibit the distributive behavior, so its documentation notes specifically how this form is defined.\r\n" + 
				"\r\n" + 
				"***OPERATORS***\r\n" + 
				"	There are 9 operators which follow a standard order of operations. Every operator is equivalent to a parenthetical function, except for the subtraction operator. Like parenthetical functions, operators have the ability to take different data types.\r\n" + 
				"	\r\n" + 
				"	Operator documentation in order of operations:\r\n" + 
				"		a[b[, c]]\r\n" + searchFunctions("valueat").getDocumentationBody(2) + "\n" +  
				"		a!\r\n" + searchFunctions("fact").getDocumentationBody(2) + "\n" +  
				"		a^b\r\n" + searchFunctions("pow").getDocumentationBody(2) + "\n" +  
				"		-a\r\n" + searchFunctions("neg").getDocumentationBody(2) + "\n" +  
				"		a*b\r\n" + searchFunctions("mult").getDocumentationBody(2) + "\n" +  
				"		a/b\r\n" + searchFunctions("div").getDocumentationBody(2) + "\n" +  
				"		a%b\r\n" + searchFunctions("mod").getDocumentationBody(2) + "\n" +  
				"		a+b\r\n" + searchFunctions("add").getDocumentationBody(2) + "\n" +  
				"		a-b\r\n" + 
				"			Domain: a and b are numbers.\r\n" + 
				"			num a, num b: Returns a-b (or equivalently a + -1.0 * b), the signed difference between a and b.\r\n\r\n" + 
				"	The order of operations is as follows: \r\n" + 
				"		1. [] (left to right)\r\n" + 
				"		2. ! (left to right)\r\n" + 
				"		3. ^, - (the negative operation equivalent to *-1, NOT the minus operation which is the same character; this is why -2^2 == -4, -3! == -6, and -{{5;6}} == {{-5;-6}}) (right to left, which allows for 3^-3^3 == 3^-(3^3) == 3^-27 != (3^-3)^3 == 3^-9)\r\n" + 
				"		4. *, /, % (left to right)\r\n" + 
				"		5. +, - (the minus operation equvalent to +-1*) (left to right)\r\n" + 
				"\r\n" + 
				"***ERRORS***\r\n" + 
				"	Every calculation has a probability of error. If the calculator encounters an error, an error message will be returned of the type \"error: <message>.\". This will allow the user to gain some information about why their calculator failed.\r\n" + 
				"	\r\n" + 
				"	Error documentation:\r\n" + 
				"		error: arguments.\r\n" + 
				"			This is returned when the wrong number or data type of inputs is passed to a function.\r\n" + 
				"		error: array bounds.\r\n" + 
				"			This is returned when the calculator attempts to manipulate invalid indices of an array.\r\n" + 
				"		error: data type.\r\n" + 
				"			This is returned when the calculator expects a number but fails to parse it due to erroneous syntax.\r\n" + 
				"		error: dimension.\r\n" + 
				"			This is returned when a matrix or array is of the wrong dimensions, or if some operation involving the dimensions of an array are invalid.\r\n" + 
				"		error: domain.\r\n" + 
				"			This is returned when an input lies outside the domain of a function. The domain of f(x) is defined as all the values of x for which f(x) is a real number of an array of real numbers.\r\n" + 
				"		error: int argument.\r\n" + 
				"			This is returned when an argument is expected to be an integer or sufficiently close to an integer but fails to be so.\r\n" + 
				"		error: maximum recursive depth reached.\r\n" +
				"			This error is caused by the fact that the in() function is capable of calling itself. If the number of in() calls during a single calculation cycle surpasses " + MAXIMUM_RECURSIVE_DEPTH + " then the calculator will stop all calculation and return this error.\r\n" + 
				"		error: name.\r\n" + 
				"			This is returned when the calculator recognizes that the input matches a function call, but the function by the input name does not exist.\r\n" + 
				"		error: singular matrix.\r\n" + 
				"			This is returned when the calculator attempts to take the inverse of a singular (non-invertible) matrix.\r\n" + 
				"		error: syntax.\r\n" + 
				"			This is returned when something has been typed in incorrectly such that it does not follow the syntax of the program.\r\n" + 
				"		error: unreal output.\r\n" + 
				"			This is returned when the output of a function is so great that it cannot be represented by the calculator, or if the output is not defined.\r\n" + 
				"	\r\n" + 
				"	Some of these errors overlap in scope, and only one error is returned per erroneous calculation, even if the calculation has multiple errors. Therefore, error messages should be moderated with a dose of reason to help understand what exactly the issue was.\r\n" + 
				"	One error that may not be dealt with by the calculator is the stack overflow error. This can result from particularly intensive calculations. If you encounter this, please restart the calculator. \r\n" + 
				"	\r\n" + 
				"***SPECIAL FUNCTIONS***\r\n" + 
				"	There are several functions within the calculator that do more than just take inputs and return number or arrays as outputs. Some of them are detailed below. If a function returns informational text, the corresponding text, and nothing else, will print as soon as that function is evaluated by the calculator.\r\n" + 
				searchFunctions("anglemode").getDocumentation(1) + "\r\n" +
				searchFunctions("ans").getDocumentation(1) + "\r\n" +
				searchFunctions("exit").getDocumentation(1) + "\r\n" +
				searchFunctions("help").getDocumentation(1) + "\r\n" +
				searchFunctions("in").getDocumentation(1) + "\r\n" +
				searchFunctions("list").getDocumentation(1) + "\r\n\r\n" +
				"***OTHER FUNCTIONS***\r\n" + 
				"	To see a list of all functions available to this calculator, run list(). This will return a numbered list of all functions in alphabetical order. Using the number corresponding to the function you want to know about, run help(<function number>) to see information and documentation for that function.";
	}
	/**
	 * @return the help
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * @param help the help to set
	 */
	public void setHelp(String help) {
		this.help = help;
	}

	/**
	 * Gets information about the "chunk" (number, parentheses expression, function, function name, etc.) ending at index (doesn't include leading -)
	 * @param str The string containing a mathematical expression
	 * @param index the index of the last character of the chunk
	 * @param includeMinus whether or not to consider minuses included within a numerical chunk.
	 * @return an int array representing the bounds of the chunk. [index of first character of outside of chunk, index of first character of inside of chunk (e.g. the parameters of a function inside parentheses), 1 + index of last character of inside of chunk, 1 + index of last character of outside of chunk]
	 */
	public static int[] chunkEndingAt(String str, int index) throws IndexOutOfBoundsException {
		int[] chunk = new int[4];
		chunk[3] = index + 1;
		
		char lastChar = str.charAt(index);

			if (Character.isDigit(lastChar)) {
				chunk[2] = chunk[3];
				
				int i = index;
				
				i--;
				while (i >= 0 && (Character.isDigit(str.charAt(i)) || str.charAt(i) == 'e' || str.charAt(i) == '.' || str.charAt(i) == '-')) {
					if (str.charAt(i) == '-' && (i < 1 || str.charAt(i-1) != 'e'))
						break;
					i--;
				}
				chunk[0] = i + 1;
				chunk[1] = i + 1;
				
				return chunk;
			}
			else if (lastChar == '}' || lastChar == ')') {
				chunk[2] = index;
				
				char openChar;
				if (lastChar == '}') {
					openChar = '{';
				}
				else {
					openChar = '(';
				}
				int bracketDeterminant = -1; // determines if the last bracket has been "closed"
				int i = index - 1;
				while(i >= 0 && bracketDeterminant != 0) {
					if (str.charAt(i) == openChar) {
						bracketDeterminant += 1;
					}
					else if(str.charAt(i) == lastChar) {
						bracketDeterminant += -1;
					}
					
					i--;
				}		
				chunk[1] = i + 2;
				int j = i;
				// For functions
				if (j < 0 || !Character.isAlphabetic(str.charAt(j))) {
					chunk[0] = i + 1;
					return chunk;
				}
				while (j >= 0 && Character.isAlphabetic(str.charAt(j))) {
					j--;
				}
				chunk[0] = j + 1;
				return chunk;
			}
			else if (Character.isAlphabetic(lastChar)) {
				chunk[2] = chunk[3];
				int i = index - 1;
				while (i >= 0 && Character.isAlphabetic(str.charAt(i))) {
					i--;
				}
				chunk[0] = i + 1;
				chunk[1] = chunk[0];
			}
		return chunk;
	}
	
	/**
	 * Gets information about the "chunk" (number, parentheses expression, function, function name, etc.) starting at index
	 * @param str The string containing a mathematical expression
	 * @param index the index of the first character of the chunk
	 * @return an int array representing the bounds of the chunk. [index of first character of outside of chunk, index of first character of inside of chunk (e.g. the parameters of a function inside parentheses), 1 + index of last character of inside of chunk, 1 + index of last character of outside of chunk]
	 */
	public static int[] chunkStartingAt(String str, int index) throws IndexOutOfBoundsException {
		int[] chunk = new int[4];
		char firstChar = str.charAt(index);
		
		if (Character.isAlphabetic(firstChar)) {
			int i = index;
			i++;
			
			while (str.charAt(i) != '(' && str.charAt(i) != '{') {
				i++;
			}
			
			chunk = chunkStartingAt(str, i);
			chunk[0] = index;
			
			return chunk;
		}
		
		chunk[0] = index;
		
		int strLength = str.length();
		
		if (Character.isDigit(firstChar) || firstChar == '-' || firstChar == '.') {
			chunk[1] = index;
			
			int i = index;
			
			i++;
			while (i < strLength && (Character.isDigit(str.charAt(i)) || str.charAt(i) == 'e' || str.charAt(i) == '.' || str.charAt(i) == '-')) {
				i++;
			}
			
			chunk[2] = i;
			chunk[3] = i;
			
			return chunk;
		}
		else if (firstChar == '(' || firstChar == '{') {
			chunk[1] = index + 1;
			
			char closeChar;
			if (firstChar == '{') {
				closeChar = '}';
			}
			else {
				closeChar = ')';
			}
			int bracketDeterminant = 1;
			
			int i = index;
			
			i++;
			
			while(i < strLength && bracketDeterminant != 0) {
				if (str.charAt(i) == closeChar) {
					bracketDeterminant += -1;
				}
				else if(str.charAt(i) == firstChar) {
					bracketDeterminant += 1;
				}
				
				i++;
			}
			
			chunk[2] = i - 1;
			chunk[3] = i;
			
			return chunk;
		}
		return chunk;
	}
	
	/**
	 * Replaces a chunk with a given String
	 * @param str The entire input to be manipulated
	 * @param begin The first index of the chunk to be replaced
	 * @param end The last index + 1 of the chunk to be replaced
	 * @param replace What to replace the chunk in question with (length does NOT have to be equal)
	 * @return str with the chunk from [begin, end) replaced with replace
	 */
	public static String chunkReplace(String str, int begin, int end, String replace) throws IndexOutOfBoundsException{
		return str.substring(0, begin) + replace + str.substring(end);
	}
	
	/**
	 * Replaces a binary operation with a parenthetical function expression
	 * @param str The entire input to be manipulated
	 * @param index The index of the character of the binary operation
	 * @param replace The name of the function to replace the binary operator with
	 * @return The str with the binary operator located at index transformed into function notation
	 */
	public static String binaryOperatorReplace(String str, int index, String replace) throws IndexOutOfBoundsException{
		int[] firstChunkBounds = chunkEndingAt(str, index - 1);
		int[] lastChunkBounds = chunkStartingAt(str, index + 1);
		String firstChunk = str.substring(firstChunkBounds[0], firstChunkBounds[3]);
		
		String lastChunk = str.substring(lastChunkBounds[0], lastChunkBounds[3]);
		return chunkReplace(str, firstChunkBounds[0], lastChunkBounds[3], replace + "(" + firstChunk + "," + lastChunk + ")");
	}
	
	/**
	 * Gets information about the calculator's state on which angle measure to use.
	 * @return true is the calculator is in radian mode, false if it is in degree mode
	 */
	public boolean getUseRadians() {
		return useRadians;
	}
	
	/**
	 * Sets the angle measure the calculator uses.
	 * @param useRadians true if the calculator is to use radians, false is the calculator is to use degrees.
	 */
	public void setUseRadians(boolean useRadians) {
		this.useRadians = useRadians;
	}
	
	/**
	 * Gets a certain answer (output) in the calculator's history.
	 * @param i The index of the answer to fetch. The calculation currently being performed is considered answer 0 and cannot be accessed. Answer 1 is the output of the first calculation performed and so on. Negative indexes are relative to answer 0: -1 represents the output of the previous calculation, for example.
	 * @return A String representing the output at i.
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String ans(int i) throws ArrayIndexOutOfBoundsException{
		return answerHistory.get(i);
	}
	
	/**
	 * Gets a certain input in the calculator's history.
	 * @param i The index of the input to fetch. The calculation currently being performed is considered input 0 and cannot be accessed. Input 1 is the input of the first calculation performed and so on. Negative indexes are relative to input 0: -1 represents the input of the previous calculation, for example.
	 * @return A String representing the input at i.
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String in(int i) throws ArrayIndexOutOfBoundsException{
		if (i < 0)
			i--;  
		return inputHistory.get(i);
	}
	
	/**
	 * Exits the calculator and prevents further calculation. All inputs are returned with a message of termination.
	 */
	public void exit() {
		open = false;
	}
	
	/**
	 * Whether or not this Calculator is open.
	 * @return false if the Calculator has been closed, true if it is still accepting input.
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * @return the functionList
	 */
	public Function[] getFunctionList() {
		return functionList;
	}

	/**
	 * @param functionList the functionList to set
	 */
	public void setFunctionList(Function[] functionList) {
		this.functionList = functionList;
	}

	/**
	 * @return the answerHistory
	 */
	public RollingList<String> getAnswerHistory() {
		return answerHistory;
	}

	/**
	 * @param answerHistory the answerHistory to set
	 */
	public void setAnswerHistory(RollingList<String> answerHistory) {
		this.answerHistory = answerHistory;
	}

	/**
	 * @return the inputHistory
	 */
	public RollingList<String> getInputHistory() {
		return inputHistory;
	}

	/**
	 * @param inputHistory the inputHistory to set
	 */
	public void setInputHistory(RollingList<String> inputHistory) {
		this.inputHistory = inputHistory;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * @return the currNumInCalls
	 */
	public int getCurrNumInCalls() {
		return currNumInCalls;
	}
	
	public void incrementCurrNumInCalls() {
		currNumInCalls++;
	}

	/**
	 * @param currNumInCalls the currNumInCalls to set
	 */
	public void setCurrNumInCalls(int currNumInCalls) {
		this.currNumInCalls = currNumInCalls;
	}
	
	/**
	 * Prints information about this project.
	 */
	public void header() {
		System.out.println("The Scientific Calculator");
		System.out.println("Created by Gabe Classon for Mr. Nichols' AP Java class.");
		System.out.println("Last modified 2019-05-01");
		System.out.println("Enter 'help()' for more information. Enter 'list()' for a list of functions.");
		System.out.println();
	}
}
