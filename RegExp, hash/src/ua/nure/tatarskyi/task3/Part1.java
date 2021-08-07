package ua.nure.tatarskyi.task3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	public static final int RANDNUMBER = 9000;
	public static final int SHIFTRAND = 999;
	public static void main(String[] args) {
		String input = Util.readFile("part1.txt");
		System.out.println(convert1(input));
		System.out.println(convert2(input));
		System.out.println(convert3(input));
		System.out.println(convert4(input));
	}

	public static String convert1(String input) {
		StringBuilder output = new StringBuilder();
		Pattern patternSurname = Pattern.compile("\\b[a-zà-ÿ¸º³¿]+\\b;", Pattern.UNICODE_CHARACTER_CLASS);
		Pattern patternEmail = Pattern.compile("[A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]+@[a-z]+\\.[a-z]+");
		Matcher matcherSurname = patternSurname.matcher(input);
		Matcher matcherEmail = patternEmail.matcher(input);

		while (matcherSurname.find() && matcherEmail.find()) {
			String surname = matcherSurname.group();
			surname = surname.substring(0, surname.length() - 1);
			String name = matcherEmail.group();
			output.append(surname + ": " + name + "\n");
		}
		return output.toString();
	}

	public static String convert2(String input) {
		StringBuilder output = new StringBuilder();
		Pattern patternFullname = Pattern.compile(";\\b[A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]+\\b [A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]+\\b;",
				Pattern.UNICODE_CHARACTER_CLASS);
		Pattern patternEmail = Pattern.compile("[A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]+@[a-z]+\\.[a-z]+");
		Matcher matcherFullname = patternFullname.matcher(input);
		Matcher matcherEmail = patternEmail.matcher(input);

		while (matcherFullname.find() && matcherEmail.find()) {
			StringBuilder fullname = new StringBuilder(matcherFullname.group());
			fullname.delete(0, 1);
			fullname.delete(fullname.length() - 1, fullname.length());
			fullname.append(" " + fullname.substring(0, fullname.indexOf(" ")));
			fullname.delete(0, fullname.indexOf(" ") + 1);
			String name = matcherEmail.group();
			output.append(fullname.toString() + " (email: " + name + ")\n");
		}

		return output.toString();
	}

	public static String convert3(String input) {
		Pattern patternSurname = Pattern.compile("[a-zà-ÿ¸º³¿]+;", Pattern.UNICODE_CHARACTER_CLASS);
		Pattern searchForMail = Pattern.compile("@[a-z]+\\.[a-z]+");
		Matcher matcherForMail = searchForMail.matcher(input);
		StringBuilder result = new StringBuilder();

		while (matcherForMail.find()) {
			String mail = matcherForMail.group().substring(1);
			if (result.toString().contains(mail)) {
				continue;
			}
			result.append(mail + " ==> ");
			for(String line: input.split("\r?\n")) {
				Matcher matcherForName = patternSurname.matcher(line);
				if (line.contains(mail) && matcherForName.find()) {
					String name = matcherForName.group();
					result.append(name.substring(0, name.length() - 1) + ", ");
				}
				
			}
			result.delete(result.length() - 2, result.length());
			result.append("\n");
		}
		return result.toString();
	}

	public static String convert4(String input) {
		SecureRandom rand = new SecureRandom();
		StringBuilder tempSB = new StringBuilder();
		for(String line: input.split("\r?\n")) {
			if ("Login;Name;Email".equals(line)) {
				tempSB.append("Login;Name;Email;Password\n");
				continue;
			}
			tempSB.append(line + ";" + (rand.nextInt(RANDNUMBER) + SHIFTRAND) + "\n");
		}
		return tempSB.toString();
	}
}
