package collections_oop;

import java.util.Arrays;

class SimpleEntry implements Map.Entry {
	private final Object key;
	private final Object value;
	
	public Object getKey() { return key; }
	public Object getValue() { return value; }
	
	public SimpleEntry(Object key, Object value) {
		this.key = key;
		this.value = value;
	}
}

public class ArrayMap implements Map {
	
	/**
	 * @invar | entries != null
	 * @invar | Arrays.stream(entries.toArray()).allMatch(e -> e instanceof Entry)
	 * @invar | Arrays.stream(entries.toArray()).map(e -> ((Entry)e).getKey()).distinct().count() == entries.size()
	 * @representationObject
	 */
	private ArrayList entries = new ArrayList();
	
	public Set entrySet() {
		ArraySet result = new ArraySet();
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}
	
	public int size() { return entries.size(); }
	
	public boolean containsKey(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				return true;
		return false;
	}
	
	public Object get(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				return ((Entry)entries.get(i)).getValue();
		return null;
	}
	
	public void put(Object key, Object value) {
		if (!containsKey(key))
			entries.add(new SimpleEntry(key, value));
	}
	
	public void remove(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).getKey().equals(key))
				entries.remove(i);
	}

}
