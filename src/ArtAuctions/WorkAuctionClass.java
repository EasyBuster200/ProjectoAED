package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class WorkAuctionClass implements WorkAuction {

    private static final long serialVersionUID = 1L;
	
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
           this.highestBid = bid;

        else if (highestBid.bidValue() < bid.bidValue())
            this.highestBid = bid;

       this.bids.addLast(bid);
    }

    @Override
    public Iterator<Bid> bidsIterator() {
        return this.bids.iterator();
    }

    @Override
    public boolean hasBids() {
        return !this.bids.isEmpty();
    }

    @Override
    public ArtWork getWork() {
        return this.work;
    }

    @Override
    public int minimumBidValue() {
        return this.minimumValue;
    }

    @Override
    public void closeAuction() {
        if (this.work.highestSoldValue() < this.highestBid.bidValue())
            this.work.setHighestSoldValue(this.highestBid.bidValue());

        this.work.setLastSoldPrice(this.highestBid.bidValue());
    }

}
