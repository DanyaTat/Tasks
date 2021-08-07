package ua.nure.tatarskyi.task2.part3;

import ua.nure.tatarskyi.task2.Container;

public interface Queue<E> extends Container<E> {

	// Appends the specified element to the end.
	void enqueue(E element);

	// Removes the head.
	E dequeue();

	// Returns the head.
	E top();

}