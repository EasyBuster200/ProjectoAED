package ArtAuctions;

import java.io.Serializable;
import dataStructures.Iterator;

public interface WorkAuction extends Serializable {
    
    /**
     * Adds a given bid to the auction
     * @param bid bid to be added
     */
    void addBid(Bid bid);

    /**
     * Return an Iterator with all the registered bids
     * @return Iterator with the registered bids
     */
    Iterator<Bid> bidsIterator();

    /**
     * @return
     */
    boolean hasBids();

    /**
     * @return
     */
    ArtWork getWork();

    /**
     * @return
     */
    int minimumBidValue();
}
