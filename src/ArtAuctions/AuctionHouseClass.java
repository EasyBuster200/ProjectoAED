package ArtAuctions;

import Exceptions.*;
import dataStructures.Iterator;
import dataStructures.DoubleList;

public class AuctionHouseClass implements AuctionHouse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DoubleList<User> users;
	private static DoubleList<ArtWork> artWorks;

	private static final int LEGAL_AGE = 18;

	public AuctionHouseClass() {
		users = new DoubleList<>();
		artWorks = new DoubleList<>();
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
	public void removeUser(String login) throws loginNotRegisteredException {
		User user = getUser(login);

		if(user == null)
			throw new loginNotRegisteredException();

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
			//TODO: unfinished
			
	}

	@Override
	public Collector infoUser(String login) throws loginNotRegisteredException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artist infoArtist(String login) throws loginNotRegisteredException, notAnArtistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArtWork infoWork(String workId) throws workIdNotRegisteredException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAuction(String auctionId) throws auctionIdAlreadyRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWorkAuction(String auctionId, String workId, int minSellValue)
			throws auctionIdNotRegisteredException, workIdNotRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bid(String auctionId, String workId, String login, int value) throws valueUnderMinimumException,
			auctionIdNotRegisteredException, loginNotRegisteredException, workIdNotRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<ArtWork> closeAuction(String auctionId) throws auctionIdNotRegisteredException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ArtWork> listAuctionWorks(String auctionId)
			throws auctionIdNotRegisteredException, noWorksAuctionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ArtWork> listArtistWorks(String login)
			throws loginNotRegisteredException, notAnArtistException, hasNoWorksException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Bid> listBidsWork(String auctionId, String workId)
			throws auctionIdNotRegisteredException, workNotInAuctionException, workHasNoBidsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ArtWork> listWorksByValue() throws noSoldWorkdsException {
		// TODO Auto-generated method stub
		return null;
	}

	private User getUser(String login) {
		Iterator<User> it = users.iterator();

		while(it.hasNext()) {
			User current = it.next();

			if(current.login().equalsIgnoreCase(login))
				return current;
		}

		return null;
	}

	private ArtWork getWork(String workId) {
		Iterator<ArtWork> it = artWorks.iterator();

		while(it.hasNext()) {
			ArtWork current = it.next();

			if(current.workId().equalsIgnoreCase(workId))
				return current;
		}

		return null;
	}

}
