package ArtAuctions;

import dataStructures.Iterator;

/**
 * Artist entity, a user subtype that can sell artworks in auction
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public interface Artist extends ArtistReadOnly {
	
	/**
	 * Adds a new art work created by the artist
	 * @param work - new art work created
	 */
	void addNewArtWork(ArtWork work);

	/**
     * Check if this Artist has no works 
     * @return <code>true</code> if this Artist has works
     * <code>false</code> otherwise
     */
	boolean hasWorks();

	/**
	 * Return a the artWorks iterator
	 * @return artWork Iterator 
	 */
	Iterator<ArtWorkReadOnly> worksIterator();

	/**
     * Check if this Artist has works in auction.
     * @return <code>true</code> if this Artist has works in auction
     * <code>false</code> otherwise.
     */
	boolean hasWorksInAuction();

	
	/**
	 * Increments the number of ArtWorks the Artist has in auction by one.
	 */
	void workAddedToAuction();

	
	/**
	 * Decrements the number of ArtWorks the Artist has in auction by one.
	 */
	void workRemovedFromAuction();

}
