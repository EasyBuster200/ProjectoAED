package dataStructures;

public class ConcatenableQueueInList<E> extends QueueInList implements ConcatenableQueue<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConcatenableQueueInList(){
		super();	
	}

	@Override
	public void append(ConcatenableQueue<E> queue) {
		// TODO   Auto-generated method stub
		if(queue instanceof ConcatenableQueueInList<?>) {
			DoubleList<E> l1 = (DoubleList<E>) this.list;
			DoubleList<E> l2 = (DoubleList<E>) ((ConcatenableQueueInList <E>) queue).list;
			l1.append(l2);
			
			((DoubleList<E>) this.list).append((DoubleList<E>)((ConcatenableQueueInList<E>)queue).list);//why not this?
			
			
			
		}else {
			while(!queue.isEmpty()) {
				this.enqueue(queue.dequeue());
			}
			
		}
	}


}
