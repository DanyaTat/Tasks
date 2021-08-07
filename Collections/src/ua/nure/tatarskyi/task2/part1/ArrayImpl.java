package ua.nure.tatarskyi.task2.part1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array<Object> {
	protected Object[] arr;
	protected int sizeOfArr;

	public ArrayImpl() {
		arr = new Object[0];
		sizeOfArr = 0;
	}

	public ArrayImpl(int i) {
		arr = new Object[i];
		sizeOfArr = 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < sizeOfArr; i++) {
			arr[i] = null;
		}
		sizeOfArr = 0;
	}

	@Override
	public int size() {
		return sizeOfArr;
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		private int nextIndex;
		private boolean deletedCurrent;

		@Override
		public boolean hasNext() {
			return (nextIndex < sizeOfArr);
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			deletedCurrent = false;
			return arr[nextIndex++];
		}

		@Override
		public void remove() {
			if (nextIndex == 0) {
				throw new IllegalStateException();
			}
			if (deletedCurrent) {
				throw new IllegalStateException();
			}
			ArrayImpl.this.remove(nextIndex - 1);
			deletedCurrent = true;
			--nextIndex;
		}
	}

	@Override
	public void add(Object element) {
		Object[] temp = new Object[sizeOfArr + 1];
		System.arraycopy(arr, 0, temp, 0, sizeOfArr);
		arr = temp;
		arr[sizeOfArr++] = element;
	}

	@Override
	public void set(int index, Object element) {
		arr[index] = element;
	}

	@Override
	public Object get(int index) {
		return arr[index];
	}

	@Override
	public int indexOf(Object element) {
		if (element == null) {
			for (int i = 0; i < sizeOfArr; i++) {
				if (arr[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < sizeOfArr; i++) {
				if (element.equals(arr[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public void remove(int index) {
		if(index < 0 || sizeOfArr == 0) {
			return;
		}
        arr[index] = null;
        for (int i = index; i < sizeOfArr - 1; ++i) {
            arr[i] = arr[i + 1];
        }
        arr[sizeOfArr - 1] = null;
        --sizeOfArr;
	}

	@Override
	public String toString() {
		StringBuilder tempBuilder = new StringBuilder("[");
		for (int i = 0; i < sizeOfArr; i++) {
			tempBuilder.append(arr[i]).append(", ");
		}
		if (sizeOfArr > 0) {
			tempBuilder.delete(tempBuilder.length() - 2, tempBuilder.length());
		}
		return tempBuilder.toString().trim() + "]";
	}
}
