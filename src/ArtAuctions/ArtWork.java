package ArtAuctions;

import java.io.Serializable;

/**
 * The ArtWork entity 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface ArtWork extends ArtWorkReadOnly, Serializable {
	
	/**
	 * Sets the new auction price of the art work.
	 * @param auctionPrice the lattest value the piece was bid for.
	 */
	void newAuctionPrice(int auctionPrice);

	/**
	 * Sets the highest value the work has been sold for in auction
	 * @pre newHighestValue > highestSoldValue() 
	 * @param newHighestSoldValue the new highest value the ArtWork has been sold for 
	 */
	void setHighestSoldValue(int newHighestSoldValue);

	/**
	 * Sets the last sold price of this artWork
	 * @param newLastSoldPrice
	 */
	void setLastSoldPrice(int newLastSoldPrice);

	/**
	 * Increments the number of ArtWorks the Author has in auction by one
	 */
	void addedToAuction();

	/**
	 * Decrements the number of ArtWorks the Author has in action by one
	 */
	void removedFromAuction();

	/**
	 * Defines the buyer, of the artWork, and changes the status of the work to sold
	 * @param buyer the buyer of the artWork
	 */
	void sold(User buyer);

}
