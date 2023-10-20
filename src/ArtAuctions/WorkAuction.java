package ArtAuctions;

import java.io.Serializable;

import dataStructures.Iterator;

public interface WorkAuction extends Serializable {
    
    void addBid(Bid bid);

    Iterator<Bid> bidsIterator();

    boolean hasBids();

    ArtWork getWork();

    int minimumBidValue();
}
