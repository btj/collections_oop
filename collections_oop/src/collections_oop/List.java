package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Each instance of this class stores a sequence of objects, called the list's elements.
 * 
 * @invar | Arrays.stream(toArray()).allMatch(e -> e != null)
 */
public interface List extends Collection {
	
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
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @inspects | this
	 * @post | result == toArray()[index]
	 */
	default Object get(int index) { return toArray()[index]; }
	
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
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(), 0, old(size()), old(toArray()), 0, old(size()))
	 * @post | get(old(size())) == object
	 */
	void add(Object object);
	
	/**
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @pre | object != null
	 * @mutates | this
	 * @post | size() == old(size())
	 * @post | IntStream.range(0, size()).allMatch(i -> get(i) == (i == index ? object : old(toArray())[i]))
	 * 
	 *     condition ? true-value : false-value
	 */
	void set(int index, Object object);
	
	/**
	 * @pre | 0 <= index
	 * @pre | index < size()
	 * @mutates | this
	 * @post | size() == old(size()) - 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index, size(), old(toArray()), index + 1, old(size()))
	 * 
	 * post | IntStream.range(0, size()).allMatch(i -> get(i) == (i < index ? old(toArray())[i] : old(toArray())[i + 1]))
	 */
	void remove(int index);
	
}
