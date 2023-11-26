package ArtAuctions;

import java.io.Serializable;
import dataStructures.Iterator;

/**
 * WorkAuction entity
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@gmail.campus.fct.unl.pt
 */
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
     * Check if this workAuction has any bids
     * @return <code>true</code> if this workAuction has bids
     * <code>false</code> otherwise
     */
    boolean hasBids();

    /**
     * Return the work of this work auction.
     * @return auction artWork  
     */
    ArtWork getWork();

    /**
     * Returns the minimum acceptable bid value in this workAuction
     * @return
     */
    int minimumBidValue();

    
    /**
     * Closes this workAuction and sets the last sold price
     * of in this workAuction
     */
    void closeAuction();
}
