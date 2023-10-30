package ArtAuctions;

import java.io.Serializable;
import Exceptions.noWorksAuctionException;
import Exceptions.workHasNoBidsException;
import dataStructures.Iterator;

public interface Auction extends Serializable {

	/**
	 * Returns the id of the auction.
	 * @return auction Id.
	 */
	String auctionId();
	
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
	 * Return the artWork with the given correspondent id
	 * @param workId - id of the work to be returned
	 * @return artWork with the correspondent id
	 */
	ArtWork getWork(String workId);

	/**
	 * Returns a bid iterator of the given work
	 * @param work with the iterator
	 * @return the work bid iterator
	 * @throws workHasNoBidsException
	 */
	Iterator<Bid> getWorkBids(ArtWork work) throws workHasNoBidsException;

    /**
     * Return the minimum value of the given work
     * @param work with minimum value
     * @return the work minimum value
     */
    int getMinimumBidValue(ArtWork work);

	/**
	 *Closes this auction 
	 */
	void closeAuction();

}
