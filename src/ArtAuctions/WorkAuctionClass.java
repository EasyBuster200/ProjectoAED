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
	
    /**
     * Work in the individual Acution
     */
    private ArtWork work;

    /**
     * Minimum Bid value
     */
    private int minimumValue;
    
    /**
     * List of the Bid s placed on the ArtWork
     */
    private List<BidReadOnly> bids;

    /**
     * The current highest Bid
     */
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
    public Iterator<BidReadOnly> bidsIterator() {
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
                this.work.setHighestSoldValue(this.highestBid.bidValue()); //If the current highest bid has a value higher than the highest sold value of the work, then update the value

            this.work.setLastSoldPrice(this.highestBid.bidValue()); //Setting the last sold price, even if its not the new highest sold price
            this.work.sold(highestBid.bidder()); //Setting the new buyer of the ArtWork
        }
        else if (bids.isEmpty() && (this.work.getBuyer() != null))
            this.work.sold(null); //If the ArtWork had already been sold to a different User, and that user wasn't updated then set it to null

        Iterator<BidReadOnly> it = bids.iterator();

        while (it.hasNext()) {
            ((Bid) it.next()).bidClosed(); //Closing all the Bid s
        }
        
        this.work.removedFromAuction(); 
    }
}

