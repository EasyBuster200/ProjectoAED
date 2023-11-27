package dataStructures;

public class InvertIntegerCompare implements Comparator<Integer> {

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 1L;
 
    public int compare( Integer key1, Integer key2) {
        return key2-key1;
       }
}
