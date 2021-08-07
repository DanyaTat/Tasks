package ua.nure.tatarskyi.task4;

public class Tree<E extends Comparable<E>> {
	
	private static class Node<E> {

		private Node<E> leftNodeChild;
		private Node<E> rightNodeChild;
		private E itemOfNode;

		public Node(E item) {
			leftNodeChild = null;
			rightNodeChild = null;
			itemOfNode = item;
		}
	}
	
	private Node<E> rootOfTree;

	public boolean add(E input) {
		if (contains(input)) {
			return false;
		} else if (rootOfTree == null) {
			rootOfTree = new Node<>(input);
		} else {
			addIn(rootOfTree, input);
		}
		return true;
	}
	
	public void addIn(Node<E> node, E input) {
		if (node.itemOfNode.compareTo(input) > 0) {
			if (node.leftNodeChild == null) {
				node.leftNodeChild = new Node<>(input);
			} else {
				addIn(node.leftNodeChild, input);
			}
		} else {
			if (node.rightNodeChild == null) {
				node.rightNodeChild = new Node<>(input);
			} else {
				addIn(node.rightNodeChild, input);
			}
		}

	}

	public void add(E[] elements) {
		for (int i = 0; i < elements.length; ++i) {
			add(elements[i]);
		}
	}

	public boolean remove(E element) {
		if (!contains(element)) {
			return false;
		}
		rootOfTree = recRemove(rootOfTree, element);
		return true;
	}

	private Node<E> recRemove(Node<E> node, E element) {
		if (node == null || (node.leftNodeChild == null && node.rightNodeChild == null)) {
			return null;
		}
		if (node.itemOfNode == element) {
			if (node.leftNodeChild == null || node.rightNodeChild == null) {
				return node.rightNodeChild == null ? node.leftNodeChild : node.rightNodeChild;
			}
			E smallest = findMostSmallRecurse(node.rightNodeChild);
			node.itemOfNode = smallest;
			node.rightNodeChild = recRemove(node.rightNodeChild, smallest);
			return node;
		}
		if (node.itemOfNode.compareTo(element) > 0) {
			node.leftNodeChild = recRemove(node.leftNodeChild, element);
			return node;
		}
		node.rightNodeChild = recRemove(node.rightNodeChild, element);
		return node;
	}

	public void print() {
		printTreeRecurse("", rootOfTree);
	}

	public void printTreeRecurse(String offset, Node<E> node) {

		if (node != null) {
			String twoSpace = "  ";
			printTreeRecurse(offset + twoSpace, node.leftNodeChild);
			System.out.println(offset + node.itemOfNode);
			printTreeRecurse(offset + twoSpace, node.rightNodeChild);
		}
	}

	private E findMostSmallRecurse(Node<E> node) {
		if (node.leftNodeChild == null) {
			return node.itemOfNode;
		} else {
			return findMostSmallRecurse(node.leftNodeChild);
		}
	}

	private Boolean contains(E element) {
		Node<E> curr = rootOfTree;
		while (curr != null) {
			if (curr.itemOfNode == element) {
				return true;
			} else if (curr.itemOfNode.compareTo(element) > 0) {
				curr = curr.leftNodeChild;
			} else {
				curr = curr.rightNodeChild;
			}
		}
		return false;
	}
}

