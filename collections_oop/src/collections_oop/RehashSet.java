package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RehashSet implements Set {
	
	/**
	 * @invar | buckets != null
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     Arrays.stream(buckets[i].toArray()).allMatch(e ->
	 *        |         Math.floorMod(e.hashCode(), buckets.length) == i))
	 * 
	 */
	private final float maxLoadFactor;
	/**
	 * @representationObject
	 * @representationObjects
	 */
	private Set[] buckets;
	
	/**
	 * @pre | 1 <= maxLoadFactor
	 * @post | size() == 0
	 */
	public RehashSet(float maxLoadFactor) {
		this.maxLoadFactor = maxLoadFactor;
		buckets = new Set[10];
		for (int i = 0; i < 10; i++)
			buckets[i] = new ArraySet();
	}
	
	private Set getBucket(Object object) {
		int hashCode = object.hashCode();
		int index = Math.floorMod(hashCode, buckets.length);
		return buckets[index];
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.stream(buckets).flatMap(bucket -> Arrays.stream(bucket.toArray())).toArray();
	}
	
	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(bucket -> bucket.size()).sum();
	}
	
	@Override
	public boolean contains(Object object) {
		return getBucket(object).contains(object);
	}
	
	@Override
	public void add(Object object) {
		getBucket(object).add(object);
		
		while (1.0 * size() / buckets.length > maxLoadFactor) {
			Set[] newBuckets = new Set[buckets.length * 2];
			for (int i = 0; i < newBuckets.length; i++)
				newBuckets[i] = new ArraySet();
			for (int i = 0; i < buckets.length; i++) {
				Object[] elements = buckets[i].toArray();
				for (Object element : elements)
					newBuckets[Math.floorMod(element.hashCode(), newBuckets.length)].add(element);
			}
			buckets = newBuckets;
		}
	}
	
	@Override
	public void remove(Object object) {
		getBucket(object).remove(object);
	}

}
