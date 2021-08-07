package ua.nure.tatarskyi.task4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	public static void main(String[] args) {
		String input = getFromFile("part1.txt");
		String query = "";
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				query = scanner.nextLine();
				if ("stop".equals(query) || "Stop".equals(query)) {
					return;
				}
				System.out.print(query + ": ");
				switch (query) {
				case "Latn":
				case "latn":
					matches(input, "[A-Za-z]+");
					break;
				case "Cyrl":
				case "cyrl":
					matches(input, "[À-ßà-ÿ¸º³¿ª¯²¨]+");
					break;
				default:
					System.out.println("Incorrect input");
					break;
				}
			}
		}
	}

	public static void matches(String input, String regex) {
		Pattern patternWord = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcherWord = patternWord.matcher(input);
		while (matcherWord.find()) {
			System.out.print(matcherWord.group() + " ");
		}
		System.out.println();
	}

	public static String getFromFile(String fileName) {
		StringBuilder input = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(fileName), "cp1251");
			while (scanner.hasNextLine()) {
				input.append(scanner.nextLine() + " ");
			}
			scanner.close();
		} catch (IOException ex) {
			System.out.print(ex.getMessage());
		}
		return input.toString();
	}
}
