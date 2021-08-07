package ua.nure.tatarskyi.task2.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.nure.tatarskyi.task2.part1.ArrayImpl;

public class StackImpl extends ArrayImpl implements Stack<Object> {

	@Override
	public void push(Object element) {
		super.add(element);
	}

	@Override
	public Object pop() {
		if (sizeOfArr == 0) {
			return null;
		}
		sizeOfArr--;
		return super.get(sizeOfArr);
	}

	@Override
	public Object top() {
		return super.get(sizeOfArr - 1);
	}

	private class IteratorImpl implements Iterator<Object> {
		private int index = sizeOfArr - 1;
		private boolean deletedCurrent;

		@Override
		public boolean hasNext() {
			return (index >= 0);
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			deletedCurrent = false;
			return arr[index--];
		}

		@Override
		public void remove() {
			if (index == sizeOfArr - 1) {
				throw new IllegalStateException();
			}
			if (deletedCurrent) {
				throw new IllegalStateException();
			}
			StackImpl.super.remove(index + 1);
			deletedCurrent = true;
		}
	}

	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}
}
