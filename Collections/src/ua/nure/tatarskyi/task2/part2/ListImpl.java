package ua.nure.tatarskyi.task2.part2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List<Object> {

	protected static class Node {
		protected Object item;
		protected Node previous;
		protected Node next;
	}

	protected int size;
	protected Node first;
	protected Node last;

	@Override
	public void clear() {
		Node x = first;
		while (x != null) {
			Node next = x.next;
			x.item = null;
			x.next = null;
			x.previous = null;
			x = next;
		}
		first = last = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		private Node currentNode = first;
		private Node lastReturned;
		private boolean isDeleted;

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (!isDeleted) {
				lastReturned = currentNode;
			}
			currentNode = currentNode.next;
			isDeleted = false;
			return lastReturned.item;
		}

		@Override
		public void remove() {
			if (isDeleted || lastReturned == null) {
				throw new IllegalStateException();
			}
			if (currentNode != null) {
				lastReturned.item = currentNode.item;
				lastReturned.next = currentNode.next;
				size--;
			} else {
				removeLast();
			}
			isDeleted = true;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void addFirst(Object element) {
		Node oldFirst = first;
		first = new Node();
		first.item = element;
		first.next = oldFirst;
		if (oldFirst != null) {
			oldFirst.previous = first;
		}
		if (isEmpty()) {
			last = first;
		}
		size++;
	}

	@Override
	public void addLast(Object element) {
		Node previousLast = last;
		last = new Node();
		last.item = element;
		last.previous = previousLast;
		if (previousLast != null) {
			previousLast.next = last;
		}
		if (isEmpty()) {
			first = last;
		}
		size++;
	}

	@Override
	public void removeFirst() {
		if (isEmpty() || first == null) {
			return;
		}
		if (first.next != null) {
			first.next.previous = null;
		} else {
			last = null;
		}
		first = first.next;
		size--;
	}

	@Override
	public void removeLast() {
		if (first == null) {
			return;
		}
		if (first.next == null) {
			first = null;
			return;
		}
		Node current = first;
		while (current.next.next != null) {
			current = current.next;
		}
		current.next.item = null;
		current.next = null;

		size--;
	}

	@Override
	public Object getFirst() {
		if (first != null) {
			Object tempObject = first.item;
			removeFirst();
			return tempObject;
		}
		return null;
	}

	public Object getFirstItem() {
		if (first != null) {
			return first.item;
		}
		return null;
	}

	@Override
	public Object getLast() {
		if (last != null) {
			Object tempObject = last.item;
			removeLast();
			return tempObject;
		}
		return null;
	}

	@Override
	public Object search(Object element) {
		if (first.item == null) {
			return null;
		}
		Node tempNode = first;
		while (true) {
			if (tempNode.item.equals(element)) {
				return tempNode.item;
			}
			if (tempNode.next != null) {
				tempNode = tempNode.next;
			} else {
				return null;
			}
		}
	}

	@Override
	public boolean remove(Object element) {
		for (Node x = first; x != null; x = x.next) {
			if (element.equals(x.item)) {
				unlink(x);
				return true;
			}
		}
		return false;
	}

	public void unlink(Node x) {
		final Node next = x.next;
		final Node prev = x.previous;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.previous = null;
		}
		if (next == null) {
			last = prev;
		} else {
			next.previous = prev;
			x.next = null;
		}
		x.item = null;
		size--;
	}

	@Override
	public String toString() {
		Node tempNode = first;
		if (tempNode == null) {
			return "[]";
		}
		StringBuilder tempBuilder = new StringBuilder("[");
		while (true) {
			String summOfStrings = "" + tempNode.item + ", ";
			tempBuilder.append(summOfStrings);
			if (tempNode.next != null) {
				tempNode = tempNode.next;
			} else {
				break;
			}
		}
		if (size() > 0) {
			tempBuilder.delete(tempBuilder.length() - 2, tempBuilder.length());
			tempBuilder.append("]");
		}
		return tempBuilder.toString();

	}
}
