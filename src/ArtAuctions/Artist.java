package ArtAuctions;

import dataStructures.Iterator;

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

	
	boolean hasWorksInAuction();

	
	void workAddedToAuction();

}
