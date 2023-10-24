package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class ArtistClass extends Collector implements Artist {
	
	private String artisticName;
	private List<ArtWork> artWorks;
	private int size;
	private boolean hasWorksInAuction;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		super(login, name, age, email);
		this.artisticName = artisticName;
		this.artWorks = new DoubleList<>();
		this.size = 0;
		this.hasWorksInAuction = false;
	}

	@Override
	public String artisticName() {
		return this.artisticName ;
	}

	@Override
	public void addNewArtWork(ArtWork work) {
		artWorks.addLast(work);
	}

	@Override
	public boolean hasWorks() {
		return size != 0;
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		return artWorks.iterator();
	}

	@Override
	public boolean hasWorksInAuction() {
		return hasWorksInAuction;
	}

	@Override
	public void workAddedToAuction() {
		hasWorksInAuction = true;
	}

}
