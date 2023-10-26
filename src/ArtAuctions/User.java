package ArtAuctions;

import java.io.Serializable;

public interface User extends Serializable {

	/**
	 * Returns the login of the user.
	 * @return user login.
	 */
	String login();
	
	/**
	 * Return the name of the user.
	 * @return user name.
	 */
	String name();
	
	/**
	 * Return the age of the user.
	 * @return user age 
	 */
	int age();
	
	/**
	 * Return the email of the user.
	 * @return user email.
	 */
	String email();

	/**
	 * Registers a bid made by the user 
	 * @param bid - the bid to be registered
	 */
	void addBid(Bid bid);

	/**
     * Check if this user has any bids
     * @return <code>true</code> if this user has bids
     * <code>false</code> otherwise
     */
	boolean hasBids();
	
}
