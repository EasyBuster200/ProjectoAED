package dataStructures;

/**
 * Concatenable Queue Abstratact Data Type
 * @author lipyc
 * @param <E>
 */
public interface ConcatenableQueue<E> extends Queue {
	
	/**
	 *Removes all the elements of the specified que and
	 *inserts them at the end opf the queue(in proper order)
	 * @param queue
	 */
	void append(ConcatenableQueue <E> queue);
}
