package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class WorkAuctionClass implements WorkAuction {

    private ArtWork work;
    private int minimumValue;
    private List<Bid> bids;
    
    public WorkAuctionClass(ArtWork work, int minimumValue) {
        this.work = work;
        this.minimumValue = minimumValue;
        this.bids = new DoubleList<>();
    }

    @Override
    public void addBid(Bid bid) {
        bids.addLast(bid);
    }

    @Override
    public Iterator<Bid> bidsIterator() {
        return bids.iterator();
    }

    @Override
    public boolean hasBids() {
        return !bids.isEmpty();
    }

    @Override
    public ArtWork getWork() {
        return work;
    }

    @Override
    public int minimumBidValue() {
        return minimumValue;
    }

}
