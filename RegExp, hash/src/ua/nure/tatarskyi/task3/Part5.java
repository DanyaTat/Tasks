package ua.nure.tatarskyi.task3;

public class Part5 {
	public static final int BORDER = 100;
	protected static final int[] ROMANVALUES = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	protected static final String[] ROMANCHARACTERS = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	public static void main(String[] args) {
		for (int i = 1; i < BORDER; i++) {
			System.out.println(i + "-->" + decimal2Roman(i) + "-->" + roman2Decimal(decimal2Roman(i)));
		}
	}

	public static String decimal2Roman(int dec) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < ROMANVALUES.length; i++) {
			int numberToAdd = dec / ROMANVALUES[i];
			if (numberToAdd == 0) {
				continue;
			}
			for(int j = 0; j < numberToAdd; j++) {
			result.append(ROMANCHARACTERS[i]);
			}
			dec = dec % ROMANVALUES[i];
		}
		return result.toString();
	}

	public static int roman2Decimal(String roman) {
		int index = 0;
		int result = 0;

		while (roman.length() > 0 && index < ROMANCHARACTERS.length) {
			if (roman.indexOf(ROMANCHARACTERS[index]) == 0) {
				result += ROMANVALUES[index];
				roman = roman.substring(ROMANCHARACTERS[index].length());
			} else {
				index++;
			}
		}
		return result;
	}
}
