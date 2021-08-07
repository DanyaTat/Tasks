package ua.nure.tatarskyi.task4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordContainer {
    private static ArrayList<Word> wordList;

    public static void main(String[] args) {
        wordList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Pattern patternOfWord = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcherWord;
        boolean stop = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            matcherWord = patternOfWord.matcher(line);
            while (matcherWord.find()) {
                String word = matcherWord.group();
                if ("stop".equals(word)) {
                    stop = true;
                    break;
                }
                addFrequency(word);
            }
            if (stop) {
                break;
            }
        }
        sorting();
        print();
    }

    private static void addFrequency(String word) {
        boolean hasWord = false;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).getContent().equals(word)) {
                wordList.get(i).setFrequency(wordList.get(i).getFrequency() + 1);
                hasWord = true;
                break;
            }
        }
        if (!hasWord) {
            wordList.add(new Word(word, 1));
        }
    }

    private static void print() {
        for (Word w : wordList) {
            System.out.println(w.getContent() + " : " + w.getFrequency());
        }
    }

    private static void sorting() {
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size() - 1; j++) {
                if (wordList.get(j).getFrequency() > wordList.get(j + 1).getFrequency()) {
                    Word tempWord = wordList.get(j);
                    wordList.set(j, wordList.get(j + 1));
                    wordList.set(j + 1, tempWord);
                }
            }
        }
        
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size() - 1; j++) {
            	if(wordList.get(j).getFrequency() == wordList.get(j + 1).getFrequency() 
                		&& wordList.get(j).getContent().compareTo(wordList.get(j + 1).getContent()) > 0) {
                	Word tempWord = wordList.get(j);
                    wordList.set(j, wordList.get(j + 1));
                    wordList.set(j + 1, tempWord);
                }
            }
        }
    }
}
