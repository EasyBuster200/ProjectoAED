package ArtAuctions;

import java.io.Serializable;

/**
 * User entity
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface User extends Serializable, UserReadOnly {

	/**
     * Check if this user has any bids
     * @return <code>true</code> if this user has bids
     * <code>false</code> otherwise
     */
	boolean hasBids();

	/**
	 * Increments the number of bids a user has
	 */
	void addBid();

	/**
	 * Decreases the number of bids a user has
	 */
	void removeBid();
	
}
