package ua.nure.tatarskyi.task2.part1;

import ua.nure.tatarskyi.task2.Container;

public interface Array<E> extends Container<E> {

	// Add the specified element to the end.
	void add(E element);

	// Sets the element at the specified position.
	void set(int index, E element);

	// Returns the element at the specified position.
	E get(int index);

	// Returns the index of the first occurrence of the specified element,
	// or -1 if this array does not contain the element.
	// (use 'equals' method to check an occurrence)
	int indexOf(E element);

	// Removes the element at the specified position.
	void remove(int index);

}