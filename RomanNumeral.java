/**
 * This application converts integers that are less than 4000
 * (i.e. default max is 3999) into Roman Numerals and converts
 * Roman Numerals into integers.
 * 
 * The user is prompted to enter either an integer or a Roman
 * Numeral. If an integer is entered, then the Roman Numeral 
 * equivalent is printed; else, the integer equivalent of 
 * the Roman Numeral is printed if it is a valid Roman Numeral.
 * If it is not a valid Roman Numeral, a message is printed
 * stating such.
 * 
 * @author Natalie Fleischaker
 * @created 5.8.2015
 * @updated 5.14.2015
 */
import java.util.Scanner;

public class RomanNumeral {
	public static final int DFLT_MAX     = 3999;
	public static final int EXIT         = -1;       //needs to be < 0
	public static final String TOO_LG    = "Integer is too large. Please try again.";
	public static final String NOT_VALID = "Not a valid integer. Please try again.";
	
	public static boolean problem;     //flagged true when problem with roman numeral
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int number;
		String roman;
                                                                                		
		do {
			problem = false;
			
			System.out.print("\nEnter either an integer between 0 and "
					         + DFLT_MAX + " or a Roman Numeral (" 
			                 + EXIT + " to exit): ");
			
			if (input.hasNextInt()) {
				number = input.nextInt();
				if (number == EXIT)
					break;
				else {
					roman = toRoman(number);
					if (roman.equals(TOO_LG) || roman.equals(NOT_VALID))
						System.out.println(roman);
					else
						System.out.println("Roman Numeral: " + roman);
				}
			} else {
				roman = input.next();
				roman = roman.toUpperCase();
				number = toDecimal(roman);
				if (problem) {
					System.out.println("This is not a proper Roman Numeral.");
					number = 0;
				}
				else
					System.out.println("Integer: " + number);
			}
		} while (number != EXIT);
		input.close();
	}
	
	/*
	 * The toRoman method takes an int as the input and
	 * converts this int into a String containing its 
	 * Roman Numeral equivalent. Returns the word nulla
	 * if 0 is entered.
	 * 
	 * If a number larger than the default max number is 
	 * entered then a message is returned stating such.
	 * 
	 * If a negative number is entered, a message is 
	 * returned which states that it is not a valid number.
	 *
	 */
	public static String toRoman(int number) {
		String roman = "";
		if (number > DFLT_MAX)
			return TOO_LG;
		if (number <= EXIT)
			return NOT_VALID;
		if (number == 0)
			return "nulla";
		while (number >= 1000) {
			roman += "M";
			number -= 1000;
		}
		while (number >= 900) {
			roman += "CM";
			number -= 900;
		}
		while (number >= 500) {
			roman += "D";
			number -= 500;
		}
		while (number >= 400) {
			roman += "CD";
			number -= 400;
		}
		while (number >= 100) {
			roman += "C";
			number -= 100;
		}
		while (number >= 90) {
			roman += "XC";
			number -= 90;
		}
		while (number >= 50) {
			roman += "L";
			number -= 50;
		}
		while (number >= 40) {
			roman += "XL";
			number -= 40;
		}
		while (number >= 10) {
			roman += "X";
			number -= 10;
		}
		while (number >= 9) {
			roman += "IX";
			number -= 9;
		}
		while (number >= 5) {
			roman += "V";
			number -= 5;
		}
		while (number >= 4) {
			roman += "IV";
			number -= 4;
		}
		while (number >= 1) {
			roman += "I";
			number -= 1;
		}
		return roman;
	}
	
	/*
	 * The toDecimal method takes a String as the input
	 * and returns the equivalent integer value if the 
	 * string entered is a valid Roman Numeral.
	 * 
	 * If the string is not a valid Roman Numeral, the
	 * problem boolean flag is changed to true so that an
	 * error message can be printed.
	 *
	 */
	public static int toDecimal(String roman) {
		int number = 0;
		if (romanCheck(roman)) {
			while (roman.startsWith("M")) {
				roman = roman.substring(1);
				number += 1000;
			}
			if (roman.startsWith("CM")) {
				roman = roman.substring(2);
				number += 900;
				if (letterValue(roman) > 99)
					problem = true;
			}
			if (roman.startsWith("D")) {
				roman = roman.substring(1);
				number += 500;
			} else if (roman.startsWith("CD")) {
				roman = roman.substring(2);
				number += 400;
				if (letterValue(roman) > 99)
					problem = true;
			}
			while (roman.startsWith("C")) {
				roman = roman.substring(1);
				number += 100;
			}
			if (roman.startsWith("XC")) {
				roman = roman.substring(2);
				number += 90;
				if (letterValue(roman) > 9)
					problem = true;
			}
			if (roman.startsWith("L")) {
				roman = roman.substring(1);
				number += 50;
			} else if (roman.startsWith("XL")) {
				roman = roman.substring(2);
				number += 40;
				if (letterValue(roman) > 9)
					problem = true;
			}
			while (roman.startsWith("X")) {
				roman = roman.substring(1);
				number += 10;
			}
			if (roman.startsWith("IX")) {
				roman = roman.substring(2);
				number += 9;
				if (roman.length() != 0)
					problem = true;
			}
			if (roman.startsWith("V")) {
				roman = roman.substring(1);
				number += 5;
			} else if (roman.startsWith("IV")) {
				roman = roman.substring(2);
				number += 4;
				if (roman.length() != 0)
					problem = true;
			}
			while (roman.startsWith("I")) {
				roman = roman.substring(1);
				number += 1;
			}
			if (roman.length() != 0)
				problem = true;
			return number;
		} else {
			problem = true;
			return number;
		}
	}
	
//  /* The recursive versions of the methods are commented out
//   * and left unused because toDecimalRecurse needs a more thorough
//   * romanCheck method which would guarantee a well-formed Roman Numeral,
//   * and toRomanRecurse would need to exclude the special case of "nulla"
//   * when 0 is entered (i.e. 0 would have to be excluded from valid 
//	 * entries).
//	 */
//	
//	/*
//	 * 
//	 *
//	 */
//	public static String toRomanRecurse(int number) {
//		if (number > DFLT_MAX)
//			return TOO_LG;
//		if (number <= EXIT)
//			return NOT_VALID;
//		if (number == 0)
//			return "";
//		if (number >= 1000)
//			return "M" + toRomanRecurse(number - 1000);
//		if (number >= 900)
//			return "CM" + toRomanRecurse(number - 900);
//		if (number >= 500)
//			return "D" + toRomanRecurse(number - 500);
//		if (number >= 400)
//			return "CD" + toRomanRecurse(number - 400);
//		if (number >= 100)
//			return "C" + toRomanRecurse(number - 100);
//		if (number >= 90)
//			return "XC" + toRomanRecurse(number - 90);
//		if (number >= 50)
//			return "L" + toRomanRecurse(number - 50);
//		if (number >= 40)
//			return "XL" + toRomanRecurse(number - 40);
//		if (number >= 10)
//			return "X" + toRomanRecurse(number - 10);
//		if (number >= 9)
//			return "IX" + toRomanRecurse(number - 9);
//		if (number >= 5)
//			return "V" + toRomanRecurse(number - 5);
//		if (number >= 4)
//			return "IV" + toRomanRecurse(number - 4);
//		if (number >= 1)
//			return "I" + toRomanRecurse(number - 1);
//		else
//			return "problem";
//	}
//	
//	/*
//	 * 
//	 *
//	 */
//	public static int toDecimalRecurse(String roman) {
//		if (roman.isEmpty())
//			return 0;
//		if (roman.startsWith("M"))
//			return 1000 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("CM"))
//			return 900 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("D"))
//			return 500 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("CD"))
//			return 400 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("C"))
//			return 100 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("XC"))
//			return 90 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("L"))
//			return 50 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("XL"))
//			return 40 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("X"))
//			return 10 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("IX"))
//			return 9 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("V"))
//			return 5 + toDecimalRecurse(roman.substring(1));
//		if (roman.startsWith("IV"))
//			return 4 + toDecimalRecurse(roman.substring(2));
//		if (roman.startsWith("I"))
//			return 1 + toDecimalRecurse(roman.substring(1));
//		else {
//			problem = true;
//			return EXIT;
//		}
//	}
//	
	
	/*
	 * Checks to see if the String roman is a valid 
	 * Roman Numeral by making sure each letter has
	 * a valid Roman Numeral value, and if the value 
	 * of the letter to the right of the current letter
	 * is higher then the letters combined together must
	 * have a valid Roman Numeral value (e.g. "XL" has a
	 * value of 40).
	 * 
	 * Also, checks to make sure that the same numeral is not
	 * repeated more than three times (i.e. XXXX is not 
	 * valid).
	 * 
	 * Returns true if the String is a valid 
	 * Roman Numeral and false otherwise.
	 * 
	 * There are still some problems that it does not check
	 * for such as IX being followed by a V or an I. These
	 * must be accounted for elsewhere.
	 */
	private static boolean romanCheck(String roman) {
		roman = roman.toUpperCase();
		
		for (int i = 0; i < roman.length() - 1; i++) {
			String letter     = "" + roman.charAt(i);
			String nextLetter = "" + roman.charAt(i + 1);
			
			int value     = letterValue(letter);
			int nextValue = letterValue(nextLetter);
			
			if (value == EXIT)
				return false;
			if (nextValue > value) {
				letter += nextLetter;
				value = letterValue(letter);
				
				if (value == EXIT)
					return false;
			} else if (nextValue == value) {
				if (value == 1000 || value == 100 || value == 10
					|| value == 1) {
					if (i + 3 < roman.length()) {
						if (roman.charAt(i) == roman.charAt(i + 2) &&
							roman.charAt(i) == roman.charAt(i + 3))
							return false;
					}
				} else
					return false;
			}
		}
		return true;
	}
	
	/*
	 * Finds the integer value of a letter if it is a
	 * legal Roman numeral. Returns EXIT constant if letter
	 * is not a legal Roman numeral. The variable letter
	 * must be upper case.
	 *
	 */
	private static int letterValue(String letter) {
		switch (letter) {
			case "I": return 1;
			case "IV": return 4;
			case "V": return 5;
			case "IX": return 9;
			case "X": return 10;
			case "XL": return 40;
			case "L": return 50;
			case "XC": return 90;
			case "C": return 100;
			case "CD": return 400;
			case "D": return 500;
			case "CM": return 900;
			case "M": return 1000;
			default: return EXIT;
		}
	}
}
