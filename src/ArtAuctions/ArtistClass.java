package ArtAuctions;

import dataStructures.Iterator;

public class ArtistClass extends Collector implements Artist {
	
	protected String artisticName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		// TODO Auto-generated constructor stub
		super(login,name,age,email);
		this.artisticName = artisticName;
	}

	@Override
	public String artisticName() {
		// TODO Auto-generated method stub
		return this.artisticName ;
	}

	@Override
	public void addNewArtWork(ArtWork work) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addNewArtWork'");
	}

	@Override
	public boolean hasWorks() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'hasWorks'");
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'worksIterator'");
	}

}
