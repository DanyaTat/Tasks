package ua.nure.tatarskyi.task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String input = Util.readFile("part6.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        Pattern patternWord = Pattern.compile("[A-ZÀ-ßa-zà-ÿ¸º³¿ª¯²¨]+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcherWord = patternWord.matcher(input);
        while (matcherWord.find()) {
            StringBuilder legacyWord = new StringBuilder(matcherWord.group());
            StringBuilder word = new StringBuilder("_" + legacyWord.toString());
            if (input.indexOf(word.toString()) != -1) {
                continue;
            }
            Pattern findOccurences = Pattern.compile("\\b" + legacyWord.toString() + "\\b", 
            		Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcherOccurences = findOccurences.matcher(input);
            int count = 0;
            while (matcherOccurences.find()) {
                matcherOccurences.group();
                count++;
            }
            if(count > 1) {
                input = input.replaceAll("\\b" + legacyWord.toString() + "\\b", word.toString());
            }
        }
        return input;
    }
}
