package collections_oop;

import java.util.Arrays;

/**
 * @invar | Arrays.stream(entrySet().toArray()).map(e -> ((Entry)e).getKey()).distinct().count() == entrySet().size()
 */
public interface Map {
	
	interface Entry {
		Object getKey();
		Object getValue();
	}
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | Arrays.stream(result.toArray()).allMatch(e -> e instanceof Entry)
	 */
	Set entrySet();
	
	default int size() { return entrySet().size(); }
	
	default boolean containsKey(Object key) { return Arrays.stream(entrySet().toArray()).anyMatch(e -> ((Entry)e).getKey().equals(key)); }
	
	default Object get(Object key) {
		return Arrays.stream(entrySet().toArray())
				     .filter(e -> ((Entry)e).getKey().equals(key))
				     .map(e -> ((Entry)e).getValue())
				     .findAny().orElse(null);
	}
	
	/**
	 * @pre | key != null
	 * @pre | value != null
	 * @mutates | this
	 * @post | containsKey(key)
	 * @post | get(key) == value
	 * @post | Arrays.stream(entrySet().toArray()).allMatch(e ->
	 *       |     ((Entry)e).getKey().equals(key) ?
	 *       |          ((Entry)e).getValue() == value
	 *       |     :
	 *       |          old(entrySet()).contains(e))
	 * @post | Arrays.stream(old(entrySet().toArray())).allMatch(e ->
	 *       |     ((Entry)e).getKey().equals(key) ||
	 *       |     entrySet().contains(e))
	 */
	void put(Object key, Object value);
	
	/**
	 * @pre | key != null
	 * @mutates | this
	 * @post | Arrays.stream(entrySet().toArray()).allMatch(e ->
	 *       |     !((Entry)e).getKey().equals(key) && old(entrySet()).contains(e))
	 * @post | Arrays.stream(old(entrySet().toArray())).allMatch(e ->
	 *       |     ((Entry)e).getKey().equals(key) || entrySet().contains(e))
	 */
	void remove(Object key);

}
