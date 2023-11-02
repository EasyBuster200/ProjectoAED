package ArtAuctions;

import Exceptions.*;
import dataStructures.Iterator;
import dataStructures.DoubleList;

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

	private DoubleList<User> users;
	private DoubleList<ArtWork> artWorks;
	private DoubleList<Auction> auctions;

	private static final int LEGAL_AGE = 18;

	/**
	 * AuctionHouseClass constructor that creates a new auctionHouse with empty list of users artWorks and auction  
	 */
	public AuctionHouseClass() {
		users = new DoubleList<>();
		artWorks = new DoubleList<>();
		auctions = new DoubleList<>();
	}

	@Override
	public void addUser(String login, String name, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		
			if (age < LEGAL_AGE)
				throw new userUnderAgeException();

			else if (getUser(login) != null)
				throw new loginAlredyRegisteredException();

			else
				users.addLast(new Collector(login, name, age, email));
	}

	@Override
	public void addArtist(String login, String name, String artisticName, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		
			if (age < LEGAL_AGE)
				throw new userUnderAgeException();

			else if (getUser(login) != null)
				throw new loginAlredyRegisteredException();

			else
				users.addLast(new ArtistClass(login, name, age, email, artisticName));
	}

	@Override
	public void removeUser(String login) throws loginNotRegisteredException, userHasBidsException, artistHasWorksInAuction {
		User user = getUser(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (user.hasBids())
			throw new userHasBidsException();

		else if (user instanceof Artist) {
			Artist artist = (Artist) user;

			if (artist.hasWorksInAuction())
				throw new artistHasWorksInAuction();

			removeArtistPaintings(artist);
			users.remove(artist);
		}

		else 
			users.remove(user);
		

	}

	@Override
	public void addWork(String workId, String authorLogin, int year, String name)
			throws workIdAlreadyRegisteredException, loginNotRegisteredException, notAnArtistException {
		
			if (getWork(workId) != null)
				throw new workIdAlreadyRegisteredException();

			User user = getUser(authorLogin);

			if(user == null)
				throw new loginNotRegisteredException();

			if (!(user instanceof Artist))
				throw new notAnArtistException();

			Artist artist = (Artist) user;
			ArtWork work = new ArtWorkClass(workId, name, year, artist);

			artWorks.addLast(work);
			artist.addNewArtWork(work);
			
	}

	@Override
	public Collector infoUser(String login) throws loginNotRegisteredException {
		User user = getUser(login);

		if (user == null)
			throw new loginNotRegisteredException();

		return (Collector) user;
	}

	@Override
	public Artist infoArtist(String login) throws loginNotRegisteredException, notAnArtistException {
		User user = getUser(login);

		if(user == null)
			throw new loginNotRegisteredException();

		else if (!(user instanceof Artist))
			throw new notAnArtistException();

		return (Artist) user;
	}

	@Override
	public ArtWork infoWork(String workId) throws workIdNotRegisteredException {
		ArtWork work = getWork(workId);

		if(work == null)
			throw new workIdNotRegisteredException();

		return work;
	}

	@Override
	public void createAuction(String auctionId) throws auctionIdAlreadyRegisteredException {
		if (getAuction(auctionId) != null)
			throw new auctionIdAlreadyRegisteredException();
		
		auctions.addLast(new AuctionClass(auctionId));
	}

	@Override
	public void addWorkAuction(String auctionId, String workId, int minSellValue)
			throws auctionIdNotRegisteredException, workIdNotRegisteredException {
				
			Auction auction = getAuction(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			ArtWork artWork = getWork(workId);

			if (artWork == null)
				throw new workIdNotRegisteredException();

			auction.addWorkAuction(artWork, minSellValue); 	
		
	}

	@Override
	public void bid(String auctionId, String workId, String login, int bidValue) 
		throws valueUnderMinimumException, auctionIdNotRegisteredException, loginNotRegisteredException, workNotInAuctionException {
			
		User user = getUser(login);
	
		if (user == null)
			throw new loginNotRegisteredException();

		Auction auction = getAuction(auctionId);

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
		Auction auction = getAuction(auctionId);

		if(auction == null)
			throw new auctionIdNotRegisteredException();

		Iterator<ArtWork> it = auction.worksIterator();
		auction.closeAuction();
		auctions.remove(auction);
		return it;
	}

	@Override
	public Iterator<ArtWork> listAuctionWorks(String auctionId)
			throws auctionIdNotRegisteredException, noWorksAuctionException {
		
			Auction auction = getAuction(auctionId);

			if(auction == null)
				throw new auctionIdNotRegisteredException();

			else if (auction.hasNoWorks())
				throw new noWorksAuctionException();

			return auction.worksIterator();
	}

	@Override
	public Iterator<ArtWork> listArtistWorks(String login)
			throws loginNotRegisteredException, notAnArtistException, hasNoWorksException {
		
			User user = getUser(login);

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
	
			Auction auction = getAuction(auctionId);

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
	 * Return the user of the given user 
	 * @param login - the login of the user
	 * @return the user, return NULL if the user does not exist
	 */
	private User getUser(String login) {
		Iterator<User> it = users.iterator();

		while(it.hasNext()) {
			User current = it.next();

			if(current.login().equalsIgnoreCase(login)) 
				return current;
		}

		return null;
	}

	
	/**
	 * Return the work of the given the workId
	 * @param workId id of the work to be searched
	 * @return the work id, NULL if the artWork does not exist.
	 */
	private ArtWork getWork(String workId) {
		Iterator<ArtWork> it = artWorks.iterator();

		while(it.hasNext()) {
			ArtWork current = it.next();

			if(current.workId().equalsIgnoreCase(workId))
				return current;
		}

		return null;
	}

	/**
	 * Return the auction of the give auctionId
	 * @param auctionId id of the auction to be searched
	 * @return the auction, return NULL if the auction does not exist
	 */
	private Auction getAuction(String auctionId) {
		Iterator<Auction> it = auctions.iterator();

		while(it.hasNext()) {
			Auction current = it.next();
			if (current.auctionId().equalsIgnoreCase(auctionId))
				return current;
		}

		return null;
	}

	/**
	 * Removes the painting of an artist that has been removed
	 * @param artist - the artist of the painting to be removed
	 */
	private void removeArtistPaintings(Artist artist) {
		Iterator<ArtWork> it = artist.worksIterator();

		while (it.hasNext())
			artWorks.remove(it.next());

	}

}
