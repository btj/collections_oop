package collections_oop;

public class LinkedList implements List {
	
	private class Node {
		/**
		 * @invar | (value == null) == (this == sentinel) 
		 * @invar | previous != null
		 * @invar | next != null
		 * @invar | previous.next == this
		 * @invar | next.previous == this
		 * 
		 * @peerObject
		 */
		private Node previous;
		private Object value;
		/**
		 * @peerObject
		 */
		private Node next;
		
		private int getSize() {
			return this == sentinel ? 0 : 1 + next.getSize();
		}
	}
	
	/**
	 * @invar | size == sentinel.next.getSize()
	 */
	private int size;
	/**
	 * @representationObject
	 */
	private Node sentinel;
	
	private Node getNode(int index) {
		if (index < size / 2) {
			Node n = sentinel.next;
			while (index > 0) {
				n = n.next;
				index--;
			}
			return n;
		} else {
			Node n = sentinel;
			while (index < size) {
				n = n.previous;
				index++;
			}
			return n;
		}
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		Node n = sentinel.next;
		for (int i = 0; i < size; i++) {
			result[i] = n.value;
			n = n.next;
		}
		return result;
	}
	
	@Override
	public int size() { return size; }
	
	@Override
	public Object get(int index) { return getNode(index).value; }
	
	@Override
	public boolean contains(Object object) {
		Node n = sentinel.next;
		while (n != sentinel) {
			if (n.value.equals(object))
				return true;
			n = n.next;
		}
		return false;
	}
	
	public LinkedList() {
		// Cyclic doubly linked list
		sentinel = new Node();
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
	}
	
	@Override
	public void add(Object object) {
		Node n = new Node();
		n.value = object;
		n.previous = sentinel.previous;
		n.next = sentinel;
		sentinel.previous = n;
		n.previous.next = n;
		size++;
	}
	
	@Override
	public void set(int index, Object object) {
		Node n = getNode(index);
		n.value = object;
	}
	
	@Override
	public void remove(int index) {
		Node n = getNode(index);
		n.previous.next = n.next;
		n.next.previous = n.previous;
		size--;
	}

}
