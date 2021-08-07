package ua.nure.tatarskyi.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	public static void main(String[] args) {
		String input = Util.readFile("part3.txt");
		System.out.println(convert(input));
	}

	public static String convert(String input) {
		Pattern patternWord = Pattern.compile("[A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]{3,}", Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcherWord = patternWord.matcher(input);
		StringBuilder result = new StringBuilder();
		while (matcherWord.find()) {
			StringBuilder legacyWord = new StringBuilder(matcherWord.group());
			StringBuilder word = new StringBuilder(legacyWord.toString());
			if (Character.isUpperCase(word.charAt(0))) {
				word.setCharAt(0, Character.toLowerCase(word.charAt(0)));
			} else {
				word.setCharAt(0, Character.toUpperCase(word.charAt(0)));
			}
			input = input.replaceAll(legacyWord.toString(), word.toString());
			result.append(input.substring(0, input.indexOf(word.toString()) + word.length()));
			input = input.substring(input.indexOf(word.toString()) + word.length());
		}
		return result.toString();
	}
}
