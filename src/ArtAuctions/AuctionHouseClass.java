package ArtAuctions;

import Exceptions.*;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
import dataStructures.SepChainHashTable;
import dataStructures.BinarySearchTree;
import dataStructures.Dictionary;
import dataStructures.Entry;
import dataStructures.EntryIterator;

/**
 * Main class for the management the interpretation and the output of commands
 * @author Lipy Cardoso (63542) ik.cardoso@campus.fct.unl.pt
 * @author Duarte Coelho (65154) dcr.coelho@campus.fct.unl.pt
 */
public class AuctionHouseClass implements AuctionHouse {

	/**
	 * Serial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dictionary linking a User unique login to their object
	 * User login --> User
	 */
	private Dictionary<String, UserReadOnly> users;

	/**
	 * Dictionary linking a Work unique ID to its object 
	 * WorkID --> ArtWork
	 */
	private Dictionary<String, ArtWorkReadOnly> artWorks;

	/**
	 * Dictionary linking an Auction unique ID to its object 
	 * Auction ID --> Auction
	 */
	private Dictionary<String, AuctionReadOnly> auctions;

	/**
	 * OrderedDictionary linking an ArtWork to its ReadOnly object
	 * Order: descending order of highest sold price, and in cases where the prices are the same in alphabetical order of the name
	 * ArtWork --> ArtWorkReadOnly
	 */
	private OrderedDictionary<ArtWork, ArtWorkReadOnly> soldArtworks;

	/**
	 * Minimum required age for a user to be allowed to register himself/herself
	 */
	private static final int LEGAL_AGE = 18;

	/**
	 * AuctionHouseClass constructor that creates a new auctionHouse with empty list of users artWorks and auction  
	 */
	public AuctionHouseClass() {
		this.users = new SepChainHashTable<>(1000); 
		this.artWorks = new SepChainHashTable<>(1000);
		this.auctions = new SepChainHashTable<>(1000);
		this.soldArtworks = new BinarySearchTree<>();
	}

	@Override
	public void addUser(String login, String name, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		
			if (age < LEGAL_AGE)
				throw new userUnderAgeException();

			else if (users.find(login) != null)
				throw new loginAlredyRegisteredException();

			else
				users.insert(login, new Collector(login, name, age, email));

	}

	@Override
	public void addArtist(String login, String name, String artisticName, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		
			if (age < LEGAL_AGE)
				throw new userUnderAgeException();

			else if (users.find(login) != null)
				throw new loginAlredyRegisteredException();

			else
				users.insert(login, new ArtistClass(login, name, age, email, artisticName));
	}

	@Override
	public void removeUser(String login) 
		throws loginNotRegisteredException, userHasBidsException, artistHasWorksInAuction {

		User user = (User) users.find(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (user.hasBids())
			throw new userHasBidsException();

		else if (user instanceof Artist) {
			Artist artist = (Artist) user;

			if (artist.hasWorksInAuction())
				throw new artistHasWorksInAuction();

			if (artist.hasWorks())
				removeArtistPaintings(artist);

			users.remove(login);
		}

		else 
			users.remove(login);
		
	}

	@Override
	public void addWork(String workId, String authorLogin, int year, String name)
			throws workIdAlreadyRegisteredException, loginNotRegisteredException, notAnArtistException {
		
			if (artWorks.find(workId) != null)
				throw new workIdAlreadyRegisteredException();

			User user = (User) users.find(authorLogin);

			if(user == null)
				throw new loginNotRegisteredException();

			if (!(user instanceof Artist))
				throw new notAnArtistException();

			Artist artist = (Artist) user;
			ArtWork work = new ArtWorkClass(workId, name, year, artist);

			artWorks.insert(workId, work);
			artist.addNewArtWork(work);
			
	}

	@Override
	public UserReadOnly infoUser(String login) throws loginNotRegisteredException {
		User user = (User) users.find(login);

		if (user == null)
			throw new loginNotRegisteredException();

		return (UserReadOnly) user;
	}

	@Override
	public ArtistReadOnly infoArtist(String login) throws loginNotRegisteredException, notAnArtistException {
		User user = (User) users.find(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (!(user instanceof Artist))
			throw new notAnArtistException();

		return (ArtistReadOnly) user;
	}

	@Override
	public ArtWorkReadOnly infoWork(String workId) throws workIdNotRegisteredException {
		ArtWork work = (ArtWork) artWorks.find(workId);

		if(work == null)
			throw new workIdNotRegisteredException();

		return (ArtWorkReadOnly) work;
	}

	@Override
	public void createAuction(String auctionId) throws auctionIdAlreadyRegisteredException {
		if (auctions.find(auctionId) != null)
			throw new auctionIdAlreadyRegisteredException();
		
		auctions.insert(auctionId, new AuctionClass(auctionId));
	}

	@Override
	public void addWorkAuction(String auctionId, String workId, int minSellValue)
			throws auctionIdNotRegisteredException, workIdNotRegisteredException {
				
			Auction auction = (Auction) auctions.find(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork artWork = (ArtWork) artWorks.find(workId);

			if (artWork == null)
				throw new workIdNotRegisteredException();

			auction.addWorkAuction(artWork, minSellValue); 	
		
	}

	@Override
	public void bid(String auctionId, String workId, String login, int bidValue) 
		throws valueUnderMinimumException, auctionIdNotRegisteredException, loginNotRegisteredException, workNotInAuctionException {
			
		User user = (User) users.find(login);
	
		if (user == null)
			throw new loginNotRegisteredException();

		Auction auction = (Auction) auctions.find(auctionId);

		if (auction == null)
			throw new auctionIdNotRegisteredException();

		ArtWork work = auction.getWork(workId);

		if (work == null)
			throw new workNotInAuctionException();

		if (bidValue < auction.getMinimumBidValue(work))
			throw new valueUnderMinimumException();

		auction.addBid(new BidClass(bidValue, user), work);
		user.addBid();
		
	}

	@Override
	public Iterator<ArtWorkReadOnly> closeAuction(String auctionId) throws auctionIdNotRegisteredException {
		Auction auction = (Auction) auctions.find(auctionId);

		if(auction == null)
			throw new auctionIdNotRegisteredException();

		Iterator<ArtWorkReadOnly> it = auction.worksIterator();
		closeAuction(auction);
		auctions.remove(auctionId);
		return it;
	}

	@Override
	public Iterator<ArtWorkReadOnly> listAuctionWorks(String auctionId)
			throws auctionIdNotRegisteredException, noWorksAuctionException {
		
			Auction auction = (Auction) auctions.find(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			else if (auction.hasNoWorks())
				throw new noWorksAuctionException();

			return auction.worksIterator();
	}

	@Override
	public Iterator<ArtWorkReadOnly> listArtistWorks(String login)
			throws loginNotRegisteredException, notAnArtistException, hasNoWorksException {
		
			User user = (User) users.find(login);

			if (user == null)
				throw new loginNotRegisteredException();

			else if (!(user instanceof Artist))
				throw new notAnArtistException();
			
			Artist artist = (Artist) user;

			if (!artist.hasWorks())
				throw new hasNoWorksException();

			return artist.worksIterator();
	}

	@Override
	public Iterator<BidReadOnly> listBidsWork(String auctionId, String workId)
			throws auctionIdNotRegisteredException, workNotInAuctionException, workHasNoBidsException {
	
			Auction auction = (Auction) auctions.find(auctionId);

			if (auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork work = auction.getWork(workId); 

			if (work == null)
				throw new workNotInAuctionException();

			return auction.getWorkBids(work);
			
	}
	
	@Override
	public Iterator<ArtWorkReadOnly> listWorksByValue() throws noSoldWorkdsException {

		if (soldArtworks.isEmpty())
			throw new noSoldWorkdsException();

		return new EntryIterator<ArtWork, ArtWorkReadOnly >(soldArtworks.iterator());
	}

	/**
	 * Removes the paintings of an artist to be removed, from the program
	 * @param artist - the artist of the painting to be removed
	 */
	private void removeArtistPaintings(Artist artist) {
		Iterator<ArtWorkReadOnly> it = artist.worksIterator();

		while (it.hasNext()) {
			ArtWork current = (ArtWork) it.next();
			artWorks.remove(current.workId());

			if (soldArtworks.find(current) != null)
				soldArtworks.remove(current);
		}

	}

	/**
	 * Closes each individual auction of the given auction, while saving sold ArtWorks.
	 * @param auction auction to be closed 
	 */
	private void closeAuction(Auction auction) {
		Iterator<Entry<String, WorkAuction>> it = auction.getIndividualAuctions();

		while (it.hasNext()) {
			WorkAuction current = it.next().getValue();
			
			ArtWork currentWork = current.getWork();

			if (current.hasBids() && soldArtworks.find(currentWork) != null)
				soldArtworks.remove(currentWork);

			current.closeAuction();

			soldArtworks.insert(currentWork, currentWork);
		}
	}

}