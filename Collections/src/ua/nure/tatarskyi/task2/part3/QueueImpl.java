package ua.nure.tatarskyi.task2.part3;

import ua.nure.tatarskyi.task2.part2.ListImpl;

public class QueueImpl extends ListImpl implements Queue<Object> {

	@Override
	public void enqueue(Object element) {
		super.addLast(element);
	}

	@Override
	public Object dequeue() {
		return super.getFirst();
	}

	@Override
	public Object top() {
		return super.getFirstItem();
	}
}
