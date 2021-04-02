package collections_oop;

import java.util.Arrays;

public class ArraySet implements Set {
	
	/**
	 * @invar | elements != null
	 * @invar | Arrays.stream(elements.toArray()).distinct().count() == elements.size()
	 * @representationObject
	 */
	private ArrayList elements = new ArrayList();
	
	@Override
	public Object[] toArray() {
		return elements.toArray();
	}
	
	@Override
	public int size() {
		return elements.size();
	}
	
	@Override
	public boolean contains(Object object) {
		return elements.contains(object);
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArraySet() {}
	
	@Override
	public void add(Object object) {
		if (!elements.contains(object))
			elements.add(object);
	}
	
	@Override
	public void remove(Object object) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).equals(object)) {
				elements.remove(i);
				return;
			}
		}
	}

}
