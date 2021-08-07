package ua.nure.tatarskyi.task2.part2;

public class Part2 {
	public static void main(String[] args) {
		ListImpl linkList = new ListImpl();
		
		linkList.addFirst(3);	
		linkList.addFirst(2);
		linkList.addFirst(1);
		System.out.println(linkList.toString());
		System.out.println(linkList.search(1));
		System.out.println(linkList.search(2));
		System.out.println(linkList.getLast());
	}
}
