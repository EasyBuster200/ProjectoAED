package dataStructures;

public class InvertibleQueueInList<E> extends QueueInList<E>  implements InvertibleQueue<E> {

	protected boolean leftToRight;
	
	public InvertibleQueueInList() {
		super();
		leftToRight = true;
	}

	@Override
	public void invert() {
		leftToRight = !leftToRight;
	}
	
	public void enqueue( E element) {
		if(leftToRight) {
			list.addLast(element);
		}else {
			list.addFirst(element);
		}
	}
	
	public E dequeue () {
		if(list.isEmpty()) {
			throw new EmptyQueueException();
		}else {
			if(leftToRight) {
				return list.removeFirst();
			}else {
				return list.removeLast();
			}
		}
	}
	
	
}
