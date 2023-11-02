package ArtAuctions;

import dataStructures.Iterator;

/**
 * Artist entity, a user subtype that can sell artworks in auction
 * @author Lipy Cardoso - 63542
 * @author Duarte Coelho - 65154
 */
public interface Artist extends User {
	
	/**
	 * Returns the artistic name of the artist.
	 * @return the artistic name
	 */
	String artisticName();
	
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
	Iterator<ArtWork> worksIterator();

	/**
     * Check if this Artist has works in auction.
     * @return <code>true</code> if this Artist has works in auction
     * <code>false</code> otherwise.
     */
	boolean hasWorksInAuction();

	
	/**
	 * Adds one, to the number of works this.author has in auction
	 */
	void workAddedToAuction();

	
	/**
	 *Removes one to the number of works this.author has in auction 
	 */
	void workRemovedFromAuction();

}
