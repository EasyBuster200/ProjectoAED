package dataStructures;

import ArtAuctions.ArtWork;

public class ArtWorkComparatorByPrice implements Comparator<ArtWork> {

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 1L;
 
    public int compare( ArtWork w1, ArtWork w2) {
        return w2.highestSoldValue()- w1.highestSoldValue();
       }
}
