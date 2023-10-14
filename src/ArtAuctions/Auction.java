package ArtAuctions;

import java.io.Serializable;

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
	void addWorkAuction(ArtWork artwork);
	
	/**
	 * Adds a bid to the given ark work.
	 * @param bid - the bid to be added.
	 * @param artWork - the art work that will the bid.
	 */
	void addBid(Bid bid,ArtWork artWork);
	
	
	
	
}
