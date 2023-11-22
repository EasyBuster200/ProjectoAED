package dataStructures;


/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <K, V> Generics - K extends Comparable
 * 
 */
public class OrderedDoubleList<K extends Comparable<K>, V> 
	implements OrderedDictionary<K , V> {

    /**
	 * Serial Version UID of the ClassgetElement().
	 */
    static final long serialVersionUID = 0L;
    
	/**
     *  Node at the head of the list.
     */
	protected DoubleListNode<Entry<K,V>> head;

    /**
     * Node at the tail of the list.
     */
	protected DoubleListNode<Entry<K,V>> tail;

    /**
     * Number of elements in the list.
     */
	protected int currentSize;
	
    /**
     * Constructor of an empty ordered double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
	public OrderedDoubleList() {
		head=null;
		tail=null;
		currentSize=0;
	}

    /**
     * Inserts the Entry element before node after.
     * Precondition: after is not the head of the ordered double list.
     * @param element - Entry to be inserted
     * @param after - Node to be next to the new node  
     */
	protected void addBeforeNode(Entry<K,V> element, DoubleListNode<Entry<K,V>> after){
		//Left as an exercise.
        DoubleListNode<Entry<K,V>> before = new DoubleListNode<>(element, after.getPrevious(), after);
        after.getPrevious().setNext(before);
        after.setPrevious(before);
        currentSize++;
	}
	
    /**
     * Inserts the Entry element at the first position in the list.
     * @param element - Entry to be inserted
     */
    protected void addFirst( Entry<K,V> element )
    {
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }


    /**
     * Inserts the Entry element at the last position in the list.
     * @param element - Entry to be inserted
     */
    protected void addLast( Entry<K,V> element )
    {
        //Left as an exercise.
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, tail, null);
        if (this.isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

	@Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		//Left as an exercise.

        if (this.isEmpty())
            throw new EmptyDictionaryException();
        else 
            return tail.getElement();
	}

    @Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		//Left as an exercise.

        if (this.isEmpty())
            throw new EmptyDictionaryException();
        else
            return head.getElement();
	}

    /**
     * Returns the node with the Entry with Key key
     * in the list, if the list contains this entry.
     * Otherwise, returns null.
     * @param key - Key of type K to be searched
     * @return DoubleListNode<E> where the Entry with key was found, or the one with the key immmediately after 
     */
	protected DoubleListNode<Entry<K,V>> findNode (K key){
		//Left as an exercise.

        DoubleListNode<Entry<K,V>> node = head;

        while (node != null) {
            if (node.getElement().getKey().compareTo(key) >= 0)
                return node;

            node = node.getNext();
        }
        
        return null;
        /*
         * Cases:
         * - Fim da Lista - return null
         * - Encontramos um q é igual - return node
         * - ENcontramos um que é "maior", logo oq procuramos n existe. - return o primeiro "maior"
         */
	}
	
    @Override
	public V find(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		//Left as an exercise.
        
        if (node == null || node.getElement().getKey().compareTo(key) != 0)
            return null;

        return node.getElement().getValue();
        /*
         * Cases:
         * - O no é nulo - return null
         * - ou a chave n é igual - return null
         */
	}


	@Override
	public V insert(K key, V value) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		if ((node != null) && (node.getElement().getKey().compareTo(key) == 0)){
			//Left as an exercise.
            V oldValue = node.getElement().getValue();
            node.getElement().setValue(value);
            return oldValue;
        
            /*
             * Save the value currently in the node
             * Replace the value in the node with the new one
             * return the old value
             */
		}
		else { 
		    Entry<K,V> newNode = new EntryClass<K,V> (key, value);
          
            if (node == head)
                addFirst(newNode);

            else if (node == null)
                addLast(newNode);

            else 
                addBeforeNode(newNode, node);
            
		  //recommended: re-use addFirst, addLast and addBeforeNode
          //Left as an exercise.
          return null; 
          /*
           * Cases:
           * - node é a head --> addFirst
           * - Caso contrario node == null addLast();
           * - Node != null addBefore
           */
		}
	}
	
	@Override
    public boolean isEmpty() {
		return currentSize == 0;
	}

    @Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K,V>>(head, tail);
	}

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode( )
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the first entry in the list.
     */
    protected V removeFirst( ) throws EmptyDictionaryException
    {
        //Left as an exercise.
        if (this.isEmpty())
            throw new EmptyDictionaryException();
    
        V oldValue = head.getElement().getValue();
        this.removeFirstNode();
    	return oldValue;
    }


    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode( )
    {
        //Left as an exercise.
        
        tail = tail.getPrevious();
        if ( tail == null )
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the last entry in the list.
     */
    protected V removeLast( ) throws EmptyDictionaryException
    {
        if ( this.isEmpty() )
            throw new EmptyDictionaryException();

        V value = tail.getElement().getValue();
        this.removeLastNode();
        return value;
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode( DoubleListNode<Entry<K,V>> node )
    {
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
        currentSize--;

    }

    @Override
    public V remove(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		if ((node == null) || (node.getElement().getKey().compareTo(key)!=0))
			return null;
		else {
			  	//Left as an exercise.
            if (node == head)
                return removeFirst();

            else if (node == tail)
                return removeLast();

            else {
                removeMiddleNode(node);
                return node.getElement().getValue();
            }
            /*
             * -No é a head --> removeFirst()
             * -No é a tail --> removeLast()
             * -No é um do "meio" --> removeMiddle()
             */
		}
	}

    @Override
	public int size() {
		return currentSize;
	}
	
	
}
