package dataStructures;

public class HashTableIterator<K,V> implements Iterator<Entry<K,V>> {

    Dictionary<K,V>[] table;
    int lastIteredList, lastEmptyList;
    Iterator<Entry<K,V>> currIterator;

    public HashTableIterator(Dictionary<K,V>[] table) {
        this.table = table;
        lastIteredList = 0;
        currIterator = this.advance();
    }

    @Override
    public boolean hasNext() {
        return currIterator.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!this.hasNext())
            throw new NoSuchElementException();

        Entry<K,V> next = currIterator.next();

        if(!(currIterator.hasNext()))
            currIterator = this.advance();

        return next;
    }

    @Override
    public void rewind() {
        lastIteredList = 0;
        this.advance();
    }
    
    private Iterator<Entry<K, V>> advance() {
        for (int i = lastIteredList; i < table.length; i++) {
            if (!(table[i].isEmpty())) {
                lastIteredList = i + 1;
                return table[i].iterator();
            } else 
                lastEmptyList = i;
        
        }

        return table[lastEmptyList].iterator(); //TODO: Not working.
    }
}