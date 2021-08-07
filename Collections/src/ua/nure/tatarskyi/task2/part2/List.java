package ua.nure.tatarskyi.task2.part2;

import ua.nure.tatarskyi.task2.Container;

public interface List<E> extends Container<E> {

	// Inserts the specified element at the beginning.
	void addFirst(E element);

	// Appends the specified element to the end.
	void addLast(E element);

	// Removes the first element.
	void removeFirst();

	// Removes the last element.
	void removeLast();

	// Removes and returns the first element.
	E getFirst();

	// Removes and returns the last element.
	E getLast();

	// Returns the first occurrence of the specified element.
	// Returns null if no such element.
	// (use 'equals' method to check an occurrence)
	E search(E element);

	// Removes the first occurrence of the specified element.
	// Returns true if this list contained the specified element. 
	// (use 'equals' method to check an occurrence)
	boolean remove(E element);

}