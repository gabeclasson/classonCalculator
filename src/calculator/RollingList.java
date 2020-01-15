package calculator;

import java.util.ArrayList;

/**
 * The Rolling List has two components: a relative and an absolute system of storage built on top of the ArrayList
 * @author Gabe Classon
 * The indexes of a RollingList are significant in that the first element is assigned index 1, the second index 2, etc. Negative (relative) indexes are allowed. The last element of the List is at -1, the second to last at -2, etc. 0 is not an allowed index. 
 * @param <T> The type of object stored by this RollingList
 */
@SuppressWarnings("serial")
public class RollingList<T> extends ArrayList<T>{
	/**
	 * Gets the element at index.
	 */
	public T get(int index) {
		return super.get(indexConvert(index));
	}
	
	/**
	 * Sets the element at index to t and returns the element which was already there.
	 */
	public T set(int index, T t) {
		return super.set(indexConvert(index), t);
	}
	
	/**
	 * Adds t to the List at index and shifts all following entries accordingly.
	 */
	public void add(int index, T t) {
		super.add(indexConvert(index), t);
	}
	
	/**
	 * Removes the entry at index from the List and returns it.
	 */
	public T remove(int index) {
		return super.remove(indexConvert(index));
	}
	
	/**
	 * The indexes of a RollingList are significant in that the first element is assigned index 1, the second index 2, etc. Negative (relative) indexes are allowed. The last element of the List is at -1, the second to last at -2, etc. 0 is not an allowed index. This method converts an index provided by the user (in the format just described) into the more familiar standard index format used throughout the language.
	 * @param index An index.
	 * @return An absolute index to be used for the internally contained ArrayList<>
	 */
	public int indexConvert(int index) {
		if (index == 0)
			return -1;
		if (index < 0)
			return index + this.size();
		return --index;
	}
}
