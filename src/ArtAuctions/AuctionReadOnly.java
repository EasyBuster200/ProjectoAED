package ArtAuctions;

public interface AuctionReadOnly {
    
    /**
	 * Returns the id of the auction.
	 * @return auction Id.
	 */
	String auctionId();

    /**
     * Return the minimum value of the given work
     * @param work with minimum value
     * @return the work minimum value
     */
    int getMinimumBidValue(ArtWork work);

    /**
	 * Return the artWork with the given correspondent id
	 * @param workId - id of the work to be returned
	 * @return artWork with the correspondent id
	 */
	ArtWork getWork(String workId);

}
