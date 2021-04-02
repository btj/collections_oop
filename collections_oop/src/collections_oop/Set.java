package collections_oop;

import java.util.Arrays;

/**
 * @invar The set does not contain duplicates.
 *     | Arrays.stream(toArray()).distinct().count() == size()
 */
public interface Set {
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @creates | result
	 * @basic
	 */
	Object[] toArray();

	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	default int size() { return toArray().length; }
	
	/**
	 * @pre | object != null
	 * @inspects | this
	 * @post | result == Arrays.stream(toArray()).anyMatch(e -> e.equals(object))
	 */
	default boolean contains(Object object) {
		Object[] elements = toArray();
		for (Object element : elements)
			if (element.equals(object))
				return true;
		return false;
	}
	
	/**
	 * @pre | object != null
	 * @mutates | this
	 * @post | contains(object)
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> contains(e))
	 * @post | Arrays.stream(toArray()).allMatch(e -> e == object || Arrays.stream(old(toArray())).anyMatch(e1 -> e1 == e))
	 */
	void add(Object object);
	
	/**
	 * @pre | object != null
	 * @mutates | this
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> e.equals(object) || contains(e))
	 * @post | Arrays.stream(toArray()).allMatch(e -> !e.equals(object) && Arrays.stream(old(toArray())).anyMatch(e1 -> e1 == e))
	 */
	void remove(Object object);
	
}
