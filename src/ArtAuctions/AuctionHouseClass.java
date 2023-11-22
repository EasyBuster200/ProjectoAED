package ArtAuctions;

import Exceptions.*;
import dataStructures.Iterator;
import dataStructures.OrderedDoubleList;
import dataStructures.SepChainHashTable;
import dataStructures.Dictionary;

/**
 * Main class for the management the interpretation and the output of commands
 * @author Lipy Cardoso - 63542
 * @author Duarte Coelho - 65154
 */
public class AuctionHouseClass implements AuctionHouse {

	/**
	 * SAerial Version UID of the Class
	 */
	private static final long serialVersionUID = 1L;

	private Dictionary<String, User> users; //User Login --> User 
	private Dictionary<String, ArtWork> artWorks; // WorkID --> Work //TODO: Ordered dictionary is ordered by the Key, we need something that can b e ordered by the last sold price
	private Dictionary<String, Auction> auctions; // AuctionId --> Auction

	private static final int LEGAL_AGE = 18;

	/**
	 * AuctionHouseClass constructor that creates a new auctionHouse with empty list of users artWorks and auction  
	 */
	public AuctionHouseClass() {
		users = new SepChainHashTable<>(); 
		artWorks = new OrderedDoubleList<>(); 
		auctions = new SepChainHashTable<>();
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
	public void removeUser(String login) throws loginNotRegisteredException, userHasBidsException, artistHasWorksInAuction {
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
	public Collector infoUser(String login) throws loginNotRegisteredException {
		User user = users.find(login);

		if (user == null)
			throw new loginNotRegisteredException();

		return (Collector) user;
	}

	@Override
	public Artist infoArtist(String login) throws loginNotRegisteredException, notAnArtistException {
		User user = users.find(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (!(user instanceof Artist))
			throw new notAnArtistException();

		return (Artist) user;
	}

	@Override
	public ArtWork infoWork(String workId) throws workIdNotRegisteredException {
		ArtWork work = artWorks.find(workId);

		if(work == null)
			throw new workIdNotRegisteredException();

		return work;
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
	public Iterator<ArtWork> closeAuction(String auctionId) throws auctionIdNotRegisteredException {
		Auction auction = auctions.find(auctionId);

		if(auction == null)
			throw new auctionIdNotRegisteredException();

		Iterator<ArtWork> it = auction.worksIterator();
		auction.closeAuction();
		auctions.remove(auctionId);
		return it;
	}

	@Override
	public Iterator<ArtWork> listAuctionWorks(String auctionId)
			throws auctionIdNotRegisteredException, noWorksAuctionException {
		
			Auction auction = auctions.find(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			else if (auction.hasNoWorks())
				throw new noWorksAuctionException();

			return auction.worksIterator();
	}

	@Override
	public Iterator<ArtWork> listArtistWorks(String login)
			throws loginNotRegisteredException, notAnArtistException, hasNoWorksException {
		
			User user = users.find(login);

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
	public Iterator<Bid> listBidsWork(String auctionId, String workId)
			throws auctionIdNotRegisteredException, workNotInAuctionException, workHasNoBidsException {
	
			Auction auction = auctions.find(auctionId);

			if (auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork work = auction.getWork(workId); 

			if (work == null)
				throw new workNotInAuctionException();

			return auction.getWorkBids(work);
			
	}
	
	@Override
	public Iterator<ArtWork> listWorksByValue() throws noSoldWorkdsException {
		return null;
	}

	/**
	 * Removes the painting of an artist that has been removed
	 * @param artist - the artist of the painting to be removed
	 */
	private void removeArtistPaintings(Artist artist) {
		Iterator<ArtWork> it = artist.worksIterator();

		while (it.hasNext())
			artWorks.remove(it.next().workId());

	}

}
