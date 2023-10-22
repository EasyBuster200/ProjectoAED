package ArtAuctions;

import dataStructures.Iterator;

public interface Artist extends User {
	
	/**
	 * Returns the artistic name of the artist.
	 * @return
	 */
	String artisticName();
	
	/**
	 * Adds a new art work created by the artist
	 * @param work - new art work created
	 */
	void addNewArtWork(ArtWork work);

	/**
	 * @return
	 */
	boolean hasWorks();

	/**
	 * @return
	 */
	Iterator<ArtWork> worksIterator();

	boolean hasWorksInAuction();

	void workAddedToAuction();

}
