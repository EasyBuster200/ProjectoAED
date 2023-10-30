package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

public class ArtistClass extends Collector implements Artist {
	
	private String artisticName;
	private List<ArtWork> artWorks;
	private int worksInAuction;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		super(login, name, age, email);
		this.artisticName = artisticName;
		this.artWorks = new DoubleList<>();
		this.worksInAuction = 0;
	}

	@Override
	public String artisticName() {
		return this.artisticName ;
	}

	@Override
	public void addNewArtWork(ArtWork work) {
		this.artWorks.addLast(work);
	}

	@Override
	public boolean hasWorks() {
		return artWorks.size() != 0;
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		return this.artWorks.iterator();
	}

	@Override
	public boolean hasWorksInAuction() {
		return this.worksInAuction != 0;
	}

	@Override
	public void workAddedToAuction() {
		this.worksInAuction++;
	}

	@Override
	public void workRemovedFromAuction() {
		this.worksInAuction--;
	}


}
