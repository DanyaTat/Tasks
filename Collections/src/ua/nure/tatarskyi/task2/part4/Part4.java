package ua.nure.tatarskyi.task2.part4;

public class Part4 {
	public static void main(String[] args) {
        StackImpl s = new StackImpl();

        s.push("A");
        s.push("B");
        s.push("C");
        
        System.out.println(s.pop());       
        System.out.println(s.pop());       
        System.out.println(s.pop());       

    }
}
