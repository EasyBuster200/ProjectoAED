package dataStructures;

import java.io.Serializable;

public interface Comparator<E> extends Serializable{

    // Compares its two arguments for order.
    // Returns a negative integer, zero or a positive integer
    // as the first argument is less than, equal to, or greater
    // than the second.
    int compare(E element1, E element2);
    
}
