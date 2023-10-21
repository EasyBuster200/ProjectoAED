package ArtAuctions;

import java.io.Serializable;
import Exceptions.noWorksAuctionException;
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
	 * @return
	 */
	boolean hasNoWorks();

    /**
     * @return
     * @throws noWorksAuctionException
     */
    Iterator<ArtWork> worksIterator();

	/**
	 * @param workId
	 * @return
	 */
	ArtWork getWork(String workId);

	Iterator<Bid> getWorkBids(ArtWork work);

    int getMinimumBidValue(ArtWork work);

}
