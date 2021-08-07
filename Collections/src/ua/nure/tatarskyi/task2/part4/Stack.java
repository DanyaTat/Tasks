package ua.nure.tatarskyi.task2.part4;

import ua.nure.tatarskyi.task2.Container;

public interface Stack<E> extends Container<E> {
	
	// Pushes the specified element onto the top.
	void push(E element);
	
	// Removes and returns the top element.
	E pop();

	// Returns the top element.
	E top();
	
}