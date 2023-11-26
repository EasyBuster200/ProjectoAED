package ArtAuctions;

import java.io.Serializable;

/**
 * The ArtWork entity 
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface ArtWork extends Serializable {
	
	/**
	 * Return the id of the art work.
	 * @return work Id.
	 */
	String workId();
	
	/**
	 * Return the name of the art work:
	 * @return work name.
	 */
	String name();

	/**
	 * Return the year of the creation of the art work.
	 * @return year created.
	 */
	int year();
	
	/**
	 * Returns the last auction price of the art work.
	 * @return last price the piece was auctioned for
	 */
	int lastAuctionPrice();
	
	/**
	 * Sets the new auction price of the art work.
	 * @param auctionPrice the lattest value the piece was bid for.
	 */
	void newAuctionPrice(int auctionPrice);
	
	/**
	 * Returns the login of the author of the art work.
	 * @return author login.
	 */
	String authorLogin();
	
	/**
	 * Returns the name of the author of the art work.
	 * @return author name
	 */
	String authorName();
	
	/**
	 * Returns a String with buyers login.
	 * @return buyer login.
	 */
	String buyerLogin();
	
	/**
	 * Return a string with the buyers name
	 * @return buyer name
	 */
	String buyerName();
	
	/**
	 * Return an integer with the highest value the piece has been sold for
	 * @return highest value the piece was sold for 
	 */
	int highestSoldValue();

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

	/**
	 * Returns the last User to buy the artWork at any aution
	 * @return the latest buyer of the artWork, or null if it hasn't been sold
	 */
	User getBuyer();
}
