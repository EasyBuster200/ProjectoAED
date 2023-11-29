package dataStructures;

/**
 * Auxilary Class, that recieves an Iterator of Entry<K,V> and transforms into an iterator of V
 * This class recieves Iterators of Entry type objects, and only returns the value of the Entry, for eaiser Iteration.
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt 
 */
public class EntryIterator<K,V> implements Iterator<V> {
    protected Iterator<Entry<K,V>> it;

    public EntryIterator (Iterator<Entry<K,V>> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return this.it.hasNext();
    }

    @Override
    public V next() throws NoSuchElementException {
        return it.next().getValue();
    }

    @Override
    public void rewind() {
        it.rewind();
    }
    
}
