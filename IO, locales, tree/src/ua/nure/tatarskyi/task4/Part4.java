package ua.nure.tatarskyi.task4;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {
	public static final String FILE_WITH_RAND = "part4.txt";
    public static final String FILE_WITH_OUTPUT = "part4_sorted.txt";

    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();
        int[] arrayOfNumbers = new int[10];
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            arrayOfNumbers[i] = rand.nextInt(50);
        }
        StringBuilder sb = new StringBuilder();
        for (int x : arrayOfNumbers) {
            sb.append(x + " ");
        }
        System.out.println("input ==> " + sb.toString().trim());
        writeInFile(FILE_WITH_RAND, sb.toString().trim());
        String[] numbersFromFile = Part1.getFromFile(FILE_WITH_RAND).split(" ");
        int[] numbers = new int[numbersFromFile.length];

        for (int i = 0; i < numbersFromFile.length; i++) {
            numbers[i] = Integer.parseInt(numbersFromFile[i]);
        }

        Arrays.sort(numbers);
        sb = new StringBuilder();
        for (int x : numbers) {
        	Pattern pattern = Pattern.compile("\\b" + x + "\\b");
        	Matcher matcher = pattern.matcher(sb.toString());
        	if(!matcher.find()) {
            sb.append(x + " ");
        	}
        }
        System.out.println("output ==> " + sb.toString().trim());

        writeInFile(FILE_WITH_OUTPUT, sb.toString().trim());
    }

    public static void writeInFile(String fileName, String data) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "cp1251"))) {
            writer.write(data);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
