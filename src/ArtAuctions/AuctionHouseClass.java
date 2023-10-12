package ArtAuctions;

import Exceptions.auctionIdAlreadyRegisteredException;
import Exceptions.auctionIdNotRegisteredException;
import Exceptions.hasNoWorksException;
import Exceptions.loginAlredyRegisteredException;
import Exceptions.loginNotRegisteredException;
import Exceptions.noSoldWorkdsException;
import Exceptions.noWorksAuctionException;
import Exceptions.notAnArtistException;
import Exceptions.userUnderAgeException;
import Exceptions.valueUnderMinimumException;
import Exceptions.workHasNoBidsException;
import Exceptions.workIdAlreadyRegisteredException;
import Exceptions.workIdNotRegisteredException;
import Exceptions.workNotInAuctionException;
import dataStructures.Iterator;

public class AuctionHouseClass implements AuctionHouse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuctionHouseClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addUser(String login, String name, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addArtist(String login, String name, String artisticName, int age, String email)
			throws userUnderAgeException, loginAlredyRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(String login) throws loginNotRegisteredException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWork(String workId, String authorLogin, int year, String name)
			throws workIdAlreadyRegisteredException, loginNotRegisteredException, notAnArtistException {
		// TODO Auto-generated method stub
		
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

}
