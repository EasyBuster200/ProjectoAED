package dataStructures;  

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            // Original comentado para nao dar erro de compilacao.
            table[i] = new OrderedDoubleList<K,V>();
            //table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            //TODO: left as an exercise.
        	//Original commented, to compile.
            this.rehash();
        // Left as an exercise.

        V oldValue = this.find(key);
        int pos = this.hash(key);

        if (oldValue == null)  {
            table[pos].insert(key, value);
            currentSize++;

        } else {
            table[pos].remove(key);
            table[pos].insert(key, value);
            currentSize++;

        }

        return oldValue;

    }
    
    @Override
    public V remove( K key )
    {
        //TODO: Left as an exercise.
        currentSize--;
        return table[hash(key)].remove(key);
    }
    
    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        //TODO: Left as an exercise.
        return new HashTableIterator<>(table);
        
        /*
        *TODO: IteratorHashTable 
        *  What do we have to keep at all times:
        * - Hastable
        * - Iterator of the current list 
        * - Pos of the last itered list 
        * 
         * Methods hasNext() --> ver se o iterador da lista corrente tem next
         *          next() --> Testar se ha next
         *                      Obter next do iterador da lista 
         *                      Se o iterador da lista n tem next e a pos atual n é a ultima --> metodo auxiliar, advance(), que procura a proxima lista n vazia
         *                                                                                          se o advance sair fora da HashTable devolvemos a ultima pos da table
         */
    } 
    
    @SuppressWarnings("unchecked")
    private void rehash() {
        //TODO: Check if * 2 is too much, in terms of space complexity

        int arraySize = HashTable.nextPrime((int) (1.1 * (currentSize * 2)));
        Dictionary<K,V>[] newTable = (Dictionary<K,V>[]) new Dictionary[arraySize];

        for ( int i = 0; i < arraySize; i++ )
            newTable[i] = new OrderedDoubleList<K,V>();
        maxSize = currentSize * 2;

        Iterator<Entry<K,V>> it = this.iterator();
        while (it.hasNext()) {
            Entry<K,V> current = it.next();
            newTable[this.hash(current.getKey())].insert(current.getKey(), current.getValue());
        }

        table = newTable;
    }
}
































