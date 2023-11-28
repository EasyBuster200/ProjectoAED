package ArtAuctions;

import Exceptions.*;
import dataStructures.Iterator;
import dataStructures.SepChainHashTable;
import dataStructures.TypeConversionIterator;
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

	private Dictionary<String, User> users; //User Login --> User 
	private Dictionary<String, ArtWork> artWorks; // WorkID --> Work
	private Dictionary<String, Auction> auctions; // AuctionId --> Auction
	private Dictionary<Integer, ArtWorkReadOnly> soldArtworks; // Last sold value --> ArtWork

	private static final int LEGAL_AGE = 18;

	/**
	 * AuctionHouseClass constructor that creates a new auctionHouse with empty list of users artWorks and auction  
	 */
	public AuctionHouseClass() {
		users = new SepChainHashTable<>(1000); 
		artWorks = new SepChainHashTable<>(1000);
		auctions = new SepChainHashTable<>(1000);
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

		User user = users.find(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (user.hasBids())
			throw new userHasBidsException();

		else if (user instanceof Artist) {
			Artist artist = (Artist) user;

			if (artist.hasWorksInAuction())
				throw new artistHasWorksInAuction();

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

			User user = users.find(authorLogin);

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
		User user = users.find(login);

		if (user == null)
			throw new loginNotRegisteredException();

		return (UserReadOnly) user;
	}

	@Override
	public ArtistReadOnly infoArtist(String login) throws loginNotRegisteredException, notAnArtistException {
		User user = users.find(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (!(user instanceof Artist))
			throw new notAnArtistException();

		return (ArtistReadOnly) user;
	}

	@Override
	public ArtWorkReadOnly infoWork(String workId) throws workIdNotRegisteredException {
		ArtWork work = artWorks.find(workId);

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
				
			Auction auction = auctions.find(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork artWork = artWorks.find(workId);

			if (artWork == null)
				throw new workIdNotRegisteredException();

			auction.addWorkAuction(artWork, minSellValue); 	
		
	}

	@Override
	public void bid(String auctionId, String workId, String login, int bidValue) 
		throws valueUnderMinimumException, auctionIdNotRegisteredException, loginNotRegisteredException, workNotInAuctionException {
			
		User user = users.find(login);
	
		if (user == null)
			throw new loginNotRegisteredException();

		Auction auction = auctions.find(auctionId);

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
		Auction auction = auctions.find(auctionId);

		if(auction == null)
			throw new auctionIdNotRegisteredException();

		Iterator<ArtWork> it = auction.worksIterator();
		closeAuction(auction);
		auctions.remove(auctionId);
		return new TypeConversionIterator<ArtWork, ArtWorkReadOnly> (it);
	}

	@Override
	public Iterator<ArtWorkReadOnly> listAuctionWorks(String auctionId)
			throws auctionIdNotRegisteredException, noWorksAuctionException {
		
			Auction auction = auctions.find(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			else if (auction.hasNoWorks())
				throw new noWorksAuctionException();

			return new TypeConversionIterator<ArtWork, ArtWorkReadOnly> (auction.worksIterator());
	}

	@Override
	public Iterator<ArtWorkReadOnly> listArtistWorks(String login)
			throws loginNotRegisteredException, notAnArtistException, hasNoWorksException {
		
			User user = users.find(login);

			if (user == null)
				throw new loginNotRegisteredException();

			else if (!(user instanceof Artist))
				throw new notAnArtistException();
			
			Artist artist = (Artist) user;

			if (!artist.hasWorks())
				throw new hasNoWorksException();

			return new TypeConversionIterator<ArtWork, ArtWorkReadOnly> (new EntryIterator<String, ArtWork> (artist.worksIterator()));
	}

	@Override
	public Iterator<BidReadOnly> listBidsWork(String auctionId, String workId)
			throws auctionIdNotRegisteredException, workNotInAuctionException, workHasNoBidsException {
	
			Auction auction = auctions.find(auctionId);

			if (auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork work = auction.getWork(workId); 

			if (work == null)
				throw new workNotInAuctionException();

			return new TypeConversionIterator<Bid, BidReadOnly> (auction.getWorkBids(work));
			
	}
	
	@Override
	public Iterator<ArtWorkReadOnly> listWorksByValue() throws noSoldWorkdsException {
		soldArtworks = new BinarySearchTree<>(); //TODO: Still not working, waiting on teachers response 
		//? Try to use a HashTable, I think they allow for equal keys
		Iterator<Entry<String,ArtWork>> it = artWorks.iterator();

		while (it.hasNext()) {
			ArtWork current = it.next().getValue();
			if (current.highestSoldValue() != 0)
				soldArtworks.insert(current.highestSoldValue(), current);
		}

		if (soldArtworks.isEmpty())
			throw new noSoldWorkdsException();

		return new EntryIterator<Integer, ArtWorkReadOnly>(soldArtworks.iterator());
	}

	/**
	 * Removes the paintings of an artist that has been removed
	 * @param artist - the artist of the painting to be removed
	 */
	private void removeArtistPaintings(Artist artist) {
		Iterator<Entry<String, ArtWork>> it = artist.worksIterator();

		while (it.hasNext())
			artWorks.remove(it.next().getValue().workId());

	}

	/**
	 * Closes each individual auction of the given auction, while storing saved sold ArtWorks.
	 * @param auction auction being closed 
	 */
	private void closeAuction(Auction auction) {
		Iterator<Entry<String, WorkAuction>> it = auction.getIndividualAuctions();

		while (it.hasNext()) {
			WorkAuction current = it.next().getValue();
			current.closeAuction();
		}
	}

}
