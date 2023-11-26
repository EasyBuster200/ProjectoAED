package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * WorkAuction implementation
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class WorkAuctionClass implements WorkAuction {

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 1L;
	
    private ArtWork work;
    private int minimumValue;
    private List<Bid> bids;
    private Bid highestBid;
    
    /**
     * WorkAuction constructor that creates a  new workAuction for a work
     * @param work the work of the work auction
     * @param minimumValue the minimum bid value for the auction
     */
    public WorkAuctionClass(ArtWork work, int minimumValue) {
        this.work = work;
        this.minimumValue = minimumValue;
        this.bids = new DoubleList<>(); 
        this.highestBid = null;
        this.work.addedToAuction();
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
        if (!bids.isEmpty()) {
            if (this.work.highestSoldValue() < this.highestBid.bidValue())
                this.work.setHighestSoldValue(this.highestBid.bidValue());

            this.work.setLastSoldPrice(this.highestBid.bidValue());
            this.work.sold(highestBid.bidder());
        }
        else if (bids.isEmpty() && (this.work.getBuyer() != null))
            this.work.sold(null);

        Iterator<Bid> it = bids.iterator();

        while (it.hasNext()) {
            it.next().bidClosed();
        }
        
        this.work.removedFromAuction();
    }
}

