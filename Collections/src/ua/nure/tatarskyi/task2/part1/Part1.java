package ua.nure.tatarskyi.task2.part1;

import java.util.Iterator;

public class Part1 {
	public static void main(String[] args) {
		ArrayImpl array = new ArrayImpl();
		array.add("A");
		array.add("B");
		array.add("C");

		System.out.println(array);
		System.out.println(array.size());

		array.clear();
		System.out.println(array);
		System.out.println(array.size());

		array.add("A");
		array.add("B");
		array.add("C");

		System.out.println(array);
		System.out.println(array.size());
		
		array.remove(0);
		array.remove(0);
		array.remove(0);
		array.remove(0);
		array.remove(0);
		array.remove(0);

		System.out.println(array);
		
		Iterator<?> iterator = array.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        
		System.out.println(array);

	}
}
