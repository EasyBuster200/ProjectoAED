package dataStructures;

/**
 * Auxilary Class, that recieves an Iterator of a given type and casts another type on the returned object
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt 
 */
public class TypeConversionIterator<K, V> implements Iterator<V> {

    /**
     * Original Iterator with the original Type
     */
    private final Iterator<K> it;

    public TypeConversionIterator(Iterator<K> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        return this.it.hasNext();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V next() throws NoSuchElementException {

        K nextItem = this.it.next();
        return (V) nextItem; 
    }

    @Override
    public void rewind() {
        this.it.rewind();
    }
}