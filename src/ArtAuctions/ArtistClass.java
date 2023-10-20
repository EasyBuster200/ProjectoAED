package ArtAuctions;

import dataStructures.Iterator;

public class ArtistClass extends Collector implements Artist {

	private static final int DEFAULT_SIZE = 50;
	
	private String artisticName;
	private ArtWork[] artWorks;
	private int size;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		super(login, name, age, email);
		this.artisticName = artisticName;
		this.artWorks = new ArtWork[DEFAULT_SIZE];
		this.size = 0;
	}

	@Override
	public String artisticName() {
		return this.artisticName ;
	}

	@Override
	public void addNewArtWork(ArtWork work) {
		artWorks[size++] = work;
	}

	@Override
	public boolean hasWorks() {
		return size != 0;
	}

	@Override
	public Iterator<ArtWork> worksIterator() {
		// TODO Auto-generated method stub
		//Maybe make an array iterator, to iterate the artworks
		throw new UnsupportedOperationException("Unimplemented method 'worksIterator'");
	}

	//TODO: add isFull and grow methods for the array, or even change it from being an array

}
