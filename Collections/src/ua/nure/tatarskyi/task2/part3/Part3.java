package ua.nure.tatarskyi.task2.part3;

import java.util.Iterator;

public class Part3 {
	public static void main(String[] args) {
		QueueImpl q = new QueueImpl();
		q.clear();
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		q.enqueue("4");
				
		Iterator<?> iterator = q.iterator();
		int index = 0;
		while (iterator.hasNext()) {
			index++;
			iterator.next();
			if (index % 2 == 0) {
				iterator.remove();
			}
		}
		
		iterator = q.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
}
