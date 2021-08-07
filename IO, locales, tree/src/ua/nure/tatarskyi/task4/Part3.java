package ua.nure.tatarskyi.task4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().split(" ");
            
            if("stop".equals(input[0])) {
                break;
            }
            
            if(input.length != 2) {
            	System.out.println("Incorrect input");
            	continue;
            }

            Locale locale = new Locale(input[1]);
            ResourceBundle langProperty = ResourceBundle.getBundle("resources", locale);
            System.out.print(langProperty.getString(input[0]) + System.lineSeparator());
        }
    }
}
