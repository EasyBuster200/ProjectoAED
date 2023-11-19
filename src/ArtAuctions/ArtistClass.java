package ArtAuctions;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import dataStructures.List;

/**
 * Artist implementation
 * @author Lipy Cardoso - 63542
 * @author Duarte Coelho - 65154
 */
public class ArtistClass extends Collector implements Artist {
	
	/**
	 * The Artist artistic name.
	 */
	private String artisticName;
	
	/**
	 * The artist art works
	 */
	private List<ArtWork> artWorks;
	
	/**
	 * The amount of work the Artist has in auction
	 */
	private int worksInAuction;

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Artist constructor that creates a new artist with the given dat
	 * @param login - artist login
	 * @param name - artist name
	 * @param age - artist age
	 * @param email - artist email
	 * @param artisticName - artist artistic name
	 */
	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		super(login, name, age, email);
		this.artisticName = artisticName;
		this.artWorks = new DoubleList<>(); //TODO: Maybe use a dictionary, could make removing faster.
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
