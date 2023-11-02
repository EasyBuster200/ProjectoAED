package ArtAuctions;

import java.io.Serializable;

/**
 * The ArtWork entity 
 * @author Lipy Cardoso - 63542
 * @author Duarte Coelho - 65154
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
	 * Returns a boolean value representing if the artwork has been sold or not before.
	 * @return <code>true</code> if the art work has been sold before 
	 * <code>false</code>.
	 */
	boolean beenSold();
	
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
	 * @param newHighestValue > highestSoldValue() 
	 */
	void setHighestSoldValue(int newHighestSoldValue);

	/**
	 * Sets the last sold price of this artWork
	 * @param newLastSoldPrice
	 */
	void setLastSoldPrice(int newLastSoldPrice);

	/**
	 *Adds one, to the number of works a user has in auction
	 */
	void addedToAuction();

	/**
	 *Removes one, to the number of works the artist has in auction
	 */
	void removedFromAuction();

	/**
	 * Defines the buyer, of the artWork, and changes the status of the work to sold
	 * @param buyer the buyer of the artWork
	 */
	void sold(User buyer);
}
