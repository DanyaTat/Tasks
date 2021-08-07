package ua.nure.tatarskyi.task3;

public class Part2 {
	public static final String SPLITTER = "[\',\\s\n\r-]+";
    public static void main(String[] args) {
        String input = Util.readFile("part2.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder min = new StringBuilder("Min: ");
        StringBuilder max = new StringBuilder("Max: ");
        int minSize = getMinLength(input);
        int maxSize = getMaxLength(input);
        
        for (String s: input.split(SPLITTER)) {
            if(s.length() == maxSize && max.indexOf(s) == -1) {
                max.append(s + ", ");
            }

            if(s.length() == minSize && min.indexOf(s) == -1) {
                min.append(s + ", ");
            }
        }

        min.delete(min.length() - 2, min.length());
        max.delete(max.length() - 2, max.length());
        return min.toString() + "\n" + max.toString();
    }
    
    public static int getMaxLength(String input) {
        int maxSize = -1;

        for (String s: input.split(SPLITTER)) {
            if(maxSize == -1) {
                maxSize = s.length();
            }
            if(s.length() > maxSize) {
                maxSize = s.length();
            }
        }
        return maxSize;
    }
    
    public static int getMinLength(String input) {
    	int minSize = -1;
    	
    	for (String s: input.split(SPLITTER)) {
            if(minSize == -1) {
                minSize = s.length();
            }
            if(s.length() < minSize) {
                minSize = s.length();
            }
        }
    	return minSize;
    }
}
