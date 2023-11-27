package ArtAuctions;

import java.io.Serializable;
import Exceptions.noWorksAuctionException;
import Exceptions.workHasNoBidsException;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * Auction entity 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface Auction extends AuctionReadOnly, Serializable {
	
	/**
	 * Adds a new work to the auction
	 * @param artwork - the art work to be added.
	 */
	void addWorkAuction(ArtWork artwork, int minimumValue);
	
	/**
	 * Adds a bid to the given ark work.
	 * @param bid - the bid to be added.
	 */
	void addBid(Bid bid, ArtWork work);

	/**
     * Check if this Auction has no works 
     * @return <code>true</code> if this Auction is empty
     * <code>false</code> otherwise
     */
	boolean hasNoWorks();

    /**
     * Returns an iterator for the works
     * @return worksIterator
     * @throws noWorksAuctionException
     */
    Iterator<ArtWork> worksIterator();

	/**
	 * Returns a bid iterator of the given work
	 * @param work with the iterator
	 * @return the work bid iterator
	 * @throws workHasNoBidsException
	 */
	Iterator<Bid> getWorkBids(ArtWork work) throws workHasNoBidsException;

    /**
	 * Return the Iterator of the individual Auctions 
     * @return Iterator of Entry with the individual auctions
     */
    Iterator<Entry<String, WorkAuction>> getIndividualAuctions();

}
