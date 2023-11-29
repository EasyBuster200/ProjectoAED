package dataStructures;

public class InvertedIntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer int1, Integer int2) {
        return int2 - int1;
    }
    
}
