package ArtAuctions;

import dataStructures.BinarySearchTree;
import dataStructures.EntryIterator;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;

/**
 * Artist implementation
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class ArtistClass extends Collector implements Artist {
	
	/**
	 * The Artists artistic name.
	 */
	private String artisticName;
	
	/**
	 * The Artists art works
	 */
	private OrderedDictionary<String, ArtWorkReadOnly> artWorks;
	
	/**
	 * The amount of ArtWorks the Artist has in auction
	 */
	private int worksInAuction;

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Artist constructor that creates a new artist with the given data
	 * @param login - artist login
	 * @param name - artist name
	 * @param age - artist age
	 * @param email - artist email
	 * @param artisticName - artist artistic name
	 */
	public ArtistClass(String login, String name, int age,String email,String artisticName) {		
		super(login, name, age, email);
		this.artisticName = artisticName;
		this.artWorks = new BinarySearchTree<>();
		this.worksInAuction = 0;
	}

	@Override
	public String artisticName() {
		return this.artisticName ;
	}

	@Override
	public void addNewArtWork(ArtWork work) {
		this.artWorks.insert(work.name(), work);
	}

	@Override
	public boolean hasWorks() {
		return artWorks.size() != 0;
	}

	@Override
	public Iterator<ArtWorkReadOnly> worksIterator() {
		return new EntryIterator<String, ArtWorkReadOnly>(this.artWorks.iterator());
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
