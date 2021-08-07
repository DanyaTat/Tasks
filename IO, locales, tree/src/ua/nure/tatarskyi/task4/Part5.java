package ua.nure.tatarskyi.task4;

public class Part5 {
    
    public static void main(String[] args) {
    	Tree tree = new Tree<>();
    	System.out.println(tree.add(7));
    	tree.add(new Integer[] { 1, 2, 3, 4});
    	System.out.println(tree.remove(5));
    	System.out.println(tree.add(7));
    	System.out.println(tree.remove(2));
    	tree.print();
    }
    
}
