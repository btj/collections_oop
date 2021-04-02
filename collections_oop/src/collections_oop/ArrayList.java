package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayList implements List {
	
	/**
	 * @invar | 0 <= size
	 * @invar | size <= elements.length
	 */
	private int size;
	/**
	 * @invar | elements != null
	 * @invar | IntStream.range(0, size).allMatch(i -> elements[i] != null)
	 * @representationObject
	 */
	private Object[] elements = new Object[10];
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Object get(int index) {
		return elements[index];
	}
	
	@Override
	public boolean contains(Object object) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(object))
				return true;
		return false;
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArrayList() {}
	
	@Override
	public void add(Object object) { // amortized constant time
		if (elements.length == size) {
			Object[] newElements = new Object[elements.length * 2];
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements;
		}
		elements[size++] = object;
	}
	
	@Override
	public void set(int index, Object object) {
		elements[index] = object;
	}
	
	@Override
	public void remove(int index) {
		System.arraycopy(elements, index + 1, elements, index, size - index);
		size--;
		elements[size] = null;
	}

}
