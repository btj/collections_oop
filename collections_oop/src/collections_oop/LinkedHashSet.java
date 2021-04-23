package collections_oop;

public class LinkedHashSet implements Set {
	
	private static class Node {
		Node previous;
		Object element;
		Node next;
	}
	
	private Node sentinel;
	private HashMap map;
	
	public LinkedHashSet(int capacity) {
		sentinel = new Node();
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
		map = new HashMap(capacity);
	}
	
	public Object[] toArray() {
		ArrayList result = new ArrayList();
		Node n = sentinel.next;
		while (n != sentinel) {
			result.add(n.element);
			n = n.next;
		}
		return result.toArray();
	}
	
	public int size() {
		return map.size();
	}
	
	public boolean contains(Object object) {
		return map.containsKey(object);
	}
	
	public void add(Object object) {
		if (map.containsKey(object))
			return;
		Node node = new Node();
		node.element = object;
		node.previous = sentinel.previous;
		node.next = sentinel;
		node.previous.next = node;
		sentinel.previous = node;
		map.put(object, node);
	}
	
	public void remove(Object object) {
		Node node = (Node)map.get(object);
		if (node != null) {
			node.next.previous = node.previous;
			node.previous.next = node.next;
			map.remove(object);
		}
	}

}
