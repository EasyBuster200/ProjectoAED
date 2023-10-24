package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class WorkAuctionClass implements WorkAuction {

    private ArtWork work;
    private int minimumValue;
    private List<Bid> bids;
    private Bid highestBid;
    
    public WorkAuctionClass(ArtWork work, int minimumValue) {
        this.work = work;
        this.minimumValue = minimumValue;
        this.bids = new DoubleList<>();
        this.highestBid = null;
    }

    @Override
    public void addBid(Bid bid) {

        if (highestBid == null)
            highestBid = bid;

        else if (highestBid.bidValue() < bid.bidValue())
            highestBid = bid;

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
