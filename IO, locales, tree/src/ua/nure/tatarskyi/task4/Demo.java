package ua.nure.tatarskyi.task4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {

    private static final InputStream STD_IN = System.in;

    public static void main(String[] args) {
        System.out.println("=========================== PART1");
        System.setIn(new ByteArrayInputStream(
                "Latn^Cyrl^asdf^latn^cyrl^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part1.main(args);
        System.setIn(STD_IN);
        
        System.out.println("=========================== PART2");
        Part2.main(args);
        
        System.out.println("=========================== PART3");
        System.setIn(new ByteArrayInputStream(
                "table ru^table en^apple ru^asdf asdf^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part3.main(args);
        System.setIn(STD_IN);
        
        System.out.println("=========================== PART4");
        Part4.main(args);
        
        System.out.println("=========================== PART5");
        Part5.main(args);
    }
}
