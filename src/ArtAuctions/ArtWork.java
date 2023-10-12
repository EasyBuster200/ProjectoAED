package ArtAuctions;

import java.io.Serializable;

/**
 * @author lipyc
 *
 */
public interface ArtWork  extends Serializable {
	
	/**
	 * Return the id of the art work.
	 * @return workId.
	 */
	String workId();
	
	/**
	 * Return the name of the art work:
	 * @return name.
	 */
	String name();

	/**
	 * Return the year of the creation of the art work.
	 * @return year.
	 */
	int year();
	
	/**
	 * Returns the last auction price of the art work.
	 * @return lastAuctionPrice.
	 */
	int lastAuctionPrice();
	
	/**
	 * Sets the new auction price of the art work.
	 * @param auctionPrice
	 */
	void newAuctionPrice(int auctionPrice);
	
	/**
	 * Returns the login of the author of the art work.
	 * @return autorLogin.
	 */
	String authorLogin();
	
	/**
	 * Returns the name of the author of the art work.
	 * @return - author name
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
	 * @return buyerLogin.
	 */
	String buyerLogin();
	
	/**
	 * Return a string with the buyers name
	 * @return buyer name
	 */
	String buyerName();
	
}
