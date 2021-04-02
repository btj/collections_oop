package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashMap implements Map {
	
	/**
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     Arrays.stream(buckets[i].entrySet().toArray()).allMatch(e ->
	 *        |          Math.floorMod(((Entry)e).getKey().hashCode(), buckets.length) == i)) 
	 * 
	 * @representationObject
	 * @representationObjects
	 */
	private Map[] buckets;
	
	public HashMap(int capacity) {
		buckets = new Map[capacity];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArrayMap();
	}
	
	Map getBucket(Object key) {
		int hashCode = key.hashCode();
		int index = Math.floorMod(hashCode, buckets.length);
		return buckets[index];
	}
	
	@Override
	public Set entrySet() {
		ArraySet result = new ArraySet();
		for (int i = 0; i < buckets.length; i++) {
			Set bucketEntries = buckets[i].entrySet();
			Object[] array = bucketEntries.toArray();
			for (int j = 0; j < array.length; j++)
				result.add(array[j]);
		}
		return result;
	}
	
	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(bucket -> bucket.size()).sum();
	}
	
	public boolean containsKey(Object key) {
		return getBucket(key).containsKey(key);
	}
	
	public Object get(Object key) {
		return getBucket(key).get(key);
	}
	
	public void put(Object key, Object value) {
		getBucket(key).put(key, value);
	}
	
	public void remove(Object key) {
		getBucket(key).remove(key);
	}

}
