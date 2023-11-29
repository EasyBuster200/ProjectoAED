package ArtAuctions;

import java.io.Serializable;

/**
 * Interface representing a read-only view of ArtWork objects
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface ArtWorkReadOnly extends Serializable, Comparable<ArtWork> {
    
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
	 * Returns the last User to buy the artWork at any aution
	 * @return the latest buyer of the artWork, or null if it hasn't been sold
	 */
	User getBuyer();
}
